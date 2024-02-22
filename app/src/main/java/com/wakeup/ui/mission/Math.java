package com.wakeup.ui.mission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wakeup.R;
import com.wakeup.service.RingService;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Math extends AppCompatActivity implements View.OnClickListener {
    private TextView question;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private Button answers[];
    private int score = 0;
    private int result = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_mission);
        initView();
        runService();
        randomQues();
        processQues();
        changePage();
    }

    private void runService() {
        startService(new Intent(this, RingService.class));
    }

    private void changePage() {
        if(score > 3){
            Intent intent = new Intent(this, Typing.class);
            startActivity(intent);
        }
    }

    private void processQues() {
        for (Button button: answers){
            button.setOnClickListener(this::onClick );
        }
    }

    private void randomQues() {
        Random random = new Random();
        final int firstNumber = random.nextInt(1000);
        final int secondNumber = random.nextInt(1000);
        question.setText(firstNumber+"+"+secondNumber+"=");
        result = firstNumber + secondNumber;
        Set<Integer> randomSet = new HashSet<>();
        //Vòng lặp thêm phần tử ngẫu nhiên vào set không trùng lặp
        for (int i = 0; i < 4; i++){
            int randomNumber = random.nextInt(4);

            // Thêm số ngẫu nhiên vào HashSet
            while (!randomSet.add(randomNumber)) {
                randomNumber = random.nextInt(4);
            }
        }
        // Vòng lặp xử lí ngẫu nhiên
        for (Integer ranNum: randomSet){
            answers[ranNum].setText(result*(ranNum+1)+"");
        }
    }

    private void initView() {
        question = findViewById(R.id.question1);
        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        answer4 = findViewById(R.id.answer_4);
        answers = new Button[]{answer1, answer2, answer3, answer4};
    }



    @Override
    public void onClick(View v) {
        Button button = findViewById(v.getId());
        if(button.getText().equals(result+"") && score <= 3){
            randomQues();
            score++;
        }
        changePage();
    }
}
