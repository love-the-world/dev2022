package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.DataVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        //返回给前端的结果
        ResultVo resultVo;

        //分页相关的参数
        DataVo<User> userDataVo;

        //结果中data对应的用户数组
        List<User> users;

        //说明传递了id，那就是findById
        if(id != null){
            users = new ArrayList<>();

            User user = userMapper.selectByPrimaryKey(id);

            if(user == null){

                userDataVo = new DataVo<>(0L, users, pageNum, pageSize);

                resultVo = new ResultVo(4044, "查无此人", false, userDataVo);
            }else{
                //查到了用户之后，扔到集合中
                users.add(user);

                userDataVo = new DataVo<>(1L, users, pageNum, pageSize);

                resultVo = new ResultVo(2000, "查询成功", true, userDataVo);
            }
        }else{
            PageHelper.startPage(pageNum, pageSize);

            users = userMapper.selectByExample(null);

            //如果数据库是空的，一个人没查到
            if(users.size() == 0){
                userDataVo = new DataVo<>(0L, users, pageNum, pageSize);

                resultVo = new ResultVo(40404, "啥也没有", false, userDataVo);
            }else{
                //封装pageInfo，为了获取总的数据量
                PageInfo<User> pageInfo = new PageInfo<>(users);

                userDataVo = new DataVo<>(pageInfo.getTotal(), users, pageNum, pageSize);

                resultVo = new ResultVo(2002, "用户查询成功", true, userDataVo);
            }


        }

        return resultVo;
    }
}
