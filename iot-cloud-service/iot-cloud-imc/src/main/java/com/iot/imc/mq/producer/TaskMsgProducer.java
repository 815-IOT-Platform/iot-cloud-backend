package com.iot.imc.mq.producer;

import com.alibaba.fastjson.JSON;
import com.iot.common.constant.MqTopicConstants;
import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.redis.util.RedisKeyUtil;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.imc.domain.AnImcInspectionReview;
import com.iot.sdk.domain.MqMessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by rongshuai on 2020/6/18 9:12
 */
@Slf4j
@Component
public class TaskMsgProducer {

    public MqMessageData createImcReview(AnImcInspectionReview anImcInspectionReview, LoginAuthDto user) {
        UpdateInfoUtil.setInsertInfo(anImcInspectionReview,user);
        String topic = MqTopicConstants.MqTopicEnum.IMC_CLOUD_TOPIC.getTopic();
        String tag = MqTopicConstants.MqTagEnum.IMC_MQ_TEST.getTag();
        String msgBody = JSON.toJSONString(anImcInspectionReview);
        String key = RedisKeyUtil.createMqKey(topic,tag,String.valueOf(anImcInspectionReview.getId()), msgBody);
        return new MqMessageData(msgBody, topic, tag, key);
    }
}
