package com.iot.imc.aop;

import com.iot.common.constant.Constants;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.redis.util.RedisUtils;
import com.iot.common.utils.ServletUtils;
import com.iot.imc.domain.AnImcInspectionItemLog;
import com.iot.imc.domain.AnImcInspectionReview;
import com.iot.imc.domain.AnImcInspectionTaskLog;
import com.iot.imc.dto.ImcAddInspectionItemDto;
import com.iot.imc.dto.ImcAddInspectionTaskDto;
import com.iot.imc.dto.ImcItemChangeStatusDto;
import com.iot.imc.dto.ImcTaskChangeStatusDto;
import com.iot.imc.enums.ItemStatusEnum;
import com.iot.imc.enums.TaskStatusEnum;
import com.iot.imc.service.IAnImcInspectionItemLogService;
import com.iot.imc.service.IAnImcInspectionItemService;
import com.iot.imc.service.IAnImcInspectionTaskLogService;
import com.iot.imc.service.IAnImcInspectionTaskService;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Created by rongshuai on 2020/5/30 21:12
 */
@Aspect
@Component
@Slf4j
public class IotImcLogAspect {

    @Autowired
    RedisUtils redis;

    @Autowired
    IAnImcInspectionTaskLogService anImcInspectionTaskLogService;

    @Autowired
    IAnImcInspectionItemLogService anImcInspectionItemLogService;

    @Autowired
    IAnImcInspectionTaskService anImcInspectionTaskService;

    @Autowired
    IAnImcInspectionItemService anImcInspectionItemService;

    private ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Pointcut("@annotation(com.iot.imc.aop.IotImcLog)")//定义切点
    public void logAnnotation() {

    }

    @Before("logAnnotation()")//在切点方法之前执行
    public void doBefore() {this.threadLocal.set(new Date(System.currentTimeMillis()));}

    @AfterReturning(pointcut = "logAnnotation()",returning = "returnValue")//在切点方法返回后执行
    public void doAfter(final JoinPoint joinPoint,final Object returnValue) {
        if(returnValue instanceof R) {
            R result = (R) returnValue;

            if (result.get("data")!=null && ("success".equals(((String)(result.get("msg")))))) {//如果操作结果非空，并且成功
                this.handleLog(joinPoint, result);//处理操作日志
            }
        }
    }

