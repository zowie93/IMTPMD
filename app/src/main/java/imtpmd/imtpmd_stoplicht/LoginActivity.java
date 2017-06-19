package imtpmd.imtpmd_stoplicht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import imtpmd.imtpmd_stoplicht.API.API;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final TextView login_number = (TextView) findViewById(R.id.login_number);
        Button   login_button = (Button) findViewById(R.id.login_button);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("imtpmd.imtpmd_stoplicht", Context.MODE_PRIVATE);

        final String studentnumber = sharedPreferences.getString("studentnumber", null);

        final Intent intent = new Intent(this, MainActivity.class);

        if (studentnumber != null) {
            startActivity(intent);
        }

        final AppCompatActivity activity = this;

        login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String login_number_new = login_number.getText().toString();

                API.login(login_number_new);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("studentnumber", login_number_new);
                editor.commit();

                Toast.makeText(activity, "U bent succesvol ingelogd.", Toast.LENGTH_LONG).show();


                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setTitle("Inloggen");
    }
}
