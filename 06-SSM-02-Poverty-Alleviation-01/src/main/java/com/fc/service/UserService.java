package com.fc.service;

import com.fc.entity.User;
import com.fc.vo.ResultVo;

public interface UserService {
    ResultVo getlist(Integer pageNum, Integer pageSize, Long id);


    ResultVo add(User user);

    ResultVo update(User user);

    ResultVo delete(Long id);
}
