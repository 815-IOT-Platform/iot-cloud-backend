package com.iot.imc.service.impl;

import java.util.Date;
import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.DateUtils;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.imc.dto.ImcInvoiceQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.imc.mapper.IotImcItemInvoiceMapper;
import com.iot.imc.domain.AnImcItemInvoice;
import com.iot.imc.service.IAnImcItemInvoiceService;
import com.iot.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

/**
 * 巡检记录Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcItemInvoiceServiceImpl implements IAnImcItemInvoiceService 
{
    @Autowired
    private IotImcItemInvoiceMapper iotImcItemInvoiceMapper;

    /**
     * 查询巡检记录
     * 
     * @param id 巡检记录ID
     * @return 巡检记录
     */
    @Override
    public AnImcItemInvoice selectAnImcItemInvoiceById(Long id)
    {
        return iotImcItemInvoiceMapper.selectAnImcItemInvoiceById(id);
    }

    /**
     * 查询巡检记录列表
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 巡检记录
     */
    @Override
    public List<AnImcItemInvoice> selectAnImcItemInvoiceList(AnImcItemInvoice anImcItemInvoice)
    {
        return iotImcItemInvoiceMapper.selectAnImcItemInvoiceList(anImcItemInvoice);
    }

    /**
     * 新增巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    @Override
    public int insertAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcItemInvoice,user);
        return iotImcItemInvoiceMapper.insert(anImcItemInvoice);
    }

    /**
     * 修改巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    @Override
    public int updateAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice)
    {
        anImcItemInvoice.setUpdateTime(DateUtils.getNowDate());
        return iotImcItemInvoiceMapper.updateAnImcItemInvoice(anImcItemInvoice);
    }

    /**
     * 删除巡检记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcItemInvoiceByIds(String ids)
    {
        return iotImcItemInvoiceMapper.deleteAnImcItemInvoiceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检记录信息
     * 
     * @param id 巡检记录ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceById(Long id)
    {
        return iotImcItemInvoiceMapper.deleteAnImcItemInvoiceById(id);
    }

    /**
     * 查询单据列表
     * @param imcInvoiceQueryDto
     * @param user
     * @return
     */
    @Override
    public List<AnImcItemInvoice> queryInvoiceList(ImcInvoiceQueryDto imcInvoiceQueryDto,LoginAuthDto user) {
        if(imcInvoiceQueryDto.getItemId() != null && imcInvoiceQueryDto.getStatus() != null){
            Example example = new Example(AnImcItemInvoice.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("inspcItemId",imcInvoiceQueryDto.getItemId());
            criteria.andEqualTo("status",imcInvoiceQueryDto.getStatus());
            return iotImcItemInvoiceMapper.selectByExample(example);
        }else{
            throw new BusinessException("参数异常");
        }
    }

    //queryDetailsById
    //TODO

    //saveData
    //TODO

    //buildPreview
    //TODO

    //itemInvoicePdf
    //TODO

    //getInvoicePreview
    //TODO

    /**
     * 用户确认后，将用户名称填入到巡检单据的用户确认字段中
     * @param itemId
     * @param user
     */
    @Override
    public void handleUserConfirm(Long itemId,LoginAuthDto user){
        Example example = new Example(AnImcItemInvoice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspcItemId",itemId);
        AnImcItemInvoice update = new AnImcItemInvoice();
        update.setUserConfirm(user.getUserName());
        update.setLastOperatorId(user.getUserId());
        update.setUpdateBy(user.getUserName() == null ? user.getLoginName() : user.getUserName());
        update.setUpdateTime(new Date());
        iotImcItemInvoiceMapper.updateByExampleSelective(update,example);
    }
}
