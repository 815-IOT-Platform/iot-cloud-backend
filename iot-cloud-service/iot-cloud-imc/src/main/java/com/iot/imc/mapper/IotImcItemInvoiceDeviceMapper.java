package com.iot.imc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.imc.domain.AnImcItemInvoiceDevice;
import java.util.List;

/**
 * 设备实例Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IotImcItemInvoiceDeviceMapper extends BaseMapper<AnImcItemInvoiceDevice>
{
    /**
     * 查询设备实例
     * 
     * @param id 设备实例ID
     * @return 设备实例
     */
    public AnImcItemInvoiceDevice selectAnImcItemInvoiceDeviceById(Long id);

    /**
     * 查询设备实例列表
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 设备实例集合
     */
    public List<AnImcItemInvoiceDevice> selectAnImcItemInvoiceDeviceList(AnImcItemInvoiceDevice anImcItemInvoiceDevice);

    /**
     * 新增设备实例
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 结果
     */
    public int insertAnImcItemInvoiceDevice(AnImcItemInvoiceDevice anImcItemInvoiceDevice);

    /**
     * 修改设备实例
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 结果
     */
    public int updateAnImcItemInvoiceDevice(AnImcItemInvoiceDevice anImcItemInvoiceDevice);

    /**
     * 删除设备实例
     * 
     * @param id 设备实例ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDeviceById(Long id);

    /**
     * 批量删除设备实例
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDeviceByIds(String[] ids);
}
