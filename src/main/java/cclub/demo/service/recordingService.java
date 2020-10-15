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
}
