package com.iot.tpc.config;


import com.iot.common.config.PcObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by rongshuai on 2019/11/27 13:06
 */
@Configuration
@EnableWebMvc
public class TpcWebMvcConfig implements WebMvcConfigurer {


//    @Autowired
//    private LoginUserHandlerResolver loginUserHandlerResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
//    {
//        argumentResolvers.add(loginUserHandlerResolver);
//    }

    /**
     * 实体类格式转换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        PcObjectMapper.buidMvcMessageConverter(converters);
    }
}
