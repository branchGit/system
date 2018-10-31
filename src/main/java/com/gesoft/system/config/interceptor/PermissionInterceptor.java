package com.gesoft.system.config.interceptor;

import com.gesoft.system.common.Constant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class PermissionInterceptor implements HandlerInterceptor {

    private static List<String> awayUrls = null;
    static{
        awayUrls = new LinkedList<String>();
        awayUrls.add("/login");//登陆页

    }



    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object o) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-control", "no-cache");
        if(awayUrls.contains(request.getServletPath())){	// 需要跳过的action
            return true;
        }
        if(request.getServletPath().indexOf("/api/") != -1){	// 需要跳过的action
            return true;
        }
        String type = request.getHeader("X-Requested-With");//获得请求类型
        Object user = request.getSession().getAttribute(Constant.SESSION_USER);
        if(user == null){	// session 是否过期
            if ("XMLHttpRequest".equalsIgnoreCase(type)){ //ajax请求 session过期时 返回字符串
                response.setHeader("sessionStatus", Constant.TIMEOUT);
                PrintWriter out = response.getWriter();
                out.print(Constant.TIMEOUT);
                out.flush();
                out.close();
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }else{	//普通http请求 直接返回页面
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }
        return true;
    }


}
