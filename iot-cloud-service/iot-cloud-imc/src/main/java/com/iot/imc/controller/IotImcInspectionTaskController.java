package com.iot.imc.controller;

import com.iot.imc.aop.IotImcLog;
import com.iot.imc.dto.ImcAddInspectionTaskDto;
import com.iot.imc.dto.ImcInspectionTaskDto;
import com.iot.imc.dto.ImcTaskChangeStatusDto;
import com.iot.imc.dto.TaskQueryDto;
import com.iot.imc.enums.RoleEnum;
import com.iot.imc.enums.TaskStatusEnum;
import com.iot.system.dto.FileUploadDto;
import com.iot.system.feign.RemoteOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.iot.common.core.domain.R;
import com.iot.common.core.controller.BaseController;
import com.iot.imc.domain.AnImcInspectionTask;
import com.iot.imc.service.IAnImcInspectionTaskService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 巡检任务表 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("inspectionTask")
@Api("巡检任务表")
public class IotImcInspectionTaskController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionTaskService anImcInspectionTaskService;

	@Autowired
	private RemoteOssService remoteOssService;
	
	/**
	 * 查询巡检任务
	 */
	@ApiOperation(value = "根据任务的ID，查询巡检任务")
	@GetMapping("getTaskByTaskId/{id}")
	public ImcInspectionTaskDto get(@PathVariable("id") Long id)
	{
		return anImcInspectionTaskService.selectAnImcInspectionTaskById(id);
		
	}
	
	/**
	 * 查询巡检任务表列表
	 */
	@ApiOperation(value = "查询巡检任务表列表")
	@GetMapping("list")
	public R list(AnImcInspectionTask anImcInspectionTask)
	{
		startPage();
        return result(anImcInspectionTaskService.selectAnImcInspectionTaskList(anImcInspectionTask));
	}
	
	
	/**
	 * 新增保存巡检任务表
	 */
	@ApiOperation(value = "新增保存巡检任务")
	@PostMapping("save")
	@IotImcLog
	public R addSave(@RequestBody ImcAddInspectionTaskDto imcAddInspectionTaskDto)
	{		
		return R.data(anImcInspectionTaskService.insertAnImcInspectionTask(imcAddInspectionTaskDto,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务表
	 */
	@ApiOperation(value = "修改保存巡检任务表")
	@PostMapping("update")
	@IotImcLog
	public R editSave(@RequestBody AnImcInspectionTask anImcInspectionTask)
	{		
		return toAjax(anImcInspectionTaskService.updateAnImcInspectionTask(anImcInspectionTask));
	}
	
	/**
	 * 删除巡检任务
	 */
	@ApiOperation(value = "删除巡检任务")
	@PostMapping("deleteTaskByTaskId/{taskId}")
	@IotImcLog
	public R remove(@PathVariable Long taskId)
	{		
		return toAjax(anImcInspectionTaskService.deleteAnImcInspectionTaskById(taskId));
	}


	/**
	 * 更改巡检任务的状态
	 */
	@ApiOperation(value = "更改巡检任务的状态")
	@PostMapping("modifyTaskStatusByTaskId")
	@IotImcLog
	public ImcTaskChangeStatusDto modifyTaskStatusByTaskId(@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
		anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,getLoginAuthDto());
		return imcTaskChangeStatusDto;
	}

	@ApiOperation(value = "根据项目查询对应的所有巡检任务")
	@PostMapping("getTaskListByProjectId")
	public R getTaskListByProjectId(@RequestBody TaskQueryDto taskQueryDto){
		return result(anImcInspectionTaskService.getTaskByProjectId(taskQueryDto));
	}

	@ApiOperation(value = "根据用户（1：甲方负责人，2：服务商）的id查询对应的巡检任务，可以指定状态（可返回总数total）")
	@PostMapping("getTaskList")
	public R getTaskListByUserId(@RequestBody TaskQueryDto taskQueryDto){
		return result(anImcInspectionTaskService.getTaskByUserId(taskQueryDto));
	}

	@ApiOperation(value = "服务商拒单")
    @PostMapping("refuseTaskByFacilitator/{taskId}")
	@IotImcLog
    public R refuseTaskByFacilitator(@PathVariable Long taskId){
	    ImcTaskChangeStatusDto imcTaskChangeStatusDto = new ImcTaskChangeStatusDto();
	    imcTaskChangeStatusDto.setStatus(TaskStatusEnum.WAITING_FOR_FACILITATOR.getStatusNum());
	    imcTaskChangeStatusDto.setTaskId(taskId);
	    return R.data(anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,getLoginAuthDto()));
    }

    @ApiOperation(value = "服务商接单")
	@PostMapping("acceptTaskByFacilitator/{taskId}")
	@IotImcLog
	public R acceptTaskByFacilitator(@PathVariable Long taskId){
		ImcTaskChangeStatusDto imcTaskChangeStatusDto = new ImcTaskChangeStatusDto();
		imcTaskChangeStatusDto.setTaskId(taskId);
		imcTaskChangeStatusDto.setStatus(TaskStatusEnum.EXECUTING.getStatusNum());
		return R.data(anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,getLoginAuthDto()));
	}

	@ApiOperation(value = "查询当前甲方负责人下面的全部未授权的任务（可返回总数total）")
    @PostMapping(value = "getAllUnauthorizedTaskList")
    public R getAllUnauthorizedTaskList(@RequestBody TaskQueryDto taskQueryDto){
        taskQueryDto.setStatus(TaskStatusEnum.WAITING_FOR_PRINCIPAL.getStatusNum());
        taskQueryDto.setRole(RoleEnum.PRINCIPAL.getStatusNum());
        return result(anImcInspectionTaskService.getTaskByUserId(taskQueryDto));
    }

    @ApiOperation(value = "查询当前甲方负责人下面的全部被否决的任务（可返回总数total")
    @PostMapping(value = "getAllDeniedTaskList")
    public R getAllDeniedTaskList(@RequestBody TaskQueryDto taskQueryDto){
	    taskQueryDto.setRole(RoleEnum.PRINCIPAL.getStatusNum());
	    taskQueryDto.setStatus(TaskStatusEnum.NO_SUCH_STATUS.getStatusNum());
	    return result(anImcInspectionTaskService.getTaskByUserId(taskQueryDto));
    }

    @ApiOperation(value = "同意执行巡检任务")
    @PostMapping(value = "acceptImcTaskByPrincipal")
	@IotImcLog
    public R acceptImcTaskByPrincipal(@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
        imcTaskChangeStatusDto.setStatus(TaskStatusEnum.WAITING_FOR_FACILITATOR.getStatusNum());
        return R.data(anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,getLoginAuthDto()));
    }

    @ApiOperation(value = "否决巡检任务")
    @PostMapping(value = "denyImcTaskByPrincipal")
	@IotImcLog
    public R denyImcTaskByPrincipal(@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
        imcTaskChangeStatusDto.setStatus(TaskStatusEnum.NO_SUCH_STATUS.getStatusNum());
        return R.data(anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,getLoginAuthDto()));
    }

    @ApiOperation(value = "根据用户id和用户角色获取全部的巡检任务数目(1->甲方负责人   2->服务商)")
    @PostMapping(value = "getImcTaskNumberByUserIdAndRole")
    public R getImcTaskNumberByUserIdAndRole(@RequestBody TaskQueryDto taskQueryDto){
	    return R.data(anImcInspectionTaskService.getImcTaskNumberByUserIdAndRole(taskQueryDto));
    }

    @ApiOperation(value = "获取全部当前服务商未分配工程师的巡检任务")
    @PostMapping(value = "getAllUnDistributedTask")
    public R getAllUnDistributedTask(@RequestBody TaskQueryDto taskQueryDto){
        return result(anImcInspectionTaskService.getAllUnDistributedTask(taskQueryDto));
    }

    @ApiOperation(value = "获取全部当前服务商未接单的巡检任务")
	@PostMapping(value = "getAllUnConfirmedTask")
	public R getAllUnConfirmedTask(@RequestBody TaskQueryDto taskQueryDto){
		taskQueryDto.setRole(RoleEnum.FACILITATOR.getStatusNum());
		taskQueryDto.setStatus(TaskStatusEnum.WAITING_FOR_ACCEPT.getStatusNum());
		return result(anImcInspectionTaskService.getTaskByUserId(taskQueryDto));
	}

	@ApiOperation(value = "获取全部当前服务商已完成的巡检任务")
	@PostMapping(value = "getAllFinishedTaskByFacilitatorId")
	public R getAllFinishedTaskByFacilitatorId(@RequestBody TaskQueryDto taskQueryDto){
		return result(anImcInspectionTaskService.getAllFinishedTaskByFacilitatorId(taskQueryDto));
	}

	@ApiOperation(value = "获取全部当前服务商的巡检任务")
	@PostMapping(value = "getAllTaskByFacilitatorId")
	public R getAllTaskByFacilitatorId(@RequestBody TaskQueryDto taskQueryDto){
		taskQueryDto.setRole(RoleEnum.FACILITATOR.getStatusNum());
		return result(anImcInspectionTaskService.getTaskByUserId(taskQueryDto));
	}

	@ApiOperation(value = "获取甲方负责人下的全部巡检任务")
	@PostMapping(value = "getAllTaskByPrincipalId")
	public R getAllTaskByPrincipalId(@RequestBody TaskQueryDto taskQueryDto){
		taskQueryDto.setRole(RoleEnum.PRINCIPAL.getStatusNum());
		return result(anImcInspectionTaskService.getTaskByUserId(taskQueryDto));
	}

	@ApiOperation(value = "根据巡检任务ID查询任务的日志")
	@PostMapping(value = "getTaskLogs/{taskId}")
	public R getTaskLogs(@PathVariable Long taskId){
		return result(anImcInspectionTaskService.getTaskLogs(taskId));
	}

	@ApiOperation(value = "文件上传测试")
	@PostMapping(value = "upload")
	public R fileUpload(@RequestParam("file") MultipartFile multipartFile) {
		try {
			FileUploadDto uploadDto = new FileUploadDto();
			uploadDto.setData(multipartFile.getBytes());
			uploadDto.setFileName(multipartFile.getOriginalFilename());
			uploadDto.setUser(getLoginAuthDto());
			return R.data(remoteOssService.editSave(uploadDto));
		} catch (Exception e) {
			return R.error("文件上传异常");
		}
	}

	@ApiOperation(value = "获取任务报表")
	@PostMapping(value = "getTaskReport/{taskId}")
	public R getTaskReport(@PathVariable("taskId")Long taskId) {
		return R.data(anImcInspectionTaskService.getTaskReport(taskId,getLoginAuthDto()));
	}


}
