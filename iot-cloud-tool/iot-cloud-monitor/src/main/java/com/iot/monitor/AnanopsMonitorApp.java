package com.iot.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class AnanopsMonitorApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(AnanopsMonitorApp.class, args);
    }
}