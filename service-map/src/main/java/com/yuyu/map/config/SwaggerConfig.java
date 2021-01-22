package com.yuyu.map.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("毕业项目 API 文档")
                        .description("地理位置服务")
                        .version("1.0")
                        .contact(new Contact("孙翔宇","#","bmzsmd@gmail.com"))
                        .license("The Apache License")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuyu.map.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
