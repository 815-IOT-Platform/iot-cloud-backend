package com.iot.websocket.feign.factory;

import com.iot.websocket.dto.MsgDto;
import com.iot.websocket.feign.RemoteWebSocketService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by rongshuai on 2020/6/5 20:36
 */
@Slf4j
@Component
public class RemoteWebSocketFallbackFactory implements FallbackFactory<RemoteWebSocketService> {

    @Override
    public RemoteWebSocketService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteWebSocketService() {
            @Override
            public Boolean createWebsocketMsg (MsgDto msgDto) {
                return null;
            }
        };
    }
}
