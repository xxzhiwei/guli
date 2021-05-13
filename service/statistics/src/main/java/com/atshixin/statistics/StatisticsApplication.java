package com.atshixin.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@ComponentScan(basePackages = {"com.atshixin"})
@MapperScan("com.atshixin.statistics.mapper")
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
