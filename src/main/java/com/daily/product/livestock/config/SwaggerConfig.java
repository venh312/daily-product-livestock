package com.daily.product.livestock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String API_NAME = "Daily Product 가축산물";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "실제 연동은 8000 포트를 통해 연동한다. (http://url:8000/endpoint)";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.daily.product"))  // Swagger를 적용할 클래스의 package명
            .paths(PathSelectors.any())  // 해당 package 하위에 있는 모든 url에 적용
            .build()
            .apiInfo(apiInfo())
            .securitySchemes(Collections.singletonList(apiKey()));
    }

    public ApiInfo apiInfo() {  // API의 이름, 현재 버전, API에 대한 정보
        return new ApiInfoBuilder()
            .title(API_NAME)
            .version(API_VERSION)
            .description(API_DESCRIPTION)
            .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("CLIENT-KEY", "Headers", "header");
    }
}
