package com.blog.controller;

import com.blog.exception.CategoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionController {
    public static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public String handleExcenption(Exception exception){
        logger.error("Exception: " + exception);
        return "error";
    }
    @ExceptionHandler(value= {CategoryNotFoundException.class, NoHandlerFoundException.class})
    @ResponseBody
    public String handleNotFoundException(Exception exception){
        logger.error("Exception: " + exception);
        return "error";
    }
    @ExceptionHandler(value = {AccessDeniedException.class})
    public String handleAccessDenied(Exception exception){
        logger.error("Exception: " + exception);
        return "error_403";
    }
}
