package com.fc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fc.dao.StudentDao;
import com.fc.dao.TbUserDao;
import com.fc.entity.Student;
import com.fc.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TbUserDao tbUserDao;

    @Test
    void InsertIn() {
        User user = new User();
        user.setId(6);
        user.setUsername("909090");
        user.setPassword("898989");

        int affectedRows = tbUserDao.insert(user);

        System.out.println(affectedRows > 0 ? "插入成功" : "插入失败");
    }

    @Test
    //UpdateWrapper可以通过.set方法来设置需要被修改的内容，不需要在传递一个实体类参数了
    //而且还支持null值
    void UpdateByUpdateWrapper() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

        wrapper.isNull("nick")
                .set("password", "23333333333");


        int affectedROws = tbUserDao.update(null, wrapper);

        System.out.println(affectedROws > 0 ? "修改成功" : "修改失败");
    }

    @Test
    void UpdateByQueryWrapper() {
        User user = new User();

        user.setPassword("6666");

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("username", "7777");

        //修改的内容和查询条件
        int affectedRows = tbUserDao.update(user, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }

    @Test
    void LogicDelete() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("username", "1222");

        int affectedRows = tbUserDao.delete(wrapper);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    @Test
    void UserSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();



        List<User> users = tbUserDao.selectList(wrapper);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void Insert() {
        Student student = new Student();
        student.setName("xiaoming");
        int affectedRows = studentDao.insert(student);
        System.out.println(affectedRows > 0 ? "添加成功" : "添加失败");
    }

    @Test
    void Select() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.between("age", "1", "700") //查询age为1~700之间的人
                .like("name", "i") //模糊查询
                .in("id", 8, 9, 10) //枚举查询，id为1， 2， 3的符合要求
                .isNotNull("age"); //age不为空
//                .isNull("age"); //为空g


        List<Student> students = studentDao.selectList(wrapper);

        System.out.println(students);
    }

    @Test
    void contextLoads() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        wrapper.eq("name", "fire") //name等为fire
                .gt("id", "10000") //id大于10000
                .orderByAsc("age") //根据id升序
                .groupBy("age") //根据age分组
                .having("age > 20") //分组过滤
                .orderByDesc("age") //根据id降序
                .lt("age", "70") //小于70
                .le("age", "70") //小于等于70
                .ge("age", "10") //大于等于10
                .gt("age", "10"); // 大于10

        List<Student> students = studentDao.selectList(wrapper);

        for (Student student : students) {
            System.out.println(student);
        }
    }

}
