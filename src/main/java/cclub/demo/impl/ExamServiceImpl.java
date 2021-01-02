package cclub.demo.impl;

import cclub.demo.mapper.ExamMapper;
import cclub.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;


}
