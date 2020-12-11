package com.iot.imc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.imc.domain.AnImcItemInvoiceDesc;
import java.util.List;

/**
 * 巡检记录对应的设备Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IotImcItemInvoiceDescMapper extends BaseMapper<AnImcItemInvoiceDesc>
{
    /**
     * 查询巡检记录对应的设备
     * 
     * @param id 巡检记录对应的设备ID
     * @return 巡检记录对应的设备
     */
    public AnImcItemInvoiceDesc selectAnImcItemInvoiceDescById(Long id);

    /**
     * 查询巡检记录对应的设备列表
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 巡检记录对应的设备集合
     */
    public List<AnImcItemInvoiceDesc> selectAnImcItemInvoiceDescList(AnImcItemInvoiceDesc anImcItemInvoiceDesc);

    /**
     * 新增巡检记录对应的设备
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 结果
     */
    public int insertAnImcItemInvoiceDesc(AnImcItemInvoiceDesc anImcItemInvoiceDesc);

    /**
     * 修改巡检记录对应的设备
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 结果
     */
    public int updateAnImcItemInvoiceDesc(AnImcItemInvoiceDesc anImcItemInvoiceDesc);

    /**
     * 删除巡检记录对应的设备
     * 
     * @param id 巡检记录对应的设备ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDescById(Long id);

    /**
     * 批量删除巡检记录对应的设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDescByIds(String[] ids);
}
