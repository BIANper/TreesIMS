package com.yuyu.tree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yuyu.tree.dao")
@EnableFeignClients(basePackages = "com.yuyu.tree.feign")
@EnableTransactionManagement
public class TreeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreeServiceApplication.class, args);
    }

}
