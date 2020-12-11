package com.iot.tpc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.tpc.domain.TpcMqProducer;
import com.iot.tpc.vo.TpcMqProducerVo;
import com.iot.tpc.vo.TpcMqPublishVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产者Mapper接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface TpcMqProducerMapper extends BaseMapper<TpcMqProducer>
{
    /**
     * 查询生产者集合.
     *
     * @param tpcMqProducer the tpc mq producer
     *
     * @return the list
     */
    List<TpcMqProducerVo> listTpcMqProducerVoWithPage(TpcMqProducer tpcMqProducer);

    /**
     * 查询发布消息集合.
     *
     * @param tpcMqProducer the tpc mq producer
     *
     * @return the list
     */
    List<TpcMqPublishVo> listTpcMqPublishVoWithPage(TpcMqProducer tpcMqProducer);

    /**
     * Delete publish by producer id int.
     *
     * @param producerId the producer id
     *
     * @return the int
     */
    int deletePublishByProducerId(@Param("producerId") Long producerId);

    /**
     * Gets by pid.
     *
     * @param producerGroup the producer group
     *
     * @return the by pid
     */
    TpcMqProducer getByPid(@Param("pid") String producerGroup);
}
