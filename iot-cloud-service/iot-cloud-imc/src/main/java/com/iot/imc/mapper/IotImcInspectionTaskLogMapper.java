package com.iot.imc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.imc.domain.AnImcInspectionTaskLog;
import com.iot.imc.dto.TaskLogDto;

import java.util.List;

/**
 * 巡检任务日志Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IotImcInspectionTaskLogMapper extends BaseMapper<AnImcInspectionTaskLog>
{
    /**
     * 查询巡检任务日志
     * 
     * @param id 巡检任务日志ID
     * @return 巡检任务日志
     */
    public AnImcInspectionTaskLog selectAnImcInspectionTaskLogById(Long id);

    /**
     * 查询巡检任务日志列表
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 巡检任务日志集合
     */
    public List<AnImcInspectionTaskLog> selectAnImcInspectionTaskLogList(AnImcInspectionTaskLog anImcInspectionTaskLog);

    /**
     * 新增巡检任务日志
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 结果
     */
    public int insertAnImcInspectionTaskLog(AnImcInspectionTaskLog anImcInspectionTaskLog);

    /**
     * 修改巡检任务日志
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 结果
     */
    public int updateAnImcInspectionTaskLog(AnImcInspectionTaskLog anImcInspectionTaskLog);

    /**
     * 删除巡检任务日志
     * 
     * @param id 巡检任务日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskLogById(Long id);

    /**
     * 批量删除巡检任务日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskLogByIds(String[] ids);

    /**
     * 查询巡检任务日志
     * @param taskId
     * @return
     */
    public List<TaskLogDto> getTaskLogs(Long taskId);
}
