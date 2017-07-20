package com.itmuch.cloud.microservicesimpleconsumermovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2017-07-18.
 */
@RestController
public class MovieController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("microservice-discovery-eureka");
    }
}
