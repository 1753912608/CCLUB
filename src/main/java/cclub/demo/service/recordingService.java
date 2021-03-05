package cclub.demo.service;

import cclub.demo.dao.recording;

import java.util.List;

public interface recordingService {


    /**
     *
     * @param user_id
     * @return
     * 根据user_id获取当前用户创建的视频面试的视频录制信息
     */
    List<recording>getMyCreateInterviewRecording(String user_id);


    /**
     *
     * @param type
     * @param id
     * @return
     * 获取录屏的信息
     */
    String getRecordingInfo(String type,String id);


    /**
     *
     * @param interview_id
     * @param interview_time_length
     * @param fileSrc
     * 存储视频面试的录屏信息
     */
    void saveInterviewRecording(String interview_id,String interview_time_length,String fileSrc);
}
