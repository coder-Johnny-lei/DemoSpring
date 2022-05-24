package com.sdjz.common.core.domain.vo;

import org.springframework.http.HttpStatus;

/**
 * 返回结果通用包装类
 *
 * @author Eddy Zhang
 * @date 2021/9/15
 * @version 1.0
 * @className JsonResult
 * @description
 */
public class JsonResult<T> {

    /**
     * 业务返回码
     */
    private Integer code;

    /**
     * 信息描述
     */
    private String message;

    /**
     * 返回参数
     */
    private T data;


    /**
     * 【构造方法】：返回 数据
     **/
    private JsonResult(HttpStatus status, T data) {
        this.code = status.value();
        this.message = status.getReasonPhrase();
        this.data = data;
    }

    /**
     * 【构造方法】：返回 错误信息
     */
    private JsonResult(HttpStatus status, String message, T data) {
        this(status, data);
        this.message = message;
    }


    /**
     * 自定义：指定返回状态；返回 成功信息：T Data
     */
    public static <T> JsonResult<T> responseData(HttpStatus status, T data) {
        if (null != status) {
            return new JsonResult<T>(status, data);
        }
        return new JsonResult<T>(HttpStatus.OK, data);
    }


    /**
     * 自定义：指定返回状态；返回 出错信息：message
     */
    public static <T> JsonResult<T> responseMessage(HttpStatus status, String message, T data) {
        if (null != status) {
            return new JsonResult<T>(status, message, data);
        }
        return new JsonResult<T>(HttpStatus.BAD_REQUEST, message, data);
    }


    /**
     * 成功：返回 T
     */
    public static <T> JsonResult<T> success(T data) {
        return responseData(HttpStatus.OK, data);
    }

    /**
     * 成功：返回 null
     */
    public static JsonResult<Void> success() {
        return responseData(HttpStatus.OK, null);
    }


    /**
     * 失败：带参，返回 T
     */
    public static JsonResult<String> failure(HttpStatus status, String errMsg) {
        if (null == status) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return responseMessage(status, errMsg,null);
    }

    /**
     * 失败：带参，返回 T
     */
    public static JsonResult failure(String msg) {
        return failure(HttpStatus.INTERNAL_SERVER_ERROR, msg);
    }

    /**
     * 失败：无参，返回 null
     */
    public static JsonResult<String> failure() {
        return failure(HttpStatus.INTERNAL_SERVER_ERROR, "服务请求异常，请稍后再试。");
    }


    /**
     * 400 : 参数错误
     **/
    public static JsonResult<String> badRequest(String msg) {
        return responseMessage(HttpStatus.BAD_REQUEST, msg, null);
    }

    /**
     * 401 :未登录
     **/
    public static JsonResult<String> unAuthorized(String msg) {
        return responseMessage(HttpStatus.UNAUTHORIZED,  msg, "授权认证失败");
    }

    /**
     * 403 :禁止访问
     **/
    public static JsonResult<String> forbidden(String msg) {
        return responseMessage(HttpStatus.FORBIDDEN,  msg, "禁止访问");
    }

    /**
     * 404 : 资源未找到
     **/
    public static JsonResult<String> notFound(String msg) {
        return responseMessage(HttpStatus.NOT_FOUND,  msg, "资源未找到");
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }


    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}

