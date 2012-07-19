package com.teamwork.project;

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
