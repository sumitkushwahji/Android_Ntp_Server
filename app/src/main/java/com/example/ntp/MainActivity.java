package com.example.ntp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    String data = "now";
    Button button;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.txtview);

        if (!CheckInternetConnection.getConnectivityStatusString(MainActivity.this).toString().equalsIgnoreCase("internet is not available")) {
            Thread thread = new Thread() {
                @Override

                public void run() {
                    while(true) {
                        super.run();
                        Date date = null;
                        data = getUTCTime();
                        /* String com = getUTCTime(); */

                        textView.setText(data);
                    }
                }
            };
            thread.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    data = getUTCTime();
                }
            }, 10);


        } else {
            Toast.makeText(MainActivity.this, "Internet is not connected", Toast.LENGTH_SHORT).show();

        }


    }
            public String getUTCTime() {
                long nowAsPerDeviceTimeZone = 0;
                SntpClint sntpClient = new SntpClint();
                if (sntpClient.requestTime("time.nplindia.org", 30000)) {
                    nowAsPerDeviceTimeZone = sntpClient.getNtpTime();
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                    TimeZone timeZoneInDevice = cal.getTimeZone();
                    int differentialOfTimeZones = timeZoneInDevice.getOffset(System.currentTimeMillis());
                    nowAsPerDeviceTimeZone -= differentialOfTimeZones;
                }
                DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                return dateFormat.format(new Date(nowAsPerDeviceTimeZone));

            }




}