package com.wakeup.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.wakeup.R;
import com.wakeup.shareData.SoundRepeatViewModel;
import com.wakeup.ui.alarm.AlarmSetup;

public class SoundRepeatFragment extends Fragment implements View.OnClickListener {
    private RadioButton[] minutes;
    private Button saveButton;
    private SoundRepeatViewModel soundRepeatViewModel;

    private AlarmSetup alarmSetup;

    public SoundRepeatFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sound_repeat_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        catchEvent();
    }

    private void catchEvent() {
        saveButton.setOnClickListener(this::onClick);
    }

    private void initView(View view) {
        minutes = new RadioButton[6];
        minutes[0] = view.findViewById(R.id.RadioButton1);
        minutes[1] = view.findViewById(R.id.RadioButton2);
        minutes[2] = view.findViewById(R.id.RadioButton3);
        minutes[3] = view.findViewById(R.id.RadioButton4);
        minutes[4] = view.findViewById(R.id.RadioButton5);
        minutes[5] = view.findViewById(R.id.RadioButton6);
        saveButton = view.findViewById(R.id.saveButton);
        soundRepeatViewModel = new ViewModelProvider(this).get(SoundRepeatViewModel.class);
        alarmSetup = (AlarmSetup) getActivity();
    }

    private void exit() {
        FragmentManager fragmentManager = alarmSetup.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveButton) {
            for (RadioButton radioButton :minutes){
                if (radioButton.isChecked()){
                    soundRepeatViewModel.setText(radioButton.getText().toString());
                }
            }
            TextView repeatMinute = alarmSetup.findViewById(R.id.tab3_layout2);
            repeatMinute.setText(soundRepeatViewModel.getText().getValue());
            exit();
        }
    }
}
