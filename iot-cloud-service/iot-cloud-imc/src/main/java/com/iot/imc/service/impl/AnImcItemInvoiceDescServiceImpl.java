package com.iot.imc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.utils.DateUtils;
import com.iot.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.imc.mapper.IotImcItemInvoiceDescMapper;
import com.iot.imc.domain.AnImcItemInvoiceDesc;
import com.iot.imc.service.IAnImcItemInvoiceDescService;
import com.iot.common.core.text.Convert;

/**
 * 巡检记录对应的设备Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcItemInvoiceDescServiceImpl implements IAnImcItemInvoiceDescService 
{
    @Autowired
    private IotImcItemInvoiceDescMapper iotImcItemInvoiceDescMapper;

    /**
     * 查询巡检记录对应的设备
     * 
     * @param id 巡检记录对应的设备ID
     * @return 巡检记录对应的设备
     */
    @Override
    public AnImcItemInvoiceDesc selectAnImcItemInvoiceDescById(Long id)
    {
        return iotImcItemInvoiceDescMapper.selectAnImcItemInvoiceDescById(id);
    }

    /**
     * 查询巡检记录对应的设备列表
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 巡检记录对应的设备
     */
    @Override
    public List<AnImcItemInvoiceDesc> selectAnImcItemInvoiceDescList(AnImcItemInvoiceDesc anImcItemInvoiceDesc)
    {
        return iotImcItemInvoiceDescMapper.selectAnImcItemInvoiceDescList(anImcItemInvoiceDesc);
    }

    /**
     * 新增巡检记录对应的设备
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 结果
     */
    @Override
    public int insertAnImcItemInvoiceDesc(AnImcItemInvoiceDesc anImcItemInvoiceDesc, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcItemInvoiceDesc,user);
        return iotImcItemInvoiceDescMapper.insert(anImcItemInvoiceDesc);
    }

    /**
     * 修改巡检记录对应的设备
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 结果
     */
    @Override
    public int updateAnImcItemInvoiceDesc(AnImcItemInvoiceDesc anImcItemInvoiceDesc)
    {
        anImcItemInvoiceDesc.setUpdateTime(DateUtils.getNowDate());
        return iotImcItemInvoiceDescMapper.updateAnImcItemInvoiceDesc(anImcItemInvoiceDesc);
    }

    /**
     * 删除巡检记录对应的设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcItemInvoiceDescByIds(String ids)
    {
        return iotImcItemInvoiceDescMapper.deleteAnImcItemInvoiceDescByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检记录对应的设备信息
     * 
     * @param id 巡检记录对应的设备ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDescById(Long id)
    {
        return iotImcItemInvoiceDescMapper.deleteAnImcItemInvoiceDescById(id);
    }
}
