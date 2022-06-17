package com.fc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Student;
import com.fc.service.StudentService;
import com.fc.service.impl.StudentServiceImpl;
import com.sun.xml.internal.org.jvnet.fastinfoset.sax.FastInfosetReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    void q() {
        BaseMapper<Student> baseMapper = studentService.getBaseMapper();

        System.out.println(baseMapper);

        Map<String, Object> map = new HashMap<>();

        List<Student> students = baseMapper.selectByMap(map);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void w() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "122");
        studentService.listByMap(map);
    }

    @Test
    void e() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Student> students = studentService.listByIds(set);
        System.out.println(students);
    }

    @Test
    void r() {
        List<Map<String, Object>> maps = studentService.listMaps(null);

        System.out.println(maps);
    }

    @Test
    void t() {
        Student student = new Student();
        studentService.save(student);
    }

    @Test
    void Y() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("yuyu");
        student1.setAge(1);
        student1.setGender("");
        student1.setInfo("21214");

        students.add(student1);

        Student student2 = new Student();
        student2.setName("12124");
        student2.setAge(1);
        student2.setGender("");
        student2.setInfo("800");

        students.add(student2);

        boolean b = studentService.saveBatch(students);

        System.out.println("是否添加成功：" + b);
    }

    @Test
    void I() {
        Student student2 = new Student();
        student2.setName("12124");
        student2.setAge(1);
        student2.setGender("");
        student2.setInfo("800");

        studentService.saveOrUpdate(student2);
    }

    @Test
    void O() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("yuyu");
        student1.setAge(1);
        student1.setGender("");
        student1.setInfo("21214");

        students.add(student1);

        Student student2 = new Student();
        student2.setName("12124");
        student2.setAge(1);
        student2.setGender("");
        student2.setInfo("800");

        students.add(student2);

        studentService.saveOrUpdateBatch(students);
    }

    @Test
    void OP() {
        Student student2 = new Student();
        student2.setName("12124");
        student2.setAge(1);
        student2.setGender("");
        student2.setInfo("800");

        studentService.updateById(student2);
    }

//    @Test
//    void GetBaseMapper() {
//        BaseMapper<Student> baseMapper = studentService.getBaseMapper();
//
//        System.out.println(baseMapper);
//
//        Map<String, Object> map = new HashMap<>();
//
//        List<Student> students = baseMapper.selectByMap(map);
//
//        for (Student student : students) {
//            System.out.println(student);
//        }
//    }
//
//    //查询全部
//    @Test
//    void List() {
//        List<Student> list = studentService.list();
//
//        for (Student student : list) {
//            System.out.println(list);
//        }
//    }
//
//    //根据Id多查
//    @Test
//    void ListByIds() {
//        List<Student> students = studentService.listByIds(Arrays.asList(5, 6, 7, 8));
//
//        System.out.println(students);
//    }
//
//    //查询
//    @Test
//    void ListMaps() {
//        List<Map<String, Object>> maps = studentService.listMaps(null);
//
//        for (Map<String, Object> map : maps) {
//            System.out.println(map);
//        }
//    }
//
//    //条件查询
//    @Test
//    void ListByMap() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "iiiii");
//        map.put("id", 7);
//
//        List<Student> students = studentService.listByMap(map);
//
//        System.out.println(students);
//    }
//
//    //插入
//    @Test
//    void Save() {
//        Student student = new Student();
//        student.setName("dasdasdad");
//        student.setAge(90);
//        student.setGender("");
//        student.setInfo("55555555");
//        student.setId(10);
//
//        boolean saved = studentService.save(student);
//
//        System.out.println("判断是否添加成功:" + saved);
//    }
//
//    //批量插入
//    @Test
//    void SaveBatch() {
//        Set<Student> students = new HashSet<>();
//
//        Student student1 = new Student();
//        student1.setName("hhhhhhh");
//        student1.setAge(7);
//        student1.setGender("");
//        student1.setInfo("23333333");
//
//        students.add(student1);
//
//        Student student2 = new Student();
//        student2.setName("wwwwwww");
//        student2.setAge(6);
//        student2.setGender("");
//        student2.setInfo("43434343434");
//
//        students.add(student2);
//
//        boolean b = studentService.saveBatch(students);
//
//        System.out.println("是否添加成功:" + b);
//    }
//
//    //传id修改，没传id插入
//    @Test
//    void SaveOrUpdate() {
//        Student student = new Student();
//        student.setName("iiiiiiiii");
//        student.setAge(2);
//        student.setGender("");
//        student.setInfo("676767676767");
//        student.setId(-64942079);
//
//        studentService.saveOrUpdate(student);
//    }
//
//    //批量传id修改，没传id插入
//    @Test
//    void SaveOrUpdateBatch() {
//        HashSet<Student> students = new HashSet<>();
//
//        Student student1 = new Student();
//        student1.setName("oooooo");
//        student1.setAge(66);
//        student1.setGender("");
//        student1.setInfo("88888");
//
//        students.add(student1);
//
//        Student student2 = new Student();
//        student2.setName("ffffff");
//        student2.setAge(54);
//        student2.setGender("");
//        student2.setInfo("fake");
//        student2.setId(10);
//
//        students.add(student2);
//
//        boolean b = studentService.saveOrUpdateBatch(students);
//
//        System.out.println("是否:" + b);
//    }
//
//    //条件删除
//    @Test
//    void RemoveByMap() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "xxxx");
//        map.put("id", 1);
//        boolean remove = studentService.removeByMap(map);
//
//        System.out.println("是否被删除:" + remove);
//    }
//
//    //根据id删除
//    @Test
//    void RemoveById() {
//        Student student = new Student();
//        student.setId(2);
//
//        boolean remove = studentService.removeById(student);
//
//        System.out.println("是否被删除" + remove);
//    }
//
//    // 根据id多删
//    // DELETE FROM student WHERE id IN ( ? , ? , ? )
//    @Test
//    void RemoveByIds() {
//        boolean remove = studentService.removeByIds(Arrays.asList(1, 2, 3));
//
//        System.out.println("是否被删除:" + remove);
//    }
//
//    // 根据id多删
//    // DELETE FROM student WHERE id=?
//    @Test
//    void R () {
//        boolean b = studentService.removeBatchByIds(Arrays.asList(1, 2, 3, 4));
//
//        System.out.println("是否被删除:" + b);
//    }
//


}
