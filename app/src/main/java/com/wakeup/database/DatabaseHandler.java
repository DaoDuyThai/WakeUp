package com.wakeup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alarm.db";
    private final String TABLE_NAME = "alarm";
    private final String ID = "id";
    private final String TIME = "time";
    private final String IsON = "isOn";
    private final String REPEAT_TIME = "repeatTime";
    private final String SOUND = "sound";
    private final String MISSION = "mission";
    private final String REPEAT_DATE = "repeatDate";

    private final String  HOURS = "hours";
    private final String MINUTES = "minutes";
    private final String PMAM = "PmAm";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabase = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER,%s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, ID, TIME, MISSION, IsON, REPEAT_TIME, REPEAT_DATE, SOUND, HOURS, MINUTES, PMAM);
        db.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropDatabase = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(dropDatabase);
        onCreate(db);
    }

}
