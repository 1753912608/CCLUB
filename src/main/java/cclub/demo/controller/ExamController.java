package cclub.demo.controller;

import cclub.demo.dao.SessionInfo;
import cclub.demo.dao.exam.exam;
import cclub.demo.dao.utils.Rand;
import cclub.demo.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;



    /**
     *
     * @param exam_name
     * @param exam_start_time
     * @param exam_longTime
     * @param exam_noEntry_time
     * @param exam_Upset_question
     * @param exam_Upset_answer
     * @param exam_jumpOut_number
     * @param exam_recording
     * @param exam_user_info
     * @param request
     * @return
     * 创建新的笔试
     */
    @ResponseBody
    @RequestMapping("/createExam")
    public String createExam(String exam_name,
                          String exam_start_time,
                          int exam_longTime,
                          int exam_noEntry_time,
                          int exam_Upset_question,
                          int exam_Upset_answer,
                          int exam_jumpOut_number,
                          int exam_recording,
                          String exam_user_info,
                          HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String exam_id=Rand.getInterviewId();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        exam exam=new exam(exam_id,exam_name,user_id,exam_start_time,
                exam_noEntry_time,exam_longTime,exam_Upset_question,exam_Upset_answer,
                exam_jumpOut_number,exam_recording,exam_user_info,0,0,0);
        return examService.createExam(exam)==1?exam_id:"";
    }



    /**
     *
     * @param request
     * @return
     * 获取当前用户创建的所有笔试
     */
    @ResponseBody
    @RequestMapping("/getMyCreatedExam")
    public List<exam>getMyCreatedExam(HttpServletRequest request){
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.getMyCreatedExamList(user_id);
    }



    /**
     *
     * @param exam_id
     * 根据笔试id删除对应的笔试
     */
    @ResponseBody
    @RequestMapping("/deleteExamById")
    public int deleteExamById(String exam_id){
        return examService.deleteExamById(exam_id);
    }
}
