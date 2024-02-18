package com.example.mine.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 例外ハンドラ
 */
@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(Exception.class)
    public String handleOriginalWebException(Exception e) {
    	e.printStackTrace();
        return "error";
    }

}