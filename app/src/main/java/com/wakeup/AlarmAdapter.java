package com.wakeup;

import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wakeup.AlarmViewHolder;
import com.wakeup.model.AlarmModel;
import com.wakeup.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;


public class AlarmAdapter extends RecyclerView.Adapter<AlarmViewHolder> {
    private Context context;
    private List<AlarmModel> alarms;

    private String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final Map<String, String> missionMap;
    static {
        missionMap = new HashMap<>();
        missionMap.put("Typing", "Báo thức");
        missionMap.put("Memory", "Giải toán");
        missionMap.put("Math", "Ghi nhớ");


    }

    public AlarmAdapter(Context context, List<AlarmModel> alarms) {
        this.context = context;
        this.alarms = alarms;
        sortAlarms();
    }

    public void setAlarms(List<AlarmModel> alarms) {
        this.alarms = alarms;
        sortAlarms();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_view, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        AlarmModel alarm = alarms.get(position);

        holder.itemView.setId(alarm.getId());

//        holder.time_view.setText(String.valueOf(alarm.getTime()));
//        holder.time_view.setText(alarm.getExactTime(alarm.getTime()));
        String hours = alarm.getHours();
        String minutes = alarm.getMinutes();
        if (hours.length() == 1) {
            hours = "0" + hours;
        }
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        holder.time_view.setText(hours + ":" + minutes);
        String repeatDateString = convertRepeatDateToString(alarm.getRepeatDate());
//        holder.repeat_date_view.setText(Arrays.toString(alarm.getRepeatDate()));
        holder.repeat_date_view.setText(repeatDateString);

//        holder.mission_view.setText(Arrays.toString(alarm.getMission()));
        String[] missionArray = alarm.getMission();
        String missionString = convertMissionToString(missionArray);
        holder.mission_view.setText(missionString);
        holder.is_on_button_view.setChecked(alarm.isOn() == 1);

        holder.is_on_button_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                alarm.setOn(isChecked ? 1 : 0);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
    public static String convertRepeatDateToString(int[] repeatDate) {
        String[] daysOfWeek = {"CN", "T2", "T3", "T4", "T5", "T6", "T7"};
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < repeatDate.length; i++) {
            if (repeatDate[i] == 1) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(daysOfWeek[i]);
            }
        }

        return stringBuilder.toString();
    }

    public static String convertMissionToString(String[] missions) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String mission : missions) {
            if (missionMap.containsKey(mission)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(missionMap.get(mission));
            }
        }

        return stringBuilder.toString();
    }



    private void sortAlarms() {
        Collections.sort(alarms, new Comparator<AlarmModel>() {
            @Override
            public int compare(AlarmModel alarm1, AlarmModel alarm2) {
                int hour1 = Integer.parseInt(alarm1.getHours());
                int hour2 = Integer.parseInt(alarm2.getHours());
                int minute1 = Integer.parseInt(alarm1.getMinutes());
                int minute2 = Integer.parseInt(alarm2.getMinutes());

                int hourComparison = Integer.compare(hour1, hour2);
                if (hourComparison != 0) {
                    return hourComparison;
                }

                int minuteComparison = Integer.compare(minute1, minute2);
                if (minuteComparison != 0) {
                    return minuteComparison;
                }
                return Integer.compare(alarm2.getIsOn(), alarm1.getIsOn());
            }
        });
    }

//    public String getNearestAlarmCountdown() {
//        long currentTimeMillis = System.currentTimeMillis();
//        long nearestAlarmTimeMillis = Long.MAX_VALUE;
//
//        String nearestAlarmTime = null;
//
//        for (AlarmModel alarm : alarms) {
//            int hour = Integer.parseInt(alarm.getHours());
//            int minute = Integer.parseInt(alarm.getMinutes());
//            long alarmTimeMillis = TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute);
//
//            if (alarmTimeMillis > currentTimeMillis && alarmTimeMillis < nearestAlarmTimeMillis) {
//                nearestAlarmTimeMillis = alarmTimeMillis;
//                nearestAlarmTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
//            }
//        }
//
//        if (nearestAlarmTime == null) {
//            return "No alarms set";
//        } else {
//            return nearestAlarmTime;
//        }
//    }

}
