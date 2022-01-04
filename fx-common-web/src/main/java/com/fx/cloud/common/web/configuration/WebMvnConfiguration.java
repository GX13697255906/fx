//package com.fx.cloud.common.web.configuration;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author xun.guo
// */
//@Slf4j
//@Configuration
//public class WebMvnConfiguration implements WebMvcConfigurer {
//
//    /**
//     * 多个WebSecurityConfigurerAdapter
//     */
//    @Configuration
//    @Order(101)
//    public static class ApiWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        public void configure(WebSecurity web) throws Exception {
////            处理swagger白名单接口
//            web.ignoring().antMatchers(
//                    "/error",
//                    "/static/**",
//                    "/v2/api-docs/**",
//                    "/swagger-resources/**",
//                    "/webjars/**",
//                    "/favicon.ico");
//        }
//    }
//
//    /**
//     * 资源处理器
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html", "doc.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//}
