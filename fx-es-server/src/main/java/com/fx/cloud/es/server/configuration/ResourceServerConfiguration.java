package com.fx.cloud.es.server.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Administrator
 * oauth2 资源服务器配置
 */
@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    /**
     * Spring Security auth 的http 资源拦截和保护  WebSecurityConfigurerAdapter也有该功能的实现接口，但默认会被ResourceServerConfigurerAdapter覆盖，
     * 可以通过修改order的大小选择使用其中一个接口
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/**").permitAll();

    }
}
