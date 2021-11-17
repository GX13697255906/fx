package com.fx.cloud.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Administrator
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FxGatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(FxGatewayServer.class, args);
    }

}
