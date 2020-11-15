package cclub.demo.impl.redisUtils;

import cclub.demo.dao.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
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



    /**
     *
     * @param user_id
     * @param list
     * 将新创建的视频面试的信息缓存到redis
     */
    public void saveMyCreatedInterview(String user_id, List<?> list,boolean init){
        if(init)
            redisTemplate.opsForList().leftPushAll("interview:interview_id:"+user_id,list);
        else
            redisTemplate.opsForList().leftPush("interview:interview_id:"+user_id,list.get(0));
    }



    /**
     *
     * @param user_id
     * @return
     * 在缓存中获取用户创建的视频面试
     */
    public List<?> getMyCreatedInterview(String user_id){
        return redisTemplate.opsForList().range("interview:interview_id:"+user_id,0,-1);
    }
}
