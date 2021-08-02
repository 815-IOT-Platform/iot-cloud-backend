//package com.iot.device.manager.mq.consumer.listener;
//
//
//import com.iot.device.manager.mq.consumer.TopicConsumer;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by huqiaoqian on 2020/10/29
// */
//@Slf4j
//@Component
//public class MsgListener implements MessageListenerConcurrently {
//    @Resource
//    TopicConsumer topicConsumer;
//
//    @Override
//    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext){
//        MessageExt msg = messageExtList.get(0);
//        String body = new String(msg.getBody());
//        String topicName = msg.getTopic();
//        String tags = msg.getTags();
//        String keys = msg.getKeys();
//        log.info("MQ消费Topic={},tag={},key={}", topicName, tags, keys);
//        // 控制幂等性使用的key
//        try {
//            //处理消息
//            topicConsumer.handlerSendMqMsg(body,topicName,tags,keys);
//        } catch (IllegalArgumentException ex) {
//            log.error("校验MQ message 失败 ex={}", ex.getMessage(), ex);
//        } catch (Exception e) {
//            log.error("处理MQ message 失败 topicName={}, keys={}, ex={}", topicName, keys, e.getMessage(), e);
//            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//        }
//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//    }
//}
