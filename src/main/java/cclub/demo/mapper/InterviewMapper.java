package cclub.demo.mapper;


import cclub.demo.dao.Interview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterviewMapper {

    /**
     *
     * @param view
     * 创建视频面试
     */
    @Insert("insert into interview values(#{interview_id},#{interview_create_user_id},#{interview_begin_time}," +
            "#{interview_company_name},#{interview_candidate_position},#{interview_candidate_phone}," +
            "#{interview_candidate_name},#{interview_candidate_mail},#{interview_judge_phone}," +
            "#{interview_candidate_name},#{interview_candidate_mail},#{interview_judge_phone}," +
            "#{interview_judge_name},#{interview_judge_mail},#{interview_recording}," +
            "#{interview_candidate_resume})")
    void createInterview(Interview view);
}
