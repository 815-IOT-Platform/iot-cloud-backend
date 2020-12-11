package com.iot.tpc.controller;

import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.tpc.dto.TpcMqMessageDto;
import com.iot.tpc.service.ITpcMqMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rongshuai on 2020/6/17 22:03
 */
@RestController
@RequestMapping(value = "/tpcApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - TpcMqConsumerController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TpcMqMessageFeiginController extends BaseController {
    @Autowired
    private ITpcMqMessageService tpcMqMessageService;

    @PostMapping(value = "saveMessageWaitingConfirm")
    @ApiOperation(httpMethod = "POST", value = "预存储消息")
    public R saveMessageWaitingConfirm(@RequestBody TpcMqMessageDto mqMessageDto) {
        logger.info("预存储消息. mqMessageDto={}", mqMessageDto);
        tpcMqMessageService.saveMessageWaitingConfirm(mqMessageDto);
        return R.ok();
    }

    @PostMapping(value = "confirmAndSendMessage")
    @ApiOperation(httpMethod = "POST", value = "确认并发送消息")
    public R confirmAndSendMessage(@RequestParam("messageKey") String messageKey) {
        logger.info("确认并发送消息. messageKey={}", messageKey);
        tpcMqMessageService.confirmAndSendMessage(messageKey);
        return R.ok();
    }

    @PostMapping(value = "saveAndSendMessage")
    @ApiOperation(httpMethod = "POST", value = "存储并发送消息")
    public R saveAndSendMessage(@RequestBody TpcMqMessageDto mqMessageDto) {
        logger.info("存储并发送消息. mqMessageDto={}", mqMessageDto);
        tpcMqMessageService.saveAndSendMessage(mqMessageDto);
        return R.ok();
    }

    @PostMapping(value = "directSendMessage")
    @ApiOperation(httpMethod = "POST", value = "直接发送消息")
    public R directSendMessage(@RequestBody TpcMqMessageDto mqMessageDto) {
        logger.info("直接发送消息. mqMessageDto={}", mqMessageDto);
        tpcMqMessageService.directSendMessage(mqMessageDto.getMessageBody(), mqMessageDto.getMessageTopic(), mqMessageDto.getMessageTag(), mqMessageDto.getMessageKey(), mqMessageDto.getProducerGroup(), mqMessageDto.getDelayLevel());
        return R.ok();
    }

    @PostMapping(value = "deleteMessageByMessageKey")
    @ApiOperation(httpMethod = "POST", value = "根据消息ID删除消息")
    public R deleteMessageByMessageKey(@RequestParam("messageKey") String messageKey) {
        logger.info("根据消息ID删除消息. messageKey={}", messageKey);
        tpcMqMessageService.deleteMessageByMessageKey(messageKey);
        return R.ok();
    }

    @PostMapping(value = "confirmReceiveMessage")
    @ApiOperation(httpMethod = "POST", value = "确认收到消息")
    public R confirmReceiveMessage(@RequestParam("cid") final String cid, @RequestParam("messageKey") final String messageKey) {
        logger.info("确认收到消息. cid={}, messageKey={}", cid, messageKey);
        tpcMqMessageService.confirmReceiveMessage(cid, messageKey);
        return R.ok();
    }

    @PostMapping(value = "saveAndConfirmFinishMessage")
    @ApiOperation(httpMethod = "POST", value = "确认消费消息")
    public R confirmConsumedMessage(@RequestParam("cid") final String cid, @RequestParam("messageKey") final String messageKey) {
        logger.info("确认完成消费消息. cid={}, messageKey={}", cid, messageKey);
        tpcMqMessageService.confirmConsumedMessage(cid, messageKey);
        return R.ok();
    }
}
