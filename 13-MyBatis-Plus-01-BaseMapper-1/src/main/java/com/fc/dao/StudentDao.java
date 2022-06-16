package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends BaseMapper<Student> {
    //根据年龄进行排序降序
    List<Student> findByAgeOnDescOrder();

}
