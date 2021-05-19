package com.atshixin.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

// Access Control Lists
@ComponentScan(basePackages = "com.atshixin")
@MapperScan(basePackages = "com.atshixin.acl.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }
}
