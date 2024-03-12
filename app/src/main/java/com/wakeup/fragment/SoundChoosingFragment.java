package com.wakeup.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wakeup.R;
import com.wakeup.ui.alarm.AlarmSetup;

public class SoundChoosingFragment extends Fragment {
    private RadioButton[] sounds;
    private Button saveButton;
    private AlarmSetup alarmSetup;
    public SoundChoosingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sound_choosing_fragment, container, false);
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

    private void onClick(View view) {
        if(view.getId() == R.id.saveButton) {
            for(int i = 0; i < sounds.length; i++) {
                if(sounds[i].isChecked()) {
                    TextView soundDisplay = alarmSetup.findViewById(R.id.soundChoosing);
                    soundDisplay.setText(sounds[i].getText());
                    exit();
                    break;
                }
            }
        }
    }
    private void exit() {
        FragmentManager fragmentManager = alarmSetup.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }

    private void initView(View view) {
        sounds = new RadioButton[3];
        sounds[0] = view.findViewById(R.id.sound1);
        sounds[1] = view.findViewById(R.id.sound2);
        sounds[2] = view.findViewById(R.id.sound3);
        saveButton = view.findViewById(R.id.saveButton);
        alarmSetup = (AlarmSetup) getActivity();
    }
}
