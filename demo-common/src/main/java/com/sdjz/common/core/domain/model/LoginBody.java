package com.sdjz.common.core.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody {

    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;
}
