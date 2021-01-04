package cclub.demo.mapper;

import cclub.demo.dao.exam.exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ExamMapper {

    /**
     *
     * @param exam
     * @return
     * 新建笔试
     */
    @Insert("insert into exam values(#{exam_id},#{exam_name},#{exam_created_user_id},#{exam_start_time}," +
            "#{exam_noEntry_time},#{exam_longTime},#{exam_Upset_question}," +
            "#{exam_Upset_answer},#{exam_jumpOut_number},#{exam_recording}," +
            "#{exam_user_info},#{exam_state})")
    void createExam(exam exam);



    /**
     *
     * @param user_id
     * @return
     * 获取当前当前用户创建的所有笔试
     */
    @Select("select *  from exam where exam_created_user_id=#{user_id}")
    List<exam>getMyCreatedExam(String user_id);
}
