package cclub.demo.controller;

import cclub.demo.dao.Interview;
import cclub.demo.dao.Rand;
import cclub.demo.dao.SessionInfo;
import cclub.demo.service.InterviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class InterviewController {

     @Autowired
     private InterviewServiceImpl interviewService;


    /**
     * 创建视频面试
     */
    @ResponseBody
    @RequestMapping("/createInterview")
    public int createInterview(String interview_begin_time,
                               String interview_company_name,
                               String interview_candidate_position,
                               String interview_candidate_phone,
                               String interview_candidate_name,
                               String interview_candidate_mail,
                               String interview_judge_phone,
                               String interview_judge_name,
                               String interview_judge_mail,
                               int interview_recording,
                               int interview_candidate_resume,
                               HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String interview_id= Rand.getInterviewId();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        List<String>list=interviewService.getInterviewUrlNotice(interview_company_name,interview_candidate_position,interview_begin_time,interview_candidate_name,user_id);
        Interview interview=new Interview(interview_id,user_id,interview_begin_time,interview_company_name,
                interview_candidate_position,interview_candidate_phone,interview_candidate_name,
                interview_candidate_mail,interview_judge_phone,interview_judge_name,interview_judge_mail,
                interview_recording,interview_candidate_resume,list.get(3),list.get(2),list.get(0),list.get(1),"00");
        try{
            interviewService.createInterview(interview);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
