package com.itmuch.cloud.microservicesimpleconsumermovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 调用方式: http://localhost:8010/user/{id}
 * */
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class MicroserviceSimpleConsumerMovieApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimpleConsumerMovieApplication.class, args);
	}
}
