package cclub.demo.impl.mqServiceImpl;


import cclub.demo.impl.ThreadPoolImpl.ThreadPoolUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queuesToDeclare = @Queue("examQuestion"))
public class workConsumer {


    @Autowired
    private ThreadPoolUtils threadPoolUtils;

    @RabbitHandler
    public void reserve(String message){
        System.out.println("rabbitmq接收消息");
        threadPoolUtils.handleExamQuestion(message.split(" ")[0],message.split(" ")[1]);
    }
}
