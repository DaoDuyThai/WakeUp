package com.wakeup.alarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.wakeup.database.DatabaseManager;
import com.wakeup.model.AlarmModel;
import com.wakeup.service.AlarmService;
import com.wakeup.ui.alarm.AlarmSetup;

import java.util.List;

public class AlarmUtils {
    private static int REQUEST_CODE = 0;

    public static void create(Context context, AlarmModel alarm) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        DatabaseManager databaseManager = new DatabaseManager(context);
        Intent intent = new Intent(context, AlarmService.class);

        List<AlarmModel> models = databaseManager.getAlarms();
        if (models.size() > 0) {
            Log.d("AlarmUtils", "size: " + models.get(models.size() - 1).isOn());
        }
        for (AlarmModel alarmModel : models) {
            if (alarmModel.isOn() == 1) {
                Log.d("AlarmUtils", "alarm" + alarmModel.getId() + "is on");
                PendingIntent pendingIntent =
                        PendingIntent.getService(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                REQUEST_CODE++;
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmModel.getTime(), pendingIntent);
            }
        }
    }

}

