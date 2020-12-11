package com.iot.imc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.utils.DateUtils;
import com.iot.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.imc.mapper.IotImcItemInvoiceDeviceMapper;
import com.iot.imc.domain.AnImcItemInvoiceDevice;
import com.iot.imc.service.IAnImcItemInvoiceDeviceService;
import com.iot.common.core.text.Convert;

/**
 * 设备实例Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcItemInvoiceDeviceServiceImpl implements IAnImcItemInvoiceDeviceService 
{
    @Autowired
    private IotImcItemInvoiceDeviceMapper iotImcItemInvoiceDeviceMapper;

    /**
     * 查询设备实例
     * 
     * @param id 设备实例ID
     * @return 设备实例
     */
    @Override
    public AnImcItemInvoiceDevice selectAnImcItemInvoiceDeviceById(Long id)
    {
        return iotImcItemInvoiceDeviceMapper.selectAnImcItemInvoiceDeviceById(id);
    }

    /**
     * 查询设备实例列表
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 设备实例
     */
    @Override
    public List<AnImcItemInvoiceDevice> selectAnImcItemInvoiceDeviceList(AnImcItemInvoiceDevice anImcItemInvoiceDevice)
    {
        return iotImcItemInvoiceDeviceMapper.selectAnImcItemInvoiceDeviceList(anImcItemInvoiceDevice);
    }

    /**
     * 新增设备实例
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 结果
     */
    @Override
    public int insertAnImcItemInvoiceDevice(AnImcItemInvoiceDevice anImcItemInvoiceDevice, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcItemInvoiceDevice,user);
        return iotImcItemInvoiceDeviceMapper.insert(anImcItemInvoiceDevice);
    }

    /**
     * 修改设备实例
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 结果
     */
    @Override
    public int updateAnImcItemInvoiceDevice(AnImcItemInvoiceDevice anImcItemInvoiceDevice)
    {
        anImcItemInvoiceDevice.setUpdateTime(DateUtils.getNowDate());
        return iotImcItemInvoiceDeviceMapper.updateAnImcItemInvoiceDevice(anImcItemInvoiceDevice);
    }

    /**
     * 删除设备实例对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcItemInvoiceDeviceByIds(String ids)
    {
        return iotImcItemInvoiceDeviceMapper.deleteAnImcItemInvoiceDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备实例信息
     * 
     * @param id 设备实例ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDeviceById(Long id)
    {
        return iotImcItemInvoiceDeviceMapper.deleteAnImcItemInvoiceDeviceById(id);
    }
}
