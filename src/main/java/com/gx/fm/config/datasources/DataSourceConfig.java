package com.gx.fm.config.datasources;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean("fm")
    @ConfigurationProperties(value = "spring.datasource.fm")
    public DataSource fm_dataSource(){
        log.info("加载fm数据源");
        return DataSourceBuilder.create().build();
    }

    @Bean("taobao")
    @ConfigurationProperties(value = "spring.datasource.taobao")
    public DataSource taobao_dataSource(){
        log.info("加载taobao数据源");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @DependsOn({"fm","taobao"})
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(){
        Map<Object,Object> map = new HashMap<>();
        map.put(DataSourceType.DataSource_FM,fm_dataSource());
        map.put(DataSourceType.DataSource_TB,taobao_dataSource());
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(fm_dataSource());
        return dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
