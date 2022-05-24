package com.sdjz.framework.web.service;

import com.sdjz.common.core.domain.entity.SysUser;
import com.sdjz.common.core.domain.model.LoginUser;
import com.sdjz.common.enums.UserStatus;
import com.sdjz.common.exception.BusinessException;
import com.sdjz.common.utils.StringUtils;
import com.sdjz.framework.exception.LoginFailException;
import com.sdjz.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new BusinessException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被禁用.", username);
            throw new LoginFailException(username,"对不起，您的账号：" + UserStatus.DISABLE.getInfo());
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDeleteFlag()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new LoginFailException(username,"对不起，您的账号：" +  UserStatus.DELETED.getInfo());
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        Set<String> menuPermission = permissionService.getMenuPermission(user);
        return new LoginUser(user.getId(), user.getDeptId(), user, menuPermission);
//        return new LoginUser(user.getId(), user.getDeptId(), user, null);


    }
}
