package com.wakeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.wakeup.fragment.GmtFragment;
import com.wakeup.fragment.MainFragment;
import com.wakeup.fragment.StopWatchFragment;
import com.wakeup.fragment.TimerFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private MainFragment mainFragment;
    private GmtFragment gmtFragment;
    private StopWatchFragment stopWatchFragment;
    private TimerFragment timerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.gmt) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, gmtFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.stop_watch) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, stopWatchFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.timer) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, timerFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }

    private void initView() {
        mainFragment = new MainFragment();
        gmtFragment = new GmtFragment();
        stopWatchFragment = new StopWatchFragment();
        timerFragment = new TimerFragment();
    }

}