package com.fx.cloud.es.server.test.testImpl;

import com.alibaba.fastjson.JSONObject;
import com.fx.cloud.es.server.configuration.ElasticConfig;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @author Administrator
 */
@Slf4j
public class TestIndexImpl {

    public static void main(String[] args) {
//        updateIndex();
//        insertIndex();
//        delIndex();
//        getIndex();
//        searchIndex();
//        createIndex();
    }


    public static void delIndex() {
        ElasticConfig client = new ElasticConfig();
        RestHighLevelClient restHighLevelClient = client.restHighLevelClient;
        DeleteIndexRequest indexRequest = new DeleteIndexRequest();
        indexRequest.indices("user");
        AcknowledgedResponse deleteResponse = null;

        try {
            deleteResponse = restHighLevelClient.indices().delete(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("删除操作响应信息 ：result = {}", JSONObject.toJSONString(deleteResponse));

        client.closeClient();

    }


    public static void getIndex() {
        ElasticConfig client = new ElasticConfig();
        RestHighLevelClient restHighLevelClient = client.restHighLevelClient;
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = null;
        try {
            getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.closeClient();
        }
        log.info("响应信息: result = {}", JSONObject.toJSONString(getIndexResponse.getMappings()));

    }

    public static void searchDoc() {
        ElasticConfig client = new ElasticConfig();
        RestHighLevelClient restHighLevelClient = client.restHighLevelClient;
        MatchQueryBuilder queryBuilder = new MatchQueryBuilder("name", "guo");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
//        分页
        searchSourceBuilder.from(0).size(5);

//        排序
        searchSourceBuilder.sort("age", SortOrder.ASC);

//        全量查询
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = new SearchResponse();
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("查询结果： result = {}", JSONObject.toJSONString(searchResponse.getHits()));
        client.closeClient();

    }

    private static void createIndex() {
        ElasticConfig client = new ElasticConfig();
        RestHighLevelClient restHighLevelClient = client.restHighLevelClient;
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("test");
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("响应信息： createIndexResponse = {}", JSONObject.toJSONString(createIndexResponse));
//        restHighLevelClient.indices().createAsync(createIndexRequest, RequestOptions.DEFAULT, responseListener());
        client.closeClient();
    }

    private static ActionListener<CreateIndexResponse> responseListener() {
        ActionListener<CreateIndexResponse> responseActionListener = new ActionListener<CreateIndexResponse>() {

            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                log.info(createIndexResponse.toString());
            }

            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };
        return responseActionListener;
    }

}
