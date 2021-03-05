package cclub.demo.impl;

import cclub.demo.dao.recording;
import cclub.demo.mapper.recordingMapper;
import cclub.demo.service.recordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class recordingServiceImpl implements recordingService {

     @Autowired
     private recordingMapper recordingMapper;


    @Override
    public List<recording> getMyCreateInterviewRecording(String user_id) {
        return recordingMapper.getMyCreateInterviewRecording(user_id);
    }

    @Override
    public String getRecordingInfo(String type, String id) {
        return recordingMapper.getRecordingInfo(type,id);
    }

    @Override
    public void saveInterviewRecording(String interview_id, String interview_time_length, String fileSrc) {
        recordingMapper.saveInterviewRecording(interview_id,interview_time_length,fileSrc);
    }
}
