package com.fc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
//有参构造
@AllArgsConstructor
//无参构造
@NoArgsConstructor
//把当前类的对象放到容器中
@Component
public class User {
    private Integer id;
    private String name;

    private String password;
}
