/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.fx.cloud.gateway.server.configuration.swagger;

import cn.hutool.core.map.MapUtil;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.fastjson.JSONObject;
import com.fx.cloud.common.constants.Constants;
import com.fx.cloud.gateway.server.entity.GatewayRoute;
import com.fx.cloud.gateway.server.service.IGatewayRouteService;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

    @Autowired
    private DiscoveryClient discoveryClient;
    private static NacosDiscoveryClient nacosDiscoveryClient = null;
    public static final String API_URI = "v2/api-docs";

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @Override
    public List<SwaggerResource> get() {
        log.info("-------------------刷新swagger列表-------------------");
        List<SwaggerResource> resources = new ArrayList<>();
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
            }
        }
        if (!CollectionUtils.isEmpty(serviceInstanceList)) {
            List<GatewayRoute> routeList = gatewayRouteService.getAllFxService();
            if (!CollectionUtils.isEmpty(routeList)) {
                List<String> finalServiceInstanceList = serviceInstanceList;
                routeList = routeList.stream().filter(e -> !e.getServiceId().equals(Constants.GATE_WAY_SERVER) && finalServiceInstanceList.contains(e.getServiceId())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(routeList)) {
                    for (GatewayRoute gatewayRoute : routeList) {
                        List<ServiceInstance> serviceInstances = nacosDiscoveryClient.getInstances(gatewayRoute.getServiceId());
                        serviceInstances = serviceInstances.stream().filter(e -> MapUtils.getString(e.getMetadata(), Constants.NACOS_HEALTHY_KEY).equals("true")).collect(Collectors.toList());
                        if (!CollectionUtils.isEmpty(serviceInstances)) {
                            resources.add(swaggerResource(gatewayRoute.getServiceId(), gatewayRoute.getPath().replace("**", API_URI)));
                        }
                    }
                }
            }
        }
//        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
//                .forEach(route -> {
//                    route.getPredicates().stream()
//                            .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                            .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
//                                    predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                                            .replace("**", API_URI))));
//                });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
