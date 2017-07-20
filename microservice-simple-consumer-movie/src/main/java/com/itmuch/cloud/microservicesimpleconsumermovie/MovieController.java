package com.itmuch.cloud.microservicesimpleconsumermovie;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by admin on 2017-07-18.
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.userFeignClient = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "ljk121"))
                .target(UserFeignClient.class, "http://microservice-simple-provider-user/");

        this.adminFeignClient = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "ljk121121"))
                .target(UserFeignClient.class, "http://microservice-simple-provider-user/");
    }

    @HystrixCommand(fallbackMethod = "findByIdUserFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10"),
    })
    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    public User findByIdUserFallback(Long id) {
        return getErrorUser();
    }

    @HystrixCommand(fallbackMethod = "findByIdAdminFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10"),
    })
    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return this.adminFeignClient.findById(id);
    }

    public User findByIdAdminFallback(Long id) {
        return getErrorUser();
    }

    private User getErrorUser() {
        User user = new User();
        user.setId(-1L);
        user.setUsername("Error");
        user.setName("Error");
        user.setAge(-1);
        user.setBalance(BigDecimal.valueOf(-1L));

        return user;
    }
}
