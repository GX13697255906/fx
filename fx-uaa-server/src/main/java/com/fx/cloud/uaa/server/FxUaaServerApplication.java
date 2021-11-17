package com.fx.cloud.uaa.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FxUaaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxUaaServerApplication.class, args);
    }

}
