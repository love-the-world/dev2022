package com.fc;

import com.fc.dao.StudentDao;
import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StudentDao studentDao;

    //根据id查询
    @Test
    void FindById() {
        Student student = studentDao.selectById(1);

        System.out.println(student);
    }

    //条件查询
    @Test
    void FindByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zch");
        map.put("age", 19);

        List<Student> students = studentDao.selectByMap(map);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    //条件查询全部
    @Test
    void FindBatchIds() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        List<Student> students = studentDao.selectBatchIds(list);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    //查询总数
    @Test
    void FindCount() {
        Long count = studentDao.selectCount(null);

        System.out.println(count);
    }

    //查询全部
    @Test
    void FindAll() {
        List<Student> students = studentDao.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    //插入
    @Test
    void Insert() {
        Student student = new Student();
        student.setName("fire");
        student.setAge(34);
        student.setGender("");
        student.setInfo("Boom");

        int affected = studentDao.insert(student);

        System.out.println(affected > 0 ? "添加成功" : "添加失败");
        System.out.println("插入学生的id为:" + student.getId());
    }

    //删除单个
    @Test
    void delete() {
        int affectedRows = studentDao.deleteById(-1230995455);

        System.out.println(affectedRows > 0 ? " 删除成功" : "删除失败");
    }

    //条件删除
    @Test
    void DeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Sunset");
        map.put("info", "castle");
        map.put("age", 1);

        int affectedRows = studentDao.deleteByMap(map);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    //多删
    @Test
    void DeleteBatchIds() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        int affectedRows = studentDao.deleteBatchIds(list);

        System.out.println(affectedRows > 0 ? "删除成功" : "删除失败");
    }

    //根据id修改
    @Test
    void Update() {
        Student student = new Student();
        student.setId(1);
        student.setName("jzy");
        int affectedRows = studentDao.updateById(student);

        System.out.println(affectedRows > 0 ? "修改成功" : "修改失败");
   }

   //自定义查询
    @Test
    void CustomSQL() {
        List<Student> list = studentDao.findByAgeOnDescOrder();

        for (Student student : list) {
            System.out.println(student);
        }
    }
}
