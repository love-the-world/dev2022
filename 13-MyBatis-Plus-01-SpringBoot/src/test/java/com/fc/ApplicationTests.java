package com.fc;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StudentDao studentDao;

    @Test
    void contextLoads() {
        studentDao.selectCount(null);
    }

}
