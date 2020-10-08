package cclub.demo.dao;


import org.springframework.beans.factory.annotation.Value;

public class NoticeTemplate {

    @Value("${com.cclub_url}")
    private static String cclub_url;


    public static String candidateNotice(String company,String position,String begin_time,String code,String user_id){
        StringBuffer buffer=new StringBuffer("");
        buffer.append("您有一个面试邀请\n\n");
        buffer.append("公司: "+company+"\n");
        buffer.append("职位: "+position+"\n");
        buffer.append("面试时间: "+begin_time+"\n");
        buffer.append("面试形式: 视频面试\n");
        buffer.append("视频面试链接: "+cclub_url+"/test_interview?code="+code+"\n");
        buffer.append("视频面试接入码: "+code+"\n");
        buffer.append("联系人: "+user_id+"\n");
        return buffer.toString();
    }


    public static String judgeNotice(String candidate_name,String position,String begin_time,String code,String user_id){
        StringBuffer buffer=new StringBuffer("");
        buffer.append("已为您安排一场视频面试\n\n");
        buffer.append("候选人: "+candidate_name+"\n");
        buffer.append("面试职位: "+position+"\n");
        buffer.append("面试时间: "+begin_time+"\n");
        buffer.append("面试形式: 视频面试\n");
        buffer.append("视频面试链接: "+cclub_url+"/test_interview?code="+code+"\n");
        buffer.append("视频接入码为: "+code+"\n");
        buffer.append("联系人: "+user_id+"\n");
        return buffer.toString();
    }
}
