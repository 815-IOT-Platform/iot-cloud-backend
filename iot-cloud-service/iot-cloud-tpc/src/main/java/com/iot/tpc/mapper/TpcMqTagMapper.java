package com.iot.tpc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.tpc.domain.TpcMqTag;
import com.iot.tpc.vo.TpcMqTagVo;

import java.util.List;

/**
 * MQ主题的标签Mapper接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface TpcMqTagMapper extends BaseMapper<TpcMqTag>
{
    /**
     * List tpc mq tag vo with page list.
     *
     * @param tpcMqTag the tpc mq tag
     *
     * @return the list
     */
    List<TpcMqTagVo> listTpcMqTagVoWithPage(TpcMqTag tpcMqTag);
}
