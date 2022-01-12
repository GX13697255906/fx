package com.fx.cloud.es.server.test.controller;

import com.fx.cloud.es.server.test.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author xun.guo
 */
@Api(value = "Person控制类", tags = "Person控制类")
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "多条件查询", notes = "多条件查询")
    @GetMapping(value = "/querySearch")
    public SearchResponse querySearch(String fieldName, String value) {
        return personService.querySearch(fieldName, value);
    }


    @ApiOperation(value = "根据Id查询", notes = "根据Id查询")
    @GetMapping(value = "/search")
    public GetResponse search() throws IOException {
        return personService.search();
    }

    @ApiOperation(value = "创建索引", notes = "创建索引")
    @GetMapping(value = "/createIndex")
    public CreateIndexResponse createIndex() {
        return personService.createIndex();
    }

    @ApiOperation(value = "插入数据", notes = "插入数据")
    @GetMapping(value = "/insertIndex")
    public IndexResponse insertIndex() throws IOException {
        return personService.insertIndex();
    }

    @ApiOperation(value = "分词器测试", notes = "分词器测试")
    @GetMapping(value = "/analysisTemplate")
    public AnalyzeResponse analysisTemplate(String text, String analyzer) throws IOException {
        return personService.analysisTemplate(text, analyzer);
    }

}
