package com.wakeup.ui.ring;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wakeup.MainActivity;
import com.wakeup.R;
import com.wakeup.service.AlarmService;
import com.wakeup.service.RingService;
import com.wakeup.ui.mission.Typing;
import com.wakeup.ui.mission.Math;

public class        RingSetUp extends AppCompatActivity{
    private Button button;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ring);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String sound = getIntent().getStringExtra("alarmSound");
            Intent intent = new Intent(this, RingService.class);
            intent.putExtra("alarmSound", sound);
            startService(intent);
        }
        configView();
        initView();
        catchEvent();

    }

    private void catchEvent() {
        button.setOnClickListener(this::changeToMission);
    }

    private void initView() {
        button = findViewById(R.id.start_button);
        button.requestFocus();
        button.setEnabled(true);
    }

    private void configView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setTurnScreenOn(true);
            setShowWhenLocked(true);
        }
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
    }


    public void changeToMission(View v) {
        stopService(new Intent(this, AlarmService.class));
        String missions = getIntent().getStringExtra("alarmMission");
        String[] mission = missions.split(" ");
        try {
            switch (mission[0]) {
                case "Math":
                    intent = new Intent(this, Math.class);
                    if (mission.length > 1) {
                        intent.putExtra("alarmMission", mission[1]);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if(mission.length == 1){
                        if(mission[0].equals("Math")){
                            intent = new Intent(this, Math.class);
                        } else if(mission[0].equals("Typing")){
                            intent = new Intent(this, Typing.class);
                        }
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    break;
                case "Typing":
                    intent = new Intent(this, Typing.class);
                    if (mission.length > 1) {
                        intent.putExtra("alarmMission", mission[1]);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if(mission.length == 1){
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    break;
                default:
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    stopService(new Intent(this, RingService.class));
                    break;
            }
        } catch (NullPointerException nullPointerException){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            stopService(new Intent(this, RingService.class));
        }
    }
}