    private void handleLog(final JoinPoint joinPoint, final Object result) {
        //日志处理
        final Date startTime = this.threadLocal.get();
        final Date endTime = new Date(System.currentTimeMillis());
        HttpServletRequest request = ServletUtils.getRequest();
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        log.info("jointPoint is {},result is {},userAgent is {}",joinPoint,result,userAgent);
        try {
            final Object[] args = joinPoint.getArgs();
            final String targetName = joinPoint.getTarget().getClass().getName();//获取被代理对象的类名
            final Class targetClass = Class.forName(targetName);//获取被代理的对象
            final Method[] methods = targetClass.getMethods();//获取被代理对象的全部方法
            final String targetMethod = joinPoint.getSignature().getName();//获取连接点的方法名
            String movement="";//当前操作的描述
            final Long taskId;//当前任务的ID
            final Long itemId;//当前任务子项的ID（如果有的话）
            final Integer status;//当前日志对应的巡检状态
            final String os = userAgent.getOperatingSystem().getName();//获取客户端操作系统
            final String browser = userAgent.getBrowser().getName();//获取客户端浏览器
            final String ipAddress = request.getRemoteAddr();//获取请求ip
            final String token = request.getHeader("token");

            IotImcLog relog = giveController(joinPoint);
            if(relog == null) {
                return ;
            }

            //根据被代理的接口传入参数的不同，进行不同的日志记录
            //获取当前操作的描述
            for(int i=0;i<methods.length;i++){
                Method method = methods[i];
                //判断是否是这个方法
                if(method.getName().equals(targetMethod)){
                    Class[] clazzs = method.getParameterTypes();
                    //判断参数是否一样
                    if(clazzs.length == args.length){
                        movement = method.getAnnotation(ApiOperation.class).value();
                        System.out.println(movement);
                    }
                }
            }

            R r = (R) result;
            if(r.get("data").getClass().getName().equals(ImcAddInspectionTaskDto.class.getName())) {
                //如果当前操作是新增巡检任务
                AnImcInspectionTaskLog taskLog;
                ImcAddInspectionTaskDto taskDto = (ImcAddInspectionTaskDto) r.get("data");
                taskId = taskDto.getId();
                status = taskDto.getStatus();
                LoginAuthDto user = redis.get(Constants.ACCESS_TOKEN + token,LoginAuthDto.class);
                taskLog = createTaskLog(taskId,status,startTime,endTime,movement,os,browser,ipAddress);
                if(anImcInspectionTaskLogService.insertAnImcInspectionTaskLog(
                    taskLog,user
                )>0){
                    log.info("巡检任务日志创建成功：{}",taskLog);
                }
                List<ImcAddInspectionItemDto> itemDtoList = taskDto.getImcAddInspectionItemDtoList();
                if(null != itemDtoList && itemDtoList.size() > 0){
                    //如果同时还创建了任务子项
                    itemDtoList.forEach(item->{
                        AnImcInspectionItemLog itemLog = createItemLog(
                                item.getId(),taskId,item.getStatus(),startTime,endTime,"新建保存巡检任务子项",os,browser,ipAddress
                        );
                        if(anImcInspectionItemLogService.insertAnImcInspectionItemLog(itemLog,user)>0){
                            log.info("巡检任务子项日志创建成功：{}",itemLog);
                        }
                    });
                }
            }else if(r.get("data").getClass().getName().equals(ImcAddInspectionItemDto.class.getName())){
                //如果当前日志是巡检任务子项的
                ImcAddInspectionItemDto itemDto = new ImcAddInspectionItemDto();
                taskId = itemDto.getInspectionTaskId();
                itemId = itemDto.getId();
                status = itemDto.getStatus();
                LoginAuthDto user = redis.get(Constants.ACCESS_TOKEN + token,LoginAuthDto.class);
                AnImcInspectionItemLog itemLog = createItemLog(
                        itemId,taskId,status,startTime,endTime,movement,os,browser,ipAddress
                );
                if(anImcInspectionItemLogService.insertAnImcInspectionItemLog(itemLog,user)>0){
                    log.info("巡检任务子项日志创建成功：{}",itemLog);
                }
            } else if(r.get("data").getClass().getName().equals(AnImcInspectionReview.class.getName())){
                //如果日志是巡检任务评论的
                AnImcInspectionReview imcInspectionReview = (AnImcInspectionReview) r.get("data");
                taskId = imcInspectionReview.getInspectionTaskId();
                status = anImcInspectionTaskService.selectByKey(taskId).getStatus();
                LoginAuthDto user = redis.get(Constants.ACCESS_TOKEN + token,LoginAuthDto.class);
                AnImcInspectionTaskLog taskLog = createTaskLog(
                        taskId,status,startTime,endTime,movement,os,browser,ipAddress
                );
                if(anImcInspectionTaskLogService.insertAnImcInspectionTaskLog(
                        taskLog,user
                )>0){
                    log.info("巡检任务日志创建成功：{}",taskLog);
                }
            } else if(r.get("data").getClass().getName().equals(ImcTaskChangeStatusDto.class.getName())){
                //如果是更改巡检任务状态
                ImcTaskChangeStatusDto taskChangeStatusDto = (ImcTaskChangeStatusDto) r.get("data");
                taskId = taskChangeStatusDto.getTaskId();
                status = taskChangeStatusDto.getStatus();
                String statusMsg = TaskStatusEnum.getStatusMsg(status);
                AnImcInspectionTaskLog taskLog;
                if("服务商拒单".equals(movement)||"服务商接单".equals(movement)||"同意执行巡检任务".equals(movement)||"否决执行巡检任务".equals(movement)){
                    taskLog = createTaskLog(taskId,status,startTime,endTime,movement ,os,browser,ipAddress);
                } else{
                    taskLog = createTaskLog(taskId,status,startTime,endTime,statusMsg,os,browser,ipAddress);
                }
                LoginAuthDto user;
                try {
                    user = redis.get(Constants.ACCESS_TOKEN + token,LoginAuthDto.class);
                }catch (Exception e){
                    user = taskChangeStatusDto.getLoginAuthDto();
                }
                if(anImcInspectionTaskLogService.insertAnImcInspectionTaskLog(taskLog,user)>0){
                    log.info("巡检任务日志创建成功：{}",taskLog);
                }
            }else if(r.get("data").getClass().getName().equals(ImcItemChangeStatusDto.class.getName())){
                //如果当前日志是修改任务子项状态的
                ImcItemChangeStatusDto itemChangeStatusDto = (ImcItemChangeStatusDto) r.get("data");
                itemId = itemChangeStatusDto.getItemId();
                status = itemChangeStatusDto.getStatus();
                taskId = anImcInspectionItemService.selectByKey(itemId).getInspectionTaskId();
                String statusMsg = ItemStatusEnum.getStatusMsg(status);
                AnImcInspectionItemLog itemLog;
                if("工程师拒单".equals(movement)||"工程师接单".equals(movement)){
                    itemLog = createItemLog(itemId,taskId,status,startTime,endTime,movement,os,browser,ipAddress);
                }else{
                    itemLog = createItemLog(itemId,taskId,status,startTime,endTime,statusMsg,os,browser,ipAddress);
                }
                LoginAuthDto user;
                try {
                    user = redis.get(Constants.ACCESS_TOKEN + token,LoginAuthDto.class);
                }catch (Exception e){
                    user = itemChangeStatusDto.getLoginAuthDto();
                }
                if(anImcInspectionItemLogService.insertAnImcInspectionItemLog(
                        itemLog,user
                )>0){
                    log.info("巡检任务子项日志创建成功：{}",itemLog);
                }
                if(anImcInspectionTaskService.selectByKey(taskId).getStatus() == TaskStatusEnum.WAITING_FOR_CONFIRM.getStatusNum()){
                    AnImcInspectionTaskLog taskLog = createTaskLog(taskId,4,startTime,endTime,TaskStatusEnum.WAITING_FOR_CONFIRM.getStatusMsg(),os,browser,ipAddress);
                    if(anImcInspectionTaskLogService.insertAnImcInspectionTaskLog(taskLog,user)>0){
                        log.info("巡检任务日志创建成功：{}",taskLog);
                    }
                }
            }
        }catch (Exception e) {
            log.error("获取注解类出现异常={}", e.getMessage(), e);
        }
    }

