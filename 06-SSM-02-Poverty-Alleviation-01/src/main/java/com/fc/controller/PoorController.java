package com.fc.controller;

import com.fc.entity.Poor;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("poor")
public class PoorController {
    @Autowired
    private PoorService poorService;

    @GetMapping("getlist")
    public ResultVo getlist(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            Long id){
        return poorService.getlist(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody PoorWithBLOBs poor){
        return  poorService.add(poor);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody PoorWithBLOBs poor){
        return poorService.update(poor);
    }

    @GetMapping("delete")
    public ResultVo delete(@RequestParam Long id){
        return poorService.delete(id);
    }

    @GetMapping("click")
    public ResultVo click(Poor poor){
        return poorService.click(poor.getId(), poor.getLastClickTime());
    }


}
