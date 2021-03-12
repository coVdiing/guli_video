package com.yunwanjia.guli.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
// 排除数据源自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.yunwanjia.guli"})
@EnableDiscoveryClient
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }
}
