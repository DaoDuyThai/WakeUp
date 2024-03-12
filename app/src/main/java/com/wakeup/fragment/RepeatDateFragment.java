package com.wakeup.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.wakeup.R;
import com.wakeup.shareData.RepeatDateViewModel;
import com.wakeup.ui.alarm.AlarmSetup;

public class RepeatDateFragment extends Fragment implements View.OnClickListener {
    private CheckBox[] date;
    private Button saveButton;
    private RepeatDateViewModel repeatDateViewModel;
    private AlarmSetup alarmSetup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repeat_date_fragment, container, false);
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
        date = new CheckBox[7];
        date[0] = view.findViewById(R.id.checkbox1);
        date[1] = view.findViewById(R.id.checkbox2);
        date[2] = view.findViewById(R.id.checkbox3);
        date[3] = view.findViewById(R.id.checkbox4);
        date[4] = view.findViewById(R.id.checkbox5);
        date[5] = view.findViewById(R.id.checkbox6);
        date[6] = view.findViewById(R.id.checkbox7);
        saveButton = view.findViewById(R.id.saveButton);
        alarmSetup = (AlarmSetup) getActivity();
        repeatDateViewModel = new ViewModelProvider(alarmSetup).get(RepeatDateViewModel.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveButton) {
            repeatDateViewModel.clearText();
            for (CheckBox checkBox : date) {
                if (checkBox.isChecked()) {
                    repeatDateViewModel.appendText(checkBox.getText().toString());
                }
            }
            exit();
        }
    }

    private void exit() {
        FragmentManager fragmentManager = alarmSetup.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.commit();
    }
}
