package com.wakeup.ui.alarm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.wakeup.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmSetup extends AppCompatActivity implements View.OnScrollChangeListener {
    private ScrollView scrollView;

    private TextView hour;

    private LinearLayout linearLayout;

    private List<TextView> textViewList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        initView();
        setTextProp();
        bindData();
    }

    private void setTextProp() {
        Typeface typeface = Typeface.create("sans-serif", Typeface.BOLD);
        for(int i = 0; i<=12; i++){
            hour = new TextView(this);
            hour.setTextSize(30);
            if (i < 10) {
                hour.setText("0" + i);
            } else {
                hour.setText(i + "");
            }
            hour.setTextColor(getResources().getColor(R.color.white));
            textViewList.add(hour);
            hour.setTypeface(typeface);

        }
    }

    private void bindData() {
        for (TextView textView: textViewList){
            linearLayout.addView(textView);
        }
        scrollView.setOnScrollChangeListener(this::onScrollChange);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

    }

    private void initView() {
        scrollView = findViewById(R.id.hour_scroll_view);
         linearLayout = new LinearLayout(this);
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Toast.makeText(this, "Run", Toast.LENGTH_LONG);

    }
}
