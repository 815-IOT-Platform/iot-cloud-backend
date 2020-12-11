package com.iot.imc.controller;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.exception.BusinessException;
import com.iot.imc.dto.*;
import com.iot.imc.enums.ItemStatusEnum;
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
import com.iot.imc.domain.AnImcInspectionItem;
import com.iot.imc.service.IAnImcInspectionItemService;

/**
 * 巡检任务子项 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("inspectionItem")
@Api("巡检任务子项")
public class IotImcInspectionItemController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionItemService anImcInspectionItemService;
	
	/**
	 * 查询巡检任务子项
	 */
	@ApiOperation(value = "查询巡检任务子项")
	@GetMapping("get/{id}")
	public ImcInspectionItemDto get(@PathVariable("id") Long id)
	{
		return anImcInspectionItemService.selectAnImcInspectionItemById(id);
		
	}
	
	/**
	 * 新增保存巡检任务子项
	 */
	@ApiOperation(value = "新增保存巡检任务子项")
	@PostMapping("save")
	public R addSave(@RequestBody ImcAddInspectionItemDto imcAddInspectionItemDto)
	{		
		return R.data(anImcInspectionItemService.insertAnImcInspectionItem(imcAddInspectionItemDto,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务子项
	 */
	@ApiOperation(value = "修改保存巡检任务子项")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcInspectionItem anImcInspectionItem)
	{		
		return toAjax(anImcInspectionItemService.updateAnImcInspectionItem(anImcInspectionItem,getLoginAuthDto()));
	}
	
	/**
	 * 删除巡检任务子项
	 */
	@ApiOperation(value = "删除巡检任务子项")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcInspectionItemService.deleteAnImcInspectionItemByIds(ids));
	}

	@ApiOperation(value = "修改巡检任务子项的状态")
	@PostMapping("modifyItemStatusByItemId")
	public R modifyItemStatusByItemId(@RequestBody ImcItemChangeStatusDto imcItemChangeStatusDto){
		LoginAuthDto user = getLoginAuthDto();
		imcItemChangeStatusDto.setLoginAuthDto(user);
		return R.data(anImcInspectionItemService.modifyImcItemStatus(imcItemChangeStatusDto,user));
	}

	@ApiOperation(value = "查询巡检任务子项")
	@PostMapping(value = "getItemList")
	public R getItemList(@RequestBody ItemQueryDto itemQueryDto){
		return result(anImcInspectionItemService.selectAnImcInspectionItemList(itemQueryDto));
	}

	@ApiOperation(value = "删除指定的巡检任务子项")
	@PostMapping(value = "deleteItemByItemId/{itemId}")
	public R deleteItemByItemId(@PathVariable Long itemId){
		return toAjax(anImcInspectionItemService.deleteAnImcInspectionItemById(itemId));
	}

	@ApiOperation(value = "提交巡检结果相关信息")
	@PostMapping(value = "putResultByItemId")
	public R putResultByItemId(@RequestBody ItemResultDto itemResultDto){
		return R.data(anImcInspectionItemService.putResultByItemId(itemResultDto,getLoginAuthDto()));
	}

	@ApiOperation(value = "工程师拒单")
	@PostMapping(value = "refuseItemByMaintainer/{itemId}")
	public R refuseItemByMaintainer(@PathVariable Long itemId){
		ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
		imcItemChangeStatusDto.setItemId(itemId);
		imcItemChangeStatusDto.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
		return R.data(anImcInspectionItemService.modifyImcItemStatus(imcItemChangeStatusDto,getLoginAuthDto()));
	}

	@ApiOperation(value = "工程师接单")
	@PostMapping(value = "acceptItemByMaintainer/{itemId}")
	public R acceptItemByMaintainer(@PathVariable Long itemId){
		ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
		imcItemChangeStatusDto.setItemId(itemId);
		imcItemChangeStatusDto.setStatus(ItemStatusEnum.IN_THE_INSPECTION.getStatusNum());
		return R.data(anImcInspectionItemService.modifyImcItemStatus(imcItemChangeStatusDto,getLoginAuthDto()));
	}

	@ApiOperation(value = "获取工程师下面的全部已接单但是未完成的巡检任务子项")
	@PostMapping(value = "getAllAcceptedItemListByMaintainer")
	public R getAllAcceptedItemByMaintainer(@RequestBody ItemQueryDto itemQueryDto){
		if(null != itemQueryDto.getMaintainerId()){
			itemQueryDto.setUserId(null);
			itemQueryDto.setTaskId(null);
			itemQueryDto.setStatus(ItemStatusEnum.IN_THE_INSPECTION.getStatusNum());
			return result(anImcInspectionItemService.selectAnImcInspectionItemList(itemQueryDto));
		}else throw new BusinessException("参数异常");
	}

	@ApiOperation(value = "根据维修工id查全部维修工已完成的任务")
	@PostMapping(value = "getAllFinishedImcItemByMaintainerId")
	public R getAllFinishedImcItemByMaintainerId(@RequestBody ItemQueryDto itemQueryDto){
		return result(anImcInspectionItemService.getAllFinishedImcItemByMaintainerId(itemQueryDto));
	}

	@ApiOperation(value = "根据巡检任务子项ID查询任务子项的日志")
	@PostMapping(value = "getItemLogs/{itemId}")
	public R getTaskLogs(@PathVariable Long itemId){
		return result(anImcInspectionItemService.getItemLogs(itemId));
	}

}
