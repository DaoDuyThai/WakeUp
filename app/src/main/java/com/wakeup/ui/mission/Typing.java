package com.wakeup.ui.mission;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wakeup.R;

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

public class Typing extends AppCompatActivity implements View.OnClickListener {
    private HashMap<String, String> map = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private TextView question;
    private EditText awswer;
    private int total = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typing_mission);
        getDataFromFile();
        initView();
        bindDataToView();
    }

    private void initView() {
        question = findViewById(R.id.question);
        awswer = findViewById(R.id.input_text);
    }

    private void bindDataToView() {
        try {
            question.setText(map.get(keys.get(0)));
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

    @Override
    public void onClick(View v) {

    }
}
