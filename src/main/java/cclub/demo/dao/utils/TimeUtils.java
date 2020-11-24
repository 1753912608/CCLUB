package cclub.demo.dao.utils;

import java.util.Calendar;

public class TimeUtils {


    /**
     *
     * @param interview_begin_time
     * @return
     * 判断视频面试是否时间过期结束(默认为次日过期)
     */
    public static boolean IsExpireTime(String interview_begin_time){
        int day=(interview_begin_time.charAt(8)-'0')*10+interview_begin_time.charAt(9)-'0';
        int month=(interview_begin_time.charAt(5)-'0')*10+interview_begin_time.charAt(6)-'0';
        Calendar now=Calendar.getInstance();
        System.out.println(now.get(Calendar.DAY_OF_MONTH)+" "+day);
        return now.get(Calendar.DAY_OF_MONTH)>day||now.get(Calendar.DAY_OF_YEAR)>month;
    }
}
