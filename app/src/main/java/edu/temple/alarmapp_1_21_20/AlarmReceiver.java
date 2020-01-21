package edu.temple.alarmapp_1_21_20;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// This BroadcastReceiver receives implicit intent in Manifest android.permission.RECEIVE_BOOT_COMPLETED to handle, so now on device reboot, SecondActivity will be launched using AlarmManager and PendingIntent
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Intent launchIntent = new Intent(context, SecondActivity.class);

        PendingIntent pi = PendingIntent.getActivity(context, 0, launchIntent, 0); // Need a PendingIntent, you first need an intent, we'll use launchIntent with getActivity() because this is a call to launch an Activity

        AlarmManager am = context.getSystemService(AlarmManager.class); // getSystemService() returns an instance of a Manager

        // Get current time + 10000 to set alarm to trigger 10 seconds from now - for one-off alarm
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pi); // Set an alarm (two types of alarm - 1. RTC (realtime clock) a wall clock alarm so it looks at what current time is and you set an offset, and 2. there's an alarm that counts when the device booted)
    }
}
