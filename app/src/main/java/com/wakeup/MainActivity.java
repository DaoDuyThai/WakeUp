package com.wakeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    MainFragment mainFragment = new MainFragment();
    GmtFragment gmtFragment = new GmtFragment();
    StopWatchFragment stopWatchFragment = new StopWatchFragment();
    TimerFragment timerFragment = new TimerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}