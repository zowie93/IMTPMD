package imtpmd.imtpmd_stoplicht.Models;

import java.util.Date;

public class Feedback {
    private int    id;
    private int    emotion_id;
    private int    meeting_id;
    private int    user_id;
    private String description;
    private Date   created_at;
    private Date   updated_at;

    public Feedback(int id, int emotion_id, int meeting_id, int user_id, String description, Date created_at, Date updated_at) {
        this.id = id;
        this.emotion_id = emotion_id;
        this.meeting_id = meeting_id;
        this.user_id = user_id;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmotion_id() {
        return emotion_id;
    }

    public void setEmotion_id(int emotion_id) {
        this.emotion_id = emotion_id;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
