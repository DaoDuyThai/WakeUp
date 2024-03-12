package com.wakeup.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlarmModel implements Serializable {
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

    public String getExactTime() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        return String.format("%02d:%02d", hours, minutes);
    }
}
