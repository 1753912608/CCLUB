package cclub.demo.service;

import cclub.demo.dao.Interview;
import cclub.demo.mapper.InterviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService{

    @Autowired
    private InterviewMapper interviewMapper;

    @Override
    public int createInterview(Interview interview) {
        try{
            interviewMapper.createInterview(interview);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public List<String> getInterviewUrlNotice(String company, String position, String begin_time, String user_id) {
        return null;
    }
}
