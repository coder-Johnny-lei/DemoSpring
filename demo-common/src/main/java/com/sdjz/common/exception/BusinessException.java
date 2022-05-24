package com.sdjz.common.exception;

/**
 * 业务异常通用类
 *
 * @author Eddy Zhang
 * @date 2021/9/14
 * @version 1.0
 * @className BusinessException
 * @description
 */
public class BusinessException extends RuntimeException {

    /**
     * 构造方法
     *
     * @author Eddy Zhang
     * @date 2021/9/15
     * @param message
     * @return BusinessException
     */
    public BusinessException (String message) {
        super(message);
    }

}
