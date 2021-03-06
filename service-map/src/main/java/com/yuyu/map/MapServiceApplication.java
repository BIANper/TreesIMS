package com.yuyu.map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.yuyu.map.feign")
@MapperScan("com.yuyu.map.dao")
public class MapServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapServiceApplication.class, args);
    }

}
