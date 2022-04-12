package com.fc.service.impl;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import com.fc.vo.DataVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        // 返回给前端的结果
        ResultVo resultVo;

        // 分页相关的参数
        DataVo<Collection> collectionDataVo;

        // 结果中data对应的用户数组
        List<Collection> collections;

        // 说明传递了id，那就是findById
        if (id != null) {
            collections = new ArrayList<>();

            // 查询
            Collection collection = collectionMapper.selectByPrimaryKey(id);

            // 没有查到用户的情况
            if (collection == null) {
                collectionDataVo = new DataVo<>(0L, collections, pageNum, pageSize);

                resultVo = new ResultVo(4044, "查无收集表数据!", false, collectionDataVo);
            } else {
                // 查到了用户扔到集合中
                collections.add(collection);

                collectionDataVo = new DataVo<>(1L, collections, pageNum, pageSize);

                resultVo = new ResultVo(2000, "查到了该收集表数据!", true, collectionDataVo);
            }
        } else {
            // 开启分页
            PageHelper.startPage(pageNum, pageSize);

            collections = collectionMapper.selectByExample(null);

            // 如果数据库是空的，一个人都没查到
            if (collections.size() == 0) {
                collectionDataVo = new DataVo<>(0L, collections, pageNum, pageSize);

                resultVo = new ResultVo(40404, "啥数据都没!!!", false, collectionDataVo);
                // 查到了
            } else {
                // 封装pageInfo，为了获取总数据量
                PageInfo<Collection> pageInfo = new PageInfo<>(collections);

                collectionDataVo = new DataVo<>(pageInfo.getTotal(), collections, pageNum, pageSize);

                resultVo = new ResultVo(2002, "收集表数据查询成功！！！!", true, collectionDataVo);

            }
        }

        return resultVo;
    }

    @Override
    public ResultVo add(Collection collection) {
        ResultVo vo;

//        //判断是否存在创建时间，没有就自己加上去
//        if(collection.getAvailable() == null){
//            collection.setAvailable(false);
//        }

        int affectedRows = collectionMapper.insertSelective(collection);

        if(affectedRows > 0){
            vo = new ResultVo(2000, "添加收集表数据成功", true, collection);
        }else{
            vo = new ResultVo(5000, "添加收集表数据失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo update(Collection collection) {
        ResultVo vo;

        int affectedRows = collectionMapper.updateByPrimaryKeySelective(collection);

        if(affectedRows > 0){
            //修改成功之后，再重新查询一次，保证返回前端拿到的是最新最全的数据
            collection = collectionMapper.selectByPrimaryKey(collection.getId());

            vo = new ResultVo(2000, "修改收集表数据成功", true, collection);
        }else{
            vo = new ResultVo(5000, "修改收集表数据失败", false, null);
        }

        return vo;
    }

    @Override
    public ResultVo del(Long id) {
        int affectedRows = collectionMapper.deleteByPrimaryKey(id);

        ResultVo vo;

        if(affectedRows > 0){
            vo = new ResultVo(2000, "删除收集表数据成功", true, null);
        }else{
            vo = new ResultVo(5000, "删除收集表数据失败", false, null);
        }

        return vo;
    }
}
