package com.gesoft.system.service.login.impl;

import com.gesoft.system.mapper.login.LoginMapper;
import com.gesoft.system.po.user.UserPo;
import com.gesoft.system.service.login.ILoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService implements ILoginService {


    @Resource
    private LoginMapper loginMapper;


    @Override
    public UserPo login(UserPo user) {
        return loginMapper.login(user);
    }
}
