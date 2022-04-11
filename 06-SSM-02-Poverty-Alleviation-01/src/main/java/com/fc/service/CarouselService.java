package com.fc.service;

import com.fc.entity.Carousel;
import com.fc.entity.User;
import com.fc.vo.ResultVo;

public interface CarouselService {
    ResultVo getList(Integer pageNum, Integer pageSize, Integer id);

    ResultVo add(Carousel carousel);

    ResultVo update(Carousel carousel);

    ResultVo del(Integer id);

    ResultVo changeState(Integer id);
}
