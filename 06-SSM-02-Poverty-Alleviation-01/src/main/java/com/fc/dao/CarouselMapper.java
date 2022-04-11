package com.fc.dao;

import com.fc.entity.Carousel;
import com.fc.entity.CarouselExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarouselMapper {
    //更改轮播图是否可用的状态
    Integer changeStatus(Integer id);

    long countByExample(CarouselExample example);

    int deleteByExample(CarouselExample example);

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    List<Carousel> selectByExample(Integer example);

    Carousel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Carousel record, @Param("example") CarouselExample example);

    int updateByExample(@Param("record") Carousel record, @Param("example") CarouselExample example);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
}