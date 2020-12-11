package com.iot.system.controller;

import com.iot.system.domain.SysPost;
import com.iot.system.service.ISysPostService;
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

/**
 * 岗位信息 提供者
 * 
 * @author ananops
 * @date 2020-05-27
 */
@RestController
@RequestMapping("post")
@Api("岗位信息")
public class SysPostController extends BaseController
{
	
	@Autowired
	private ISysPostService sysPostService;
	
	/**
	 * 查询岗位信息表
	 */
	@ApiOperation(value = "查询岗位信息表")
	@GetMapping("get/{postId}")
	public SysPost get(@PathVariable("postId") Long postId)
	{
		return sysPostService.selectPostById(postId);
		
	}
	
	/**
	 * 查询岗位信息列表
	 */
	@ApiOperation(value = "查询岗位信息列表")
	@GetMapping("list")
	public R list(SysPost sysPost)
	{
		startPage();
        return result(sysPostService.selectPostList(sysPost));
	}
	
	
	/**
	 * 新增保存岗位信息
	 */
	@ApiOperation(value = "新增保存岗位信息")
	@PostMapping("save")
	public R addSave(@RequestBody SysPost sysPost)
	{		
		return toAjax(sysPostService.insertPost(sysPost));
	}

	/**
	 * 修改保存岗位信息
	 */
	@ApiOperation(value = "修改保存岗位信息")
	@PostMapping("update")
	public R editSave(@RequestBody SysPost sysPost)
	{		
		return toAjax(sysPostService.updatePost(sysPost));
	}
	
	/**
	 * 删除岗位信息表
	 */
	@ApiOperation(value = "删除岗位信息表")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(sysPostService.deletePostByIds(ids));
	}
	
}
