package com.example.taslock;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    public static final String KEY_EXTRA = "com.example.taslock.KEY_BOOK";


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String title = null;
        Log.i("Receiver", "Broadcast received: " + action);
        System.out.print("receiver");



//        String gTime = null;
        if (action.equals("my.action.string1")) {
            title = intent.getExtras().getString("extra");

        }
        System.out.print(title);
//        if (action.equals("my.action.string2")) {
//            gTime = intent.getExtras().getString("extra");
//
//        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyTaskit")
                .setContentTitle(title + " Due")
                .setContentText("Your scheduled " + title + " task is Due.")
                .setSmallIcon(R.drawable.taskit)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

    }
}
