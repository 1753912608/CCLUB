package cclub.demo.dao;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SessionInfo {
    //用户登录的用户名(手机号)
    public static String Session_phone="Session_phone";

    //存储用户名的Cookie
    public static String CCLUB_phone="CCLUB_phone";

    //用于存储当前笔试的用户邮箱
    public static String EXAM_USER_MAIL="exam_user_mail";

    public static void setSession(HttpServletRequest request,String key,String value){
        RequestAttributes ra= RequestContextHolder.getRequestAttributes();
        request=((ServletRequestAttributes)ra).getRequest();
        request.getSession(true).setAttribute(key,value);
    }

    public static Object getSession(HttpServletRequest request,String key){
        RequestAttributes ra=RequestContextHolder.getRequestAttributes();
        request = ((ServletRequestAttributes)ra).getRequest();
        return request.getSession(true).getAttribute(key);
    }
}
