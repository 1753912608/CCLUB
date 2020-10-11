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


    /**
     *
     * @param user_id
     * @return
     * 获取当前用户创建的视频面试
     */
    List<Interview>getMyCreateInterviewList(String user_id);


    /**
     *
     * @param resume_interview_id
     * @param resume_content_url
     * @return
     * 上传候选人的简历
     */
    int insertCandidateResume(String resume_interview_id,String resume_content_url);



    /**
     *
     * @param interview_id
     * @return
     * 删除指定的视频面试
     */
    void deleteInterview(String interview_id);



    /**
     *
     * @param resume_interview_id
     * @return
     * 删除指定的简历
     */
    void deleteResume(String resume_interview_id);



    /**
     *
     * @param interview_id
     * @param newState
     * @return
     * 修改视频面试的状态
     */
    int modifyInterviewState(String interview_id,String newState);
}
