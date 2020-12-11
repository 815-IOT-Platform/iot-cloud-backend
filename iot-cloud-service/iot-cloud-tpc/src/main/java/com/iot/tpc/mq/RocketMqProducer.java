package com.iot.tpc.mq;

import com.iot.common.config.GlobalConstants;
import com.iot.common.core.mq.MqMessage;
import com.iot.common.exception.BusinessException;
import com.iot.tpc.service.MqProducerBeanFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by rongshuai on 2020/6/17 17:51
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RocketMqProducer {
    private static final int PRODUCER_RETRY_TIMES = 3;

    public static SendResult sendSimpleMessage(String body, String topic, String tag, String key, String pid, Integer delayLevel) {
        if (delayLevel == null) {
            delayLevel = 0;
        }
        Message message = MqMessage.checkMessage(body, topic, tag, key);
        if (delayLevel < 0 || delayLevel > GlobalConstants.Number.EIGHTEEN_INT) {
            throw new BusinessException("延迟级别错误");
        }
        message.setDelayTimeLevel(delayLevel);
        return retrySendMessage(pid, message);
    }
    //核心方法：重试发送消息（重试次数3次）
    //pid:producerGroup -->发送邮件服务是PID_UAC
    //cid:consumerGroup -->监听邮件消息服务是CID_OPC
    private static SendResult retrySendMessage(String pid, Message msg) {
        int iniCount = 1;
        SendResult result;
        while (true) {
            try {
                result = MqProducerBeanFactory.getBean(pid).send(msg);
                break;
            } catch (Exception e) {
                log.error("发送消息失败:", e);
                if (iniCount++ >= PRODUCER_RETRY_TIMES) {
                    throw new BusinessException("MQ重试三次,仍然发送失败");
                }
            }
        }
        log.info("<== 发送MQ SendResult={}", result);
        return result;
    }
}
