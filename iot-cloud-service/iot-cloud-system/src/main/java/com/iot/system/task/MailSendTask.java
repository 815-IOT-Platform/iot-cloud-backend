package com.iot.system.task;

import com.iot.system.domain.MailConstants;
import com.iot.system.domain.MailSendLog;
import com.iot.system.domain.SysUser;
import com.iot.system.service.IMailSendLogService;
import com.iot.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MailSendTask {
    @Resource
    IMailSendLogService mailSendLogService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ISysUserService sysUserService;

    @Scheduled(cron = "0/10 * * * * ?") //10秒
    public void mailResendTask() {
        log.info("进入定时任务");
        List<MailSendLog> logs = mailSendLogService.getMailSendLogsByStatus();
        if (logs == null || logs.size() == 0) {
            return;
        }
        logs.forEach(mailSendLog -> {
            if (mailSendLog.getCount() >= 3) { //最大重试次数为三次
                mailSendLogService.updateMailSendLogStatus(mailSendLog.getMsgId(), 2);//直接设置该条消息发送失败
            } else {
                mailSendLogService.updateCount(mailSendLog.getMsgId(), new Date());
                SysUser sysUser = sysUserService.selectUserById(mailSendLog.getEmpId());
                //重试
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, sysUser, new CorrelationData(mailSendLog.getMsgId()));
            }
        });
    }
}
