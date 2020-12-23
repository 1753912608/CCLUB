package cclub.demo.impl.websocket;

import cclub.demo.dao.Interview;
import cclub.demo.dao.chat;
import cclub.demo.impl.InterviewServiceImpl;
import cclub.demo.mapper.InterviewMapper;
import cclub.demo.service.InterviewService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 前后端交互的类实现消息的接收推送(自己发送给自己)
 *
 * @ServerEndpoint(value = "/test/one") 前端通过此URI和后端交互，建立连接
 */
@ServerEndpoint(value = "/test/one/{interview_id}")
@Component
public class OneWebSocket {


    private static ConcurrentHashMap<String, ArrayList<Session>>webSocketMap=new ConcurrentHashMap<>();


    private static CopyOnWriteArraySet<Session>webSocketSet=new CopyOnWriteArraySet<>();

    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("interview_id")String interview_id,
                       Session session) {
        if(interview_id.equals("ming")){
            webSocketSet.add(session);
        }else{
            ArrayList<Session>sessionArrayList=new ArrayList<>();
            sessionArrayList.add(session);
            onlineCount.incrementAndGet(); // 在线数加1
            if(webSocketMap.get(interview_id)==null){
                webSocketMap.put(interview_id,sessionArrayList);
            }else{
                sessionArrayList.addAll(webSocketMap.get(interview_id));
                webSocketMap.put(interview_id,sessionArrayList);
            }
            brocast();
        }
        //log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {

        onlineCount.decrementAndGet(); // 在线数减1
        //log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
       // log.info("服务端收到客户端[{}]的消息:{}", session.getId(), chatMessage);
        System.out.println(session.getId()+"   "+message);
        chat chat= JSON.parseObject(message,chat.class);
        String interview_id=chat.getInterview_id();
        ArrayList<Session>sessionArrayList=webSocketMap.get(interview_id);
        for(Session session1:sessionArrayList){
            this.sendMessage(message,session1);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            //log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), chatMessage);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            //log.error("服务端发送消息给客户端失败：{}", e);
        }
    }

    /**
     * 当有面试官/候选人(进入或者退出)面试房间后更新当前面试的状态
     */
    private void brocast(){
        for(Session session:webSocketSet){
            try {
                session.getBasicRemote().sendText("ming");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
