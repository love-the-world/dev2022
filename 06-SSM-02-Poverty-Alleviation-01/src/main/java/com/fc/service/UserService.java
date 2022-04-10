package com.fc.service;

import com.fc.vo.ResultVo;

public interface UserService {
    ResultVo getList(Integer pageNum, Integer pageSize, Long id);
}
