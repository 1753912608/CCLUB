package cclub.demo.controller;


import cclub.demo.service.recordingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class recordingController {

    @Autowired
    private recordingServiceImpl recordingService;


}
