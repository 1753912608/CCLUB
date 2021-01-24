package cclub.demo.dao.utils;

import cclub.demo.dao.exam.exam;

import java.nio.channels.InterruptedByTimeoutException;
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


    /**
     *
     * @param exam
     * @return
     * 根据加载的数据判断当前笔试是否开始,进行,结束
     */
    public static int ExamTimeState(exam exam){
        Calendar now=Calendar.getInstance();
        int now_year=now.get(Calendar.YEAR),now_month=now.get(Calendar.MONTH)+1,now_day=now.get(Calendar.DAY_OF_MONTH),now_hour=now.get(Calendar.HOUR_OF_DAY),now_minute=now.get(Calendar.MINUTE);
        int exam_long=exam.getExam_longTime();
        String str1=exam.getExam_start_time().split(" ")[0];
        String str2=exam.getExam_start_time().split(" ")[1];
        int year=Integer.parseInt(str1.split("-")[0]),month=Integer.parseInt(str1.split("-")[1]),day=Integer.parseInt(str1.split("-")[2]);
        int hour= Integer.parseInt(str2.split(":")[0]),minute=Integer.parseInt(str2.split(":")[1]);
        long exam_time=(year-1)*365*24*60+(month-1)*30+(hour-1)*60+minute;
        long now_time=(now_year-1)*365*24*60+(now_month-1)*30+(now_hour-1)*60+now_minute;
        if(exam_time>now_time){
            return 0;
        }else if(exam_time+exam_long>=now_time){
            return 1;
        }
        return 2;
    }
}
