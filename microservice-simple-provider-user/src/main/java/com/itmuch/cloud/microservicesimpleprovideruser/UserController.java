package com.itmuch.cloud.microservicesimpleprovideruser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by admin on 2017-07-18.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Object pricipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (pricipal instanceof UserDetails) {
            UserDetails user = (UserDetails)pricipal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();

            for (GrantedAuthority c : collection) {
                UserController.LOGGER.info("当前用户{}，角色{}", user.getUsername(), c.getAuthority());
            }
        }
        else {
            // do other thing.
        }

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
