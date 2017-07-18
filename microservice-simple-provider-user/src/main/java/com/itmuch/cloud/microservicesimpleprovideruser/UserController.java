package com.itmuch.cloud.microservicesimpleprovideruser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by admin on 2017-07-18.
 */
@RestController
public class UserController {

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
//        User user = this.userRepository.findOne(id);
//        return user;

        // 这里没有采用数据库，直接新建一个对象赋值处理了
        User user = new User();
        switch (id.intValue()) {
            case 1:
                user.setName("LinJK");
                user.setBalance(BigDecimal.valueOf(111.11));
                user.setAge(18);
                user.setId(new Long(1));
                user.setUsername("Lin JingKe");
                break;
            case 2:
                user.setName("ZhangS");
                user.setBalance(BigDecimal.valueOf(222.22));
                user.setAge(22);
                user.setId(new Long(2));
                user.setUsername("Zhang San");
                break;
            default:
                user.setName("Default");
                user.setBalance(BigDecimal.valueOf(000.00));
                user.setAge(0);
                user.setId(new Long(0));
                user.setUsername("Default Value");
                break;
        }

        return user;
    }
}
