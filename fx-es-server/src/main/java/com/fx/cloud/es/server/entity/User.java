package com.fx.cloud.es.server.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@Document(indexName = "user")
public class User implements Serializable {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String sex;

    @Field(type = FieldType.Keyword)
    private String tel;

    @Field(type = FieldType.Integer)
    private Integer age;


    public User() {
    }

}
