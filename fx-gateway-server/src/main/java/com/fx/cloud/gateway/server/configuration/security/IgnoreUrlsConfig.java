package com.fx.cloud.gateway.server.configuration.security;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 网关白名单配置
 *
 * @author xun.guo
 */
@Data
@Component
public class IgnoreUrlsConfig {
    private List<String> urls = Lists.newArrayList(
            "/**/login/**",
            "/**/v2/api-docs",
            "/doc.html/**",
            "/error",
            "/static/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/favicon.ico");

    public IgnoreUrlsConfig() {
//        urls.add("/**");
    }

}
