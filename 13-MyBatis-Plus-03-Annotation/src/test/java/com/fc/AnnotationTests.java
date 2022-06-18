package com.fc;

import com.fc.dao.StudentDao;
import com.fc.dao.TbUserDao;
import com.fc.entity.Student;
import com.fc.entity.User;
import com.fc.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class AnnotationTests {
    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private StudentDao studentDao;

    @Test
    void Test() {
        List<Student> students = studentDao.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void contextLoads() {
        List<User> tbUsers = tbUserDao.selectList(null);

        for (User tbUser  : tbUsers) {
            System.out.println(tbUser);
        }
    }

    @Test
    void delete() {
        int affectedRows = tbUserDao.deleteById(1);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    //测试生成UUID
    @Test
    void TestUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);
    }



}
