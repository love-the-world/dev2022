package com.fc.dao.impl;

import com.fc.dao.UserDao;
import com.fc.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMySQLImpl implements UserDao {
    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        users.add(new User(1, "海边宝宝", "132456"));
        users.add(new User(2, "派大星", "15456"));
        users.add(new User(3, "温蒂", "666666"));
        return users;
    }
}
