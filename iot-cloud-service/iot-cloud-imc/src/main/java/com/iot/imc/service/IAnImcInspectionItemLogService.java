package com.iot.imc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.imc.domain.AnImcInspectionItemLog;

import java.util.List;

/**
 * 巡检任务子项日志Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcInspectionItemLogService 
{
    /**
     * 查询巡检任务子项日志
     * 
     * @param id 巡检任务子项日志ID
     * @return 巡检任务子项日志
     */
    public AnImcInspectionItemLog selectAnImcInspectionItemLogById(Long id);

    /**
     * 查询巡检任务子项日志列表
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 巡检任务子项日志集合
     */
    public List<AnImcInspectionItemLog> selectAnImcInspectionItemLogList(AnImcInspectionItemLog anImcInspectionItemLog);

    /**
     * 新增巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    public int insertAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog, LoginAuthDto user);

    /**
     * 修改巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    public int updateAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog);

    /**
     * 批量删除巡检任务子项日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemLogByIds(String ids);

    /**
     * 删除巡检任务子项日志信息
     * 
     * @param id 巡检任务子项日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemLogById(Long id);
}
