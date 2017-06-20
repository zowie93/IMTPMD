package imtpmd.imtpmd_stoplicht.Models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date extends java.util.Date {

    private java.util.Date date;

    public Date(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.date = df.parse(date);
        } catch (ParseException e) {
            this.date = new java.util.Date();
        }
    }

    @Override
    public String toString () {
        return new SimpleDateFormat("dd MMMM yyyy hh:mm").format(this.date);
    }
}
