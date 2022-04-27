package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("findById")
    public User findById(@RequestParam  Integer id) {
        return userService.findById(id);
    }

    @RequestMapping("insert")
    public Map<String, Object> insert(User user) {
        Map<String, Object> map = new HashMap<>();

        Integer affectedRows = userService.insert(user);

        if (affectedRows > 0) {
            map.put("code", 200);
            map.put("message", "添加成功");
            map.put("success", true);
            map.put("count", null);
        } else {
            map.put("code", -1000);
            map.put("message", "添加失败");
            map.put("success", false);
            map.put("count", null);
        }

        return map;
    }

    @RequestMapping("update")
    public Map<String, Object> update(User user) {
        Map<String, Object> map2 = new HashMap<>();

        Integer affectedRows = userService.update(user);

        if (affectedRows > 0) {
            map2.put("code", 200);
            map2.put("message", "修改成功");
            map2.put("success", true);
            map2.put("count", null);
        } else {
            map2.put("code", -1000);
            map2.put("message", "修改失败");
            map2.put("success", false);
            map2.put("count", null);
        }

        return map2;
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();

        Integer affectedRows = userService.delete(id);

        if (affectedRows > 0) {
            map.put("code", 200);
            map.put("message", "删除成功");
            map.put("success", true);
            map.put("count", null);
        } else {
            map.put("code", -1000);
            map.put("message", "删除失败");
            map.put("success", false);
            map.put("count", null);
        }

        return map;
    }
}
