package cclub.demo.impl;

import cclub.demo.dao.exam.*;
import cclub.demo.dao.utils.Rand;
import cclub.demo.dao.utils.TimeUtils;
import cclub.demo.impl.redisUtils.RedisServiceImpl;
import cclub.demo.mapper.ExamMapper;
import cclub.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private RedisServiceImpl redisService;


    @Override
    public int createExam(exam exam) {
        try{
            examMapper.createExam(exam);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExam(exam exam) {
        try{
            examMapper.updateExam(exam);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<exam> getMyCreatedExamList(String user_id) {
        return handleExamList(examMapper.getMyCreatedExam(user_id));
    }

    @Override
    public List<Integer> queryExamQuestionByUserId(String user_id) {
        List<Integer>examQuestionList=new ArrayList<>();
        return examQuestionList;
    }

    @Transactional
    @Override
    public int deleteExamById(String exam_id) {
        try{
            examMapper.deleteExamById(exam_id);
            examMapper.deleteExamQuestion(exam_id);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public exam getOneExamInfo(String exam_id) {
        return examMapper.getOneExamInfo(exam_id);
    }

    @Override
    public List<exam_user> getExamUserListById(String exam_id) {
       return examMapper.getExamUserListById(exam_id);
    }

    @Override
    public List<Map<String, Object>> createExcelRecord(List<exam_user> list) {
        List<Map<String,Object>>mapList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Map<String,Object>map=new HashMap<>();
            exam_user exam_user=list.get(i);
            map.put("access_code",exam_user.getAccess_code());
            map.put("exam_id",exam_user.getExam_id());
            map.put("candidate_phone",exam_user.getCandidate_phone());
            map.put("candidate_name",exam_user.getCandidate_name());
            map.put("candidate_mail",exam_user.getCandidate_mail());
            map.put("exam_notice",exam_user.getExam_notice()==1?"已发笔试通知":"未发笔试通知");
            map.put("exam_user_score",exam_user.getExam_user_score()==-1?"---":exam_user.getExam_user_score());
            map.put("exam_user_state",exam_user.getExam_user_state()==1?"已进入笔试":"未进入笔试");
            map.put("exam_user_skip_number",exam_user.getExam_user_skip_number());
            mapList.add(map);
        }
        return mapList;
    }

    @Transactional
    @Override
    public int addExamCandidate(exam_user exam_user) {
        try{
            examMapper.addExamCandidate(exam_user);
            examMapper.updateExamCandidateNumber(exam_user.getExam_id(),1);
            return 1;
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public int deleteExamUserByAccessCode(String access_code,String exam_id) {
        try{
            examMapper.deleteExamUser(access_code);
            examMapper.updateExamCandidateNumber(exam_id,-1);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCandidateNotice(String access_code) {
        try {
            examMapper.updateCandidateNotice(access_code);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateExamUserInfo(String access_code, String candidate_name, String candidate_phone, String candidate_mail) {
        try{
            examMapper.updateExamUserInfo(access_code,candidate_name,candidate_phone,candidate_mail);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<exam_user> noticeMoreCandidate(String exam_id, int more) {
        return examMapper.getCandidateNoticeList(exam_id,more);
    }

    @Transactional
    @Override
    public int updateChoiceQuestion(String exam_id,String question_id,String choice_question_name, String[] question_options, String choice_question_answer, int choice_question_difficult, int choice_question_score, String choice_question_remarks, String user_id) {
        System.out.println(question_id);
        if(question_id.equals("")){
            try {
                question_id= Rand.getInterviewCode();
                examMapper.addChoiceQuestion(question_id,choice_question_name,question_options,choice_question_answer,choice_question_difficult,choice_question_score,choice_question_remarks,user_id);
                examMapper.updateExamQuestionNumber(exam_id,1);
                examMapper.insertExamQuestion(question_id,exam_id);
                return 1;
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        }else{
            try {
                examMapper.updateChoiceQuestion(question_id,choice_question_name,question_options,choice_question_answer,choice_question_difficult,choice_question_score,choice_question_remarks);
                return 1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Transactional
    @Override
    public int updateJudgeQuestion(String exam_id, judge_question judge_question) {
        if(judge_question.getJudge_question_id().equals("")){
            judge_question.setJudge_question_id(Rand.getInterviewCode());
            try{
                examMapper.addJudgeQuestion(judge_question);
                examMapper.updateExamQuestionNumber(exam_id,1);
                examMapper.insertExamQuestion(judge_question.getJudge_question_id(),exam_id);
                return 1;
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        }else{
            try{
                examMapper.updateJudgeQuestion(judge_question);
                return 1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Transactional
    @Override
    public int updateCompletionQuestion(String exam_id, completion_question completion_question) {
        if(completion_question.getCompletion_question_id().equals("")){
            completion_question.setCompletion_question_id(Rand.getInterviewCode());
            try{
                examMapper.addCompletionQuestion(completion_question);
                examMapper.updateExamQuestionNumber(exam_id,1);
                examMapper.insertExamQuestion(completion_question.getCompletion_question_id(),exam_id);
                return 1;
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        }else{
            try{
                examMapper.updateCompletionQuestion(completion_question);
                return 1;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public List<choice_question> getChoiceQuestionList(String user_id) {
        List<choice_question>choiceQuestionList=examMapper.getChoiceQuestionList(user_id);
        return choiceQuestionList;
    }

    @Override
    public List<judge_question> getJudgeQuestionList(String user_id) {
        return examMapper.getJudgeQuestionList(user_id);
    }

    @Override
    public List<completion_question> getCompletionQuestionList(String user_id) {
        return examMapper.getCompletionQuestionList(user_id);
    }

    @Override
    public int deleteMySubjectQuestion(String question_id, int question_type) {
        try{
            examMapper.deleteMySubjectQuestion(question_id,question_type);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<choice_question> getChoiceQuestionListByExamId(String exam_id) {
        return examMapper.getChoiceQuestionListByExamId(exam_id);
    }

    @Override
    public List<judge_question> getJudgeQuestionListByExamId(String exam_id) {
        return examMapper.getJudgeQuestionListByExamId(exam_id);
    }

    @Override
    public List<completion_question> getCompletionQuestionListByExamId(String exam_id) {
        return examMapper.getCompletionQuestionListByExamId(exam_id);
    }


    @Transactional
    @Override
    public int deleleExamQuestion(String exam_id, String question_id) {
        try {
            examMapper.deleteExamOneQuestion(exam_id,question_id);
            examMapper.updateExamQuestionNumber(exam_id,-1);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public choice_question getOneChoiceQuestion(String question_id) {
        return  examMapper.getOneChoiceQuestion(question_id);
    }

    @Override
    public judge_question getOneJudgeQuestion(String question_id) {
        return examMapper.getOneJudgeQuestion(question_id);
    }

    @Override
    public completion_question getOneCompletionQuestion(String question_id) {
        return examMapper.getOneCompletionQuestion(question_id);
    }

    @Override
    public int addQuestionBySubject(String exam_id, String[] questionIdList) {
        List<String>arr=examMapper.getExamQuestionId(exam_id);
        for(int i=0;i<questionIdList.length;i++){
            if(!arr.contains(questionIdList[i])){
                examMapper.insertExamQuestion(questionIdList[i],exam_id);
            }
        }
        return 1;
    }


    /**
     *
     * @return
     * 处理笔试列表的状态
     */
    public List<exam> handleExamList(List<exam> examList){
        for(exam exam:examList){
            int oldState=exam.getExam_state();
            exam.setExam_state(TimeUtils.ExamTimeState(exam));
            if(exam.getExam_state()!=oldState){
                examMapper.updateExamState(exam.getExam_id(),exam.getExam_state());
            }
        }
        return examList;
    }

    @Override
    public int judgeExamUserInfoExist(String exam_id, String candidate_name, String candidate_phone, String candidate_mail) {
        return examMapper.judgeExamUserInfoExist(exam_id,candidate_name,candidate_phone,candidate_mail)==null?0:1;
    }

    @Override
    public void updateExamUserState(String exam_id, String candidate_name, String candidate_phone, String candidate_mail) {
        examMapper.updateExamUserState(exam_id,candidate_name,candidate_phone,candidate_mail,1);
    }

    @Override
    public int getExamUserSkipNumber(String exam_id, String exam_user_mail) {
        int skip_number=examMapper.getExamUserSkipNumber(exam_id,exam_user_mail);
        examMapper.updateExamUserSkipNumber(exam_id,exam_user_mail,skip_number+1);
        return skip_number+1;
    }

    @Transactional
    @Override
    public void endExam(String exam_id, String user_id, String recording_url) {
        try {
            examMapper.updateExamUserRecordingUrl(exam_id,user_id,recording_url);
            examMapper.updateEndExamUserState(exam_id,user_id,2);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }

    @Override
    public exam_user getOneExamUser(String exam_id, String user_id) {
        return examMapper.getOneExamUser(exam_id,user_id);
    }

    @Override
    public void eValuteQuestion(String user_id, String exam_id) {
        List<choice_question>choice_questions=examMapper.getChoiceQuestionListByExamId(exam_id);
        List<judge_question>judge_questions=examMapper.getJudgeQuestionListByExamId(exam_id);
        List<completion_question>completion_questions=examMapper.getCompletionQuestionListByExamId(exam_id);
        for(choice_question question:choice_questions){
            String answer=redisService.getOneQuestionAnswer(exam_id,user_id,question.getChoice_question_id());
            if(answer!=null&&answer.replace(","," ").equals(question.getChoice_question_answer())) {
                examMapper.updateExamUserScore(exam_id, user_id, question.getChoice_question_score());
            }
            redisService.removeKey(exam_id+":"+user_id+":"+question.getChoice_question_id());
        }
        for(completion_question question:completion_questions){
            String answer=redisService.getOneQuestionAnswer(exam_id,user_id,question.getCompletion_question_id());
            System.out.println(answer);
            if(answer!=null&&answer.replace(","," ").equals(question.getCompletion_question_answer())){
                examMapper.updateExamUserScore(exam_id,user_id,question.getCompletion_question_score());
            }
            redisService.removeKey(exam_id+":"+user_id+":"+question.getCompletion_question_id());
        }
        for(judge_question question:judge_questions){
            String answer=redisService.getOneQuestionAnswer(exam_id,user_id,question.getJudge_question_id());
            if(answer!=null&&answer.equals(question.getJudge_question_answer())){
                examMapper.updateExamUserScore(exam_id,user_id,question.getJudge_question_score());
            }
            redisService.removeKey(exam_id+":"+user_id+":"+question.getJudge_question_id());
        }
    }

    @Override
    public void updateUserExamScore(String exam_id, String user_id, int newScore) {
        examMapper.updateExamUserScore(exam_id,user_id,newScore);
    }
}
