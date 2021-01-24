package cclub.demo.controller;

import cclub.demo.dao.SessionInfo;
import cclub.demo.dao.exam.*;
import cclub.demo.dao.utils.Rand;
import cclub.demo.dao.utils.TimeUtils;
import cclub.demo.impl.ExamServiceImpl;
import cclub.demo.impl.ExcelImpl.ExcelUtils;
import cclub.demo.impl.ThreadPoolImpl.ThreadPoolUtils;
import cclub.demo.impl.mailServiceImpl.mailDemoUtils;
import cclub.demo.impl.redisUtils.RedisServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private ThreadPoolUtils threadPoolUtils;

    @Autowired
    private mailDemoUtils mailDemoUtils;

    private static String HOSTURLEXAM="127.0.0.1:8080/test_examing/";


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
                          String exam_id,
                          HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        exam exam=new exam("",exam_name,user_id,exam_start_time,
                exam_noEntry_time,exam_longTime,exam_Upset_question,exam_Upset_answer,
                exam_jumpOut_number,exam_recording,exam_user_info,0,0,0,0);
        if(exam_id.isEmpty()){
            exam.setExam_id(Rand.getInterviewId());
            return examService.createExam(exam)==1?exam.getExam_id():"";
        }else{
            exam.setExam_id(exam_id);
            examService.updateExam(exam);
        }
        return "";
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



    /**
     *
     * @param exam_id
     * @return
     * 根据笔试id获取对应的笔试
     */
    @ResponseBody
    @RequestMapping("/getOneExamInfo")
    public exam getOneExamInfo(String exam_id){
        return examService.getOneExamInfo(exam_id);
    }



    /**
     *
     * @param exam_id
     * @param exam_name
     * @return
     * 将指定的笔试导出候选人信息
     */
    @ResponseBody
    @RequestMapping("/exportExamUserSheet")
    public void exportExamUserSheet(String exam_id,
                                   String exam_name,
                                   HttpServletResponse res)
    throws IOException{
        List<exam_user>list=examService.getExamUserListById(exam_id);
        List<Map<String,Object>>mapList=new ArrayList<>();
        String[] keys={"access_code","exam_id","candidate_name","candidate_phone","candidate_mail","exam_notice","exam_user_score","exam_user_state"};
        String[] colums={"笔试接入码","笔试id","候选人姓名","候选人电话","候选人邮箱","是否被通知","候选人分数","候选人是否进入笔试"};
        mapList=examService.createExcelRecord(list);
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        try {
            ExcelUtils.createWorkBook(mapList,keys,colums).write(os);
        }catch (IOException e){
            e.printStackTrace();
        }
        byte[] content=os.toByteArray();
        InputStream is=new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        res.reset();
        res.setContentType("application/vnd.ms-excel;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename="+ new String((exam_name + "-"+exam_id+".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = res.getOutputStream();
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            bis = new BufferedInputStream(is);
            bos=new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }


    /**
     *
     * @param exam_id
     * @param candidate_name
     * @param candidate_phone
     * @param candidate_mail
     * @param exam_start_time
     * @param exam_noEntry_time
     * @param exam_longTime
     * @param exam_name
     * @param exam_notice
     * @return
     * 单个添加候选人
     */
    @ResponseBody
    @RequestMapping("/addExamCandidate")
    public int addExamCandidate(String exam_id,
                                 String candidate_name,
                                 String candidate_phone,
                                 String candidate_mail,
                                 String exam_start_time,
                                 int exam_noEntry_time,
                                 int exam_longTime,
                                 String exam_name,
                                 int exam_notice)
    {
        String access_code=Rand.getInterviewCode();
        int result= examService.addExamCandidate(new exam_user(access_code,exam_id,candidate_phone,candidate_name,exam_notice,-1,0,candidate_mail));
        if(result==1){
            //发送笔试邀请到候选人邮箱
            mailDemoUtils.sendExamTemplateNotice(candidate_mail,exam_name,exam_start_time,exam_noEntry_time,exam_longTime,candidate_name,HOSTURLEXAM+access_code);
        }
        return result;
    }


    /**
     *
     * @param file
     * @param request
     * @return
     * 批量添加候选人的时候提交候选人Excel     */
    @ResponseBody
    @RequestMapping("/uploadCandidateExcel")
    public int uploadCandidateExcel(MultipartFile file,
                                    HttpServletRequest request)
    throws Exception
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        String fileSrc="file/"+file.getOriginalFilename();
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File("src/main/resources/static/"+fileSrc));
        if(!ExcelUtils.analysisCandidateExcel(fileSrc))return -1;
        else{
            List<List<String>>list=ExcelUtils.handleCandidateExcel(fileSrc);
            redisService.setCandidateExcel(list,user_id);
        }
        try{
            FileUtils.forceDeleteOnExit(new File("src/main/resources/static/"+fileSrc));
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


    /**
     *
     * @param exam_notice
     * @param exam_id
     * @param exam_name
     * @param exam_start_time
     * @param exam_noEntry_time
     * @param exam_longTime
     * @param request
     * @return
     * 批量添加候选人
     */
    @ResponseBody
    @RequestMapping("/addCandidateByExcel")
    public int addCandidateByExcel(int exam_notice,
                                   String exam_id,
                                   String exam_name,
                                   String exam_start_time,
                                   int exam_noEntry_time,
                                   int exam_longTime,
                                   HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        List<List<String>>list=redisService.getCandidateExcel(user_id);
        if(list==null)return 0;
        else{
            threadPoolUtils.handleExcelTask(list,exam_id,exam_notice,exam_name,exam_start_time,exam_noEntry_time,exam_longTime,HOSTURLEXAM);
        }
        return 1;
    }


    /**
     *
     * @param exam_id
     * @return
     * 根据指定的exam_id获取参加该笔试的所有候选人信息
     */
    @ResponseBody
    @RequestMapping("/getExamUserList")
    public List<exam_user>getExamUserList(String exam_id){
        return examService.getExamUserListById(exam_id);
    }


    /**
     *
     * @param access_code
     * @param exam_id
     * @return
     * 根据access_code删除对应的笔试候选人
     */
    @ResponseBody
    @RequestMapping("/deleteExamUserByAccessCode")
    public int deleteExamUserByAccessCode(String access_code,
                                          String exam_id){
        return examService.deleteExamUserByAccessCode(access_code,exam_id);
    }


    /**
     *
     * @param candidate_name
     * @param candidate_mail
     * @param exam_start_time
     * @param exam_noEntry_time
     * @param exam_longTime
     * @param exam_name
     * @param access_code
     * @return
     * 通知单个候选人笔试信息
     */
    @ResponseBody
    @RequestMapping("/noticeOneCandidte")
    public int noticeOneCandidte(String candidate_name,
                                 String candidate_mail,
                                 String exam_start_time,
                                 int exam_noEntry_time,
                                 int exam_longTime,
                                 String exam_name,
                                 String access_code)
    {
        //发送笔试邀请到候选人邮箱
        mailDemoUtils.sendExamTemplateNotice(candidate_mail,exam_name,exam_start_time,exam_noEntry_time,exam_longTime,candidate_name,HOSTURLEXAM+access_code);
        return examService.updateCandidateNotice(access_code);
    }



    /**
     *
     * @param access_code
     * @param candidate_name
     * @param candidate_phone
     * @param candidate_mail
     * @return
     * 修改候选人个人信息
     */
    @ResponseBody
    @RequestMapping("/updateExamUserInfo")
    public int updateExamUserInfo(String access_code,
                                  String candidate_name,
                                  String candidate_phone,
                                  String candidate_mail)
    {
        return examService.updateExamUserInfo(access_code,candidate_name,candidate_phone,candidate_mail);
    }



    /**
     *
     * @param exam_id
     * @param more
     * @param exam_name
     * @param exam_start_time
     * @param exam_noEntry_time
     * @param exam_longTime
     * @return
     * 批量通知候选人
     */
    @ResponseBody
    @RequestMapping("/noticeMoreCandidate")
    public int noticeMoreCandidate(String exam_id,
                                   int more,
                                   String exam_name,
                                   String exam_start_time,
                                   int exam_noEntry_time,
                                   int exam_longTime)
    {
        List<exam_user>list=examService.noticeMoreCandidate(exam_id,more);
        return threadPoolUtils.noticeMoreCandidate(list,exam_name,exam_start_time,exam_noEntry_time,exam_longTime,HOSTURLEXAM);
    }



    /**
     *
     * @param question_id
     * @param exam_id
     * @param choice_question_name
     * @param question_options
     * @param choice_question_answer
     * @param choice_question_difficult
     * @param choice_question_score
     * @param choice_question_remarks
     * @param request
     * @return
     * 添加选择题
     */
    @ResponseBody
    @RequestMapping("/addChoiceQuestion")
    public int addChoiceQuestion(String question_id,
                                 String exam_id,
                                 String choice_question_name,
                                 String[] question_options,
                                 String choice_question_answer,
                                 int choice_question_difficult,
                                 int choice_question_score,
                                 String choice_question_remarks,
                                 HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.updateChoiceQuestion(exam_id,question_id,choice_question_name,question_options,choice_question_answer,choice_question_difficult,choice_question_score,choice_question_remarks,user_id);
    }



    /**
     *
     * @param question_id
     * @param exam_id
     * @param judge_question_name
     * @param judge_question_option_false
     * @param judge_question_option_true
     * @param judge_question_answer
     * @param judge_question_score
     * @param judge_question_remarks
     * @param request
     * @return
     * 添加判断题
     */
    @ResponseBody
    @RequestMapping("/addJudgeQuestion")
    public int addJudgeQuestion(String question_id,
                                String exam_id,
                                String judge_question_name,
                                String judge_question_option_false,
                                String judge_question_option_true,
                                String judge_question_answer,
                                int judge_question_score,
                                int judge_question_difficult,
                                String judge_question_remarks,
                                HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.updateJudgeQuestion(exam_id,new judge_question(question_id,judge_question_name,judge_question_option_false,judge_question_option_true,user_id,judge_question_answer,judge_question_difficult,judge_question_score,judge_question_remarks));
    }



    /**
     *
     * @param question_id
     * @param exam_id
     * @param completion_question_name
     * @param completion_question_answer
     * @param completion_question_difficult
     * @param completion_question_score
     * @param completion_question_remarks
     * @param request
     * @return
     * 添加填空题
     */
    @ResponseBody
    @RequestMapping("/addCompletionQuestion")
    public int addCompletionQuestion(String question_id,
                                     String exam_id,
                                     String completion_question_name,
                                     String completion_question_answer,
                                     int completion_question_difficult,
                                     int completion_question_score,
                                     String completion_question_remarks,
                                     HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.updateCompletionQuestion(exam_id,new completion_question(question_id,completion_question_name,completion_question_answer,user_id,completion_question_difficult,completion_question_score,completion_question_remarks));
    }



    /**
     *
     * @param request
     * @return
     * 获取当前用户创建的选择题列表
     */
    @ResponseBody
    @RequestMapping("/getChoiceQuestionList")
    public List<choice_question>getChoiceQuestionListByUserId(HttpServletRequest request){
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.getChoiceQuestionList(user_id);
    }



    /**
     *
     * @param request
     * @return
     * 获取当前用户创建的判断题列表
     */
    @ResponseBody
    @RequestMapping("/getJudgeQuestionList")
    public List<judge_question>getJudgeQuestionListByUserId(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.getJudgeQuestionList(user_id);
    }




    /**
     * \
     * @param request
     * @return
     * 获取当前用户创建的填空题列表
     */
    @ResponseBody
    @RequestMapping("/getCompletionQuestionList")
    public List<completion_question>getCompletionQuestionListByUserId(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
        return examService.getCompletionQuestionList(user_id);
    }




    /**
     *
     * @param question_id
     * @param question_type
     * @return
     * 删除题库的试题
     */
    @ResponseBody
    @RequestMapping("/deleteMySubjectQuestion")
    public int deleteMySubjectQuestion( String question_id,
                                       int question_type)
    {
        System.out.println(question_id+" "+question_type);
        return examService.deleteMySubjectQuestion(question_id,question_type);
    }


    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的选择题列表
     */
    @ResponseBody
    @RequestMapping("/getChoiceQuestionListByExamId")
    public List<choice_question>getChoiceQuestionListByExamId(String exam_id){
        return examService.getChoiceQuestionListByExamId(exam_id);
    }



    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的判断题列表
     */
    @ResponseBody
    @RequestMapping("/getJudgeQuestionByExamId")
    public List<judge_question>getJudgeQuestionByExamId(String exam_id){
        return examService.getJudgeQuestionListByExamId(exam_id);
    }



    /**
     *
     * @param exam_id
     * @return
     * 获取当前笔试的填空题列表
     */
    @ResponseBody
    @RequestMapping("/getCompletionQuestionListByExamId")
    public List<completion_question>getCompletionQuestionListByExamId(String exam_id){
        return examService.getCompletionQuestionListByExamId(exam_id);
    }



    /**
     *
     * @param exam_id
     * @param question_id
     * @return
     * 删除笔试中的某个题目
     */
    @ResponseBody
    @RequestMapping("/deleteExamQuestion")
    public int deleteExamQuestion(String exam_id,
                                  String question_id)
    {
        return examService.deleleExamQuestion(exam_id,question_id);
    }



    /**
     *
     * @param question_id
     * @param typeQuestion
     * @return
     * 获取单个试题信息
     */
    @ResponseBody
    @RequestMapping("/getOneQuestion")
    public Object getOneQuestion(String question_id,
                                 int typeQuestion){
       if(typeQuestion==1){
           return examService.getOneChoiceQuestion(question_id);
       }else if(typeQuestion==2){
           return examService.getOneJudgeQuestion(question_id);
       }else if(typeQuestion==3){
           return examService.getOneCompletionQuestion(question_id);
       }
       return null;
    }



    /**
     *
     * @param exam_id
     * @param questionIdList
     * @return
     * 从题库中导入笔试题
     */
    @ResponseBody
    @RequestMapping("/addQuestionBySubject")
    public int addQuestionBySubject(String exam_id,
                                    String[] questionIdList){
        return examService.addQuestionBySubject(exam_id,questionIdList);
    }
}
