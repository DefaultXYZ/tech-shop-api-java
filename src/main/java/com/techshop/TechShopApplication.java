package com.techshop;

import com.techshop.config.properties.api.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ApiProperties.class})
public class TechShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechShopApplication.class, args);
    }

}
