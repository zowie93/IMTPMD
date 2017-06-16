package imtpmd.imtpmd_stoplicht.Models;

import java.text.SimpleDateFormat;

public class Date extends java.util.Date {

    private java.util.Date date;


    public Date(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString () {
        return new SimpleDateFormat("dd-MM-yyyy hh:mm").format(this.date);
    }
}
