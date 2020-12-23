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
        String[] time=interview_begin_time.split(" ")[0].split("-");
        int day=Integer.parseInt(time[2]);
        int month=Integer.parseInt(time[1]);
        Calendar now=Calendar.getInstance();
        int now_month=now.get(Calendar.MONTH)+1,now_day=now.get(Calendar.DAY_OF_MONTH);
        if(now_month<month)return true;
        else if(day<now_day&&now_month==month)return true;
        else return false;
    }
}
