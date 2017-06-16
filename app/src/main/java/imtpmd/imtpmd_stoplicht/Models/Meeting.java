package imtpmd.imtpmd_stoplicht.Models;

import java.util.ArrayList;
import java.util.Date;

public class Meeting {
    private int    id;
    private int    user_id;
    private String name;
    private String description;
    private Date   starting_at;
    private Date   ending_at;
    private Date   created_at;
    private Date   updated_at;

    public Meeting(int id, int user_id, String name, String description, Date starting_at, Date ending_at, Date created_at, Date updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.starting_at = starting_at;
        this.ending_at = ending_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarting_at() {
        return starting_at;
    }

    public void setStarting_at(Date starting_at) {
        this.starting_at = starting_at;
    }

    public Date getEnding_at() {
        return ending_at;
    }

    public void setEnding_at(Date ending_at) {
        this.ending_at = ending_at;
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