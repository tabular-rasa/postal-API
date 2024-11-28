package com.vnici.postal.service;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = { "com.vnici.postal"})
@MapperScan("com.vnici.postal.biz.mapper")
public class PostalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostalServiceApplication.class, args);
        log.info("postal service app start ....");
    }
}
