package com.iot.system.config;

import com.iot.system.domain.MailConstants;
import com.iot.system.service.IMailSendLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 自定义 RabbitTemplate
 * Created By ChengHao On 2020/6/3
 */
@Slf4j
@Configuration
public class RabbitConfig {
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;
    @Resource
    IMailSendLogService mailSendLogService;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);

        /**
         * 详解： https://www.cnblogs.com/wangiqngpei557/p/9381478.html
         */
        //message 从 producer 到 rabbitmq broker cluster 则会返回一个 confirmCallback
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                log.info(msgId + ":消息发送成功");
                log.info("cause:{}", cause);
                mailSendLogService.updateMailSendLogStatus(msgId, 1);//修改数据库中的记录，消息投递成功
            } else {
                log.info(msgId + ":消息发送失败");
            }
        });
        //message 从 exchange->queue 投递失败则会返回一个 returnCallback
        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingkey) -> {
            log.info("消息发送失败");
        });
        return rabbitTemplate;
    }

    @Bean
    Queue mailQueue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange mailExchange() {
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

}
