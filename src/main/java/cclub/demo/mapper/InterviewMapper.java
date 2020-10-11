package cclub.demo.mapper;


import cclub.demo.dao.Interview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InterviewMapper {

    /**
     *
     * @param view
     * 创建视频面试
     */
    @Insert("insert into interview values(#{interview_id},#{interview_create_user_id}," +
            "#{interview_begin_time},#{interview_company_name},#{interview_candidate_position}," +
            "#{interview_candidate_phone},#{interview_candidate_name},#{interview_candidate_mail}," +
            "#{interview_judge_phone},#{interview_judge_name},#{interview_judge_mail}," +
            "#{interview_recording},#{interview_candidate_resume},#{interview_invitation_judge_notice}," +
            "#{interview_invitation_candidate_notice},#{interview_room_state},#{interview_candidate_code}," +
            "#{interview_judge_code})")
    void createInterview(Interview view);


    /**
     *
     * @param user_id
     * @return
     * 获取当前用户创建的视频面试
     */
    @Select("select * from interview where interview_create_user_id=#{user_id}")
    List<Interview>getMyCreateInterviewList(String user_id);


    /**
     *
     * @param interview_id
     * @param interview_content_url
     * 创建视频面试时提交候选人的简历
     */
    @Insert("insert into resume values(#{interview_id},#{interview_content_url})")
    void insertCandidateResume(String interview_id,String interview_content_url);


    /**
     *
     * @param interview_id
     * 删除指定的视频面试
     */
    @Delete("delete from interview where interview_id=#{interview_id}")
    void deleteInterview(String interview_id);


    /**
     *
     * @param resume_interview_id
     * 删除指定的简历表
     */
    @Delete("delete from resume where resume_interview_id=#{resume_interview_id}")
    void deleteResume(String resume_interview_id);



    /**
     *
     * @param interview_id
     * @param newState
     * 修改视频面试的状态
     */
    @Update("update interview set interview_room_state=#{newState}")
    void modifyInterviewState(String interview_id,String newState);
}
