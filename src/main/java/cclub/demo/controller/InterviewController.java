package cclub.demo.controller;

import cclub.demo.dao.Interview;
import cclub.demo.dao.Rand;
import cclub.demo.dao.SessionInfo;
import cclub.demo.dao.resume;
import cclub.demo.service.InterviewServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;


@Controller
public class InterviewController {

     @Autowired
     private InterviewServiceImpl interviewService;


    /**
     * 创建视频面试
     */
    @ResponseBody
    @RequestMapping(value = "/createInterview",method = RequestMethod.POST)
    public int createInterview(@RequestParam(value = "interview_begin_time",required = false) String interview_begin_time,
                               @RequestParam(value = "interview_company_name",required = false) String interview_company_name,
                               @RequestParam(value = "interview_candidate_position",required = false) String interview_candidate_position,
                               @RequestParam(value = "interview_candidate_phone",required = false) String interview_candidate_phone,
                               @RequestParam(value = "interview_candidate_name",required = false) String interview_candidate_name,
                               @RequestParam(value = "interview_candidate_mail",required = false) String interview_candidate_mail,
                               @RequestParam(value = "interview_judge_phone",required = false) String interview_judge_phone,
                               @RequestParam(value = "interview_judge_name",required = false) String interview_judge_name,
                               @RequestParam(value = "interview_judge_mail",required = false) String interview_judge_mail,
                               @RequestParam(value = "interview_recording",required = false) int interview_recording,
                               @RequestParam(value = "interview_candidate_resume",required = false) int interview_candidate_resume,
                               HttpServletRequest request,
                               @RequestParam(value = "resume",required = false) MultipartFile resume)
    {
        String newFileName=interview_company_name+' '+interview_candidate_name+' '+interview_candidate_position;
        String filename=resume.getOriginalFilename();
        String suffex=filename.substring(filename.lastIndexOf('.'));
        String filesrc="file/"+newFileName+suffex;
        HttpSession session=request.getSession();
        String interview_id= Rand.getInterviewId();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        List<String>list=interviewService.getInterviewUrlNotice(interview_company_name,interview_candidate_position,interview_begin_time,interview_candidate_name,user_id);
        Interview interview=new Interview(interview_id,user_id,interview_begin_time,interview_company_name,
                interview_candidate_position,interview_candidate_phone,interview_candidate_name,
                interview_candidate_mail,interview_judge_phone,interview_judge_name,interview_judge_mail,
                interview_recording,interview_candidate_resume,list.get(3),list.get(2),list.get(0),list.get(1),"00");
        try{
            //事务提交处理
            interviewService.createInterview(interview);
            FileUtils.copyInputStreamToFile(resume.getInputStream(),new File("src/main/resources/static/"+filesrc));
            interviewService.insertCandidateResume(interview_id,filesrc);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


    /**
     *
     * @param request
     * @return
     * 获取当前用户创建的视频面试
     */
    @ResponseBody
    @RequestMapping("/getMyCreateInterviewList")
    public List<Interview>getMyCreateInterviewList(HttpServletRequest request){
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return interviewService.getMyCreateInterviewList(user_id);
    }


    /**
     *
     * @param interview_id
     * @return
     * 删除指定视频面试
     */
    @ResponseBody
    @RequestMapping("/delete_interview")
    public int delete_interview(String interview_id){
        System.out.println(interview_id);
        try{
            interviewService.deleteInterview(interview_id);
            interviewService.deleteResume(interview_id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }



    /**
     *
     * @param interview_id
     * @param interview_state
     * @return
     * 修改视频面试的状态
     */
    @ResponseBody
    @RequestMapping("/modify_Interview_State")
    public int modify_Interview_State(String interview_id,String interview_state){
        if(interview_state.equals("22")){

        }
        return interviewService.modifyInterviewState(interview_id,interview_state);
    }

}
