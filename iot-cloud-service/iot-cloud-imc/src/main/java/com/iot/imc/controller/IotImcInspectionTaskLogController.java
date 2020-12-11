package com.iot.imc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.iot.common.core.domain.R;
import com.iot.common.core.controller.BaseController;
import com.iot.imc.domain.AnImcInspectionTaskLog;
import com.iot.imc.service.IAnImcInspectionTaskLogService;

/**
 * 巡检任务日志 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("taskLog")
@Api("巡检任务日志")
public class IotImcInspectionTaskLogController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionTaskLogService anImcInspectionTaskLogService;
	
	/**
	 * 查询巡检任务日志
	 */
	@ApiOperation(value = "查询巡检任务日志")
	@GetMapping("get/{id}")
	public AnImcInspectionTaskLog get(@PathVariable("id") Long id)
	{
		return anImcInspectionTaskLogService.selectAnImcInspectionTaskLogById(id);
		
	}
	
	/**
	 * 查询巡检任务日志列表
	 */
	@ApiOperation(value = "查询巡检任务日志列表")
	@GetMapping("list")
	public R list(AnImcInspectionTaskLog anImcInspectionTaskLog)
	{
		startPage();
        return result(anImcInspectionTaskLogService.selectAnImcInspectionTaskLogList(anImcInspectionTaskLog));
	}
	
	
	/**
	 * 新增保存巡检任务日志
	 */
	@ApiOperation(value = "新增保存巡检任务日志")
	@PostMapping("save")
	public R addSave(@RequestBody AnImcInspectionTaskLog anImcInspectionTaskLog)
	{		
		return toAjax(anImcInspectionTaskLogService.insertAnImcInspectionTaskLog(anImcInspectionTaskLog,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务日志
	 */
	@ApiOperation(value = "修改保存巡检任务日志")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcInspectionTaskLog anImcInspectionTaskLog)
	{		
		return toAjax(anImcInspectionTaskLogService.updateAnImcInspectionTaskLog(anImcInspectionTaskLog));
	}
	
	/**
	 * 删除巡检任务日志
	 */
	@ApiOperation(value = "删除巡检任务日志")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcInspectionTaskLogService.deleteAnImcInspectionTaskLogByIds(ids));
	}
	
}
