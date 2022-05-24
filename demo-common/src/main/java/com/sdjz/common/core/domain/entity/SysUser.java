package com.sdjz.common.core.domain.entity;

import com.sdjz.common.core.domain.BaseEntity;
import lombok.Data;


@Data
public class SysUser  extends BaseEntity {

    /**
     * uuid
     */
    private String id;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 角色id
     */
    private String roleId;


    /**
     * token
     */
    private String token;

    /**
     * 用户状态
     */
    private String status;

    public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(String id)
    {
        return "1".equals(id);
    }
}
