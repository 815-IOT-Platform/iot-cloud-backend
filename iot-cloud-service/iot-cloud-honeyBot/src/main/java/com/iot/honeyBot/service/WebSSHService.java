package com.iot.honeyBot.service;

import com.iot.honeyBot.model.dto.WebSSHData;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public interface WebSSHService {
    public void initConnection(Integer userId);

    public void recvHandle(WebSSHData webSSHData, SimpMessagingTemplate template);

    public void close(Integer userId);
}