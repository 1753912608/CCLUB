package cclub.demo.dao.exam;

public class completion_question {
    private String completion_question_id;
    private String completion_question_name;
    private String completion_question_answer;
    private String completion_question_created_user_id;
    private int completion_question_difficult;
    private int completion_question_score;
    private String completion_question_remarks;

    public completion_question(String completion_question_id,
                               String completion_question_name,
                               String completion_question_answer,
                               String completion_question_created_user_id,
                               int completion_question_difficult,
                               int completion_question_score,
                               String completion_question_remarks) {
        this.completion_question_id = completion_question_id;
        this.completion_question_name = completion_question_name;
        this.completion_question_answer = completion_question_answer;
        this.completion_question_created_user_id = completion_question_created_user_id;
        this.completion_question_difficult = completion_question_difficult;
        this.completion_question_score = completion_question_score;
        this.completion_question_remarks = completion_question_remarks;
    }

    public void setCompletion_question_id(String completion_question_id) {
        this.completion_question_id = completion_question_id;
    }

    public void setCompletion_question_name(String completion_question_name) {
        this.completion_question_name = completion_question_name;
    }

    public void setCompletion_question_answer(String completion_question_answer) {
        this.completion_question_answer = completion_question_answer;
    }

    public void setCompletion_question_created_user_id(String completion_question_created_user_id) {
        this.completion_question_created_user_id = completion_question_created_user_id;
    }

    public void setCompletion_question_difficult(int completion_question_difficult) {
        this.completion_question_difficult = completion_question_difficult;
    }

    public void setCompletion_question_score(int completion_question_score) {
        this.completion_question_score = completion_question_score;
    }

    public void setCompletion_question_remarks(String completion_question_remarks) {
        this.completion_question_remarks = completion_question_remarks;
    }

    public String getCompletion_question_id() {
        return completion_question_id;
    }

    public String getCompletion_question_name() {
        return completion_question_name;
    }

    public String getCompletion_question_answer() {
        return completion_question_answer;
    }

    public String getCompletion_question_created_user_id() {
        return completion_question_created_user_id;
    }

    public int getCompletion_question_difficult() {
        return completion_question_difficult;
    }

    public int getCompletion_question_score() {
        return completion_question_score;
    }

    public String getCompletion_question_remarks() {
        return completion_question_remarks;
    }

    @Override
    public String toString() {
        return "completion_question{" +
                "completion_question_id='" + completion_question_id + '\'' +
                ", completion_question_name='" + completion_question_name + '\'' +
                ", completion_question_answer='" + completion_question_answer + '\'' +
                ", completion_question_created_user_id='" + completion_question_created_user_id + '\'' +
                ", completion_question_difficult=" + completion_question_difficult +
                ", completion_question_score=" + completion_question_score +
                ", completion_question_remarks='" + completion_question_remarks + '\'' +
                '}';
    }
}
