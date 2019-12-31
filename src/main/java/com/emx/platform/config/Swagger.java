package com.emx.platform.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emx.platform.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList<ApiKey>();
        apiKeyList.add(new ApiKey("Token", "Token", "header"));
        return apiKeyList;
    }
    
    private List<SecurityContext> securityContexts() {
    	List<SecurityContext> sclist= new ArrayList<SecurityContext>();
    	sclist.add(SecurityContext.builder()
         .securityReferences(defaultAuth())
         .forPaths(PathSelectors.regex("^(?!auth).*$"))
         .build());
        return sclist;
    }
    
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Token", authorizationScopes));
        return securityReferences;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ERP系统  后台接口文档")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
