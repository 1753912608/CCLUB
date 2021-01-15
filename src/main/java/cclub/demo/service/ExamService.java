package cclub.demo.service;

import cclub.demo.dao.exam.exam;
import cclub.demo.dao.exam.exam_user;

import java.util.List;
import java.util.Map;


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



    /**
     *
     * @param exam_id
     * @return
     * 根据笔试id获取参加笔试的所有候选人信息
     */
    List<exam_user>getExamUserListById(String exam_id);



    /**
     *
     * @param list
     * @return
     * 生成Excel数据
     */
    List<Map<String,Object>>createExcelRecord(List<exam_user>list);



    /**
     *
     * @param exam_user
     * @return
     * 添加笔试候选人
     */
    int addExamCandidate(exam_user exam_user);



    /**
     *
     * @param access_code
     * @param exam_id
     * @return
     * 根据access_code删除对应的笔试候选人
     */
    int deleteExamUserByAccessCode(String access_code,String exam_id);



    /**
     *
     * @param access_code
     * @return
     * 更新候选人当前笔试是否被通知
     */
    int updateCandidateNotice(String access_code);

}
