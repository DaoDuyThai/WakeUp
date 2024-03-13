package com.wakeup.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wakeup.model.AlarmModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseHandler databaseHandler;

    public DatabaseManager(Context context) {
        databaseHandler = new DatabaseHandler(context);
    }

    public void addAlarm(AlarmModel alarm) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        String mission = "";
        String repeatDate = "";
        for (int i = 0; i < alarm.getMission().length; i++) {
            mission += alarm.getMission()[i];
            if (i != alarm.getMission().length - 1) {
                mission += ",";
            }
        }
        for (int i = 0; i < alarm.getRepeatDate().length; i++) {
            repeatDate += alarm.getRepeatDate()[i];
            if (i != alarm.getRepeatDate().length - 1) {
                repeatDate += ",";
            }
        }
        db.execSQL("INSERT INTO alarm (time, mission, isOn, repeatTime, repeatDate, sound) VALUES ('" + alarm.getTime() + "', '" + mission + "', '" + alarm.isOn() + "', '" + alarm.getRepeatTime() + "', '" + repeatDate + "', '" + alarm.getSound() + "')");
        db.close();
    }

    public void updateAlarm(AlarmModel alarm) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.execSQL("UPDATE alarm SET time = '" + alarm.getTime() + "', mission = '" + alarm.getMission() + "', isOn = '" + alarm.isOn() + "', repeatTime = '" + alarm.getRepeatTime() + "', repeatDate = '" + alarm.getRepeatDate() + "', sound = '" + alarm.getSound() + "' WHERE id = " + alarm.getId());
        db.close();
    }

    public List<AlarmModel> getAlarms() {
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alarm", null);
        List<AlarmModel> alarms = new ArrayList<>();
        cursor.moveToFirst();
        int[] repeatDateInt = new int[7];
         do {
            AlarmModel alarm = new AlarmModel();
            alarm.setId(cursor.getInt(0));
            alarm.setTime(cursor.getLong(1));
            alarm.setMission(cursor.getString(2).split(","));
            alarm.setOn(cursor.getInt(3) == 1 ? 1 : 0);
            alarm.setRepeatTime(cursor.getInt(4));
            String[] repeatDate = cursor.getString(5).split(",");
            for (int i = 0; i < repeatDate.length ; i++) {
                repeatDateInt[i] = Integer.parseInt(repeatDate[i]);
            }
            alarm.setRepeatDate(repeatDateInt);
            alarm.setSound(cursor.getInt(6));
            alarms.add(alarm);
        }  while (cursor.moveToNext());
        db.close();
        return alarms;
    }

    public void deleteAlarm(int id) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.execSQL("DELETE FROM alarm WHERE id = " + id);
        db.close();
    }

    public void deleteAllAlarm() {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.execSQL("DELETE FROM alarm");
        db.close();
    }


}
