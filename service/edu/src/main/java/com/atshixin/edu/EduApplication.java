package com.atshixin.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 2021-1-7 在多次修改后，应该将构建代码（target）删除，重新构建
@SpringBootApplication
@ComponentScan(basePackages = {"com.atshixin"}) // 指定扫描的包前缀（默认只扫描当前启动类所在目录
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
