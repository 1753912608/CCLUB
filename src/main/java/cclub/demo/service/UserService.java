package cclub.demo.service;


import cclub.demo.dao.Interview;
import cclub.demo.dao.User;

public interface UserService {

    /**
     *
     * @param phone
     * 根据用户输入的手机号码完成登录
     */
    void login(String phone);


    /**
     *
     * @param user_id
     * @return
     * 判断当前用户是否注册
     */
    boolean IsRegister(String user_id);


    /**
     *
     * @param user_id
     * @param user_company
     * 根据新注册的用户初始化对应的团队
     */
    int init_company(String user_id,String user_company);


    /**
     *
     * @param user_id
     * @return
     * 根据用户id获取用户信息
     */
    User getUserInfo(String user_id);


}
