package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserDao extends BaseMapper<User> {
}
