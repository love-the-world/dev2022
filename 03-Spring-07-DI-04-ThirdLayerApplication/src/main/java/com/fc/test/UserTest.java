package com.fc.test;

import com.fc.controller.UserController;
import com.fc.dao.UserDao;
import com.fc.entity.User;
import com.fc.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    @Test
    public void test(){
//        UserController userController = new UserController();
//
//        List<User> list = UserController.findAll();
//
//        System.out.println(list);
    }

    @Test
    public void testContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = applicationContext.getBean(User.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);
        UserService userService = applicationContext.getBean(UserService.class);
        UserController userController = applicationContext.getBean(UserController.class);

        List<User> list = userController.findAll();

        System.out.println(list);

        System.out.println(user);
        System.out.println(userDao);
        System.out.println(userService);
        System.out.println(userController);


    }
}
