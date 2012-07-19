package com.teamwork.project;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ExpandableListView;
import static com.teamwork.project.Constants.*;

public class DayProgressActivity extends Activity {
	private ExpandableListView dayListView = null;
	private ArrayList<WorkoutExercise> selectedDayExercises = null;
	private WorkoutPlan plan = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day_progress);
		plan = ((ProjectYApplication) getApplication()).trainingPlan;
		
		// getting array of exercises in selected day
		Intent intent = getIntent();
		int selectedSeries = intent.getIntExtra(selectedSeriesString, -1);
		int selectedDay = intent.getIntExtra(selectedDayString, -1);
		if (selectedSeries != -1) {
			if (selectedDay != -1) {
				selectedDayExercises = 
					plan.getListOfSeries().get(selectedSeries).getListOfDays().get(selectedDay).getListOfExercises();
			} else {
				Log.e("error", "Error getting selected day from intent. DayProgressActivity");
			}
			Log.e("error", "Error getting selected series from intent. DayProgressActivity");
		}
		
		dayListView = (ExpandableListView) findViewById(R.id.dayListView);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_day_progress, menu);
		return true;
	}


}
