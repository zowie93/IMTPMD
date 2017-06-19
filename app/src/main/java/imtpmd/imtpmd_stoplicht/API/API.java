package imtpmd.imtpmd_stoplicht.API;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import imtpmd.imtpmd_stoplicht.Models.Date;
import imtpmd.imtpmd_stoplicht.Models.Emotion;
import imtpmd.imtpmd_stoplicht.Models.Feedback;
import imtpmd.imtpmd_stoplicht.Models.Meeting;
import imtpmd.imtpmd_stoplicht.Models.User;

/**
 * Created by maartenpaauw on 17-06-17.
 */

public class API {

    public static ArrayList<Meeting> getAllMeetings() {

        ArrayList<Meeting> meetings = new ArrayList<>();

        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://188.226.134.236/api/meeting");
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject meeting = jsonArray.getJSONObject(i);
                JSONObject user = meeting.getJSONObject("user");

                meetings.add(new Meeting(
                        meeting.getInt("id"),
                        new User(
                                user.getInt("id"),
                                user.getString("number")
                        ),
                        meeting.getString("name"),
                        meeting.getString("description"),
                        new Date(meeting.getString("starting_at")),
                        new Date(meeting.getString("ending_at"))
                ));
            }
        } catch (Exception e) {}

        return meetings;
    }

    public static Meeting getAllMeetingById(int meeting_id) {

        Meeting meeting = new Meeting();

        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://188.226.134.236/api/meeting/1");
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));

            JSONObject jsonObject = new JSONObject(reader.readLine());

            meeting.setName(jsonObject.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return meeting;
    }

    public static ArrayList<Feedback> getFeedbackByMeetingId(int meeting_id) {

        ArrayList<Feedback> feedback = new ArrayList<Feedback>();

        feedback.add(new Feedback(1, new Emotion("Blij", "blij"),             new User(1, "s1094220"), "omschrijving", new Date("2017-06-16 20:17:51")));
        feedback.add(new Feedback(2, new Emotion("Verdrietig", "verdrietig"), new User(2, "s6969696"), "omschrijving", new Date("2017-06-16 20:17:51")));

        return feedback;

    }
}
