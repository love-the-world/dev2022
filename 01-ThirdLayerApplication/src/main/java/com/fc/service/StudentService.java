package com.fc.service;

import com.fc.bean.PageInfo;

//接口
public interface StudentService {
    PageInfo<Student> getPageInfo(int pageNo, int pageSize);
}
