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
import com.iot.imc.domain.AnImcItemInvoiceDevice;
import com.iot.imc.service.IAnImcItemInvoiceDeviceService;

/**
 * 设备实例 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("device")
@Api("设备实例")
public class IotImcItemInvoiceDeviceController extends BaseController
{
	
	@Autowired
	private IAnImcItemInvoiceDeviceService anImcItemInvoiceDeviceService;
	
	/**
	 * 查询设备实例
	 */
	@ApiOperation(value = "查询设备实例")
	@GetMapping("get/{id}")
	public AnImcItemInvoiceDevice get(@PathVariable("id") Long id)
	{
		return anImcItemInvoiceDeviceService.selectAnImcItemInvoiceDeviceById(id);
		
	}
	
	/**
	 * 查询设备实例列表
	 */
	@ApiOperation(value = "查询设备实例列表")
	@GetMapping("list")
	public R list(AnImcItemInvoiceDevice anImcItemInvoiceDevice)
	{
		startPage();
        return result(anImcItemInvoiceDeviceService.selectAnImcItemInvoiceDeviceList(anImcItemInvoiceDevice));
	}
	
	
	/**
	 * 新增保存设备实例
	 */
	@ApiOperation(value = "新增保存设备实例")
	@PostMapping("save")
	public R addSave(@RequestBody AnImcItemInvoiceDevice anImcItemInvoiceDevice)
	{		
		return toAjax(anImcItemInvoiceDeviceService.insertAnImcItemInvoiceDevice(anImcItemInvoiceDevice,getLoginAuthDto()));
	}

	/**
	 * 修改保存设备实例
	 */
	@ApiOperation(value = "修改保存设备实例")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcItemInvoiceDevice anImcItemInvoiceDevice)
	{		
		return toAjax(anImcItemInvoiceDeviceService.updateAnImcItemInvoiceDevice(anImcItemInvoiceDevice));
	}
	
	/**
	 * 删除设备实例
	 */
	@ApiOperation(value = "删除设备实例")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcItemInvoiceDeviceService.deleteAnImcItemInvoiceDeviceByIds(ids));
	}
	
}
