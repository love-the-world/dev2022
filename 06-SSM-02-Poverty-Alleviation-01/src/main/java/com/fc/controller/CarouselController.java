package com.fc.controller;

import com.fc.entity.Carousel;
import com.fc.entity.User;
import com.fc.service.CarouselService;
import com.fc.service.UserService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @GetMapping("getList")
    public ResultVo getList(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            Integer id){
        return carouselService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody Carousel carousel){
        return carouselService.add(carousel);
    }
    @PostMapping("update")
    public ResultVo update(@RequestBody Carousel carousel){
        return carouselService.update(carousel);
    }

    @GetMapping("del")
    public ResultVo del(@RequestParam Integer id){
        return carouselService.del(id);
    }

    @PostMapping("state")
    public ResultVo changeState(@RequestParam Integer id){
        return carouselService.changeState(id);
    }
}
