package com.gesoft.system.controller.login;

import com.gesoft.system.po.user.UserPo;
import com.gesoft.system.service.login.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ApiLoginController {


    @Resource
    private ILoginService loginService;


    @PostMapping("/api/login")
    String login(UserPo user){
        log.info("本次登陆用户名："+user.getLoginName());
        log.info("本次登陆密码："+user.getPassWord());

        System.out.println(user);
        UserPo temp = loginService.login(user);

        if(null != temp && user.getLoginName().equals(temp.getLoginName())&&user.getPassWord().equals(temp.getPassWord())){
            log.info("登陆成功");
            return "{\"result\":\"true\"}";
        }else{
            log.info("登陆失败");
            return "{\"result\":\"false\"}";
        }


    }


}
