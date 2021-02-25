package cclub.demo.interceptors;

import cclub.demo.dao.SessionInfo;
import cclub.demo.impl.redisUtils.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    private RedisServiceImpl redisService;

    public LoginInterceptor(RedisServiceImpl redisService){
        this.redisService=redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器访问");
        if(redisService.getSession(request.getRemoteAddr())==null){
            request.getRequestDispatcher("test_login").forward(request,response);
            return false;
        }
        return true;
    }
}
