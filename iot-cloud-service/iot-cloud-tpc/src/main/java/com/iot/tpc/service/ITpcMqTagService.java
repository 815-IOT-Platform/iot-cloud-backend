package com.iot.tpc.service;

import com.iot.common.core.service.IService;
import com.iot.tpc.domain.TpcMqTag;
import com.iot.tpc.vo.TpcMqTagVo;

import java.util.List;

/**
 * MQ主题的标签Service接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface ITpcMqTagService extends IService<TpcMqTag> {

    /**
     * 查询Tag列表.
     *
     * @param mdcMqTag the mdc mq tag
     *
     * @return the list
     */
    List<TpcMqTagVo> listWithPage(TpcMqTag mdcMqTag);

    /**
     * 根据ID删除TAG.
     *
     * @param tagId the tag id
     *
     * @return the int
     */
    int deleteTagById(Long tagId);
}
