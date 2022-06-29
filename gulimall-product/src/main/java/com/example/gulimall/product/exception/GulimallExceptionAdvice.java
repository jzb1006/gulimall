package com.example.gulimall.product.exception;

import com.example.gulimall.common.exception.BizCodeEnume;
import com.example.gulimall.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice("com.example.gulimall.product.controller")
public class GulimallExceptionAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public Result hanleValidException(MethodArgumentNotValidException e){
        log.error("数据校验失败{}",e.getMessage());

        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            objectObjectHashMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(BizCodeEnume.VAILD_EXCEPTION.getCode());
        objectResult.setData(objectObjectHashMap);
        return objectResult;
    }

    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage());
        Result result = new Result();
        result.setData(BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
        result.setCode(BizCodeEnume.UNKNOW_EXCEPTION.getCode());
        return result;
    }
}
