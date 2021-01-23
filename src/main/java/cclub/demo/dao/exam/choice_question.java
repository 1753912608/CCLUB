package cclub.demo.dao.exam;

public class choice_question {
    private String choice_question_id;
    private String choice_question_name;
    private String choice_question_option_A;
    private String choice_question_option_B;
    private String choice_question_option_C;
    private String choice_question_option_D;
    private String choice_question_option_E;
    private String choice_question_option_F;
    private String choice_question_option_G;
    private String choice_question_created_user_id;
    private String choice_question_answer;
    private int choice_question_difficult;
    private int choice_question_score;
    private String choice_question_remarks;

    public choice_question(String choice_question_id, String choice_question_name,
                           String choice_question_option_A, String choice_question_option_B,
                           String choice_question_option_C, String choice_question_option_D,
                           String choice_question_option_E, String choice_question_option_F,
                           String choice_question_option_G, String choice_question_created_user_id,
                           String choice_question_answer, int choice_question_difficult,
                           int choice_question_score, String choice_question_remarks) {
        this.choice_question_id = choice_question_id;
        this.choice_question_name = choice_question_name;
        this.choice_question_option_A = choice_question_option_A;
        this.choice_question_option_B = choice_question_option_B;
        this.choice_question_option_C = choice_question_option_C;
        this.choice_question_option_D = choice_question_option_D;
        this.choice_question_option_E = choice_question_option_E;
        this.choice_question_option_F = choice_question_option_F;
        this.choice_question_option_G = choice_question_option_G;
        this.choice_question_created_user_id = choice_question_created_user_id;
        this.choice_question_answer = choice_question_answer;
        this.choice_question_difficult = choice_question_difficult;
        this.choice_question_score = choice_question_score;
        this.choice_question_remarks = choice_question_remarks;
    }

    public void setChoice_question_id(String choice_question_id) {
        this.choice_question_id = choice_question_id;
    }

    public void setChoice_question_name(String choice_question_name) {
        this.choice_question_name = choice_question_name;
    }

    public void setChoice_question_option_A(String choice_question_option_A) {
        this.choice_question_option_A = choice_question_option_A;
    }

    public void setChoice_question_option_B(String choice_question_option_B) {
        this.choice_question_option_B = choice_question_option_B;
    }

    public void setChoice_question_option_C(String choice_question_option_C) {
        this.choice_question_option_C = choice_question_option_C;
    }

    public void setChoice_question_option_D(String choice_question_option_D) {
        this.choice_question_option_D = choice_question_option_D;
    }

    public void setChoice_question_option_E(String choice_question_option_E) {
        this.choice_question_option_E = choice_question_option_E;
    }

    public void setChoice_question_option_F(String choice_question_option_F) {
        this.choice_question_option_F = choice_question_option_F;
    }

    public void setChoice_question_option_G(String choice_question_option_G) {
        this.choice_question_option_G = choice_question_option_G;
    }

    public void setChoice_question_created_user_id(String choice_question_created_user_id) {
        this.choice_question_created_user_id = choice_question_created_user_id;
    }

    public void setChoice_question_answer(String choice_question_answer) {
        this.choice_question_answer = choice_question_answer;
    }

    public void setChoice_question_difficult(int choice_question_difficult) {
        this.choice_question_difficult = choice_question_difficult;
    }

    public void setChoice_question_score(int choice_question_score) {
        this.choice_question_score = choice_question_score;
    }

    public void setChoice_question_remarks(String choice_question_remarks) {
        this.choice_question_remarks = choice_question_remarks;
    }

    public String getChoice_question_id() {
        return choice_question_id;
    }

    public String getChoice_question_name() {
        return choice_question_name;
    }

    public String getChoice_question_option_A() {
        return choice_question_option_A;
    }

    public String getChoice_question_option_B() {
        return choice_question_option_B;
    }

    public String getChoice_question_option_C() {
        return choice_question_option_C;
    }

    public String getChoice_question_option_D() {
        return choice_question_option_D;
    }

    public String getChoice_question_option_E() {
        return choice_question_option_E;
    }

    public String getChoice_question_option_F() {
        return choice_question_option_F;
    }

    public String getChoice_question_option_G() {
        return choice_question_option_G;
    }

    public String getChoice_question_created_user_id() {
        return choice_question_created_user_id;
    }

    public String getChoice_question_answer() {
        return choice_question_answer;
    }

    public int getChoice_question_difficult() {
        return choice_question_difficult;
    }

    public int getChoice_question_score() {
        return choice_question_score;
    }

    public String getChoice_question_remarks() {
        return choice_question_remarks;
    }

    @Override
    public String toString() {
        return "choice_question{" +
                "choice_question_id='" + choice_question_id + '\'' +
                ", choice_question_name='" + choice_question_name + '\'' +
                ", choice_question_option_A='" + choice_question_option_A + '\'' +
                ", choice_question_option_B='" + choice_question_option_B + '\'' +
                ", choice_question_option_C='" + choice_question_option_C + '\'' +
                ", choice_question_option_D='" + choice_question_option_D + '\'' +
                ", choice_question_option_E='" + choice_question_option_E + '\'' +
                ", choice_question_option_F='" + choice_question_option_F + '\'' +
                ", choice_question_option_G='" + choice_question_option_G + '\'' +
                ", choice_question_created_user_id='" + choice_question_created_user_id + '\'' +
                ", choice_question_answer='" + choice_question_answer + '\'' +
                ", choice_question_difficult=" + choice_question_difficult +
                ", choice_question_score=" + choice_question_score +
                ", choice_question_remarks='" + choice_question_remarks + '\'' +
                '}';
    }
}
