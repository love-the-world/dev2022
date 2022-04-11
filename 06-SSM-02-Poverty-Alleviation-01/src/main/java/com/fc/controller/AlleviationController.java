package com.fc.controller;

import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("alleviation")
public class AlleviationController {
    @Autowired
    private AlleviationService alleviationService;

    @GetMapping("getList")
    public ResultVo getList(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            Long id){
        return alleviationService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody Alleviation alleviation){
        return  alleviationService.add(alleviation);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody Alleviation alleviation){
        return alleviationService.update(alleviation);
    }

    @GetMapping("del")
    public ResultVo del(@RequestParam Long id){
        return alleviationService.del(id);
    }

    @PostMapping("click")
    public ResultVo click(Alleviation alleviation) {
        return alleviationService.click(alleviation.getId(), alleviation.getLastClickTime());
    }
}
