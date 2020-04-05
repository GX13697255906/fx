package com.gx.fm.config.datasources;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建动态数据源上下文
 * 动态数据源上下文管理，切换
 */
@Slf4j
public class DataSourceContextHolder {

    // 存放当前线程使用的数据源类型
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    static String dataSourceType = null;

    //设置数据源
    public static void setDataSource(String type){
        log.info("DataSourceContextHolder.ThreadLocal 切换至数据源{}",type);
        dataSourceType = type;
        contextHolder.set(type);
    }
    //获取数据源
    public static String getDataSource(){
        log.info("DataSourceContextHolder.ThreadLocal 获取数据源{}", dataSourceType);
        return contextHolder.get();
    }
    //清楚数据源
    public static void clearDataSource(){
        log.info("DataSourceContextHolder.ThreadLocal 移除数据源{}",dataSourceType);
        dataSourceType = null;
        contextHolder.remove();
    }

}
