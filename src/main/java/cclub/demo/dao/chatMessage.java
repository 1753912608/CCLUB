package cclub.demo.dao;

import javax.websocket.Session;

public class chatMessage {
    private String interview_id;
    private Session session;

    public chatMessage(String interview_id, Session session) {
        this.interview_id = interview_id;
        this.session = session;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getInterview_id() {
        return interview_id;
    }

    public Session getSession() {
        return session;
    }
}
