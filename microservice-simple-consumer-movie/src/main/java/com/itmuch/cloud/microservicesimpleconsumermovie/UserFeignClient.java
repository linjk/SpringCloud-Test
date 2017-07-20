package com.itmuch.cloud.microservicesimpleconsumermovie;

import feign.Param;
import feign.RequestLine;

/**
 * Created by admin on 2017-07-20.
 */

public interface UserFeignClient {
    @RequestLine("GET /{id}")
    public User findById(@Param("id") Long id);
}
