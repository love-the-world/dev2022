package com.fc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(keepGlobalPrefix = false)
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String Info;
}
