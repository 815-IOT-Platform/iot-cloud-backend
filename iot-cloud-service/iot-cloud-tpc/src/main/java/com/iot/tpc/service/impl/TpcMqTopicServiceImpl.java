package com.iot.tpc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.BaseService;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.tpc.domain.TpcMqTag;
import com.iot.tpc.dto.AddTopicDto;
import com.iot.tpc.dto.TopicBindTagDto;
import com.iot.tpc.mapper.TpcMqTagMapper;
import com.iot.tpc.vo.TpcMqTopicVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.iot.tpc.mapper.TpcMqTopicMapper;
import com.iot.tpc.domain.TpcMqTopic;
import com.iot.tpc.service.ITpcMqTopicService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * MQ主题Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TpcMqTopicServiceImpl extends BaseService<TpcMqTopic> implements ITpcMqTopicService {
    @Resource
    private TpcMqTopicMapper tpcMqTopicMapper;

    @Resource
    private TpcMqTagMapper tpcMqTagMapper;

    /**
     * 新增主题
     * @param addTopicDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public TpcMqTopic addMqTopic(AddTopicDto addTopicDto, LoginAuthDto loginAuthDto){
        TpcMqTopic tpcMqTopic = new TpcMqTopic();
        UpdateInfoUtil.setModifyInfo(tpcMqTopic,loginAuthDto);
        BeanUtils.copyProperties(addTopicDto,tpcMqTopic);
        int result = tpcMqTopicMapper.insert(tpcMqTopic);
        if(result==1){
            return tpcMqTopic;
        }else{
            throw new BusinessException("topic创建失败");
        }
    }

    /**
     * 为主题绑定标签
     * @param topicBindTagDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public TpcMqTag topicBindTag(TopicBindTagDto topicBindTagDto, LoginAuthDto loginAuthDto){
        TpcMqTag tpcMqTag = new TpcMqTag();
        UpdateInfoUtil.setModifyInfo(tpcMqTag,loginAuthDto);
        BeanUtils.copyProperties(topicBindTagDto,tpcMqTag);
        int result = tpcMqTagMapper.insert(tpcMqTag);
        if(result==1){
            return tpcMqTag;
        }else{
            throw new BusinessException("topic绑定tag失败");
        }
    }

    @Override
    public List<TpcMqTopicVo> listWithPage(TpcMqTopic mdcMqTopic) {
        return tpcMqTopicMapper.listTpcMqTopicVoWithPage(mdcMqTopic);
    }
}
