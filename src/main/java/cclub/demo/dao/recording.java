package cclub.demo.dao;

public class recording {
    private String recording_interview_id;
    private int recording_time_length;
    private String recording_video_url;

    public recording(String recording_interview_id, int recording_time_length, String recording_video_url) {
        this.recording_interview_id = recording_interview_id;
        this.recording_time_length = recording_time_length;
        this.recording_video_url = recording_video_url;
    }

    public void setRecording_interview_id(String recording_interview_id) {
        this.recording_interview_id = recording_interview_id;
    }

    public void setRecording_time_length(int recording_time_length) {
        this.recording_time_length = recording_time_length;
    }

    public void setRecording_video_url(String recording_video_url) {
        this.recording_video_url = recording_video_url;
    }

    public String getRecording_interview_id() {
        return recording_interview_id;
    }

    public int getRecording_time_length() {
        return recording_time_length;
    }

    public String getRecording_video_url() {
        return recording_video_url;
    }
}
