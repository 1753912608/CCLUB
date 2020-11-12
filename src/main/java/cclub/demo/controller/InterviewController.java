package cclub.demo.controller;

import cclub.demo.dao.*;
import cclub.demo.impl.InterviewServiceImpl;
import mailDemoUtils;
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
import java.io.IOException;
import java.util.List;


@Controller
public class InterviewController {

     @Autowired
     private InterviewServiceImpl interviewService;

     @Autowired
     private mailDemoUtils mailDemoUtils;


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
        String filename="",suffex="",filesrc="";
        if(interview_candidate_resume==1){
            filename=resume.getOriginalFilename();
            suffex=filename.substring(filename.lastIndexOf('.'));
            filesrc="file/"+newFileName+suffex;
        }
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
            if(interview_candidate_resume==1){
                FileUtils.copyInputStreamToFile(resume.getInputStream(),new File("src/main/resources/static/"+filesrc));
                interviewService.insertCandidateResume(interview_id,filesrc);
            }
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
        return interviewService.deleteInterview(interview_id);
    }



    /**
     *
     * @param interview_id
     * @param interview_state
     * @return
     * 结束视频面试
     */
    @ResponseBody
    @RequestMapping("/end_Interview_State")
    public int end_Interview_State(String interview_id,String interview_state){
        return interviewService.endInterviewState(interview_id,interview_state);
    }



    /**
     *
     * @param request
     * @return
     * 获取当前用户创建的所有视频面试对应的备注
     */
    @ResponseBody
    @RequestMapping("/getMyCreateInterviewRemarksList")
    public List<remarks>getMyCreateInterviewRemarksList(HttpServletRequest request){
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return interviewService.getMyCreateInterviewRemarksList(user_id);
    }



    /**
     *
     * @param interview_id
     * @param state
     * @param content
     * @return
     * 设置视频面试的备注
     */
    @ResponseBody
    @RequestMapping("/setInterviewRemarks")
    public int setInterviewRemarks(String interview_id,
                                   String state,
                                   String content)
    {
        return interviewService.setInterviewRemarks(new remarks(interview_id,state,content));
    }


    /**
     *
     * @param interview_id
     * @param company
     * @param position
     * @param isSendMail
     * @param mail
     * @param name
     * @param request
     * @return
     * 取消该视频面试
     */
    @ResponseBody
    @RequestMapping("/cancelInterview")
    public int cancelInterview(String interview_id,
                               String company,
                               String position,
                               boolean isSendMail,
                               String mail,
                               String name,
                               HttpServletRequest request)
    {
        System.out.println(interview_id+" "+company+" "+position+" "+isSendMail+" "+mail+" "+name);
        if(isSendMail){
            HttpSession session=request.getSession();
            String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
            mailDemoUtils.sendTemplateNoticeCancel(mail,name,position,company,user_id);
        }
        return interviewService.cancelInterview(interview_id,"22");
    }



    /**
     *
     * @param interview_id
     * @param company
     * @param position
     * @param recording
     * @param resume
     * @param file
     * @return
     * 修改视频面试信息
     */
    @ResponseBody
    @RequestMapping(value = "updateInterview",method = RequestMethod.POST)
    public int updateInterview(@RequestParam(value = "interview_id",required = false) String interview_id,
                               @RequestParam(value = "company",required = false) String company,
                               @RequestParam(value = "position",required = false) String position,
                               @RequestParam(value = "recording",required = false) int recording,
                               @RequestParam(value = "resume",required = false) int resume,
                               @RequestParam(value = "time",required = false) String time,
                               @RequestParam(value = "file",required = false) MultipartFile file)
    throws IOException
    {
        String ordResumeUrl=interviewService.getResumeUrl(interview_id);
        if(resume==1){
            String filename="",suffex="",filesrc="";
            if(ordResumeUrl!=null){
                try{
                    FileUtils.forceDeleteOnExit(new File("src/main/resources/static/"+ordResumeUrl));
                }catch (Exception e){
                    e.printStackTrace();
                }
                interviewService.deleteResume(interview_id);
            }
            filename=file.getOriginalFilename();
            suffex=filename.substring(filename.lastIndexOf('.'));
            filesrc="file/"+interview_id+suffex;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File("src/main/resources/static/"+filesrc));
            interviewService.insertCandidateResume(interview_id,filesrc);
        }
        return interviewService.updateInterview(new Interview(interview_id,time,company,position,recording,resume));
    }



    /**
     *
     * @param interview_code
     * @param request
     * 将用户面试码存储在session中
     */
    @ResponseBody
    @RequestMapping("/saveInterviewCode")
    public void saveInterviewCode(String interview_code,HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute(SessionInfo.Session_Interview_code,interview_code);
    }
}
