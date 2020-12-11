package com.iot.tpc.service.impl;

import java.util.List;

import com.iot.common.core.service.BaseService;
import com.iot.tpc.service.ITpcMqConsumerService;
import com.iot.tpc.vo.TpcMqTagVo;
import org.springframework.stereotype.Service;
import com.iot.tpc.mapper.TpcMqTagMapper;
import com.iot.tpc.domain.TpcMqTag;
import com.iot.tpc.service.ITpcMqTagService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * MQ主题的标签Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TpcMqTagServiceImpl extends BaseService<TpcMqTag> implements ITpcMqTagService {

    @Resource
    private TpcMqTagMapper tpcMqTagMapper;
    @Resource
    private ITpcMqConsumerService tpcMqConsumerService;

    @Override
    public List<TpcMqTagVo> listWithPage(TpcMqTag mdcMqTag) {
        return tpcMqTagMapper.listTpcMqTagVoWithPage(mdcMqTag);
    }

    @Override
    public int deleteTagById(Long tagId) {
        // 删除订阅的tag
        tpcMqConsumerService.deleteSubscribeTagByTagId(tagId);
        // 删除tag
        return tpcMqTagMapper.deleteByPrimaryKey(tagId);
    }
}

