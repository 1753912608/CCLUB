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


    private String phone="session_phone";

    private String exam_user_mail="exam_user_mail";

    public SessionInfo(String phone, String exam_user_mail) {
        this.phone = phone;
        this.exam_user_mail = exam_user_mail;
    }

    public SessionInfo(){}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setExam_user_mail(String exam_user_mail) {
        this.exam_user_mail = exam_user_mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getExam_user_mail() {
        return exam_user_mail;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "phone='" + phone + '\'' +
                ", exam_user_mail='" + exam_user_mail + '\'' +
                '}';
    }
}
