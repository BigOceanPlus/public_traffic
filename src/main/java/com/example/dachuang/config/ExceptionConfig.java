package com.example.dachuang.config;

import com.example.dachuang.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
    public Result except(Exception e){
        e.printStackTrace();
        return Result.error("服务器内部错误");
    }
}
