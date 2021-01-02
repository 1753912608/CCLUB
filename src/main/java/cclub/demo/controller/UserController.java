package cclub.demo.controller;

import cclub.demo.dao.SessionInfo;
import cclub.demo.dao.User;
import cclub.demo.impl.aliyunServiceImpl.aliyunUtils;
import cclub.demo.impl.redisUtils.RedisServiceImpl;
import cclub.demo.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisServiceImpl redisService;


    /**
     *
     * @param phone
     * @param rand_uuid
     * @return
     * 发送短信验证码
     */
    @ResponseBody
    @RequestMapping("/sendPhoneCode")
    public int sendPhoneCode(String phone,String rand_uuid){
        String code=userService.sendPhoneCode();
        int result=1;
        try{
            System.out.println(phone+"的验证码为:"+code);
            //aliyunUtils.sendCode(phone,code);
            redisService.savePhoneCode(rand_uuid,code);
        }catch (Exception e){
            result=0;
            System.out.println("服务器请求错误");
        }
        return result;
    }

    /**
     *
     * @param rand_uuid
     * @param code
     * @param phone
     * @param request
     * @param response
     * @return
     * 对用户输入的短信验证码做校验
     */
    @ResponseBody
    @RequestMapping("/check")
    public int check(String rand_uuid,
                     String code,
                     String phone,
                     HttpServletRequest request,
                     HttpServletResponse response)
    {
        if(redisService.getPhoneCode(rand_uuid).equals(code)){
            System.out.println("phone:"+phone);
            HttpSession session=request.getSession();
            session.setAttribute(SessionInfo.Session_phone,phone);
            Cookie cookie=new Cookie(SessionInfo.CCLUB_phone,phone);
            response.addCookie(cookie);
            if(userService.IsRegister(phone)){
                return 2;
            }
            else{
                userService.login(phone);
            }
            return 1;
        }
        return 0;
    }


    /**
     *
     * @param request
     * @param user_company
     * @return
     *初始化用户所在公司
     */
    @ResponseBody
    @RequestMapping("/init_company")
    public int init_company(HttpServletRequest request,
                            String user_company){
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return userService.init_company(user_id,user_company);
    }


    /**
     *
     * @param request
     * @return
     * 根获取用户信息
     */
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public User getUserInfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return userService.getUserInfo(user_id);
    }


    /**
     *
     * @param request
     * @return
     * 退出登录
     */
    @ResponseBody
    @RequestMapping("/signOut")
    public void signOut(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute(SessionInfo.Session_phone);
    }
}
