package cclub.demo.mapper.sqlProvider;

import org.apache.ibatis.jdbc.SQL;

public class ExamProvider {


    /**
     *
     * @param exam_id
     * @param more
     * @return
     * 获取批量通知候选人的列表
     */
    public String getNoticeList(String exam_id,int more){
        return new SQL(){{
            SELECT("*");
            FROM("exam_user");
            if(more==0){
                WHERE("exam_notice=#{more}");
            }
            WHERE("exam_id=#{exam_id}");
        }}.toString();
    }
}
