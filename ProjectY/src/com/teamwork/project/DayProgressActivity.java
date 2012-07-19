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

		if (plan != null) {
			// getting array of exercises in selected day
			Intent intent = getIntent();
			int selectedSeries = intent.getIntExtra(selectedSeriesString, -1);
			int selectedDay = intent.getIntExtra(selectedDayString, -1);
			if (selectedSeries != -1) {
				if (selectedDay != -1) {
					selectedDayExercises = 
						plan.getListOfSeries().get(selectedSeries).getListOfDays().
							get(selectedDay).getListOfExercises();
//					Log.e("test", String.format("in selected day %d exercises", selectedDayExercises.size()));
				} else {
					Log.e("error", String.format(
							"Error getting selected day from intent: selectedDay = %d. DayProgressActivity", selectedDay));
				}
			} else {
				Log.e("error", String.format(
						"Error getting selected series from intent: selectedSeries = %d. DayProgressActivity", selectedSeries));
			}

			dayListView = (ExpandableListView) findViewById(R.id.dayListView);
			dayListView.setAdapter(new DayProgressListViewAdapter(getApplicationContext(), selectedDayExercises));
			dayListView.expandGroup(
					plan.getListOfSeries().get(selectedSeries).
						getListOfDays().get(selectedDay).getCurrentExerciseNumber());
		} else {
			Log.e("error", "Error using WorkoutPlan: it is not created. DayProgressActivity");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_day_progress, menu);
		return true;
	}


}
