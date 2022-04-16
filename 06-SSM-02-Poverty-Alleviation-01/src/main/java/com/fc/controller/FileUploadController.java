package com.fc.controller;

import com.fc.service.FileUpLoadService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private FileUpLoadService fileUpLoadService;

    @PostMapping("uploadImg")
    public ResultVo FileUpLoad(MultipartFile file, @RequestParam String type){
        return fileUpLoadService.FileUpLoad(file, type);
    }
}
