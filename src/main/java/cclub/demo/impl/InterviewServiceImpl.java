package cclub.demo.impl;

import cclub.demo.dao.Interview;
import cclub.demo.dao.NoticeTemplate;
import cclub.demo.dao.Rand;
import cclub.demo.dao.remarks;
import cclub.demo.dao.utils.TimeUtils;
import cclub.demo.mapper.InterviewMapper;
import cclub.demo.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

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
        return handleInterviewList(interviewMapper.getMyCreateInterviewList(user_id));
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
    @Transactional(rollbackFor = {Exception.class})
    public int deleteInterview(String interview_id) {
        try{
            interviewMapper.deleteInterview(interview_id);
            interviewMapper.deleteResume(interview_id);
            return 1;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void deleteResume(String resume_interview_id) {
        interviewMapper.deleteResume(resume_interview_id);
    }

    @Override
    public int endInterviewState(String interview_id, String newState) {
        try{
            interviewMapper.endInterviewState(interview_id,newState);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public List<remarks> getMyCreateInterviewRemarksList(String user_id) {
        return interviewMapper.getMyCreateInterviewRemarksList(user_id);
    }

    @Override
    public int setInterviewRemarks(remarks remarks) {
        try{
            interviewMapper.setInterviewRemarks(remarks);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int cancelInterview(String interview_id, String newState) {
        try{
            interviewMapper.cancelInterview(interview_id,newState);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int updateInterview(Interview interview) {
        try{
            interviewMapper.updateInterview(interview);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public String getResumeUrl(String resume_interview_id) {
        return interviewMapper.getResumeUrl(resume_interview_id);
    }

    @Override
    public Interview getOneInterviewInfo(String interview_code) {
        return interviewMapper.getOneInterviewInfo(interview_code);
    }


    /**
     *
     * @param list
     * @return
     * 将时间过期的视频面试数据进行筛选处理
     */
    private List<Interview> handleInterviewList(List<Interview>list){
        for(Interview view:list){
            if(TimeUtils.IsExpireTime(view.getInterview_begin_time())){
                view.setInterview_room_state("33");
                endInterviewState(view.getInterview_id(),"33");
            }
        }
        return list;
    }
}
