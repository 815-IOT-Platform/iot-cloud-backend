package com.iot.imc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.imc.domain.AnImcInspectionItem;

import java.util.List;

/**
 * 巡检任务子项Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IotImcInspectionItemMapper extends BaseMapper<AnImcInspectionItem>
{
    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    public AnImcInspectionItem selectAnImcInspectionItemById(Long id);

    /**
     * 查询巡检任务子项列表
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 巡检任务子项集合
     */
    public List<AnImcInspectionItem> selectAnImcInspectionItemList(AnImcInspectionItem anImcInspectionItem);

    /**
     * 新增巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    public int insertAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem);

    /**
     * 修改巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    public int updateAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem);

    /**
     * 删除巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemById(Long id);

    /**
     * 批量删除巡检任务子项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemByIds(String[] ids);

    /**
     * 修改巡检任务子项的状态
     * @param anImcInspectionItem
     * @return
     */
    public int modifyItemStatus(AnImcInspectionItem anImcInspectionItem);

    /**
     * 根据维修工id查全部维修工已完成的任务
     * @param maintainerId
     * @return
     */
    public List<AnImcInspectionItem> getAllFinishedImcItemByMaintainerId(Long maintainerId);
}
