package cclub.demo.controller;


import cclub.demo.dao.SessionInfo;
import cclub.demo.dao.recording;
import cclub.demo.impl.recordingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class recordingController {


   @Autowired
   private recordingServiceImpl recordingService;



    /**
     *
     * @param request
     * @return
     * 根据user_id获取当前用户创建的视频面试的视频录制信息
     */
   @ResponseBody
   @RequestMapping("/getMyCreateInterviewRecording")
   public List<recording> getMyCreateInterviewRecording(HttpServletRequest request){
       HttpSession session=request.getSession();
       String user_id=(String)session.getAttribute(SessionInfo.Session_phone);
       return recordingService.getMyCreateInterviewRecording(user_id);
   }


    /**
     *
     * @param type
     * @param id
     * @return
     * 获取录屏的信息
     */
   @ResponseBody
   @RequestMapping("/getRecordingInfo")
   public String getRecordingInfo(String type,String id){
       System.out.println(type+" "+id);
       return recordingService.getRecordingInfo(type,id);
   }

}
