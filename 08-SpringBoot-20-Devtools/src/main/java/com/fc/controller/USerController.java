package com.fc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class USerController {
    @RequestMapping("get")
    public String get() {
        return "222";
    }
}