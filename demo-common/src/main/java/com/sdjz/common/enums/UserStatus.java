package com.sdjz.common.enums;

/**
 * 用户状态
 * 
 * @author ruoyi
 */

public enum UserStatus
{
    OK("0", "正常"), DISABLE("1", "禁用"), DELETED("2", "锁定");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
