package com.sdjz.system.service;

import com.sdjz.common.core.domain.entity.SysUser;

public interface SysUserService {

    public SysUser selectUserByUserName(String userName);
}
