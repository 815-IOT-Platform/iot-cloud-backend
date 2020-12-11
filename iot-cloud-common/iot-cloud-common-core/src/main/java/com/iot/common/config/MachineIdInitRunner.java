//package com.ananops.common.config;
//
//import com.ananops.common.core.dto.ZkRegisterDto;
//import com.ananops.common.core.generator.IncrementIdGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.net.InetAddress;
//
///**
// * Created by rongshuai on 2020/6/4 11:08
// */
//@Component
//@Order
//@Slf4j
//public class MachineIdInitRunner implements CommandLineRunner {
//    @Value("${spring.application.name}")
//    private String applicationName;
//
//    @Override
//    public void run(String... args) throws Exception {
//        String hostAddress = InetAddress.getLocalHost().getHostAddress();
//        log.info("###MachineIdInitRunner, init. HostAddress={}, applicationName={}",hostAddress,applicationName);
//        Long serviceId = new IncrementIdGenerator(new ZkRegisterDto(applicationName,hostAddress)).nextId();
//        IncrementIdGenerator.setServiceId(serviceId);
//        log.info("###MachineIdInitRunner, finish, serviceId is {}<<<<<<<<<<<<<<",serviceId);
//    }
//}
