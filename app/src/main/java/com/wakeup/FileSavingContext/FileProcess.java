package com.wakeup.FileSavingContext;

import android.content.Context;

import com.wakeup.model.AlarmModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileProcess {
    private Context context;

    public FileProcess(Context context) {
        this.context = context;
    }
    public void saveAlarm(AlarmModel alarm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("alarm.dat", Context.MODE_PRIVATE));
        oos.writeObject(alarm);
        oos.close();
    }

    public List<AlarmModel> getAlarm() throws IOException {
        List<AlarmModel> alarms = new ArrayList<>();
        ObjectInputStream ois = new ObjectInputStream(context.openFileInput("alarm.dat"));
        try {
            AlarmModel alarm = (AlarmModel) ois.readObject();
            alarms.add(alarm);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
        return alarms;
    }
}
