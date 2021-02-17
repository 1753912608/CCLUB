package cclub.demo.impl.mqServiceImpl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class workProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String message){
        rabbitTemplate.convertAndSend("examQuestion",message);
    }
}
