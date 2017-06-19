package imtpmd.imtpmd_stoplicht.API;

import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import imtpmd.imtpmd_stoplicht.Models.Date;
import imtpmd.imtpmd_stoplicht.Models.Emotion;
import imtpmd.imtpmd_stoplicht.Models.Feedback;
import imtpmd.imtpmd_stoplicht.Models.Meeting;
import imtpmd.imtpmd_stoplicht.Models.User;

public class API {

    public static void login (String login_number_new) {
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://188.226.134.236/api/user/login");
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("number", login_number_new));
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            defaultHttpClient.execute(httpPost);
        }

        catch (Exception e) {}
    }

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
            HttpGet httpGet = new HttpGet("http://188.226.134.236/api/meeting/" + meeting_id);
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

        ArrayList<Feedback> feedback = new ArrayList<>();

        feedback.add(new Feedback(1, new Emotion("Blij", "blij"),             new User(1, "s1094220"), "omschrijving", new Date("2017-06-16 20:17:51")));
        feedback.add(new Feedback(2, new Emotion("Verdrietig", "verdrietig"), new User(2, "s6969696"), "omschrijving", new Date("2017-06-16 20:17:51")));

        return feedback;

    }

    public static void giveFeedback(int meeting_id, int emotion_id, String username, String description) {
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://188.226.134.236/api/feedback/give");
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("meeting_id", Integer.toString(meeting_id)));
            pairs.add(new BasicNameValuePair("emotion_id", Integer.toString(emotion_id)));
            pairs.add(new BasicNameValuePair("number", username));
            pairs.add(new BasicNameValuePair("description", description));
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            defaultHttpClient.execute(httpPost);
        }

        catch (Exception e) {}
    }
}
