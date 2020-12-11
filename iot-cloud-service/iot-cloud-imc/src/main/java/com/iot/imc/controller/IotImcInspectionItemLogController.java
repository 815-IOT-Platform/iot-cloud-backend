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
import com.iot.imc.domain.AnImcInspectionItemLog;
import com.iot.imc.service.IAnImcInspectionItemLogService;

/**
 * 巡检任务子项日志 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("itemLog")
@Api("巡检任务子项日志")
public class IotImcInspectionItemLogController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionItemLogService anImcInspectionItemLogService;
	
	/**
	 * 查询巡检任务子项日志
	 */
	@ApiOperation(value = "查询巡检任务子项日志")
	@GetMapping("get/{id}")
	public AnImcInspectionItemLog get(@PathVariable("id") Long id)
	{
		return anImcInspectionItemLogService.selectAnImcInspectionItemLogById(id);
		
	}
	
	/**
	 * 查询巡检任务子项日志列表
	 */
	@ApiOperation(value = "查询巡检任务子项日志列表")
	@GetMapping("list")
	public R list(AnImcInspectionItemLog anImcInspectionItemLog)
	{
		startPage();
        return result(anImcInspectionItemLogService.selectAnImcInspectionItemLogList(anImcInspectionItemLog));
	}
	
	
	/**
	 * 新增保存巡检任务子项日志
	 */
	@ApiOperation(value = "新增保存巡检任务子项日志")
	@PostMapping("save")
	public R addSave(@RequestBody AnImcInspectionItemLog anImcInspectionItemLog)
	{		
		return toAjax(anImcInspectionItemLogService.insertAnImcInspectionItemLog(anImcInspectionItemLog,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务子项日志
	 */
	@ApiOperation(value = "修改保存巡检任务子项日志")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcInspectionItemLog anImcInspectionItemLog)
	{		
		return toAjax(anImcInspectionItemLogService.updateAnImcInspectionItemLog(anImcInspectionItemLog));
	}
	
	/**
	 * 删除巡检任务子项日志
	 */
	@ApiOperation(value = "删除巡检任务子项日志")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcInspectionItemLogService.deleteAnImcInspectionItemLogByIds(ids));
	}
	
}
