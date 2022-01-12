package com.fx.cloud.gateway.server.configuration.route;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fx.cloud.gateway.server.entity.GatewayRoute;
import com.fx.cloud.gateway.server.service.IGatewayRouteService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * 代码配置路由
 */
@Configuration
public class RouteConfig {

    @Autowired
    private IGatewayRouteService gatewayRouteService;
    @Autowired
    private DiscoveryClient discoveryClient;

    private static NacosDiscoveryClient nacosDiscoveryClient = null;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        CompositeDiscoveryClient compositeDiscoveryClient = (CompositeDiscoveryClient) discoveryClient;
        List<DiscoveryClient> discoveryClientList = compositeDiscoveryClient.getDiscoveryClients();
        for (DiscoveryClient e : discoveryClientList) {
            if (e.getClass().equals(NacosDiscoveryClient.class)) {
                nacosDiscoveryClient = (NacosDiscoveryClient) e;
            }
        }
        List<String> serviceInstanceList = Lists.newArrayList();
        if (nacosDiscoveryClient != null) {
            serviceInstanceList = nacosDiscoveryClient.getServices();
            if (!CollectionUtils.isEmpty(serviceInstanceList)) {
                serviceInstanceList = serviceInstanceList.stream().filter(e -> e.contains("fx-")).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(serviceInstanceList)) {
                    List<GatewayRoute> routeList = gatewayRouteService.getAllFxService();
                    if (!CollectionUtils.isEmpty(routeList)) {
                        List<String> finalServiceInstanceList = serviceInstanceList;
                        routeList = routeList.stream().filter(e -> finalServiceInstanceList.contains(e.getServiceId())).collect(Collectors.toList());
                        if (!CollectionUtils.isEmpty(routeList)) {
                            for (GatewayRoute gatewayRoute : routeList) {
                                builder.route(gatewayRoute.getServiceId(), p ->
                                        p.path(gatewayRoute.getPath())
                                                .filters(f -> f.stripPrefix(gatewayRoute.getStripPrefix()).preserveHostHeader())
                                                .uri(gatewayRoute.getUrl()));
                            }
                        }
                    }
                }
            }
        }


        RouteLocator routeLocator = builder.build();
        return routeLocator;
    }

    @Bean
    public GatewayProperties gatewayProperties() {
        return new GatewayProperties();
    }


}
