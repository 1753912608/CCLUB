package cclub.demo.service;

import cclub.demo.dao.recording;

public interface recordingService {


    /**
     *
     * @param interview_id
     * @return
     * 根据interview_id获取recording信息
     */
    recording getOneRecording(String interview_id);
}
