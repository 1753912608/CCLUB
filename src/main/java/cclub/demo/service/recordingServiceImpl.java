package cclub.demo.service;

import cclub.demo.dao.recording;
import cclub.demo.mapper.recordingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class recordingServiceImpl implements recordingService{

     @Autowired
     private recordingMapper recordingMapper;

    @Override
    public recording getOneRecording(String interview_id) {
        return recordingMapper.getOneRecording(interview_id);
    }
}
