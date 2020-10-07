package cclub.demo.controller;

import cclub.demo.dao.Rand;
import cclub.demo.dao.SessionInfo;
import cclub.demo.service.InterviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
                               String interview_recording,
                               String interview_candidate_resume,
                               HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String interview_id= Rand.getInterviewId();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);

        return 1;
    }
}
