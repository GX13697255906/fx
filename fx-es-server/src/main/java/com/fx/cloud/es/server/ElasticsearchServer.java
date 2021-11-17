package com.fx.cloud.es.server;

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
public class ElasticsearchServer {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchServer.class, args);
    }

}
