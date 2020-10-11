package cclub.demo.service;

import cclub.demo.dao.Interview;
import cclub.demo.dao.NoticeTemplate;
import cclub.demo.dao.Rand;
import cclub.demo.mapper.InterviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
    public List<String> getInterviewUrlNotice(String company, String position, String begin_time,String candidate_name, String user_id) {
        String interview_candidate_code= Rand.getInterviewCode();
        String interview_judge_code=Rand.getInterviewCode();
        List<String>list=new ArrayList<>();
        list.add(interview_candidate_code);
        list.add(interview_judge_code);
        list.add(NoticeTemplate.candidateNotice(company,position,begin_time,interview_candidate_code,user_id));
        list.add(NoticeTemplate.judgeNotice(candidate_name,position,begin_time,interview_judge_code,user_id));
        return list;
    }

    @Override
    public List<Interview> getMyCreateInterviewList(String user_id) {
        return interviewMapper.getMyCreateInterviewList(user_id);
    }

    @Override
    public int insertCandidateResume(String resume_interview_id, String resume_content_url) {
        try{
            interviewMapper.insertCandidateResume(resume_interview_id,resume_content_url);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public void deleteInterview(String interview_id) {
        interviewMapper.deleteInterview(interview_id);
    }

    @Override
    public void deleteResume(String resume_interview_id) {
        interviewMapper.deleteResume(resume_interview_id);
    }

    @Override
    public int modifyInterviewState(String interview_id, String newState) {
        try{
            interviewMapper.modifyInterviewState(interview_id,newState);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
