package com.wakeup.fragment;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wakeup.MainActivity;
import com.wakeup.R;
import com.wakeup.AlarmAdapter;
import com.wakeup.database.DatabaseManager;
import com.wakeup.model.AlarmModel;
import com.wakeup.ui.alarm.AlarmSetup;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlarmAdapter alarmAdapter;
    private List<AlarmModel> alarms;

    private FloatingActionButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the add alarm activity
                Intent intent = new Intent(getActivity(), AlarmSetup.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DatabaseManager databaseManager = new DatabaseManager(getActivity());
        try {
            alarms = databaseManager.getAlarms();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        alarmAdapter = new AlarmAdapter(getActivity(), alarms);
        if (!alarms.isEmpty()) {
            alarmAdapter = new AlarmAdapter(getActivity(), alarms);
            recyclerView.setAdapter(alarmAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
        }

        return rootView;
    }


}