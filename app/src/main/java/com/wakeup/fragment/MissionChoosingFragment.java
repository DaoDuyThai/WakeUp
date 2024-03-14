package com.wakeup.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.wakeup.R;
import com.wakeup.shareData.MissonViewModel;
import com.wakeup.ui.alarm.AlarmSetup;

public class MissionChoosingFragment extends Fragment implements View.OnClickListener {
    private Context context;

    private AlarmSetup alarmSetup;

    private LinearLayout typingMission, mathMission;

    private ImageView exitIcon;
    private MissonViewModel missonViewModel;

    public MissionChoosingFragment(Context context) {
        this.context = context;
    }

    public MissionChoosingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.mission_choosing_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        catchEvent();
    }

    private void catchEvent() {
        exitIcon.setOnClickListener(this::onClick);
        mathMission.setOnClickListener(this::onClick);
        typingMission.setOnClickListener(this::onClick);
    }

    private void initView(View view) {
        exitIcon = view.findViewById(R.id.exit);
        typingMission = view.findViewById(R.id.typing_mission);
        mathMission = view.findViewById(R.id.math_mission);
        alarmSetup = (AlarmSetup) getActivity();
        missonViewModel = new ViewModelProvider(alarmSetup).get(MissonViewModel.class);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.exit) {
            FragmentManager fm = alarmSetup.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(this);
            ft.commit();
        } else if (v.getId() == R.id.typing_mission) {
            missonViewModel.setText("Typing");
        } else if (v.getId() == R.id.math_mission) {
            missonViewModel.setText("Math");
        }
    }

}
