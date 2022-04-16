package com.fc.service.impl;

import com.fc.service.FileUpLoadService;
import com.fc.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class FileUpLoadServiceImpl implements FileUpLoadService {
    @Override
    public ResultVo FileUpLoad(MultipartFile file, String type) {
        String path = "D:\\apache-tomcat-8.5.37\\webapps\\upload\\Poverty-Alleviation\\" + type;

        File pathfile = new File(path);

        //如果文件夹路径不存在
        if(!pathfile.exists()){
            //创建多级路径
            pathfile.mkdirs();
        }

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //获取日期格式化器
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        //获取格式化之后的日期时间
        String formatDate = formatter.format(new Date());

        //获取文件名后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //新的文件名等于格式化之后的日期时间加上后缀
        originalFilename = formatDate + suffix;

        ResultVo resultVo = new ResultVo();



        try {
            //文件上传操作
            file.transferTo(new File(pathfile, originalFilename));

            //准备一个map用于存储图片的网络路径
            HashMap<Object, Object> map = new HashMap<>();
            map.put("imgurl", "http://ocalhost:8081/upload/Poverty-Alleviation/" + type + "/" + originalFilename);

            resultVo.setCode(2000);
            resultVo.setSuccess(true);
            resultVo.setMessage("上传成功！！！");
            resultVo.setData(map);
        } catch (IOException e) {
            e.printStackTrace();

            resultVo.setCode(1000);
            resultVo.setSuccess(false);
            resultVo.setMessage("上传失败！！！");
        }


        return resultVo;
    }
}
