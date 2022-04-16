package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.entity.User;
import com.fc.service.CarouselService;
import com.fc.vo.DataVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Integer id) {
        // 返回给前端的结果
        ResultVo resultVo;

        // 分页相关的参数
        DataVo<Carousel> carouselDataVO;

        // 结果中data对应的用户数组
        List<Carousel> carousels;

        // 说明传递了id，那就是findById
        if (id != null) {
            carousels = new ArrayList<>();

            // 查询
            Carousel carousel = carouselMapper.selectByPrimaryKey(id);

            // 没有查到用户的情况
            if (carousel == null) {

                carouselDataVO = new DataVo<>(0L, carousels, pageNum, pageSize);

                resultVo = new ResultVo(4044, "查无此图!", false, carouselDataVO);
            } else {
                // 查到了用户扔到集合中
                carousels.add(carousel);

                carouselDataVO = new DataVo<>(1L, carousels, pageNum, pageSize);

                resultVo = new ResultVo(2000, "查到了该图!", true, carouselDataVO);
            }
        } else {
            // 开启分页
            PageHelper.startPage(pageNum, pageSize);

            carousels = carouselMapper.selectByExample(null);

            // 如果数据库是空的，一个人都没查到
            if (carousels.size() == 0) {
                carouselDataVO = new DataVo<>(0L, carousels, pageNum, pageSize);

                resultVo = new ResultVo(40404, "啥图都没!!!", false, carouselDataVO);
                // 查到了
            } else {
                // 封装pageInfo，为了获取总数据量
                PageInfo<Carousel> pageInfo = new PageInfo<>(carousels);

                carouselDataVO = new DataVo<>(pageInfo.getTotal(), carousels, pageNum, pageSize);

                resultVo = new ResultVo(2002, "轮播图查询成功！！！!", true, carouselDataVO);

            }
        }

        return resultVo;
    }

    @Override
    public ResultVo add(Carousel carousel) {
        ResultVo vo;

        //判断是否存在创建时间，没有就自己加上去
        if(carousel.getAvailable() == null){
            carousel.setAvailable(false);
        }

        int affectedRows = carouselMapper.insertSelective(carousel);

        if(affectedRows > 0){
            vo = new ResultVo(2000, "添加图片成功", true, carousel);
        }else{
            vo = new ResultVo(5000, "添加图片失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo update(Carousel carousel) {
        ResultVo vo;

        int affectedRows = carouselMapper.updateByPrimaryKeySelective(carousel);

        if(affectedRows > 0){
            //修改成功之后，再重新查询一次，保证返回前端拿到的是最新最全的数据
            carousel = carouselMapper.selectByPrimaryKey(carousel.getId());

            vo = new ResultVo(2000, "修改图片成功", true, carousel);
        }else{
            vo = new ResultVo(5000, "修改图片失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo del(Integer id) {
        int affectedRows = carouselMapper.deleteByPrimaryKey(id);

        ResultVo vo;

        if(affectedRows > 0){
            vo = new ResultVo(2000, "删除图片成功", true, null);
        }else{
            vo = new ResultVo(5000, "删除图片失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo changeState(Integer id) {
        Integer affectedRows = carouselMapper.changeStatus(id);

        ResultVo vo;

        if(affectedRows > 0){
            vo = new ResultVo(2000, "修改了当前状态成功", true, null);
        }else{
            vo = new ResultVo(5000, "修改了当前状态失败", false, null);
        }

        return vo;

//        Carousel carousel = carouselMapper.selectByPrimaryKey(id);
//
//        carousel.setAvailable(!carousel.getAvailable());
//
//        carouselMapper.updateByPrimaryKeySelective(carousel);
    }

}