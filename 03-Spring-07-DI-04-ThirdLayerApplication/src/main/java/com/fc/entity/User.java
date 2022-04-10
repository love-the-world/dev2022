package com.fc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//有参构造
@AllArgsConstructor
//无参构造
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;

    private String password;
}
