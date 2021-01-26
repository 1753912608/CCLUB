package cclub.demo.impl.ThreadPoolImpl;

import cclub.demo.dao.exam.exam;
import cclub.demo.dao.exam.exam_user;
import cclub.demo.dao.utils.Rand;
import cclub.demo.impl.ExamServiceImpl;
import cclub.demo.impl.mailServiceImpl.mailDemoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ThreadPoolUtils {
     private static ExecutorService pool;

     private static int initPoolSize=20;

     private static volatile boolean isWork=false;

     @Autowired
     private ExamServiceImpl examService;

     @Autowired
     private mailDemoUtils mailDemoUtils;

     public static void initPool(){
         if(!isWork){
             pool=Executors.newFixedThreadPool(initPoolSize);
             isWork=true;
         }
     }

     public int handleExcelTask(List<List<String>>list,String exam_id,int exam_notice,String exam_name,String exam_start_time,int exam_noEntry_time,int exam_longTime,String url){
         List<String>nameList=list.get(0);
         List<String>phoneList=list.get(1);
         List<String>mailList=list.get(2);
         initPool();
         for(int i=0;i<nameList.size();i++){
             final int index=i;
             pool.execute(new Runnable() {
                 @Override
                 public void run() {
                     String access_code=Rand.getInterviewCode();
                     examService.addExamCandidate(new exam_user(access_code,exam_id,phoneList.get(index),nameList.get(index),exam_notice,-1,0,mailList.get(index),0));
                     if(exam_notice==1){
                         //发送邮件到候选人邮箱
                         mailDemoUtils.sendExamTemplateNotice(mailList.get(index),exam_name,exam_start_time,exam_noEntry_time,exam_longTime,nameList.get(index),url+exam_id);
                     }
                 }
             });
         }
         return 1;
     }



     public int noticeMoreCandidate(List<exam_user>list,String exam_name,String exam_start_time,int exam_noEntry_time,int exam_longTime,String url){
         initPool();
         for(int i=0;i<list.size();i++){
             final int index=i;
             pool.execute(new Runnable() {
                 @Override
                 public void run() {
                     examService.updateCandidateNotice(list.get(index).getAccess_code());
                     mailDemoUtils.sendExamTemplateNotice(list.get(index).getCandidate_mail(),exam_name,exam_start_time,exam_noEntry_time,exam_longTime,list.get(index).getCandidate_name(),url+list.get(index).getAccess_code());
                 }
             });
         }
         return 1;
     }
}
