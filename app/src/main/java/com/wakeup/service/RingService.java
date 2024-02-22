package com.wakeup.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RingService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        // Tạo một đối tượng MediaPlayer
        mediaPlayer = new MediaPlayer();

        try {
            // Cài đặt nguồn âm thanh cho MediaPlayer
            mediaPlayer.setDataSource("./res/Media/bao-thuc-quan-doi.mp3");

            // Chuẩn bị MediaPlayer
            mediaPlayer.prepare();

            // Phát âm thanh
            mediaPlayer.start();
        } catch (Exception e){

        }
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
        // Khởi động Service

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
