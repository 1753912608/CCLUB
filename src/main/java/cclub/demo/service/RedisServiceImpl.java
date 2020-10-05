package cclub.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl {

    @Autowired
    private RedisTemplate<String,Object>redisTemplate;


    /**
     *
     * @param rand_uuid
     * @param rand_code
     * rand_uuid为前端随机的字符串，防止用户刷验证码
     * rand_code为发送给用户的6位数手机验证码
     */
    public void savePhoneCode(String rand_uuid,String rand_code){
        redisTemplate.opsForValue().set(rand_uuid,rand_code,6000, TimeUnit.SECONDS);
    }


    /**
     *
     * @param rand_uuid
     * @return
     * 根据前端随机生成的字符串获取指定用户的验证码
     */
    public String getPhoneCode(String rand_uuid){
        return (String)redisTemplate.opsForValue().get(rand_uuid);
    }
}
