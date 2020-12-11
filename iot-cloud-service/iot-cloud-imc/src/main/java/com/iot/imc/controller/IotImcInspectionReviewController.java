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
import com.iot.imc.domain.AnImcInspectionReview;
import com.iot.imc.service.IAnImcInspectionReviewService;

/**
 * 巡检任务评论 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("review")
@Api("巡检任务评论")
public class IotImcInspectionReviewController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionReviewService anImcInspectionReviewService;
	
	/**
	 * 查询巡检任务评论
	 */
	@ApiOperation(value = "查询巡检任务评论")
	@GetMapping("get/{id}")
	public AnImcInspectionReview get(@PathVariable("id") Long id)
	{
		return anImcInspectionReviewService.selectAnImcInspectionReviewById(id);
		
	}
	
	/**
	 * 查询巡检任务评论列表
	 */
	@ApiOperation(value = "查询巡检任务评论列表")
	@GetMapping("list")
	public R list(AnImcInspectionReview anImcInspectionReview)
	{
		startPage();
        return result(anImcInspectionReviewService.selectAnImcInspectionReviewList(anImcInspectionReview));
	}
	
	
	/**
	 * 新增保存巡检任务评论
	 */
	@ApiOperation(value = "新增保存巡检任务评论")
	@PostMapping("save")
	public R addSave(@RequestBody AnImcInspectionReview anImcInspectionReview)
	{		
		return toAjax(anImcInspectionReviewService.insertAnImcInspectionReview(anImcInspectionReview,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务评论
	 */
	@ApiOperation(value = "修改保存巡检任务评论")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcInspectionReview anImcInspectionReview)
	{		
		return toAjax(anImcInspectionReviewService.updateAnImcInspectionReview(anImcInspectionReview));
	}
	
	/**
	 * 删除巡检任务评论
	 */
	@ApiOperation(value = "删除巡检任务评论")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcInspectionReviewService.deleteAnImcInspectionReviewByIds(ids));
	}
	
}
