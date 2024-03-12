package com.wakeup.ui.alarm;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.wakeup.R;
import com.wakeup.service.AlarmService;

public class RequestRunTime extends AppCompatActivity {

    private static final String PERMISSION_FOREGROUND_SERVICE = "android.permission.FOREGROUND_SERVICE";
    private static final String PERMISSION_WAKE_LOCK = "android.permission.WAKE_LOCK";
    private static final String PERMISSION_POST_NOTIFICATION = "android.permission.POST_NOTIFICATION";
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_runtime_permission);
        button = findViewById(R.id.button);
        button.setOnClickListener((v) -> requestRunTimePermission());

    }


    private void requestRunTimePermission() {
        if(ActivityCompat.checkSelfPermission(this, PERMISSION_FOREGROUND_SERVICE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_FOREGROUND_SERVICE)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permission needed");
            builder.setMessage("This permission is needed to run the alarm service");
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCompat.requestPermissions(this, new String[]{PERMISSION_FOREGROUND_SERVICE}, 1);
                dialog.dismiss();
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_WAKE_LOCK}, 1);
        }
        if(ActivityCompat.checkSelfPermission(this, PERMISSION_WAKE_LOCK) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_WAKE_LOCK}, 1);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_WAKE_LOCK)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permission needed");
            builder.setMessage("This permission is needed to run the alarm service");
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCompat.requestPermissions(this, new String[]{PERMISSION_WAKE_LOCK}, 1);
                dialog.dismiss();
            });
            builder.show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_WAKE_LOCK}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, AlarmSetup.class);
                startService(intent);
            }
        }
    }
}
