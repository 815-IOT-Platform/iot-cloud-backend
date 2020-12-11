package com.iot.imc.manager;

import com.iot.imc.domain.AnImcInspectionReview;
import com.iot.imc.mapper.IotImcInspectionReviewMapper;
import com.iot.sdk.annotation.MqProducerStore;
import com.iot.sdk.domain.MqMessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rongshuai on 2020/6/17 22:21
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class TaskManager {
    @Autowired
    IotImcInspectionReviewMapper reviewMapper;

    @MqProducerStore
    public void sendReviewMqTestMsg(final MqMessageData mqMessageData, AnImcInspectionReview anImcInspectionReview) {
        log.info("发送review插入测试消息：{}",mqMessageData);
        reviewMapper.insert(anImcInspectionReview);
    }
}
