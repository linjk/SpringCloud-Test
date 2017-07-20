package com.itmuch.cloud.microservicesimpleconsumermovie;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by admin on 2017-07-20.
 */

@FeignClient(name = "microservice-simple-provider-user",
             configuration = FeignConfiguration.class)
public interface UserFeignClient {
    @RequestLine("GET /{id}")
    public User findById(@Param("id") Long id);
}
