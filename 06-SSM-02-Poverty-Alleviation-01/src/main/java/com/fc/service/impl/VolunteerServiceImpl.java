package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
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
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRecruitmentMapper volunteerMapper;

    @Override
    public ResultVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = volunteerMapper.click(id, lastClickTime);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(1000, "扶贫志愿者招聘浏览量加1成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "扶贫志愿者招聘浏览量加1失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        // 返回给前端的结果
        ResultVo resultVo;

        // 分页相关的参数
        DataVo<VolunteerRecruitment> dataVo;

        // 结果中data对应的用户数组
        List<VolunteerRecruitment> volunteers;

        // 说明传递了id，那就是findById
        if (id != null) {
            volunteers = new ArrayList<>();

            // 查询
            VolunteerRecruitment volunteer = volunteerMapper.selectByPrimaryKey(id);

            // 没有查到用户的情况
            if (volunteer == null) {
                dataVo = new DataVo<>(0L, volunteers, pageNum, pageSize);

                resultVo = new ResultVo(4000, "没有这条志愿者招聘信息!", false, dataVo);
            } else {
                // 如果是查询单个，那么点击量应该加1
                click(volunteer.getId(), null);

                // 更新点击的次数
                volunteer.setClickNum(volunteer.getClickNum() + 1);

                // 查到了用户扔到集合中
                volunteers.add(volunteer);

                dataVo = new DataVo<>(1L, volunteers, pageNum, pageSize);

                resultVo = new ResultVo(1000, "查到了该招聘信息!", true, dataVo);
            }
        } else {
            // 开启分页
            PageHelper.startPage(pageNum, pageSize);

            volunteers = volunteerMapper.selectByExampleWithBLOBs(null);

            // 如果数据库是空的，一个人都没查到
            if (volunteers.size() == 0) {
                dataVo = new DataVo<>(0L, volunteers, pageNum, pageSize);

                resultVo = new ResultVo(4100, "没有招聘信息!!!", false, dataVo);
                // 查到了
            } else {
                // 封装pageInfo，为了获取总数据量
                PageInfo<VolunteerRecruitment> pageInfo = new PageInfo<>(volunteers);

                dataVo = new DataVo<>(pageInfo.getTotal(), volunteers, pageNum, pageSize);

                resultVo = new ResultVo(1100, "志愿者招聘信息查询成功！！！!", true, dataVo);

            }
        }

        return resultVo;
    }

    @Override
    public ResultVo add(VolunteerRecruitment volunteer) {
        ResultVo vo;
        // 判断是否存在创建时间，没有就自己加上
        if (volunteer.getCreateTime() == null) {
            volunteer.setCreateTime(new Date());
        }

        // 新创建的扶贫政策，点击量应该是0
        volunteer.setClickNum(0);
        volunteer.setLastClickTime(null);

        int affectedRows = volunteerMapper.insertSelective(volunteer);

        if (affectedRows > 0) {
            vo = new ResultVo(1000, "添加志愿者招聘信息成功！！", true, volunteer);
        } else {
            vo = new ResultVo(5000, "添加志愿者招聘信息失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo update(VolunteerRecruitment volunteer) {
        int affectedRows = volunteerMapper.updateByPrimaryKeySelective(volunteer);

        ResultVo vo;

        if (affectedRows > 0) {
            // 修改完成之后，再重新查询一次，保证返回给前端的是最新最全的数据
            volunteer = volunteerMapper.selectByPrimaryKey(volunteer.getId());

            vo = new ResultVo(1000, "修改志愿者招聘信息成功！！", true, volunteer);
        } else {
            vo = new ResultVo(5000, "修改志愿者招聘信息失败！！", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo delete(Long id) {
        int affectedRows = volunteerMapper.deleteByPrimaryKey(id);

        ResultVo vo;

        if (affectedRows > 0) {
            vo = new ResultVo(1000, "删除志愿者招聘信息成功！！", true, null);
        } else {
            vo = new ResultVo(5000, "删除志愿者招聘信息失败！！", false, null);
        }

        return vo;
    }
}
