package cclub.demo.impl.redisUtils;

import cclub.demo.dao.Interview;
import com.google.gson.Gson;
import org.apache.poi.ss.formula.functions.T;
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
        redisTemplate.opsForValue().set(rand_uuid,rand_code,60, TimeUnit.SECONDS);
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
     * @param list
     * @param user_id
     * 将批量提交候选人信息的Excel表解析后的数组进行缓存
     */
    public void setCandidateExcel(List<List<String>>list,String user_id){
        redisTemplate.opsForValue().set("candidateExcel:"+user_id,list,60*60,TimeUnit.SECONDS);
    }



    /**
     *
     * @param user_id
     * @return
     * 将缓存的候选人提交的Excel数据列表取出
     */
    public List<List<String>> getCandidateExcel(String user_id){
        return (List<List<String>>)redisTemplate.opsForValue().get("candidateExcel:"+user_id);
    }

}
