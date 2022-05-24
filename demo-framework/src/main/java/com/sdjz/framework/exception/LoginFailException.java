package com.sdjz.framework.exception;


/**
 * 登录失败异常
 *
 */
public class LoginFailException extends RuntimeException {

    private String username;

    /**
     * 构造方法
     */
    public LoginFailException(String username,String message) {
        super(message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}