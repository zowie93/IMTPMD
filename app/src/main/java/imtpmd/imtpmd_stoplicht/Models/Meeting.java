package imtpmd.imtpmd_stoplicht.Models;

import java.io.Serializable;

public class Meeting implements Serializable {
    private int    id;
    private User   user;
    private String name;
    private String description;
    private Date   starting_at;
    private Date   ending_at;

    public Meeting(int id, User user, String name, String description, Date starting_at, Date ending_at) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.starting_at = starting_at;
        this.ending_at = ending_at;
    }

    public Meeting () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString () {
        return this.getName();
    }
}