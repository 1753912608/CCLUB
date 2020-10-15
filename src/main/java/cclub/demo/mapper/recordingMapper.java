package cclub.demo.mapper;

import cclub.demo.dao.recording;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface recordingMapper {


    /**
     *
     * @param interview_id
     * @return
     * 根据interview_id获取recording信息
     */
    @Select("select * from recording where recording_interview_id=#{interview_id}")
    recording getOneRecording(String interview_id);
}
