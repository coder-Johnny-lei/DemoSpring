package com.sdjz.framework.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Johnny_JinLei
 * @version V1.0
 * @Project: becmas-parent-api
 * @Package com.hiwintech.common.exception
 * @Description: Validator 异常处理类
 * @date Date : 2021年10月28日 10:56 上午
 */
@RestControllerAdvice
public class BindExceptionHandler {
    @ExceptionHandler(BindException.class)
    public String handleBindException(HttpServletRequest request, BindException exception) {
        List<FieldError> allErrors = exception.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError errorMessage : allErrors) {
            sb.append(errorMessage.getField()).append(": ").append(errorMessage.getDefaultMessage()).append(", ");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
