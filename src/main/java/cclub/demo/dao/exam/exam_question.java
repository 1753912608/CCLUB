package cclub.demo.dao.exam;

public class exam_question {
    private String exam_id;
    private int  question_number;

    public exam_question(String exam_id, int question_number) {
        this.exam_id = exam_id;
        this.question_number = question_number;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public String getExam_id() {
        return exam_id;
    }

    public int getQuestion_number() {
        return question_number;
    }
}
