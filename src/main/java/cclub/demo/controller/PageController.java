package cclub.demo.controller;


import cclub.demo.dao.SessionInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping("/ming")
    public String ming(HttpServletRequest request){
//        Cookie[] cookies=request.getCookies();
//        for(Cookie c:cookies){
//            if(c.getName().equals(SessionInfo.CCLUB_phone)){
//                HttpSession session=request.getSession();
//                session.setAttribute(SessionInfo.Session_phone,c.getValue());
//                return "index";
//            }
//        }
        return "index";
    }

    @RequestMapping("/test_login")
    public String test_login(){
        return "login";
    }


    @RequestMapping("/test_login_init")
    public String test_login_init(){
        return "login_init";
    }


    @RequestMapping("/test_interview")
    public String test_interview(){
        return "interview";
    }


    @RequestMapping("/test_interview_end")
    public String test_interview_end(){
        return "interview_end";
    }
}
