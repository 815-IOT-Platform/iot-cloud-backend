package com.iot.imc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.utils.DateUtils;
import com.iot.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.imc.mapper.IotImcInspectionItemLogMapper;
import com.iot.imc.domain.AnImcInspectionItemLog;
import com.iot.imc.service.IAnImcInspectionItemLogService;
import com.iot.common.core.text.Convert;

/**
 * 巡检任务子项日志Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionItemLogServiceImpl implements IAnImcInspectionItemLogService 
{
    @Autowired
    private IotImcInspectionItemLogMapper iotImcInspectionItemLogMapper;

    /**
     * 查询巡检任务子项日志
     * 
     * @param id 巡检任务子项日志ID
     * @return 巡检任务子项日志
     */
    @Override
    public AnImcInspectionItemLog selectAnImcInspectionItemLogById(Long id)
    {
        return iotImcInspectionItemLogMapper.selectAnImcInspectionItemLogById(id);
    }

    /**
     * 查询巡检任务子项日志列表
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 巡检任务子项日志
     */
    @Override
    public List<AnImcInspectionItemLog> selectAnImcInspectionItemLogList(AnImcInspectionItemLog anImcInspectionItemLog)
    {
        return iotImcInspectionItemLogMapper.selectAnImcInspectionItemLogList(anImcInspectionItemLog);
    }

    /**
     * 新增巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcInspectionItemLog,user);
        return iotImcInspectionItemLogMapper.insert(anImcInspectionItemLog);
    }

    /**
     * 修改巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog)
    {
        anImcInspectionItemLog.setUpdateTime(DateUtils.getNowDate());
        return iotImcInspectionItemLogMapper.updateAnImcInspectionItemLog(anImcInspectionItemLog);
    }

    /**
     * 删除巡检任务子项日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionItemLogByIds(String ids)
    {
        return iotImcInspectionItemLogMapper.deleteAnImcInspectionItemLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务子项日志信息
     * 
     * @param id 巡检任务子项日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemLogById(Long id)
    {
        return iotImcInspectionItemLogMapper.deleteAnImcInspectionItemLogById(id);
    }
}
