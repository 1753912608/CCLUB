package cclub.demo.mapper.sqlProvider;

import org.apache.ibatis.jdbc.SQL;

public class RecordingProvider {

    public String getRecordingInfo(String type,String id){
        return new SQL(){{
            if(type.equals("exam")){
                SELECT("exam_user_recording");
                FROM("exam_user");
                WHERE("access_code=#{id}");
            }else{
                SELECT("recording_video_url");
                FROM("recording");
                WHERE("recording_interview_id=#{id}");
            }
        }}.toString();
    }
}
