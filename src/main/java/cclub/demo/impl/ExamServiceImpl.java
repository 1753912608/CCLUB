package cclub.demo.impl;

import cclub.demo.dao.exam.exam;
import cclub.demo.mapper.ExamMapper;
import cclub.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;


    @Override
    public int createExam(exam exam) {
        try{
            examMapper.createExam(exam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<exam> getMyCreatedExamList(String user_id) {
        return examMapper.getMyCreatedExam(user_id);
    }
}
