package com.iot.tpc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.IService;
import com.iot.tpc.domain.TpcMqProducer;
import com.iot.tpc.dto.AddMqProducerDto;
import com.iot.tpc.vo.TpcMqProducerVo;
import com.iot.tpc.vo.TpcMqPublishVo;

import java.util.List;

/**
 * 生产者Service接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface ITpcMqProducerService extends IService<TpcMqProducer> {
    /**
     * 创建一个Mq生产者
     * @param addMqProducerDto
     * @param loginAuthDto
     * @return
     */
    TpcMqProducer addProducer(AddMqProducerDto addMqProducerDto, LoginAuthDto loginAuthDto);
    /**
     * List producer vo with page list.
     *
     * @param mdcMqProducer the mdc mq producer
     *
     * @return the list
     */
    List<TpcMqProducerVo> listProducerVoWithPage(TpcMqProducer mdcMqProducer);

    /**
     * 查询发布者列表.
     *
     * @param mdcMqProducer the mdc mq producer
     *
     * @return the list
     */
    List<TpcMqPublishVo> listPublishVoWithPage(TpcMqProducer mdcMqProducer);

    /**
     * 根据生产者ID删除生产者.
     *
     * @param id the id
     *
     * @return the int
     */
    int deleteProducerById(Long id);

    /**
     * 根据pid更新生产者状态为在线.
     *
     * @param producerGroup the producer group
     */
    void updateOnLineStatusByPid(String producerGroup);

    /**
     * 根据pid更新生产者状态为离线.
     *
     * @param producerGroup the producer group
     */
    void updateOffLineStatusByPid(String producerGroup);
}