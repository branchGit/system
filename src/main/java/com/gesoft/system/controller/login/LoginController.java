package com.gesoft.system.controller.login;

import com.gesoft.system.controller.BaseController;
import com.gesoft.system.po.user.UserPo;
import com.gesoft.system.service.login.ILoginService;
import org.apache.ibatis.type.Alias;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {


    @Resource
    private ILoginService loginService;

    @GetMapping("login")
    public String index(){
        return "login";
    }

    @RequestMapping("login")
    public String login(UserPo user){

        System.out.println(user);
        UserPo temp = loginService.login(user);

        if(null != temp && user.getLoginName().equals(temp.getLoginName())&&user.getPassWord().equals(temp.getPassWord())){
            getSession().setAttribute("user",temp);
            return "redirect:/article/index";
        }else{
            return index();
        }

    }

}
