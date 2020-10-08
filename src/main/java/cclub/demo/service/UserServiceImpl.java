package cclub.demo.service;


import cclub.demo.dao.Rand;
import cclub.demo.dao.User;
import cclub.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public String sendPhoneCode(){
        return Rand.getPhoneCode();
    }

    @Override
    public void login(String phone) {
        this.userMapper.login(phone);
    }

    @Override
    public boolean IsRegister(String user_id) {
        return userMapper.getUserCompany(user_id)!=null;
    }

    @Override
    public int init_company(String user_id, String user_company) {
        try{
            userMapper.init_company(user_id,user_company);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User getUserInfo(String user_id) {
        return userMapper.getUserInfo(user_id);
    }
}
