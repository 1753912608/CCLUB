package cclub.demo.mapper;

import cclub.demo.dao.exam.*;
import cclub.demo.mapper.sqlProvider.ExamProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ExamMapper {


    /**
     *
     * @param exam
     * 新增笔试
     */
    @Insert("insert into exam values(#{exam_id},#{exam_name},#{exam_created_user_id}," +
            "#{exam_start_time},#{exam_noEntry_time},#{exam_longTime}," +
            "#{exam_Upset_question},#{exam_Upset_answer},#{exam_jumpOut_number}," +
            "#{exam_recording},#{exam_user_info},#{exam_state},#{exam_question_number}," +
            "#{exam_user_number},#{exam_score})")
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
            "exam_user_number=#{exam_user_number}  where exam_id=#{exam_id}")
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
            "#{exam_notice},#{exam_user_score},#{exam_user_state}," +
            "#{exam_user_skip_number})")
    void addExamCandidate(exam_user exam_user);



    /**
     *
     * @param exam_id
     * @param exam_candidate_number
     * 对应的笔试新增候选人的数量字段
     */
    @Update("update exam set exam_user_number=exam_user_number+#{exam_candidate_number} where exam_id=#{exam_id}")
    void updateExamCandidateNumber(String exam_id,int exam_candidate_number);



    /**
     *
     * @param access_code
     * 根据access_code删除对应的笔试候选人
     */
    @Delete("delete from exam_user where access_code=#{access_code}")
    void deleteExamUser(String access_code);


    /**
     *
     * @param access_code
     * @return
     * 更新候选人当前笔试是否被通知
     */
    @Update("update exam_user set exam_notice=1 where access_code=#{access_code}")
    void updateCandidateNotice(String access_code);


    /**
     *
     * @param access_code
     * @param candidate_name
     * @param candidate_phone
     * @param candidate_mail
     * @return
     * 修改候选人个人信息
     */
    @Update("update exam_user set candidate_name=#{candidate_name}," +
            "candidate_phone=#{candidate_phone},candidate_mail=#{candidate_mail}" +
            " where access_code=#{access_code}")
    void updateExamUserInfo(String access_code,String candidate_name,String candidate_phone,String candidate_mail);


    /**
     * \
     * @param exam_id
     * @param more
     * @return
     * 获取批量通知候选人的列表
     */
    @SelectProvider(type = ExamProvider.class,method = "getNoticeList")
    List<exam_user>getCandidateNoticeList(@Param("exam_id") String exam_id,@Param("more") int more);




    /**
     *
     * @param question_id
     * @param choice_question_name
     * @param question_options
     * @param choice_question_answer
     * @param choice_question_difficult
     * @param choice_question_score
     * @param choice_question_remarks
     * @param user_id
     * 添加选择题
     */
    @InsertProvider(type = ExamProvider.class,method = "addChoiceQuestion")
    void addChoiceQuestion(String question_id,
                           String choice_question_name,
                           String[] question_options,
                           String choice_question_answer,
                           int choice_question_difficult,
                           int choice_question_score,
                           String choice_question_remarks,
                           String user_id);



    /**
     *
     * @param exam_id
     * @param updateNumber
     * 更新笔试的题目数量
     */
    @Update("update exam set exam_question_number=exam_question_number+#{updateNumber} where exam_id=#{exam_id}")
    void updateExamQuestionNumber(String exam_id,int updateNumber);




    /**
     *
     * @param question_id
     * @param exam_id
     * 绑定新增的试题与对应的笔试
     */
    @Insert("insert into exam_question values(#{exam_id},#{question_id})")
    void insertExamQuestion(String question_id,String exam_id);


    /**
     *
     * @param judge_question
     * 添加判断题
     */
    @Insert("insert into judge_question values(#{judge_question_id}," +
            "#{judge_question_name},#{judge_question_option_false}," +
            "#{judge_question_option_true},#{judge_question_created_user_id}," +
            "#{judge_question_answer},#{judge_question_difficult}," +
            "#{judge_question_score},#{judge_question_remarks})")
    void addJudgeQuestion(judge_question judge_question);




    /**
     *
     * @param completion_question
     * 添加填空题
     */
    @Insert("insert into completion_question values(#{completion_question_id}," +
            "#{completion_question_name},#{completion_question_answer}," +
            "#{completion_question_created_user_id},#{completion_question_difficult}," +
            "#{completion_question_score},#{completion_question_remarks})")
    void addCompletionQuestion(completion_question completion_question);



    /**
     *
     * @param user_id
     * @return
     * 获取题库选择题列表
     */
    @Select("select * from choice_question where choice_question_created_user_id=#{user_id}")
    List<choice_question>getChoiceQuestionList(String user_id);



    /**
     *
     * @param user_id
     * @return
     * 获取题库判断题列表
     */
    @Select("select * from judge_question where judge_question_created_user_id=#{user_id}")
    List<judge_question>getJudgeQuestionList(String user_id);



    /**
     *
     * @param user_id
     * @return
     * 获取填空题列表
     */
    @Select("select * from completion_question where completion_question_created_user_id=#{user_id}")
    List<completion_question>getCompletionQuestionList(String user_id);



    /**
     *
     * @param question_id
     * @param question_type
     * 删除题库试题
     */
    @DeleteProvider(type = ExamProvider.class,method = "deleteMySubjectQuestion")
    void deleteMySubjectQuestion(String question_id,int question_type);



    /**
     *
     * @param exam_id
     * 删除笔试时,删除所有绑定该笔试的试题id
     */
    @Delete("delete from exam_question where exam_id=#{exam_id}")
    void deleteExamQuestion(String exam_id);


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的选择题列表
     */
    @Select("select choice_question.* from choice_question,exam_question where choice_question.choice_question_id=exam_question.question_id and exam_question.exam_id=#{exam_id}")
    List<choice_question>getChoiceQuestionListByExamId(String exam_id);


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的判断题列表
     */
    @Select("select judge_question.* from judge_question,exam_question where judge_question.judge_question_id=exam_question.question_id and exam_question.exam_id=#{exam_id}")
    List<judge_question>getJudgeQuestionListByExamId(String exam_id);


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的填空题列表
     */
    @Select("select completion_question.* from completion_question,exam_question where completion_question.completion_question_id=exam_question.question_id and exam_question.exam_id=#{exam_id}")
    List<completion_question>getCompletionQuestionListByExamId(String exam_id);


    /**
     *
     * @param exam_id
     * @param question_id
     * @return
     * 删除笔试中的某个题目
     */
    @Delete("delete from exam_question where exam_id=#{exam_id} and question_id=#{question_id}")
    int deleteExamOneQuestion(String exam_id,String question_id);




    /**
     *
     * @param question_id
     * @return
     * 获取单个选择题信息
     */
    @Select("select * from choice_question where choice_question_id=#{question_id}")
    choice_question getOneChoiceQuestion(String question_id);



    /**
     *
     * @param question_id
     * @return
     * 获取单个判断题信息
     */
    @Select("select * from judge_question where judge_question_id=#{question_id}")
    judge_question getOneJudgeQuestion(String question_id);


    /**
     *
     * @param question_id
     * @return
     * 获取单个填空题信息
     */
    @Select("select * from completion_question where completion_question_id=#{question_id}")
    completion_question getOneCompletionQuestion(String question_id);


    /**
     *
     * @param question_id
     * @param choice_question_name
     * @param question_options
     * @param choice_question_answer
     * @param choice_question_difficult
     * @param choice_question_score
     * @param choice_question_remarks
     * 更新选择题信息
     */
    @UpdateProvider(type = ExamProvider.class,method = "updateChoiceQuestion")
    void updateChoiceQuestion(String question_id,
                              String choice_question_name,
                              String[] question_options,
                              String choice_question_answer,
                              int choice_question_difficult,
                              int choice_question_score,
                              String choice_question_remarks);


    /**
     *
     * @param judge_question
     * 更新判断题信息
     */
    @Update("update judge_question set judge_question_name=#{judge_question_name}," +
            "judge_question_option_false=#{judge_question_option_false}," +
            "judge_question_option_true=#{judge_question_option_true}," +
            "judge_question_answer=#{judge_question_answer}," +
            "judge_question_difficult=#{judge_question_difficult}," +
            "judge_question_score=#{judge_question_score}," +
            "judge_question_remarks=#{judge_question_remarks} " +
            "where judge_question_id={judge_question_id}")
    void updateJudgeQuestion(judge_question judge_question);



    /**
     *
     * @param completion_question
     * 更新填空题信息
     */
    @Update("update completion_question set completion_question_name=#{completion_question_name}," +
            "completion_question_answer=#{completion_question_answer}," +
            "completion_question_difficult=#{completion_question_difficult}," +
            "completion_question_score=#{completion_question_score}," +
            "completion_question_remarks=#{completion_question_remarks} " +
            "where completion_question_id=#{completion_question_id}")
    void updateCompletionQuestion(completion_question completion_question);



    /**
     *
     * @param exam_id
     * @return
     * 获取一个笔试的所有试题id
     */
    @Select("select question_id from exam_question where exam_id=#{exam_id}")
    List<String>getExamQuestionId(String exam_id);



    /**
     *
     * @param exam_id
     * @param newState
     * 更新笔试的状态
     */
    @Update("update exam set exam_state=#{newState} where exam_id=#{exam_id}")
    void updateExamState(String exam_id,int newState);



    /**
     *
     * @param exam_id
     * @param name
     * @param phone
     * @param mail
     * @return
     */
    @Select("select * from exam_user where candidate_name=#{name} " +
            "and candidate_phone=#{phone} and candidate_mail=#{mail} " +
            "and exam_id=#{exam_id}")
    exam_user judgeExamUserInfoExist(String exam_id,String name,String phone,String mail);



    /**
     *
     * @param exam_id
     * @param candidate_name
     * @param candidate_phone
     * @param candidate_mail
     * @param newState
     * 当用户进入笔试房间后,更新用户的状态
     */
    @Update("update exam_user set exam_user_state=#{newState} where " +
            "exam_id=#{exam_id} and candidate_name=#{candidate_name} " +
            "and candidate_phone=#{candidate_phone} and candidate_mail=#{candidate_mail}")
    void updateExamUserState(String exam_id,String candidate_name,String candidate_phone,String candidate_mail,int newState);


    /**
     *
     * @param exam_id
     * @param exam_user_mail
     * @param newNumber
     * @return
     * 更新用户切换页面的次数
     */
    @Update("update exam_user set exam_user_skip_number=#{newNumber} where exam_id=#{exam_id} and candidate_mail=#{exam_user_mail}")
    void updateExamUserSkipNumber(String exam_id,String exam_user_mail,int newNumber);


    /**
     *
     * @param exam_id
     * @param exam_user_mail
     * @return
     * 获取用户笔试中切换页面的次数
     */
    @Select("select exam_user_skip_number from exam_user where exam_id=#{exam_id} and candidate_mail=#{exam_user_mail}")
    int getExamUserSkipNumber(String exam_id,String exam_user_mail);


    /**
     *
     * @param exam_id
     * @param user_id
     * @param recording_url
     * 更新用户的笔试录屏
     */
    @Update("update exam_user set exam_user_recording=#{recording_url} where exam_id=#{exam_id} candidate_phone=#{user_id}")
    void updateExamUserRecordingUrl(String exam_id,String user_id,String recording_url);


    /**
     *
     * @param exam_id
     * @param user_id
     * @param newState
     * 结束笔试后更新用户的新状态
     */
    @Update("update exam_user set exam_user_state=#{newState} where exam_id=#{exam_id} and candidate_phone=#{user_id}")
    void updateExamUserState(String exam_id,String user_id,int newState);
}
