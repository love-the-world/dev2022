package com.fc.service;

import com.fc.entity.Alleviation;
import com.fc.vo.ResultVo;

import java.util.Date;

public interface AlleviationService {
    ResultVo getList(Integer pageNum, Integer pageSize, Long id);

    ResultVo update(Alleviation alleviation);

    ResultVo del(Long id);

    ResultVo add(Alleviation alleviation);

    ResultVo click(Long id, Date lastClickTime);
}
