package com.learn.security.controller;

import com.learn.security.entity.User;
import com.learn.security.repository.UserRepository;
import com.learn.security.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //1.Register user in DB
    @PostMapping("/register")
    public User register(@RequestBody User user){
      //return userRepository.save(user);
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
      return userService.verify(user);
      // if(!Objects.isNull(u))
      //      return u;
        // else
       //    return "Failure";
    }
}
