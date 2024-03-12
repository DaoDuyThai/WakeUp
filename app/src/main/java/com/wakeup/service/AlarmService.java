package com.wakeup.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.wakeup.R;
import com.wakeup.database.DatabaseManager;
import com.wakeup.model.AlarmModel;
import com.wakeup.ui.alarm.AlarmSetup;
import com.wakeup.ui.ring.RingSetUp;

import java.util.List;


public class AlarmService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = buildNotification();
        startForeground(1, notification);
        Log.d("Samsung", "in");
        sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        return START_NOT_STICKY;
    }


    private Notification buildNotification() {
        Intent intent = new Intent(this, RingSetUp.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder((this))
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Alarm")
                .setContentText("Alarm is ringing")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setFullScreenIntent(pendingIntent, true);
        notificationBuilder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("123", "123", NotificationManager.IMPORTANCE_HIGH));
            notificationBuilder.setChannelId("123");
        }
        return notificationBuilder.build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
