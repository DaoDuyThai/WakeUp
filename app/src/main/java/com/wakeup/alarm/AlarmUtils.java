package com.wakeup.alarm;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RestrictTo;

import com.wakeup.database.DatabaseManager;
import com.wakeup.model.AlarmModel;
import com.wakeup.service.AlarmService;
import com.wakeup.ui.alarm.AlarmSetup;

import java.util.Calendar;
import java.util.List;

public class AlarmUtils {

    public static void create(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        DatabaseManager databaseManager = new DatabaseManager(context);
        List<AlarmModel> models = databaseManager.getAlarms();
        for (AlarmModel alarmModel : models) {
            String missions = "";
            if ( alarmModel != null && alarmModel.isOn() == 1) {
                Log.d("AlarmTime", alarmModel.getId() + "");
                for (String mission : alarmModel.getMission()) {
                    missions += mission + " ";
                }
                Intent intent = new Intent(context, AlarmService.class);
                intent.putExtra("alarmMission", missions);
                intent.putExtra("alarmSound", alarmModel.getSound());
                if (alarmModel.getTime() > System.currentTimeMillis()) {
                    PendingIntent pendingIntent;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                        pendingIntent =
                                PendingIntent.getService(context, alarmModel.getId(), intent, PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
                    } else {
                        pendingIntent =
                                PendingIntent.getService(context, alarmModel.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    }
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alarmModel.getTime(), pendingIntent);
                } else {
                    alarmModel.setTime(alarmModel.getTime() + 24 * 60 * 60 * 1000);
                    databaseManager.updateAlarm(alarmModel);
                }

            } else if (alarmModel != null && alarmModel.isOn() == 0) {
                Intent intent1 = new Intent(context, AlarmService.class);
                PendingIntent pendingIntent = PendingIntent.getService(context, alarmModel.getId(), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);

            }
        }
    }

}

