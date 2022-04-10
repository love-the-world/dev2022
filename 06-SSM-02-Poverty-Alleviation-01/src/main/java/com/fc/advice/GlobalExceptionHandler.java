package com.fc.advice;

import com.fc.vo.ResultVo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//用于对Controller进行增强
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public ResultVo duplicateKeyException(DuplicateKeyException e){
        System.out.println(e.getMessage());

        return new ResultVo(6000, "输入了重复的名字，请换个名字", false, null);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultVo runtimeException(RuntimeException e){
        System.out.println(e.getMessage());

        return new ResultVo(5000, "系统操作异常，请稍后重试", false, null);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultVo missingServletRequestParameterException(MissingServletRequestParameterException e){
        System.out.println(e.getMessage());

        return new ResultVo(4600, "缺少了重要的请求参数，请重新发送", false, null);
    }
}
