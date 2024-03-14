package com.wakeup.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.wakeup.R;

public class RingService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Dừng MediaPlayer
        mediaPlayer.stop();
        // Giải phóng MediaPlayer
        mediaPlayer.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String sound = intent.getStringExtra("alarmSound");
        if(sound.equals("1")){
            mediaPlayer = MediaPlayer.create(this, R.raw.tinh_ngu_di_em);
        } else if (sound.equals("2")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bao_thuc_quan_doi);
        } else if (sound.equals("3")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.day_di_ong_chau_oi);
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.bao_thuc_quan_doi);
        }
        // Khởi động Service`
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Bắt đầu phát nhạc
                if (!mediaPlayer.isPlaying()) {
                    Log.d("Nhạc", "Đang chạy rồi đó");
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
