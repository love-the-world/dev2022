package com.fc.service;

import com.fc.entity.VolunteerRecruitment;
import com.fc.vo.ResultVo;

import java.util.Date;

public interface VolunteerService {
    ResultVo click(Long id, Date lastClickTime);

    ResultVo getList(Integer pageNum, Integer pageSize, Long id);

    ResultVo add(VolunteerRecruitment volunteer);

    ResultVo update(VolunteerRecruitment volunteer);

    ResultVo delete(Long id);
}
