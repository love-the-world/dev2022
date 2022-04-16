package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getlist")
    public ResultVo getlist(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            Long id){
        return userService.getlist(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody User user){
        return  userService.add(user);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("delete")
    public ResultVo delete(@RequestParam Long id){
        return userService.delete(id);
    }
}
