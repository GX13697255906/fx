package com.fx.cloud.es.server.service;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author xun.guo
 */
public interface PersonService {

    /**
     * 创建索引
     *
     * @return CreateIndexResponse
     */
    CreateIndexResponse createIndex();

    /**
     * 添加索引
     *
     * @return
     * @throws IOException
     */
    IndexResponse insertIndex() throws IOException;

    /**
     * 简单查询
     *
     * @return
     * @throws IOException
     */
    GetResponse search() throws IOException;

    /**
     * 分词器测试
     * @param text
     * @param analyzer
     * @return
     * @throws IOException
     */
    AnalyzeResponse analysisTemplate(String text, String analyzer) throws IOException;


    /**
     * 条件查询
     *
     * @param fieldName
     * @param value
     * @return SearchResponse
     */
    SearchResponse querySearch(String fieldName, String value);


}
