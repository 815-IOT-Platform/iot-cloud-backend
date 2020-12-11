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
import com.iot.imc.domain.AnImcItemInvoiceDesc;
import com.iot.imc.service.IAnImcItemInvoiceDescService;

/**
 * 巡检记录对应的设备 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("desc")
@Api("巡检记录对应的设备")
public class IotImcItemInvoiceDescController extends BaseController
{
	
	@Autowired
	private IAnImcItemInvoiceDescService anImcItemInvoiceDescService;
	
	/**
	 * 查询巡检记录对应的设备
	 */
	@ApiOperation(value = "查询巡检记录对应的设备")
	@GetMapping("get/{id}")
	public AnImcItemInvoiceDesc get(@PathVariable("id") Long id)
	{
		return anImcItemInvoiceDescService.selectAnImcItemInvoiceDescById(id);
		
	}
	
	/**
	 * 查询巡检记录对应的设备列表
	 */
	@ApiOperation(value = "查询巡检记录对应的设备列表")
	@GetMapping("list")
	public R list(AnImcItemInvoiceDesc anImcItemInvoiceDesc)
	{
		startPage();
        return result(anImcItemInvoiceDescService.selectAnImcItemInvoiceDescList(anImcItemInvoiceDesc));
	}
	
	
	/**
	 * 新增保存巡检记录对应的设备
	 */
	@ApiOperation(value = "新增保存巡检记录对应的设备")
	@PostMapping("save")
	public R addSave(@RequestBody AnImcItemInvoiceDesc anImcItemInvoiceDesc)
	{		
		return toAjax(anImcItemInvoiceDescService.insertAnImcItemInvoiceDesc(anImcItemInvoiceDesc,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检记录对应的设备
	 */
	@ApiOperation(value = "修改保存巡检记录对应的设备")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcItemInvoiceDesc anImcItemInvoiceDesc)
	{		
		return toAjax(anImcItemInvoiceDescService.updateAnImcItemInvoiceDesc(anImcItemInvoiceDesc));
	}
	
	/**
	 * 删除巡检记录对应的设备
	 */
	@ApiOperation(value = "删除巡检记录对应的设备")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcItemInvoiceDescService.deleteAnImcItemInvoiceDescByIds(ids));
	}
	
}
