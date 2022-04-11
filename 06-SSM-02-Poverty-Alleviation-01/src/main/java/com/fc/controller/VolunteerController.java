package com.fc.controller;

import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("click")
    public ResultVo click(VolunteerRecruitment volunteer) {
        return volunteerService.click(volunteer.getId(), volunteer.getLastClickTime());
    }

    @GetMapping("getList")
    public ResultVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3")  Integer pageSize,
                            Long id) {
        return volunteerService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody VolunteerRecruitment volunteer) {
        return volunteerService.add(volunteer);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody VolunteerRecruitment volunteer){
        return volunteerService.update(volunteer);
    }

    @GetMapping("delete")
    public ResultVo delete(@RequestParam Long id) {
        return volunteerService.delete(id);
    }

}
