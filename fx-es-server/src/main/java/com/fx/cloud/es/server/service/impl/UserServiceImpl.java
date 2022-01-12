package com.fx.cloud.es.server.service.impl;

import com.fx.cloud.es.server.entity.User;
import com.fx.cloud.es.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Override
    public void insertUserDoc() {
        User user = new User(7L, "teng.zheng", "男", "15801829607", 28);
        restTemplate.save(user);

    }
}
