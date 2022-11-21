package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText txt_number,txt_massage;
        Button btnsend;
        txt_massage= findViewById(R.id.textmsg);
        txt_number = findViewById(R.id.textnumber);
        btnsend = findViewById(R.id.sendbtn);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Construct data
                    String apiKey = "apikey=" + "NWE1NTU3NmI2NzU5NjQ1YTcxMzU3MzY0NmI3Njc4NDg=";
                    String message = "&message=" + txt_massage.getText().toString();
                    String sender = "&sender=" + "Nikhil";
                    String numbers = "&numbers=" + txt_number.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        // stringBuffer.append(line);
                        Toast.makeText(getApplicationContext(), "The massage"+line, Toast.LENGTH_SHORT).show();
                    }
                    rd.close();

                    // return stringBuffer.toString();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "the error_massage is "+ e, Toast.LENGTH_SHORT).show();
                   // System.out.println("Error SMS " + e);
                    // return "Error "+e;
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
