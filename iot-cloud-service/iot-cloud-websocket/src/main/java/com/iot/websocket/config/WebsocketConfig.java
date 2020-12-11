package com.iot.websocket.config;

import com.iot.common.config.PcObjectMapper;
import com.iot.common.utils.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.security.Principal;
import java.util.List;

/**
 * Created by rongshuai on 2020/6/5 15:44
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints (StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker (MessageBrokerRegistry config) {
        // prefix for subscribe，topic用来广播，queue单独发送
        config.enableSimpleBroker("/topic","/queue");
        // prefix for send
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel (ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    final String userId = accessor.getFirstNativeHeader("userId");
                    if (null != userId) {
                        if (StringUtils.isNotEmpty(userId)) {
                            System.out.println(userId);
                            Principal principal = () -> userId;
                            accessor.setUser(principal);
                        }
                    }
                } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    System.out.println(accessor);
                } else if (StompCommand.MESSAGE.equals(accessor.getCommand())) {
                    System.out.println(accessor);
                } else if (StompCommand.COMMIT.equals(accessor.getCommand())) {
                    System.out.println(accessor);
                } else {
                    System.out.println(accessor);
                }
                return message;
            }
        });
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters){
        try {
            PcObjectMapper.buidMessageConverter(messageConverters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
