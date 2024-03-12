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

import java.util.Arrays;
import java.util.List;



public class AlarmAdapter extends RecyclerView.Adapter<AlarmViewHolder> {
    private Context context;
    private List<AlarmModel> alarms;

    public AlarmAdapter(Context context, List<AlarmModel> alarms) {
        this.context = context;
        this.alarms = alarms;
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

        holder.time_view.setText(String.valueOf(alarm.getTime()));
        holder.time_view.setText(alarm.getExactTime());

        holder.repeat_date_view.setText(Arrays.toString(alarm.getRepeatDate()));
        holder.mission_view.setText(Arrays.toString(alarm.getMission()));
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
}
