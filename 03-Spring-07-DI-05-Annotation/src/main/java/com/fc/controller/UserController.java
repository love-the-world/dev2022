package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public List<User> findAll(){
        return userService.findAll();

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
