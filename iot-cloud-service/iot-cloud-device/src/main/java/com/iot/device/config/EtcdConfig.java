package com.iot.device.config;

import io.etcd.jetcd.Client;
import io.etcd.jetcd.Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;

@Data
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "iot-cloud.etcd")
public class EtcdConfig {
    private String endpoints;

    @Bean
    public Client etcdClient() {
        String[] eps = endpoints.split(",");
        Collection<URI> endpoints = Util.toURIs(Arrays.asList(eps));

        return Client.builder().endpoints(endpoints).build();
    }
}
