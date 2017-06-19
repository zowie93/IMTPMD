package imtpmd.imtpmd_stoplicht.Models;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int    id;
    private String number;

    public User(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public User (String number) {
        this.number = number;
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
}