    /**
     * 巡检任务日志
     * @param taskId
     * @param status
     * @param startTime
     * @param endTime
     * @param movement
     * @param os
     * @param browser
     * @param ipAddress
     * @return
     */
    private AnImcInspectionTaskLog createTaskLog(Long taskId,Integer status,Date startTime,Date endTime,String movement,String os,String browser,String ipAddress){//创建一条任务的日志
        AnImcInspectionTaskLog imcInspectionTaskLog = new AnImcInspectionTaskLog();
        imcInspectionTaskLog.setTaskId(taskId);
        imcInspectionTaskLog.setStatus(status);
        imcInspectionTaskLog.setCreateTime(startTime);
        imcInspectionTaskLog.setUpdateTime(endTime);
        imcInspectionTaskLog.setMovement(movement);
        imcInspectionTaskLog.setStatusTimestamp(endTime);
        imcInspectionTaskLog.setOs(os);
        imcInspectionTaskLog.setBrowser(browser);
        imcInspectionTaskLog.setIpAddress(ipAddress);
        return imcInspectionTaskLog;
    }

    /**
     * 巡检任务子项日志
     * @param itemId
     * @param taskId
     * @param status
     * @param startTime
     * @param endTime
     * @param movement
     * @param os
     * @param browser
     * @param ipAddress
     * @return
     */
    private AnImcInspectionItemLog createItemLog(Long itemId,Long taskId,Integer status,Date startTime,Date endTime,String movement,String os,String browser,String ipAddress){
        AnImcInspectionItemLog imcInspectionItemLog = new AnImcInspectionItemLog();
        imcInspectionItemLog.setItemId(itemId);
        imcInspectionItemLog.setTaskId(taskId);
        imcInspectionItemLog.setCreateTime(startTime);
        imcInspectionItemLog.setUpdateTime(endTime);
        imcInspectionItemLog.setMovement(movement);
        imcInspectionItemLog.setStatusTimestamp(endTime);
        imcInspectionItemLog.setStatus(status);
        imcInspectionItemLog.setOs(os);
        imcInspectionItemLog.setBrowser(browser);
        imcInspectionItemLog.setIpAddress(ipAddress);
        return imcInspectionItemLog;
    }

    /**
     * 是否存在注解, 如果存在就记录日志
     */
    private static IotImcLog giveController(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        String methodName = joinPoint.getSignature().getName();
        if (null != methods && 0 < methods.length) {
            for (Method met : methods) {
                IotImcLog relog = met.getAnnotation(IotImcLog.class);
                if (null != relog && methodName.equals(met.getName())) {
                    return relog;
                }
            }
        }

        return null;
    }
}
