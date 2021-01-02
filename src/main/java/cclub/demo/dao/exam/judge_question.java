package cclub.demo.dao.exam;

public class judge_question {
    private String judge_question_id;
    private String judge_question_name;
    private String judge_question_option_false;
    private String judge_question_option_true;
    private String judge_question_created_user_id;
    private String judge_question_answer;
    private int judge_question_difficult;
    private int judge_question_score;
    private String judge_question_remarks;

    public judge_question(String judge_question_id,
                          String judge_question_name,
                          String judge_question_option_false,
                          String judge_question_option_true,
                          String judge_question_created_user_id,
                          String judge_question_answer,
                          int judge_question_difficult,
                          int judge_question_score,
                          String judge_question_remarks) {
        this.judge_question_id = judge_question_id;
        this.judge_question_name = judge_question_name;
        this.judge_question_option_false = judge_question_option_false;
        this.judge_question_option_true = judge_question_option_true;
        this.judge_question_created_user_id = judge_question_created_user_id;
        this.judge_question_answer = judge_question_answer;
        this.judge_question_difficult = judge_question_difficult;
        this.judge_question_score = judge_question_score;
        this.judge_question_remarks = judge_question_remarks;
    }

    public void setJudge_question_id(String judge_question_id) {
        this.judge_question_id = judge_question_id;
    }

    public void setJudge_question_name(String judge_question_name) {
        this.judge_question_name = judge_question_name;
    }

    public void setJudge_question_option_false(String judge_question_option_false) {
        this.judge_question_option_false = judge_question_option_false;
    }

    public void setJudge_question_option_true(String judge_question_option_true) {
        this.judge_question_option_true = judge_question_option_true;
    }

    public void setJudge_question_created_user_id(String judge_question_created_user_id) {
        this.judge_question_created_user_id = judge_question_created_user_id;
    }

    public void setJudge_question_answer(String judge_question_answer) {
        this.judge_question_answer = judge_question_answer;
    }

    public void setJudge_question_difficult(int judge_question_difficult) {
        this.judge_question_difficult = judge_question_difficult;
    }

    public void setJudge_question_score(int judge_question_score) {
        this.judge_question_score = judge_question_score;
    }

    public void setJudge_question_remarks(String judge_question_remarks) {
        this.judge_question_remarks = judge_question_remarks;
    }

    public String getJudge_question_id() {
        return judge_question_id;
    }

    public String getJudge_question_name() {
        return judge_question_name;
    }

    public String getJudge_question_option_false() {
        return judge_question_option_false;
    }

    public String getJudge_question_option_true() {
        return judge_question_option_true;
    }

    public String getJudge_question_created_user_id() {
        return judge_question_created_user_id;
    }

    public String getJudge_question_answer() {
        return judge_question_answer;
    }

    public int getJudge_question_difficult() {
        return judge_question_difficult;
    }

    public int getJudge_question_score() {
        return judge_question_score;
    }

    public String getJudge_question_remarks() {
        return judge_question_remarks;
    }
}
