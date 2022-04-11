package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
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
public class AlleviationServiceImpl implements AlleviationService {
    @Autowired
    private AlleviationMapper alleviationMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        //返回给前端的结果
        ResultVo resultVo;

        //分页相关的参数
        DataVo<Alleviation> dataVo;

        //结果中data对应的用户数组
        List<Alleviation> alleviations;

        //说明传递了id，那就是findById
        if(id != null){
            alleviations = new ArrayList<>();

            Alleviation alleviation = alleviationMapper.selectByPrimaryKey(id);

            if(alleviation == null){

                dataVo = new DataVo<>(0L, alleviations, pageNum, pageSize);

                resultVo = new ResultVo(4044, "查无此政策", false, dataVo);
            }else{
                //如果是查询单个，点击量应该加1
                click(alleviation.getId(), null);

                //更新点击的次数
                alleviation.setClickNum(alleviation.getClickNum()+1);

                //查到了用户之后，扔到集合中
                alleviations.add(alleviation);

                dataVo = new DataVo<>(1L, alleviations, pageNum, pageSize);

                resultVo = new ResultVo(2000, "查询政策成功", true, dataVo);
            }
        }else{
            PageHelper.startPage(pageNum, pageSize);

            alleviations = alleviationMapper.selectByExample(null);

            //如果数据库是空的，一个人没查到
            if(alleviations.size() == 0){
                dataVo = new DataVo<>(0L, alleviations, pageNum, pageSize);

                resultVo = new ResultVo(40404, "啥也没有", false, dataVo);
            }else{
                //封装pageInfo，为了获取总的数据量
                PageInfo<Alleviation> pageInfo = new PageInfo<>(alleviations);

                dataVo = new DataVo<>(pageInfo.getTotal(), alleviations, pageNum, pageSize);

                resultVo = new ResultVo(2002, "政策查询成功", true, dataVo);
            }


        }

        return resultVo;
    }

    @Override
    public ResultVo update(Alleviation alleviation) {
        ResultVo vo;

        int affectedRows = alleviationMapper.updateByPrimaryKeySelective(alleviation);

        if(affectedRows > 0){
            //修改成功之后，再重新查询一次，保证返回前端拿到的是最新最全的数据
            alleviation = alleviationMapper.selectByPrimaryKey(alleviation.getId());

            vo = new ResultVo(2000, "修改政策成功", true, alleviation);
        }else{
            vo = new ResultVo(5000, "修改政策失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo del(Long id) {
        int affectedRows = alleviationMapper.deleteByPrimaryKey(id);

        ResultVo vo;

        if(affectedRows > 0){
            vo = new ResultVo(2000, "删除政策成功", true, null);
        }else{
            vo = new ResultVo(5000, "删除政策失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo add(Alleviation alleviation) {
        ResultVo vo;

        //判断是否存在创建时间，没有就自己加上去
        if(alleviation.getCreateTime() == null){
            alleviation.setCreateTime(new Date());
        }

        int affectedRows = alleviationMapper.insertSelective(alleviation);

        if(affectedRows > 0){
            vo = new ResultVo(2000, "添加政策成功", true, alleviation);
        }else{
            vo = new ResultVo(5000, "添加政策失败", false, null);
        }


        return vo;
    }
    @Override
    public ResultVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = alleviationMapper.click(id, lastClickTime);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(1000, "播放量加1成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "播放量加1失败！！", false, null);
        }

        return vo;
    }
}
