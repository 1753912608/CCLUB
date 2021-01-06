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


    @RequestMapping("/test_interview/{interview_id}")
    public String test_interview(){
        return "interview";
    }


    @RequestMapping("/test_interview_end/{interview_id}")
    public String test_interview_end(){
        return "interview_end";
    }


    @RequestMapping("/Test")
    public String test(){
        return "test";
    }


    @RequestMapping("/test_createWrittenExam/{exam_id}")
    public String createWrittenExam(){
        return "create_written_exam";
    }


    @RequestMapping("/test_editSubject/{exam_id}")
    public String editSubject(){
        return "editSubject";
    }


    @RequestMapping("/test_addSubject/{exam_id}")
    public String addSubject(){
        return "addSubject";
    }


    @RequestMapping("/Test1")
    public String test1(){
        return "test1";
    }


    @RequestMapping("/Test2")
    public String test2(){
        return "test2";
    }


    @RequestMapping("/test_exam")
    public String eaxm(){
        return "exam";
    }


    @RequestMapping("/test_subject/{exam_id}")
    public String subject(){
        return "subject";
    }


    @RequestMapping("/test_examing/{exam_id}")
    public String examing(){
        return "examing";
    }

}
