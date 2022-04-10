package com.fc.dao.impl;

import com.fc.dao.UserDao;
import com.fc.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        //连接数据库

        ArrayList<User> users = new ArrayList<>();

        users.add(new User(1, "黑虎阿福", "132456"));
        users.add(new User(2, "负心汉", "15456"));
        users.add(new User(3, "姜智赢", "666666"));
        return users;
    }
}
