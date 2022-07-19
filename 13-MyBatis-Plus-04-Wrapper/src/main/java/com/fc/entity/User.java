package com.fc.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//用于关联实体类和数据库的
//keepGlobalPrefix配置全局的前缀，默认值为false，只要我们添加了@TableName，就不会走我们的全局前缀
@TableName(value = "tb_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nick;
    private String head;
    private String mood;
    @TableLogic
    private Boolean available;
}
