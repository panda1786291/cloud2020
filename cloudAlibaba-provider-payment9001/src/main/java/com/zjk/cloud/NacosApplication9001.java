package com.zjk.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication9001.class);
    }
}
