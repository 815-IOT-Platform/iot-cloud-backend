package com.iot.imc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.imc.domain.AnImcInspectionTask;

import java.util.List;

/**
 * 巡检任务表Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IotImcInspectionTaskMapper extends BaseMapper<AnImcInspectionTask>
{
    /**
     * 查询巡检任务表
     * 
     * @param id 巡检任务表ID
     * @return 巡检任务表
     */
    public AnImcInspectionTask selectAnImcInspectionTaskById(Long id);

    /**
     * 查询巡检任务表列表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 巡检任务表集合
     */
    public List<AnImcInspectionTask> selectAnImcInspectionTaskList(AnImcInspectionTask anImcInspectionTask);

    /**
     * 新增巡检任务表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 结果
     */
    public int insertAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask);

    /**
     * 修改巡检任务表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 结果
     */
    public int updateAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask);

    /**
     * 删除巡检任务表
     * 
     * @param id 巡检任务表ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskById(Long id);

    /**
     * 批量删除巡检任务表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskByIds(String[] ids);

    /**
     * 修改巡检任务状态
     * @param anImcInspectionTask
     * @return
     */
    public int modifyTaskStatus(AnImcInspectionTask anImcInspectionTask);

    /**
     * 查询全部服务商下未分配工程师的巡检任务
     * @param facilitatorId
     * @return
     */
    public List<AnImcInspectionTask> getAllUnDistributedTask(Long facilitatorId);

    /**
     * 获取全部当前服务商已完成的巡检任务
     * @param facilitatorId
     * @return
     */
    public List<AnImcInspectionTask> getAllFinishedTaskByFacilitatorId(Long facilitatorId);
}
