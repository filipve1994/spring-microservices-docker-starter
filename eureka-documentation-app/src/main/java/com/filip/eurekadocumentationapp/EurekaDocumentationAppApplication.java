package com.filip.eurekadocumentationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class EurekaDocumentationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDocumentationAppApplication.class, args);
	}

}
