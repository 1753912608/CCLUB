package cclub.demo.mapper;

import cclub.demo.dao.recording;
import cclub.demo.mapper.sqlProvider.RecordingProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface recordingMapper {


    /**
     *
     * @param user_id
     * @return
     * 根据当前用户查询该用户创建的视频面试的录制信息
     */
    @Select("select recording.* from recording,interview where recording.recording_interview_id=interview.interview_id " +
            "and interview.interview_create_user_id=#{user_id}")
    List<recording>getMyCreateInterviewRecording(String user_id);


    /**
     *
     * @param type
     * @param id
     * @return
     * 获取录屏的信息
     */
    @SelectProvider(type = RecordingProvider.class,method = "getRecordingInfo")
    String getRecordingInfo(String type,String id);


    /**
     *
     * @param interview_id
     * @param interview_time_length
     * @param fileSrc
     * 存储视频面试的录屏信息
     */
    @Insert("insert into recording values(#{interview_id},#{interview_time_length},#{fileSrc})")
    void saveInterviewRecording(String interview_id,String interview_time_length,String fileSrc);
}
