package com.fc;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fc.dao.TbUserDao;
import com.fc.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;

//测试条件优先级
@SpringBootTest
public class WrapperPrecedenceTests {
    @Autowired
    private TbUserDao tbUserDao;

    //匿名内部类的写法
    @Test
    void TestAnno() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("password", "23333333333")
                .and(userUpdateWrapper -> {
                    userUpdateWrapper.gt("head", "1")
                        .or()
                        .isNull("mood");
                })
                .set("nick", "2B");

        int affectedRows = tbUserDao.update(null, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }

    //lambda表达式的写法
    @Test
    void Test2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("password", "23333333333")
                .and(c -> {
                    c.gt("head", "1")
                            .or()
                            .isNull("mood");
                })
                .set("nick", "2B");

        int affectedRows = tbUserDao.update(null, wrapper);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
    }

    @Test
    void Test() {
        // 姓名为11，并且nick大约10或者mood不为null
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

        wrapper.eq("username", "11")
                .and(c -> {
                    c.gt("nick", "10")
                            .or()
                            .isNotNull("mood");
                })
                .set("info", "class");

        tbUserDao.update(null, wrapper);

    }
}
