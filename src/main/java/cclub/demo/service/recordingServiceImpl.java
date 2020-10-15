package cclub.demo.service;

import cclub.demo.dao.recording;
import cclub.demo.mapper.recordingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class recordingServiceImpl implements recordingService{

     @Autowired
     private recordingMapper recordingMapper;

    @Override
    public List<recording> getMyCreateInterviewRecording(String user_id) {
        return recordingMapper.getMyCreateInterviewRecording(user_id);
    }
}
