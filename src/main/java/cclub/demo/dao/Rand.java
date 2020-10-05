package cclub.demo.dao;

import java.util.Random;

public class Rand {
    public static String getPhoneCode(){
        StringBuffer buffer=new StringBuffer("");
        Random rand=new Random();
        for(int i=0;i<6;i++){
            buffer.append(rand.nextInt(6));
        }
        return buffer.toString();
    }
}
