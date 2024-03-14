package com.wakeup.ui.mission;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wakeup.R;
import com.wakeup.service.RingService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Typing extends AppCompatActivity implements View.OnClickListener {
    private HashMap<String, String> map = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private TextView question;
    private EditText editText;
    private Button button;
    private int total = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typing_mission);
        configView();
        getDataFromFile();
        initView();
        bindDataToView();
        processTyping();
    }

    private void processTyping() {
        button.setOnClickListener(this::onClick);
    }

    private void initView() {
        question = findViewById(R.id.question);
        editText = findViewById(R.id.input_text);
        button = findViewById(R.id.enter_button);
    }

    private void bindDataToView() {
        try {
            Random random = new Random();
            int total = keys.size();
            int index = random.nextInt(total);
            question.setText(map.get(keys.get(index)));
            Log.d("keyalarm", keys.get(index));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private void getDataFromFile() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.data);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elements = line.split("-");
                map.put(elements[0], elements[1]);
                keys.add(elements[0]);
            }
            bufferedReader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enter_button) {
//
            try {
                if (keys.contains("'" + editText.getText().toString() + "'")) {
                    Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
                    total++;
                    if (total < 3) {
                        bindDataToView();
                    } else {
                        stopService(new Intent(this, RingService.class));
                    }
                } else {
                    Toast.makeText(this, "Đã sai!", Toast.LENGTH_SHORT).show();
                }
//
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
