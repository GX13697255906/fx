package com.fx.cloud.es.server.repository;

import com.fx.cloud.es.server.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {


}
