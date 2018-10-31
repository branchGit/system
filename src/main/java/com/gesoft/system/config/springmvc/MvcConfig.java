package com.gesoft.system.config.springmvc;

import com.gesoft.system.config.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor( new PermissionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.gif")
                .excludePathPatterns("/**/*.js");
    }
}
