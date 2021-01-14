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

     @Autowired
     private ExamServiceImpl examService;

     @Autowired
     private mailDemoUtils mailDemoUtils;

     public static void initPool(){
         pool=Executors.newFixedThreadPool(initPoolSize);
     }

     public int handleExcelTask(List<List<String>>list,String exam_id,int exam_notice,String exam_name,String exam_start_time,int exam_noEntry_time,int exam_longTime,String url){
         List<String>nameList=list.get(0);
         List<String>phoneList=list.get(1);
         List<String>mailList=list.get(2);
         initPoolSize=Math.min(initPoolSize,nameList.size());
         initPool();
         for(int i=0;i<nameList.size();i++){
             final int index=i;
             pool.execute(new Runnable() {
                 @Override
                 public void run() {
                     String access_code=Rand.getInterviewCode();
                     examService.addExamCandidate(new exam_user(access_code,exam_id,phoneList.get(index),nameList.get(index),exam_notice,-1,0,mailList.get(index)));
                     if(exam_notice==1){
                         //发送邮件到候选人邮箱
                         mailDemoUtils.sendExamTemplateNotice(mailList.get(index),exam_name,exam_start_time,exam_noEntry_time,exam_longTime,nameList.get(index),url+access_code);
                     }
                 }
             });
         }
         pool.shutdown();
         return 1;
     }
}
