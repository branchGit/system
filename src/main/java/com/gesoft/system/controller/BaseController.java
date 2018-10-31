package com.gesoft.system.controller;

import com.gesoft.system.common.DataUtils;
import com.gesoft.system.po.user.UserPo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * controller 基类
 */
public class BaseController {


    /**
     * 获取request
     * @return
     */
    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
    }


    /**
     * 获取session
     * @return
     */
    public HttpSession getSession(){
        return getRequest().getSession();
    }


    /**
     * 获取当前登陆用户
     * @return
     */
    public UserPo getUser(){
        return (UserPo) getSession().getAttribute("user");
    }

    /**
     * 获取工具类
     * @return
     */
    public DataUtils  getDu(){
        return  new DataUtils();
    }



}
