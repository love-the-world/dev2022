package com.fc.controller;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("collection")
@CrossOrigin("*")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("getList")
    public ResultVo getList(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            Long id){
        return collectionService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody Collection collection){
        return collectionService.add(collection);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody Collection collection){
        return collectionService.update(collection);
    }

    @GetMapping("del")
    public ResultVo del(@RequestParam Long id){
        return collectionService.del(id);
    }
}
