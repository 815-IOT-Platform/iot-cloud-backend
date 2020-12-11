package com.iot.imc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.imc.domain.AnImcItemInvoice;
import java.util.List;

/**
 * 巡检记录Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IotImcItemInvoiceMapper extends BaseMapper<AnImcItemInvoice>
{
    /**
     * 查询巡检记录
     * 
     * @param id 巡检记录ID
     * @return 巡检记录
     */
    public AnImcItemInvoice selectAnImcItemInvoiceById(Long id);

    /**
     * 查询巡检记录列表
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 巡检记录集合
     */
    public List<AnImcItemInvoice> selectAnImcItemInvoiceList(AnImcItemInvoice anImcItemInvoice);

    /**
     * 新增巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    public int insertAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice);

    /**
     * 修改巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    public int updateAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice);

    /**
     * 删除巡检记录
     * 
     * @param id 巡检记录ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceById(Long id);

    /**
     * 批量删除巡检记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceByIds(String[] ids);
}
