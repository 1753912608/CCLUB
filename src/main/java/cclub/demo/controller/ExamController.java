package cclub.demo.controller;

import cclub.demo.dao.SessionInfo;
import cclub.demo.dao.exam.exam;
import cclub.demo.dao.exam.exam_user;
import cclub.demo.dao.utils.Rand;
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
}
