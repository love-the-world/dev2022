package com.fc.controller;

import com.fc.service.UserService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getList")
    public ResultVo getList(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            Long id){
        return userService.getList(pageNum, pageSize, id);
    }
}
