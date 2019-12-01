package com.in.user.service;

import com.in.user.domain.response.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public User findById(long id){
        if(id>10) return null;
        User user = new User();
        user.setAge(1);
        user.setName("Sanjay Gupta");
        return user;
    }

    public void deleteUser(long id){
        logger.info("The user with id - [{}] has been deleted at {}",id, LocalDateTime.now());
    }
}
