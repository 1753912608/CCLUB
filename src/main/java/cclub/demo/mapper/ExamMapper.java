package cclub.demo.mapper;

import cclub.demo.dao.exam.exam;
import cclub.demo.dao.exam.exam_question;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ExamMapper {

    /**
     *
     * @param exam
     * @return
     * 新建笔试
     */
    @Update("update exam set exam_name=#{exam_name}," +
            "exam_created_user_id=#{exam_created_user_id}," +
            "exam_start_time=#{exam_start_time},exam_noEntry_time=#{exam_noEntry_time}," +
            "exam_longTime=#{exam_longTime},exam_Upset_question=#{exam_Upset_question}," +
            "exam_Upset_answer=#{exam_Upset_answer},exam_jumpOut_number=#{exam_jumpOut_number}," +
            "exam_recording=#{exam_recording},exam_user_info=#{exam_user_info}," +
            "exam_state=#{exam_state},exam_question_number=#{exam_question_number}," +
            "exam_user_number=#{exam_user_number} where exam_id=#{exam_id}")
    void createExam(exam exam);



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
}
