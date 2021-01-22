package cclub.demo.service;

import cclub.demo.dao.exam.*;

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



    /**
     *
     * @param access_code
     * @param candidate_name
     * @param candidate_phone
     * @param candidate_mail
     * @return
     * 修改候选人个人信息
     */
    int updateExamUserInfo(String access_code,String candidate_name,String candidate_phone,String candidate_mail);



    /**
     *
     * @param exam_id
     * @param more
     * @return
     * 批量通知候选人
     */
    List<exam_user>noticeMoreCandidate(String exam_id,int more);



    /**
     *
     * @param exam_id
     * @param question_id
     * @param choice_question_name
     * @param question_options
     * @param choice_question_answer
     * @param choice_question_difficult
     * @param choice_question_score
     * @param choice_question_remarks
     * @param user_id
     * @return
     * 添加选择题
     */
    int addChoiceQuestion(String exam_id,String question_id,String choice_question_name,String[] question_options,String choice_question_answer,int choice_question_difficult,int choice_question_score,String choice_question_remarks,String user_id);



    /**
     *
     * @param exam_id
     * @param judge_question
     * @return
     * 添加判断题
     */
    int addJudgeQuestion(String exam_id, judge_question judge_question);



    /**
     *
     * @param exam_id
     * @param completion_question
     * @return
     * 添加填空题
     */
    int addCompletionQuestion(String exam_id, completion_question completion_question);



    /**
     *
     * @param user_id
     * @return
     * 获取当前用户创建的选择题列表
     */
    List<choice_question>getChoiceQuestionList(String user_id);



    /**
     *
     * @param user_id
     * @return
     * 获取当前用户创建的判断题列表
     */
    List<judge_question>getJudgeQuestionList(String user_id);


    /**
     *
     * @param user_id
     * @return
     * 获取当前用户创建的填空题列表
     */
    List<completion_question>getCompletionQuestionList(String user_id);



    /**
     *
     * @param question_id
     * @param question_type
     * @return
     * 删除题库的试题
     */
    int deleteMySubjectQuestion(String question_id,int question_type);


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的选择题列表
     */
    List<choice_question>getChoiceQuestionListByExamId(String exam_id);


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的判断题列表
     */
    List<judge_question>getJudgeQuestionListByExamId(String exam_id);


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的填空题列表
     */
    List<completion_question>getCompletionQuestionListByExamId(String exam_id);



    /**
     *
     * @param exam_id
     * @param question_id
     * @return
     * 删除笔试中的某个题目
     */
    int deleleExamQuestion(String exam_id,String question_id);
}
