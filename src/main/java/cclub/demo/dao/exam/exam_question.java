package cclub.demo.dao.exam;

public class exam_question {
    private String exam_id;
    private String question_id;

    public exam_question(String exam_id, String question_id) {
        this.exam_id = exam_id;
        this.question_id = question_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getExam_id() {
        return exam_id;
    }

    public String getQuestion_id() {
        return question_id;
    }
}
