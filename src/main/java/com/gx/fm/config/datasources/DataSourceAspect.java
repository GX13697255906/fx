package com.gx.fm.config.datasources;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Before(value = "@annotation(ds)")
    public void beforeDataSource(DataSource ds) {
        String value = ds.value();
        System.out.println("value = "+value);
        DataSourceContextHolder.setDataSource(value);
        log.info("当前使用的数据源为：{}", value);
    }

    @After("@annotation(ds)")
    public void afterDataSource(DataSource ds){
        DataSourceContextHolder.clearDataSource();
    }

}
