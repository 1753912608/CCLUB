package cclub.demo.mapper;

import cclub.demo.dao.exam.exam;
import cclub.demo.dao.exam.exam_question;
import cclub.demo.dao.exam.exam_user;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ExamMapper {


    /**
     *
     * @param exam
     * 修改笔试
     */
    @Insert("insert into exam values(#{exam_id},#{exam_name},#{exam_created_user_id}," +
            "#{exam_start_time},#{exam_noEntry_time},#{exam_longTime}," +
            "#{exam_Upset_question},#{exam_Upset_answer},#{exam_jumpOut_number}," +
            "#{exam_recording},#{exam_user_info},#{exam_state},#{exam_question_number}," +
            "#{exam_user_number})")
    void createExam(exam exam);


    /**
     *
     * @param exam
     * @return
     * 修改笔试
     */
    @Update("update exam set exam_name=#{exam_name}," +
            "exam_created_user_id=#{exam_created_user_id}," +
            "exam_start_time=#{exam_start_time},exam_noEntry_time=#{exam_noEntry_time}," +
            "exam_longTime=#{exam_longTime},exam_Upset_question=#{exam_Upset_question}," +
            "exam_Upset_answer=#{exam_Upset_answer},exam_jumpOut_number=#{exam_jumpOut_number}," +
            "exam_recording=#{exam_recording},exam_user_info=#{exam_user_info}," +
            "exam_state=#{exam_state},exam_question_number=#{exam_question_number}," +
            "exam_user_number=#{exam_user_number} where exam_id=#{exam_id}")
    void updateExam(exam exam);



    /**
     *
     * @param user_id
     * @return
     * 获取当前当前用户创建的所有笔试
     */
    @Select("select *  from exam where exam_created_user_id=#{user_id}")
    List<exam>getMyCreatedExam(String user_id);



    /**
     *
     * @param exam_id
     * 根据笔试id删除对应的笔试
     */
    @Delete("delete from exam where exam_id=#{exam_id}")
    void deleteExamById(String exam_id);


    /**
     *
     * @param exam_id
     * @return
     * 根据笔试id获取对应的笔试
     */
    @Select("select * from exam where exam_id=#{exam_id}")
     exam getOneExamInfo(String exam_id);



    /**
     *
     * @param exam_id
     * @return
     * 根据笔试id获取参加笔试的所有候选人信息
     */
    @Select("select * from exam_user where exam_id=#{exam_id}")
    List<exam_user>getExamUserListById(String exam_id);



    /**
     *
     * @param exam_user
     * @return
     * 添加笔试候选人
     */
    @Insert("insert into exam_user values(#{access_code},#{exam_id}," +
            "#{candidate_name},#{candidate_phone},#{candidate_mail}," +
            "#{exam_notice},#{exam_user_score},#{exam_user_state})")
    void addExamCandidate(exam_user exam_user);



    /**
     *
     * @param exam_id
     * @param exam_candidate_number
     * 对应的笔试新增候选人的数量字段
     */
    @Update("update exam set exam_user_number=#{exam_candidate_number} where exam_id=#{exam_id}")
    void updateExamCandidateNumber(String exam_id,int exam_candidate_number);
}
