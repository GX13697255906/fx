package com.fx.cloud.common.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fx.cloud.common.mybatis.entity.AbstractEntity;

import java.io.File;
import java.util.Collections;

/**
 * @author Administrator
 */
public class GeneratorCodeUtils {

    private final static String url = "jdbc:mysql://172.16.1.37:3306/dms_base?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";

    private final static String username = "dms";

    private final static String password = "dB2ZsBcgphJ6HSkBj1Ro3e";

    private final static String driverName = "com.mysql.jdbc.Driver";

    private final static String type = "mysql";

    private final static String author = "xun.guo";

    private final static String outputDir = "D://mybatisCode";

    public GeneratorCodeUtils() {
        File file = new File(outputDir);
        if (!file.exists()) {
            file.mkdir();
        }
    }


    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author)
                            .enableSwagger()
                            .fileOverride()
                            .outputDir(outputDir)
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("com.fx.cloud.gateway.server")
                            .serviceImpl("service.impl")
                            .mapper( "mapper")
                            .xml( "xml")
                            .entity("entity")
                            .service ("service")
                            .controller("controller")
                            .build();
                })
                .strategyConfig(builder -> {
                    builder.addInclude("gateway_route", "gateway_access_logs")
                            .addTablePrefix("fx_", "biz_")
                            .entityBuilder()
                            .superClass(AbstractEntity.class)
                            .enableLombok()
                            .controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .build()

                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }


}
