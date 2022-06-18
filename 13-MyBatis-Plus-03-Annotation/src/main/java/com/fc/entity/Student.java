package com.fc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(keepGlobalPrefix = false)
public class Student {

    private Integer id;
    @TableField(value = "name")
    private String password;
    @TableField(select = false)
    private Integer age;
    private String gender;
    @TableField(exist = false)
    private String Info;
}
