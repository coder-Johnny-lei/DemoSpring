package com.sdjz.framework.exception.handler;


import com.sdjz.common.core.domain.vo.JsonResult;
import com.sdjz.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理类
 *
 * @author Eddy Zhang
 * @date 2021/9/15
 * @version 1.0
 * @className GeneralExceptionHandler
 * @description spring mvc异常统一处理
 */
@ControllerAdvice
@ResponseBody
public class GeneralExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    /***
     * 404处理
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JsonResult<String> notFountHandler(HttpServletRequest request, NoHandlerFoundException ex){
        String msg = String.format("%s - %s", request.getMethod(), request.getRequestURI());
        log.warn("资源未找到：{}, ex:{}", msg, ex.getMessage());
        return JsonResult.notFound(msg);
    }

    /**
     *  处理业务逻辑异常
     *
     * @date 2021年4月24日 最后一次更新
     * @param
     * @return Object
     */
    @ExceptionHandler({BusinessException.class, ValidationException.class, ServletRequestBindingException.class})
    public JsonResult<String> handleBusinessException(Exception ex){
        log.info(ex.getMessage());
        log.error("异常",ex);
        return JsonResult.badRequest(ex.getMessage());
    }

    /**
     *  处理参数验证异常
     *
     * @date 2021年4月24日 最后一次更新
     * @param
     * @return Object
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public JsonResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return this.extractBindingResultMessage(ex.getBindingResult());
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public JsonResult<?> ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return JsonResult.badRequest(String.join(",",msgList));
    }


    /**
     *  处理参数验证异常 - @RequestParam
     *
     * @date 2021年4月24日 最后一次更新
     * @param ex exception
     * @return JsonResult
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult<String> handleMyException(MissingServletRequestParameterException ex) {
        String msg =  String.format("[%s不能为空！]", ex.getParameterName());
        return JsonResult.badRequest(msg);
    }

    /**
     *  处理服务异常
     *
     * @date 2021年4月24日 最后一次更新
     * @param ex exception
     * @return JsonResult
     */
    @ExceptionHandler(Throwable.class)
    public JsonResult<?> handleServiceException(Exception ex){
           if (ex instanceof BindException){
            return this.extractBindingResultMessage(((BindException) ex).getBindingResult());
        }
        log.error("服务异常：{}", ex.getMessage(), ex);
        return JsonResult.failure(ex.getMessage());
    }

    /**
     * 从 BindException中提取提示信息
     * @return JsonResult
     */
    private JsonResult<String> extractBindingResultMessage (BindingResult bindingResult){
        // 提取出所有异常的 field 的错误提示信息
        StringBuilder strBuilder = new StringBuilder("[");
        // 必填字段列表
        List<FieldError> errorList = bindingResult.getFieldErrors();
        int len = errorList.size();
        for(int i = 0; i < len; i++){
            FieldError fieldError = errorList.get(i);
            strBuilder
                    //.append(fieldError.getField())
                    //.append(":")
                    .append(fieldError.getDefaultMessage());
            if (i< (len -1) ) strBuilder.append(", ");
        }
        strBuilder.append("]");
        return JsonResult.badRequest(strBuilder.toString());
    }


    /***
     * 空指针异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public JsonResult<String> nullPointerHandler(HttpServletRequest request, NullPointerException ex){
        log.info("\n\n请求：{} - {}，发生空指针异常：java.lang.NullPointerException：", request.getMethod(), request.getRequestURI(), ex);
        return JsonResult.badRequest("请求数据为空！");
    }


}
