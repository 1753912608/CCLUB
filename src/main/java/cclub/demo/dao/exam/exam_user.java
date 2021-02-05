package cclub.demo.dao.exam;

public class exam_user {
    private String access_code;
    private String exam_id;
    private String candidate_phone;
    private String candidate_name;
    private String candidate_mail;
    private int exam_notice;
    private int exam_user_score;
    private int exam_user_state;
    private int exam_user_skip_number;
    private String exam_user_recprding;

    public exam_user(String access_code, String exam_id, String candidate_phone,
                     String candidate_name, int exam_notice, int exam_user_score,
                     int exam_user_state,String candidate_mail,int exam_user_skip_number) {
        this.access_code = access_code;
        this.exam_id = exam_id;
        this.candidate_phone = candidate_phone;
        this.candidate_name = candidate_name;
        this.exam_notice = exam_notice;
        this.exam_user_score = exam_user_score;
        this.exam_user_state = exam_user_state;
        this.candidate_mail=candidate_mail;
        this.exam_user_skip_number=exam_user_skip_number;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public void setCandidate_phone(String candidate_phone) {
        this.candidate_phone = candidate_phone;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public void setExam_notice(int exam_notice) {
        this.exam_notice = exam_notice;
    }

    public void setExam_user_score(int exam_user_score) {
        this.exam_user_score = exam_user_score;
    }

    public void setExam_user_state(int exam_user_state) {
        this.exam_user_state = exam_user_state;
    }

    public String getAccess_code() {
        return access_code;
    }

    public String getExam_id() {
        return exam_id;
    }

    public String getCandidate_phone() {
        return candidate_phone;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public int getExam_notice() {
        return exam_notice;
    }

    public int getExam_user_score() {
        return exam_user_score;
    }

    public int getExam_user_state() {
        return exam_user_state;
    }

    public void setCandidate_mail(String candidate_mail) {
        this.candidate_mail = candidate_mail;
    }

    public String getCandidate_mail() {
        return candidate_mail;
    }

    public void setExam_user_skip_number(int exam_user_skip_number) {
        this.exam_user_skip_number = exam_user_skip_number;
    }

    public int getExam_user_skip_number() {
        return exam_user_skip_number;
    }

    public void setExam_user_recprding(String exam_user_recprding) {
        this.exam_user_recprding = exam_user_recprding;
    }

    public String getExam_user_recprding() {
        return exam_user_recprding;
    }

    @Override
    public String toString() {
        return "exam_user{" +
                "access_code='" + access_code + '\'' +
                ", exam_id='" + exam_id + '\'' +
                ", candidate_phone='" + candidate_phone + '\'' +
                ", candidate_name='" + candidate_name + '\'' +
                ", candidate_mail='" + candidate_mail + '\'' +
                ", exam_notice=" + exam_notice +
                ", exam_user_score=" + exam_user_score +
                ", exam_user_state=" + exam_user_state +
                ", exam_user_skip_number=" + exam_user_skip_number +
                ", exam_user_recprding='" + exam_user_recprding + '\'' +
                '}';
    }
}
