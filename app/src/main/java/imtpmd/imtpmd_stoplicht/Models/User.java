package imtpmd.imtpmd_stoplicht.Models;

import java.util.Date;

public class User {

    private int    id;
    private String number;
    private Date   created_at;
    private Date   updated_at;

    public User(int id, String number, Date created_at, Date updated_at) {
        this.id = id;
        this.number = number;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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