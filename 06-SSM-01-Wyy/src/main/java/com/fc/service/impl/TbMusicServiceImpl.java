package com.fc.service.impl;

import com.fc.dao.TbMusicMapper;
import com.fc.entity.TbMusic;
import com.fc.entity.TbMusicExample;
import com.fc.service.TbMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbMusicServiceImpl implements TbMusicService {
    @Autowired
    private TbMusicMapper tbMusicMapper;

    @Override
    public List<TbMusic> findAll() {
        //无条件查全部
        return tbMusicMapper.selectByExample(null);
    }

    @Override
    public TbMusic findById(Integer musicId) {
        return tbMusicMapper.selectByPrimaryKey(musicId);
    }

    @Override
    public TbMusic nextSong(Integer musicId) {
        //获取最大Id
        Integer maxId = tbMusicMapper.findMaxId();

        if(musicId == maxId){
            musicId = 1;
        }else{
            musicId++;
        }



        return tbMusicMapper.selectByPrimaryKey(musicId);
    }

    @Override
    public TbMusic prevSong(Integer musicId) {
        Integer minId = tbMusicMapper.findMinId();

        if (musicId.equals(minId)){
            musicId = tbMusicMapper.findMaxId();
        }else {
            musicId--;
        }


        return tbMusicMapper.selectByPrimaryKey(musicId);
    }

    @Override
    public List<TbMusic> search(String keyword) {
        TbMusicExample musicExample = new TbMusicExample();

        TbMusicExample.Criteria criteria = musicExample.createCriteria();

        criteria.andMusicNameLike("%" + keyword + "%");

        return tbMusicMapper.selectByExample(musicExample);
    }

}
