package com.iot.tpc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.BaseService;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.tpc.dto.AddMqProducerDto;
import com.iot.tpc.vo.TpcMqProducerVo;
import com.iot.tpc.vo.TpcMqPublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.iot.tpc.mapper.TpcMqProducerMapper;
import com.iot.tpc.domain.TpcMqProducer;
import com.iot.tpc.service.ITpcMqProducerService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 生产者Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TpcMqProducerServiceImpl extends BaseService<TpcMqProducer> implements ITpcMqProducerService {

    @Resource
    private TpcMqProducerMapper tpcMqProducerMapper;

    @Override
    public TpcMqProducer addProducer(AddMqProducerDto addMqProducerDto, LoginAuthDto loginAuthDto){
        TpcMqProducer tpcMqProducer = new TpcMqProducer();
        UpdateInfoUtil.setInsertInfo(tpcMqProducer,loginAuthDto);
        BeanUtils.copyProperties(addMqProducerDto,tpcMqProducer);
        int result = tpcMqProducerMapper.insert(tpcMqProducer);
        if(result==1){
            return tpcMqProducer;
        }else{
            throw new BusinessException("producer创建失败");
        }
    }

    @Override
    public List<TpcMqProducerVo> listProducerVoWithPage(TpcMqProducer mdcMqProducer) {
        return tpcMqProducerMapper.listTpcMqProducerVoWithPage(mdcMqProducer);
    }

    @Override
    public List<TpcMqPublishVo> listPublishVoWithPage(TpcMqProducer mdcMqProducer) {
        return tpcMqProducerMapper.listTpcMqPublishVoWithPage(mdcMqProducer);
    }

    @Override
    public int deleteProducerById(Long producerId) {
        // 删除consumer
        tpcMqProducerMapper.deleteByPrimaryKey(producerId);
        // 删除发布关系
        return tpcMqProducerMapper.deletePublishByProducerId(producerId);
    }

    @Override
    public void updateOnLineStatusByPid(final String producerGroup) {
        logger.info("更新生产者pid={}状态为在线", producerGroup);
        this.updateStatus(producerGroup, 10);

    }

    @Override
    public void updateOffLineStatusByPid(final String producerGroup) {
        logger.info("更新生产者pid={}状态为离线", producerGroup);
        this.updateStatus(producerGroup, 20);
    }

    private void updateStatus(final String producerGroup, final int status) {
        TpcMqProducer tpcMqProducer = tpcMqProducerMapper.getByPid(producerGroup);
        if (tpcMqProducer.getStatus() != null && tpcMqProducer.getStatus() != status) {
            TpcMqProducer update = new TpcMqProducer();
            update.setStatus(status);
            update.setId(tpcMqProducer.getId());
            tpcMqProducerMapper.updateByPrimaryKeySelective(update);
        }
    }
}
