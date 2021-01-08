package cclub.demo.service;

import cclub.demo.dao.exam.exam;

import java.util.List;


public interface ExamService {


    /**
     *
     * @param exam
     * @return
     * 新建笔试
     */
    int createExam(exam exam);



    /**
     *
     * @param exam
     * @return
     * 修改笔试
     */
    int updateExam(exam exam);


    /**
     *
     * @param user_id
     * @return
     * 根据当前用户的id获取创建的所有笔试
     */
    List<exam>getMyCreatedExamList(String user_id);



    /**
     *
     * @param user_id
     * @return
     * 根据当前用户的id获取该用户创建的所有笔试对应的题目个数
     */
    List<Integer>queryExamQuestionByUserId(String user_id);



    /**
     *
     * @param exam_id
     * 根据笔试id删除对应的笔试
     */
    int deleteExamById(String exam_id);



    /**
     *
     * @param exam_id
     * @return
     * 根据笔试id获取对应的笔试
     */
    exam getOneExamInfo(String exam_id);

}
