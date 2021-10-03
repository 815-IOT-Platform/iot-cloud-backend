package com.iot.honeyBot.controller;

import com.iot.honeyBot.model.dto.WebSSHData;
import com.iot.honeyBot.service.WebSSHService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/websocket")
public class WebSocketController {
    private SimpMessagingTemplate template;
    private WebSSHService webSSHService;

    @MessageMapping("/msg")
    public void sendMessage(@RequestBody WebSSHData webSSHData) {
        webSSHService.recvHandle(webSSHData, template);  // 处理发送消息
    }
}