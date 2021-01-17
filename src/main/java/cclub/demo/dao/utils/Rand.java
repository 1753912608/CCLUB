package cclub.demo.dao.utils;

import java.util.Random;

public class Rand {

    private enum suffex{cclub,choice,completion,judge}
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
        StringBuffer buffer=new StringBuffer(suffex.valueOf("cclub").toString());
        Random rand=new Random();
        for(int i=0;i<12;i++){
            buffer.append(rand.nextInt(10));
        }
        return buffer.toString();
    }


    /**
     *
     * @return
     * 生成面试房间的链接
     */
    public static String getInterviewCode(){
        StringBuffer buffer=new StringBuffer("");
        Random rand=new Random();
        for(int i=0;i<10;i++){
            buffer.append(rand.nextInt(10));
        }
        return buffer.toString();
    }



}
