package cclub.demo.controller;

import cclub.demo.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;


}
