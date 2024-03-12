package com.wakeup.ui.ring;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wakeup.R;
import com.wakeup.model.AlarmModel;

public class RingSetUp extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ring);
        initView();
        catchEvent();
        configView();

    }

    private void catchEvent() {
        button.setOnClickListener(this::goToMission);
    }

    private void goToMission(View view) {

    }

    private void initView() {
        button = findViewById(R.id.stop_button);
    }

    private void configView() {
//        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyWakelock");
//        wakeLock.acquire();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setTurnScreenOn(true);
            setShowWhenLocked(true);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
//        wakeLock.release();
    }
}
