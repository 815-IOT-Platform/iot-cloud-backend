package com.iot.websocket.config;

import com.iot.common.config.PcObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/9 17:32
 */
@Configuration
@EnableWebMvc
public class WebsocketMvcConfig implements WebMvcConfigurer {

    /**
     * 实体类格式转换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        PcObjectMapper.buidMvcMessageConverter(converters);
    }
}
