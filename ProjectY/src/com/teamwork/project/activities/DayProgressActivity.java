package com.teamwork.project.activities;

import com.teamwork.project.R;
import com.teamwork.project.R.layout;
import com.teamwork.project.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DayProgressActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_progress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_day_progress, menu);
        return true;
    }

    
}
