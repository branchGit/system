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


    /**
     * 获取项目名称
     * @return
     */
    public String getContextPath(){
        return getRequest().getContextPath();
    }


    /**
     * 获取ip地址
     * @param
     * @return
     */
    public  String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        return ip;
    }


    public  String getPort() {

        return getRequest().getServerPort()+"";
    }


}
