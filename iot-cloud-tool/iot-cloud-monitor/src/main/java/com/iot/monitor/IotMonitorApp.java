package com.iot.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class IotMonitorApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(IotMonitorApp.class, args);
    }
}