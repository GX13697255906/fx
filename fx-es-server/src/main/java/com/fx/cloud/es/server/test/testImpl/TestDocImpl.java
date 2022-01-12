package com.fx.cloud.es.server.test.testImpl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.fx.cloud.es.server.configuration.ElasticConfig;
import com.fx.cloud.es.server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author Administrator
 */
@Slf4j
public class TestDocImpl {

    public static void main(String[] args) {
        delBatchDoc();
//        insertBatchDoc();
//        delDoc();
//        getDoc();
//        updateIndex();
//        insertIndex();
//        delIndex();
//        getIndex();
//        searchIndex();
//        createIndex();
    }


    public static void delBatchDoc() {
        ElasticConfig config = new ElasticConfig();
        RestHighLevelClient client = config.restHighLevelClient;
        BulkRequest bulkRequest = new BulkRequest();
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index("user").id("6");
        bulkRequest.add(deleteRequest);
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("批量删除操作 响应信息 : result = {}", JSONObject.toJSONString(bulkResponse));
        config.closeClient();
    }

    public static void delDoc() {
        ElasticConfig config = new ElasticConfig();
        RestHighLevelClient client = config.restHighLevelClient;
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.index("user").id("6");
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("GetRequest 响应信息 ： result = {}", JSONObject.toJSONString(deleteResponse));
        config.closeClient();
    }

    public static void insertBatchDoc() {
        ElasticConfig config = new ElasticConfig();
        RestHighLevelClient client = config.restHighLevelClient;
        BulkRequest bulkRequest = new BulkRequest("user");
        User user = new User();
        user.setName("zemao.zhu");
        user.setAge(24);
        user.setTel("15801829606");
        user.setSex("男");
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("6");
        indexRequest.source(JSONObject.toJSONString(user), XContentType.JSON);
        bulkRequest.add(indexRequest);
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("批量插入操作 响应信息 : result = {}", JSONObject.toJSONString(bulkResponse));
        config.closeClient();
    }


    public static void insertDoc() {
        ElasticConfig client = new ElasticConfig();
        RestHighLevelClient restHighLevelClient = client.restHighLevelClient;
        IndexRequest indexRequest = new IndexRequest("user").id("6");
        User user = new User();
        user.setName("ben.liu");
        user.setAge(24);
        user.setTel("15801829606");
        user.setSex("男");
        indexRequest.source(JSONObject.toJSONString(user), XContentType.JSON);
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("插入操作的响应信息 ： result  = {}", JSONObject.toJSONString(indexResponse));
        client.closeClient();
    }

    public static void updateDoc() {
        ElasticConfig client = new ElasticConfig();
        RestHighLevelClient restHighLevelClient = client.restHighLevelClient;

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("6");
        User user = new User();
        user.setName("zhuze.mao");
        user.setAge(24);
        user.setTel("15801829606");
        user.setSex("男");
        updateRequest.doc(BeanUtil.beanToMap(user), XContentType.JSON);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("更新操作响应信息： result = {}", JSONObject.toJSONString(updateResponse));
        client.closeClient();
//        restHighLevelClient.up
    }


    public static void getDoc() {
        ElasticConfig config = new ElasticConfig();
        RestHighLevelClient client = config.restHighLevelClient;
        GetRequest getRequest = new GetRequest();
        getRequest.index("user").id("6");
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("GetRequest 响应信息 ： result = {}", JSONObject.toJSONString(getResponse));
        config.closeClient();
    }

}
