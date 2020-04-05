package com.gx.fm.config.datasources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

//    private static Map<Object,Object> dataSourceMap = new HashMap<>();
//    private static DynamicDataSource instance;
//    private static byte[] lock = new byte[0];
//
//    // 单例模式，保证获取到都是同一个对象，
//    public static synchronized DynamicDataSource getInstance(){
//        if(instance==null){
//            synchronized (lock){
//                if(instance==null){
//                    instance=new DynamicDataSource();
//                }
//            }
//        }
//        return instance;
//    }
//
//    @Override
//    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
//        super.setTargetDataSources(targetDataSources);
//        dataSourceMap.putAll(targetDataSources);
//        super.afterPropertiesSet();
//    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
//
//    public Map<Object,Object> getDataSourceMap(){
//        return dataSourceMap;
//    }
}
