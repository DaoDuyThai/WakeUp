package com.wakeup.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.TimeZone;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlarmModel {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private long time;
    private String[] mission;
    private int isOn;
    private int repeatTime;
    private int[] repeatDate;
    private int sound;

    public AlarmModel(int id, long time, String[] mission, int isOn, int repeatTime, int[] repeatDate, int sound, String minutes, String hours) {
        this.id = id;
        this.time = time;
        this.mission = mission;
        this.isOn = isOn;
        this.repeatTime = repeatTime;
        this.repeatDate = repeatDate;
        this.sound = sound;
        this.minutes = minutes;
        this.hours = hours;
    }

    public int getIsOn() {
        return isOn;
    }

    public void setIsOn(int isOn) {
        this.isOn = isOn;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    //add hours and minutes
    private String minutes;
    private String hours;
    private String PmAm;

    public String getPmAm() {
        return PmAm;
    }

    public void setPmAm(String pmAm) {
        PmAm = pmAm;
    }

    public AlarmModel(int id, long time, String[] mission, int isOn, int repeatTime, int[] repeatDate, int sound, String minutes, String hours, String pmAm) {
        this.id = id;
        this.time = time;
        this.mission = mission;
        this.isOn = isOn;
        this.repeatTime = repeatTime;
        this.repeatDate = repeatDate;
        this.sound = sound;
        this.minutes = minutes;
        this.hours = hours;
        PmAm = pmAm;
    }

    public AlarmModel() {
    }

    public AlarmModel(long time, String[] mission, int isOn, int repeatTime, int[] repeatDate, int sound) {
        this.time = time;
        this.mission = mission;
        this.isOn = isOn;
        this.repeatTime = repeatTime;
        this.repeatDate = repeatDate;
        this.sound = sound;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String[] getMission() {
        return mission;
    }

    public void setMission(String[] mission) {
        this.mission = mission;
    }

    public int isOn() {
        return isOn;
    }

    public void setOn(int on) {
        isOn = on;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public int[] getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(int[] repeatDate) {
        this.repeatDate = repeatDate;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getExactTime(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        // Get hour, minute, and AM/PM from the Calendar object
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int amPm = calendar.get(Calendar.AM_PM);

        // Convert hour to 12-hour format if necessary
        String amPmString = (amPm == Calendar.AM) ? "AM" : "PM";
        if (hourOfDay > 12) {
            hourOfDay -= 12;
        } else if (hourOfDay == 0) {
            hourOfDay = 12;
        }

        // Format the time as HH:mm AM/PM
        return String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay, minute, amPmString);
    }
}
