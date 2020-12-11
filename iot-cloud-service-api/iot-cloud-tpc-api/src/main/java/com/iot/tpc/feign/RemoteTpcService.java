package com.iot.tpc.feign;

import com.iot.common.constant.ServiceNameConstants;
import com.iot.common.core.domain.R;
import com.iot.tpc.dto.TpcMqMessageDto;
import com.iot.tpc.feign.factory.RemoteTpcFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rongshuai on 2020/6/17 21:45
 */
@FeignClient(name = ServiceNameConstants.TPC_SERVICE, fallbackFactory = RemoteTpcFallbackFactory.class)
public interface RemoteTpcService {

    /**
     * 预存储消息.
     *
     * @param mqMessageDto the mq message dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/saveMessageWaitingConfirm")
    R saveMessageWaitingConfirm(@RequestBody TpcMqMessageDto mqMessageDto);

    /**
     * 确认并发送消息.
     *
     * @param messageKey the message key
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/confirmAndSendMessage")
    R confirmAndSendMessage(@RequestParam("messageKey") String messageKey);

    /**
     * 存储并发送消息.
     *
     * @param mqMessageDto the mq message dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/saveAndSendMessage")
    R saveAndSendMessage(@RequestBody TpcMqMessageDto mqMessageDto);

    /**
     * 直接发送消息.
     *
     * @param mqMessageDto the mq message dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/directSendMessage")
    R directSendMessage(@RequestBody TpcMqMessageDto mqMessageDto);

    /**
     * 根据messageKey删除消息记录.
     *
     * @param messageKey the message key
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/deleteMessageByMessageKey")
    R deleteMessageByMessageKey(@RequestParam("messageKey") String messageKey);

    /**
     * Confirm receive message wrapper.
     *
     * @param cid        the cid
     * @param messageKey the message key
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/confirmReceiveMessage")
    R confirmReceiveMessage(@RequestParam("cid") final String cid, @RequestParam("messageKey") final String messageKey);

    /**
     * Save and confirm finish message wrapper.
     *
     * @param cid        the cid
     * @param messageKey the message key
     *
     * @return the wrapper
     */
    @PostMapping(value = "/tpcApi/saveAndConfirmFinishMessage")
    R confirmConsumedMessage(@RequestParam("cid") final String cid, @RequestParam("messageKey") final String messageKey);
}
