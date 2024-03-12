package com.wakeup;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AlarmViewHolder extends RecyclerView.ViewHolder {

    public TextView time_view;
    public TextView repeat_date_view;
    public TextView mission_view;
    public Switch is_on_button_view;

    public AlarmViewHolder(@NonNull View itemView) {
        super(itemView);
        time_view = itemView.findViewById(R.id.time);
        repeat_date_view = itemView.findViewById(R.id.repeat_date);
        mission_view = itemView.findViewById(R.id.mission);
        is_on_button_view = itemView.findViewById(R.id.is_on_button);
    }
}
