package cclub.demo.dao.exam;

public class cacheQuestion {
    private String question_id;
    private String answer;

    public cacheQuestion(String question_id, String answer) {
        this.question_id = question_id;
        this.answer = answer;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "cacheQuestion{" +
                "question_id='" + question_id + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
