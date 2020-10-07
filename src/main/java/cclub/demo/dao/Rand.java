package cclub.demo.dao;

import java.util.Random;

public class Rand {
    /**
     *
     * @return
     * 生成手机验证码
     */
    public static String getPhoneCode(){
        StringBuffer buffer=new StringBuffer("");
        Random rand=new Random();
        for(int i=0;i<6;i++){
            buffer.append(rand.nextInt(10));
        }
        return buffer.toString();
    }


    /**
     *
     * @return
     *生成视频面试id
     */
    public static String getInterviewId(){
        StringBuffer buffer=new StringBuffer("CCLUB");
        Random rand=new Random();
        for(int i=0;i<12;i++){
            buffer.append(rand.nextInt(10));
        }
        return buffer.toString();
    }
}
