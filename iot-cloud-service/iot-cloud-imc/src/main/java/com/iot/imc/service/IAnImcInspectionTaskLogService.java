package com.iot.imc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.imc.domain.AnImcInspectionTaskLog;
import java.util.List;

/**
 * 巡检任务日志Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcInspectionTaskLogService 
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
    public int insertAnImcInspectionTaskLog(AnImcInspectionTaskLog anImcInspectionTaskLog, LoginAuthDto user);

    /**
     * 修改巡检任务日志
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 结果
     */
    public int updateAnImcInspectionTaskLog(AnImcInspectionTaskLog anImcInspectionTaskLog);

    /**
     * 批量删除巡检任务日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskLogByIds(String ids);

    /**
     * 删除巡检任务日志信息
     * 
     * @param id 巡检任务日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskLogById(Long id);
}
