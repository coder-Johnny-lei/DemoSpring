package com.sdjz.framework.web.service;


import com.sdjz.common.core.domain.model.LoginUser;
import com.sdjz.framework.exception.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String login(String username,String password){
        Authentication authentication = null;
        try{
            //用户校验 该方法 会去调用UserDetailsServiceImpl.loadUserByUsername
             authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            e.printStackTrace();
            throw new LoginFailException(username,"用户名或密码错误!请重试!");
        }

        //生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = tokenService.createToken(loginUser);
        return  token;
    }
}
