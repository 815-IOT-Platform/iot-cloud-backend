package com.iot.tpc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.BaseService;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.StringUtils;
import com.iot.common.utils.bean.BeanUtils;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.tpc.domain.TpcMqSubscribe;
import com.iot.tpc.domain.TpcMqTopic;
import com.iot.tpc.dto.AddMqConsumerDto;
import com.iot.tpc.dto.ConsumerSubscribeTopicDto;
import com.iot.tpc.mapper.TpcMqSubscribeMapper;
import com.iot.tpc.mapper.TpcMqTopicMapper;
import com.iot.tpc.vo.TpcMqConsumerVo;
import com.iot.tpc.vo.TpcMqSubscribeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.tpc.mapper.TpcMqConsumerMapper;
import com.iot.tpc.domain.TpcMqConsumer;
import com.iot.tpc.service.ITpcMqConsumerService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


/**
 * 消费者Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TpcMqConsumerServiceImpl extends BaseService<TpcMqConsumer> implements ITpcMqConsumerService
{
    @Autowired
    private TpcMqConsumerMapper tpcMqConsumerMapper;

    @Autowired
    private TpcMqTopicMapper tpcMqTopicMapper;

    @Autowired
    private TpcMqSubscribeMapper tpcMqSubscribeMapper;

    public TpcMqSubscribe consumerSubcribeTopic(ConsumerSubscribeTopicDto consumerSubscribeTopicDto){
        String consumerCode = consumerSubscribeTopicDto.getConsumerCode();
        String topicCode = consumerSubscribeTopicDto.getTopicCode();
        Long consumerId = null;
        Long topicId = null;
        if(consumerCode==null||topicCode==null){
            throw new BusinessException("参数异常");
        }
        Example example = new Example(TpcMqConsumer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("consumerCode",consumerCode);
        List<TpcMqConsumer> tpcMqConsumerList = tpcMqConsumerMapper.selectByExample(example);
        if(tpcMqConsumerList.size()==0){
            throw new BusinessException("无此消费者");
        }
        consumerId = tpcMqConsumerList.get(0).getId();
        Example example2 = new Example(TpcMqTopic.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("topicCode",topicCode);
        List<TpcMqTopic> tpcMqTopicList = tpcMqTopicMapper.selectByExample(example2);
        if(tpcMqTopicList.size()==0){
            throw new BusinessException("无此topic");
        }
        topicId = tpcMqTopicList.get(0).getId();
        Long subscribeId = super.generateId();
        TpcMqSubscribe tpcMqSubscribe = new TpcMqSubscribe();
        tpcMqSubscribe.setId(subscribeId);
        tpcMqSubscribe.setConsumerCode(consumerCode);
        tpcMqSubscribe.setConsumerId(consumerId);
        tpcMqSubscribe.setTopicCode(topicCode);
        tpcMqSubscribe.setTopicId(topicId);
        tpcMqSubscribeMapper.insert(tpcMqSubscribe);
        return tpcMqSubscribe;
    }

    @Override
    public TpcMqConsumer addConsumer(AddMqConsumerDto addMqConsumerDto, LoginAuthDto loginAuthDto){
        TpcMqConsumer tpcMqConsumer = new TpcMqConsumer();
        UpdateInfoUtil.setInsertInfo(tpcMqConsumer,loginAuthDto);
        BeanUtils.copyProperties(addMqConsumerDto,tpcMqConsumer);
        int result = tpcMqConsumerMapper.insert(tpcMqConsumer);
        if(result==1){
            return tpcMqConsumer;
        }else{
            throw new BusinessException("consumer创建失败");
        }
    }

    @Override
    public List<TpcMqConsumerVo> listConsumerVoWithPage(TpcMqConsumer tpcMqConsumer) {
        return tpcMqConsumerMapper.listTpcMqConsumerVoWithPage(tpcMqConsumer);
    }

    @Override
    public List<TpcMqSubscribeVo> listSubscribeVoWithPage(TpcMqConsumer tpcMqConsumer) {
        return tpcMqConsumerMapper.listTpcMqSubscribeVoWithPage(tpcMqConsumer);
    }

    @Override
    public int deleteSubscribeTagByTagId(Long tagId) {
        return tpcMqConsumerMapper.deleteSubscribeTagByTagId(tagId);
    }

    @Override
    public int deleteConsumerById(Long consumerId) {
        // 删除消费者
        tpcMqConsumerMapper.deleteByPrimaryKey(consumerId);
        // 删除订阅关系
        List<Long> subscribeIdList = tpcMqConsumerMapper.listSubscribeIdByConsumerId(consumerId);
        if (StringUtils.isNotEmpty(subscribeIdList)) {
            tpcMqConsumerMapper.deleteSubscribeByConsumerId(consumerId);
            // 删除订阅tag
            tpcMqConsumerMapper.deleteSubscribeTagBySubscribeIdList(subscribeIdList);
        }
        return 1;
    }

    @Override
    public List<TpcMqSubscribeVo> listSubscribeVo(List<Long> subscribeIdList) {
        return tpcMqConsumerMapper.listSubscribeVo(subscribeIdList);
    }

    @Override
    public List<String> listConsumerGroupByTopic(final String topic) {
        return tpcMqConsumerMapper.listConsumerGroupByTopic(topic);
    }

    @Override
    public void updateOnLineStatusByCid(final String consumerGroup) {
        logger.info("更新消费者cid={}状态为在线", consumerGroup);
        this.updateStatus(consumerGroup, 10);

    }

    @Override
    public void updateOffLineStatusByCid(final String consumerGroup) {
        logger.info("更新消费者cid={}状态为离线", consumerGroup);
        this.updateStatus(consumerGroup, 20);
    }

    private void updateStatus(final String consumerGroup, final int status) {
        TpcMqConsumer tpcMqConsumer = tpcMqConsumerMapper.getByCid(consumerGroup);
        if (tpcMqConsumer.getStatus() != null && tpcMqConsumer.getStatus() != status) {
            TpcMqConsumer update = new TpcMqConsumer();
            update.setStatus(status);
            update.setId(tpcMqConsumer.getId());
            tpcMqConsumerMapper.updateByPrimaryKeySelective(update);
        }
    }
}
