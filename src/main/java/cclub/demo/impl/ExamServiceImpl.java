package cclub.demo.impl;

import cclub.demo.dao.exam.exam;
import cclub.demo.dao.exam.exam_question;
import cclub.demo.mapper.ExamMapper;
import cclub.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<exam> getMyCreatedExamList(String user_id) {
        return examMapper.getMyCreatedExam(user_id);
    }

    @Override
    public List<Integer> queryExamQuestionByUserId(String user_id) {
        List<Integer>examQuestionList=new ArrayList<>();
        return examQuestionList;
    }

    @Override
    public int deleteExamById(String exam_id) {
        try{
            examMapper.deleteExamById(exam_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public exam getOneExamInfo(String exam_id) {
        return examMapper.getOneExamInfo(exam_id);
    }
}
