package com.iot.imc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.IService;
import com.iot.imc.domain.AnImcInspectionItem;
import com.iot.imc.dto.*;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * 巡检任务子项Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcInspectionItemService extends IService<AnImcInspectionItem>
{
    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    public ImcInspectionItemDto selectAnImcInspectionItemById(Long id);

    /**
     * 查询巡检任务子项列表
     * 
     * @param itemQueryDto 巡检任务子项
     * @return 巡检任务子项集合
     */
    public PageInfo selectAnImcInspectionItemList(ItemQueryDto itemQueryDto);

    /**
     * 新增巡检任务子项
     * 
     * @param imcAddInspectionItemDto 巡检任务子项
     * @return 结果
     */
    public ImcAddInspectionItemDto insertAnImcInspectionItem(ImcAddInspectionItemDto imcAddInspectionItemDto, LoginAuthDto user);

    /**
     * 修改巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    public int updateAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem,LoginAuthDto user);

    /**
     * 批量删除巡检任务子项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemByIds(String ids);

    /**
     * 删除巡检任务子项信息
     * 
     * @param id 巡检任务子项ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemById(Long id);

    /**
     * 修改巡检任务子项的状态
     * @param imcItemChangeStatusDto
     * @param user
     * @return
     */
    public ImcItemChangeStatusDto modifyImcItemStatus(ImcItemChangeStatusDto imcItemChangeStatusDto,LoginAuthDto user);

    /**
     * 提交巡检结果相关信息
     * @param itemResultDto
     * @param user
     * @return
     */
    public ImcItemChangeStatusDto putResultByItemId(ItemResultDto itemResultDto, LoginAuthDto user);

    /**
     * 根据维修工id查全部维修工已完成的任务
     * @param itemQueryDto
     * @return
     */
    public PageInfo getAllFinishedImcItemByMaintainerId(ItemQueryDto itemQueryDto);

    /**
     * 查询巡检任务子项的日志
     * @param itemId
     * @return
     */
    public List<ItemLogDto> getItemLogs(Long itemId);

    /**
     * 子项转化
     * @param imcInspectionItems
     * @return
     */
    public List<ImcInspectionItemDto> transform(List<AnImcInspectionItem> imcInspectionItems);
}
