package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("add")
    public ResultVo add(@RequestBody User user){
        return  userService.add(user);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("del")
    public ResultVo del(@RequestParam Long id){
        return userService.del(id);
    }
}
