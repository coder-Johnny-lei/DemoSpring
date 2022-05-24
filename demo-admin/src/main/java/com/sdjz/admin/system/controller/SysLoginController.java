package com.sdjz.admin.system.controller;


import com.sdjz.common.core.domain.model.LoginBody;
import com.sdjz.framework.aop.JsonResultController;
import com.sdjz.framework.web.service.SysLoginService;
import com.sdjz.framework.web.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Map;

@JsonResultController
@Slf4j
public class SysLoginController {

    @Resource
    private SysLoginService sysLoginService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginBody loginBody){
        String token = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword());
        return null;
    }
}
