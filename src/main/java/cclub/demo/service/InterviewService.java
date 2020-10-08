package cclub.demo.service;

import cclub.demo.dao.Interview;

import java.util.List;

public interface InterviewService {


    /**
     *
     * @param interview
     * @return
     * 创建视频面试
     */
    int createInterview(Interview interview);


    /**
     *
     * @param company
     * @param position
     * @param begin_time
     * @param candidate_name
     * @param user_id
     * @return
     * 生成面试官,候选人的面试进入链接以及面试通知
     */
    List<String> getInterviewUrlNotice(String company,String position,String begin_time,String candidate_name,String user_id);



}
