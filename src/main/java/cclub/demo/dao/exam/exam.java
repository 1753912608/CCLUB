package cclub.demo.dao.exam;

public class exam {
    private String exam_id;
    private String exam_name;
    private String exam_created_user_id;
    private String exam_start_time;
    private int exam_noEntry_time;
    private int exam_longTime;
    private int exam_Upset_question;
    private int exam_Upset_answer;
    private int exam_jumpOut_number;
    private int exam_recording;
    private String exam_user_info;
    private int exam_state;
    private int exam_question_number;
    private int exam_user_number;
    private int exam_score;

    public exam(String exam_id, String exam_name, String exam_created_user_id,
                String exam_start_time, int exam_noEntry_time, int exam_longTime,
                int exam_Upset_question, int exam_Upset_answer, int exam_jumpOut_number,
                int exam_recording, String exam_user_info, int exam_state,
                int exam_question_number,int exam_user_number,int exam_score) {
        this.exam_id = exam_id;
        this.exam_name = exam_name;
        this.exam_created_user_id = exam_created_user_id;
        this.exam_start_time = exam_start_time;
        this.exam_noEntry_time = exam_noEntry_time;
        this.exam_longTime = exam_longTime;
        this.exam_Upset_question = exam_Upset_question;
        this.exam_Upset_answer = exam_Upset_answer;
        this.exam_jumpOut_number = exam_jumpOut_number;
        this.exam_recording = exam_recording;
        this.exam_user_info = exam_user_info;
        this.exam_state = exam_state;
        this.exam_question_number=exam_question_number;
        this.exam_user_number=exam_user_number;
        this.exam_score=exam_score;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public void setExam_created_user_id(String exam_created_user_id) {
        this.exam_created_user_id = exam_created_user_id;
    }

    public void setExam_start_time(String exam_start_time) {
        this.exam_start_time = exam_start_time;
    }

    public void setExam_noEntry_time(int exam_noEntry_time) {
        this.exam_noEntry_time = exam_noEntry_time;
    }

    public void setExam_longTime(int exam_longTime) {
        this.exam_longTime = exam_longTime;
    }

    public void setExam_Upset_question(int exam_Upset_question) {
        this.exam_Upset_question = exam_Upset_question;
    }

    public void setExam_Upset_answer(int exam_Upset_answer) {
        this.exam_Upset_answer = exam_Upset_answer;
    }

    public void setExam_jumpOut_number(int exam_jumpOut_number) {
        this.exam_jumpOut_number = exam_jumpOut_number;
    }

    public void setExam_recording(int exam_recording) {
        this.exam_recording = exam_recording;
    }

    public void setExam_user_info(String exam_user_info) {
        this.exam_user_info = exam_user_info;
    }

    public void setExam_state(int exam_state) {
        this.exam_state = exam_state;
    }

    public String getExam_id() {
        return exam_id;
    }

    public String getExam_created_user_id() {
        return exam_created_user_id;
    }

    public String getExam_start_time() {
        return exam_start_time;
    }

    public int getExam_noEntry_time() {
        return exam_noEntry_time;
    }

    public int getExam_longTime() {
        return exam_longTime;
    }

    public int getExam_Upset_question() {
        return exam_Upset_question;
    }

    public int getExam_Upset_answer() {
        return exam_Upset_answer;
    }

    public int getExam_jumpOut_number() {
        return exam_jumpOut_number;
    }

    public int getExam_recording() {
        return exam_recording;
    }

    public String getExam_user_info() {
        return exam_user_info;
    }

    public int getExam_state() {
        return exam_state;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_question_number(int exam_question_number) {
        this.exam_question_number = exam_question_number;
    }

    public void setExam_user_number(int exam_user_number) {
        this.exam_user_number = exam_user_number;
    }

    public int getExam_question_number() {
        return exam_question_number;
    }

    public int getExam_user_number() {
        return exam_user_number;
    }

    public void setExam_score(int exam_score) {
        this.exam_score = exam_score;
    }

    public int getExam_score() {
        return exam_score;
    }
}
