package com.fx.cloud.common.web.configuration;

import com.fx.cloud.common.model.FxSwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@EnableConfigurationProperties({FxSwaggerProperties.class})
@ConditionalOnProperty(prefix = "fx.cloud.swagger", name = "enabled", havingValue = "true")
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfiguration {

    private FxSwaggerProperties fxSwaggerProperties;

    public SwaggerConfiguration(FxSwaggerProperties fxSwaggerProperties) {
        this.fxSwaggerProperties = fxSwaggerProperties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes()).securityContexts(Lists.newArrayList(securityContext())).pathMapping("/");

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(e -> e.requestMappingPattern().matches("/.*"))
                .build();
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title(fxSwaggerProperties.getTitle())
                .description(fxSwaggerProperties.getDescription())
                .version(fxSwaggerProperties.getVersion())
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    private List<SecurityScheme> securitySchemes() {
        return Lists.newArrayList(
                new ApiKey("BearerToken", "Authorization", "header"));
    }

}
