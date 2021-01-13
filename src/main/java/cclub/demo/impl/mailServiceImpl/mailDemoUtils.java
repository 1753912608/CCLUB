package cclub.demo.impl.mailServiceImpl;
import cclub.demo.config.mailConfig;
import cclub.demo.dao.exam.exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class mailDemoUtils {

    @Autowired
    private MailOperation mailOperation;

    private String user=mailConfig.USERNAME;
    private String password=mailConfig.PASSWORD;
    private String host="smtp.qq.com";
    private String from="1753972608@qq.com";
    private String subject="CCLUB笔试面试平台";

    public void sendTemplateNoticeCancel(String to,String name,String position,String company,String user_id)
    {
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>亲爱的"+name+"同学你好,由于特殊原因,已取消"+company+"公司的"+position+"职位,具体情况可联系hr:"+user_id+"<br/><h2 style='color:green'></h2><br/>本邮件由系统自动发出，请勿回复。<br/>感谢您的使用。<br/>茗少集团科技有限公司</div>"
                +"</div>");
        send(user,password,host,from,to,sb);
    }


    public void sendModifyTemplateNotice(String to,String content){
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'><br/>感谢您的使用。<br/>茗少集团科技有限公司</div>"
                +"</div>");
        send(user,password,host,from,to,sb);
    }


    public void sendExamTemplateNotice(String to, String exam_name,String exam_start_time,int exam_noEntry_time,int exam_longTime,String candidate_name){
        //笔试邮箱内容
        StringBuffer buffer=new StringBuffer("");
        buffer.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>亲爱的"+candidate_name+"<br/>现邀请你参加"+exam_name+"笔试<br/>笔试开始时间:"+exam_start_time+"<br/>"+exam_noEntry_time+"分钟后禁止进入笔试<br/>考试时长为:"+exam_longTime+"<br/>茗少集团科技有限公司</div>"
                +"</div>");
        send(user,password,host,from,to,buffer);
    }

    private void send(String user,String password,String host,String from,String to,StringBuffer sb) {
        try {
            String res = this.mailOperation.sendMail(user, password, host, from, to,
                    subject, sb.toString());
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
