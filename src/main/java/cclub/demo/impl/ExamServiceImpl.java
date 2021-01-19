package cclub.demo.impl;

import cclub.demo.dao.exam.*;
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
        return examMapper.getMyCreatedExam(user_id);
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
    public int addChoiceQuestion(String exam_id,String question_id,String choice_question_name, String[] question_options, String choice_question_answer, int choice_question_difficult, int choice_question_score, String choice_question_remarks, String user_id) {
        try {
            examMapper.addChoiceQuestion(question_id,choice_question_name,question_options,choice_question_answer,choice_question_difficult,choice_question_score,choice_question_remarks,user_id);
            examMapper.updateExamQuestionNumber(exam_id,1);
            examMapper.insertExamQuestion(question_id,exam_id);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public int addJudgeQuestion(String exam_id, judge_question judge_question) {
        try{
            examMapper.addJudgeQuestion(judge_question);
            examMapper.updateExamQuestionNumber(exam_id,1);
            examMapper.insertExamQuestion(judge_question.getJudge_question_id(),exam_id);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Transactional
    @Override
    public int addCompletionQuestion(String exam_id, completion_question completion_question) {
        try{
            examMapper.addCompletionQuestion(completion_question);
            examMapper.updateExamQuestionNumber(exam_id,1);
            examMapper.insertExamQuestion(completion_question.getCompletion_question_id(),exam_id);
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
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
}
