package imtpmd.imtpmd_stoplicht.Models;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
    private int     id;
    private Emotion emotion;
    private Meeting meeting;
    private User    user;
    private String  description;
    private Date    created_at;
    private Date    updated_at;

    public Feedback(int id, Emotion emotion, Meeting meeting, User user, String description, Date created_at, Date updated_at) {
        this.id = id;
        this.emotion = emotion;
        this.meeting = meeting;
        this.user = user;
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

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
