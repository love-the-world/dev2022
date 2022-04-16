package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.Poor;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
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
public class PoorServiceImpl implements PoorService {
    @Autowired
    private PoorMapper poorMapper;

    @Override
    public ResultVo getlist(Integer pageNum, Integer pageSize, Long id) {
        //返回给前端的结果
        ResultVo resultVo;

        //分页相关的参数
        DataVo<Poor> poorDataVo;

        //结果中data对应的用户数组
        List<Poor> poors;

        //说明传递了id，那就是findById
        if(id != null){
            poors = new ArrayList<>();

            Poor poor = poorMapper.selectByPrimaryKey(id);

            if(poor == null){

                poorDataVo = new DataVo<>(0L, poors, pageNum, pageSize);

                resultVo = new ResultVo(4044, "查无此贫困户", false, poorDataVo);
            }else{
                //查到了用户之后，扔到集合中
                poors.add(poor);

                poorDataVo = new DataVo<>(1L, poors, pageNum, pageSize);

                resultVo = new ResultVo(2000, "查询贫困户成功", true, poorDataVo);
            }
        }else{
            PageHelper.startPage(pageNum, pageSize);

            poors = poorMapper.selectByExample(null);

            //如果数据库是空的，一个人没查到
            if(poors.size() == 0){
                poorDataVo = new DataVo<>(0L, poors, pageNum, pageSize);

                resultVo = new ResultVo(40404, "啥也没有", false, poorDataVo);
            }else{
                //封装pageInfo，为了获取总的数据量
                PageInfo<Poor> pageInfo = new PageInfo<>(poors);

                poorDataVo = new DataVo<>(pageInfo.getTotal(), poors, pageNum, pageSize);

                resultVo = new ResultVo(2002, "贫困户查询成功", true, poorDataVo);
            }


        }

        return resultVo;
    }

    public ResultVo add(PoorWithBLOBs poor) {
        ResultVo resultVo;

        //判断是否存在创建时间，没有就自己加上去
        if(poor.getCreateTime() == null){
            poor.setCreateTime(new Date());
        }

        int affectedRows = poorMapper.updateByPrimaryKeySelective(poor);

        if(affectedRows > 0){
            resultVo = new ResultVo(2000, "添加贫困户成功", true, poor);
        }else{
            resultVo = new ResultVo(5000, "添加贫困户失败", false, null);
        }


        return resultVo;
    }

    public ResultVo update(PoorWithBLOBs poor) {
        ResultVo vo;

        int affectedRows = poorMapper.updateByPrimaryKeySelective(poor);

        if(affectedRows > 0){
            //修改成功之后，再重新查询一次，保证返回前端拿到的是最新最全的数据
            poor = poorMapper.selectByPrimaryKey(poor.getId());

            vo = new ResultVo(2000, "修改贫困户成功", true, poor);
        }else{
            vo = new ResultVo(5000, "修改贫困户失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        int affectedRows = poorMapper.deleteByPrimaryKey(id);

        ResultVo vo;

        if(affectedRows > 0){
            vo = new ResultVo(2000, "删除贫困户成功", true, null);
        }else{
            vo = new ResultVo(5000, "删除贫困户失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = poorMapper.click(id, lastClickTime);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(1000, "播放量加1成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "播放量加1失败！！", false, null);
        }

        return vo;
    }
}
