package com.itmuch.cloud.microservicesimpleprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 调用方式: http://localhost:8000/{id}
 * */
@EnableDiscoveryClient
@SpringBootApplication
/**
 * SoringBootApplication是如下三个注解的组合注解:
 * 	1. @Configuration
 * 	2. @EnableAutoConfiguration
 * 	3. @ComponentScan
 * */
public class MicroserviceSimpleProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimpleProviderUserApplication.class, args);
	}
}
