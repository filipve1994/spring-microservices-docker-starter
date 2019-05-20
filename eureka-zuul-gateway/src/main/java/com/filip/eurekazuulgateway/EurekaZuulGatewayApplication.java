package com.filip.eurekazuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient    // It acts as a eureka client
@EnableZuulProxy        // Enable Zuul
@SpringBootApplication
public class EurekaZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaZuulGatewayApplication.class, args);
	}

}
