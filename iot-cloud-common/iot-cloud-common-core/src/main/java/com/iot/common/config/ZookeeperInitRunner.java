package com.iot.common.config;

import com.iot.common.config.properties.IotProperties;
import com.iot.common.zk.registry.RegistryCenterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by rongshuai on 2020/6/16 20:42
 */
@Component
@Order
@Slf4j
public class ZookeeperInitRunner implements CommandLineRunner {

    @Autowired
    private IotProperties iotProperties;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * Run.
     *
     * @param args the args
     *
     * @throws Exception the exception
     */
    @Override
    public void run(String... args) throws Exception {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        log.info("###ZookeeperInitRunner，init. HostAddress={}, applicationName={}", hostAddress, applicationName);
        RegistryCenterFactory.startup(iotProperties, hostAddress, applicationName);//进入注册中心
        log.info("###ZookeeperInitRunner，finish<<<<<<<<<<<<<");
    }

}
