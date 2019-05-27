package com.filip.employeeapplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//public class SwaggerDocumentationConfiguration {
public class SwaggerDocumentationConfiguration implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry
//                .addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }




    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee REST CRUD operations API in Spring-Boot 2")
                .description(
                        "Sample REST API for centalized documentation using Spring Boot and spring-fox swagger 2 ")
                .termsOfServiceUrl("")
                .version("0.0.1-SNAPSHOT")
                .contact(
                        new Contact(
                                "Filip Vanden Eynde",
                                "https://github.com/filipve1994",
                                "https://github.com/filipve1994")).build();
    }

    @Bean
    public Docket configureControllerPackageAndConvertors() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                 .apis(RequestHandlerSelectors.basePackage("com.filip.employeeapplication.web.rest.resource"))
//                .apis(RequestHandlerSelectors.basePackage("com.filip.employeeapplication"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


}