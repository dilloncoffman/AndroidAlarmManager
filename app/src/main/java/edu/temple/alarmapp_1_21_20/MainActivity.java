package edu.temple.alarmapp_1_21_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent launchIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchIntent = new Intent(this, SecondActivity.class);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(launchIntent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Using AlarmManager, grab a reference to all system services, assume it's started and running already

                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, launchIntent, 0); // Need a PendingIntent, you first need an intent, we'll use launchIntent with getActivity() because this is a call to launch an Activity

                AlarmManager am = getSystemService(AlarmManager.class); // getSystemService() returns an instance of a Manager

                // Get current time + 10000 to set alarm to trigger 10 seconds from now - for one-off alarm
                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pi); // Set an alarm (two types of alarm - 1. RTC (realtime clock) a wall clock alarm so it looks at what current time is and you set an offset, and 2. there's an alarm that counts when the device booted)
                // am.cancel(pi); // How you're suppose to cancel an alarm, if you create an app with a lot of alarms, you have to have some structure internally keeping track of what alarms are set
            }
        });
    }
}
