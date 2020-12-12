package com.iot;

import com.iot.tpc.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by rongshuai on 2020/6/17 9:22
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableRyFeignClients
@MapperScan("com.iot.*.mapper")
public class IotTpcApp {
    public static void main(String[] args) {
        SpringApplication.run(IotTpcApp.class, args);
    }
}
