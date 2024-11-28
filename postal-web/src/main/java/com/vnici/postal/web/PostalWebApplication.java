package com.vnici.postal.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication(scanBasePackages = { "com.vnici.postal"})
public class PostalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostalWebApplication.class, args);
        log.info("postal web app start ....");
    }
}
