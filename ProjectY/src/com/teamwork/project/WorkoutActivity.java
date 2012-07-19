package com.teamwork.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import static com.teamwork.project.Constants.*;

public class WorkoutActivity extends Activity implements ExpandableListView.OnChildClickListener {
	private ExpandableListView planListView = null;
	private WorkoutPlan plan = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		plan = ((ProjectYApplication) getApplication()).trainingPlan;
		if (plan == null) {
			emulateReadingPlanFromDB();
			plan = ((ProjectYApplication) getApplication()).trainingPlan;
		}
		
		planListView = (ExpandableListView) findViewById(R.id.planListView);
		planListView.setAdapter(new PlanListViewAdapter(getApplicationContext(), plan));
		// expanding current group
		planListView.expandGroup(plan.getCurrentSeriesNumber());
		planListView.setOnChildClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_workout, menu);
		return true;
	}

	/** 
	 * Fills global application WorkoutPlan object with random data
	 * instead of this function should be some operation of reading data from DB
	 * into global WorkoutPlan object as field of ProjectYApplication.
	 */
	private void emulateReadingPlanFromDB() {
		ProjectYApplication appState = (ProjectYApplication) getApplication();
		appState.trainingPlan = new WorkoutPlan();
		// creating 2 series of exercises
		for(int k = 0; k < 2; k++) {
			WorkoutSeries tempSeries = new WorkoutSeries(String.format("Series %d", k+1));
			// adding 5 days to each series
			for(int j = 0; j < 5; j++) {
				WorkoutDay tempDay = new WorkoutDay(String.format("Day %d", j+1));
				// adding 4 exercises to each day
				for(int i = 0; i < 4; i++) {
					WorkoutExercise tempExercise = new WorkoutExercise();
					// filling just with planned sets, but need to read all sets and exercises
					// adding 3 planned sets
					tempExercise.addPlannedSet(5 + i, 40f + i * 5);
					tempExercise.addPlannedSet(4 + i, 38.5f + i * 5);
					tempExercise.addPlannedSet(4 + i, 36f + i * 5);
					tempExercise.setName(String.format("Exercise %d", i+1));
					tempDay.addExercise(tempExercise);
				}
				tempDay.setDescription("Short description of exercises");
				tempSeries.addDay(tempDay);
			}
			appState.trainingPlan.addSeries(tempSeries);
		}
		appState.trainingPlan.finishCurrentDay();
		appState.trainingPlan.finishCurrentDay();
	}

	/**
	 * Process click on child item (day of exercise)
	 * return true if click was handled
	 */
	public boolean onChildClick(ExpandableListView parent, View v,
			int sectionNo, int dayNo, long id) {
		Intent intent = new Intent(getApplicationContext(), DayProgressActivity.class);
		// giving selected day to the next activity
		intent.putExtra(selectedSeriesString, sectionNo);
		intent.putExtra(selectedDayString, dayNo);
		startActivityForResult(intent, RESULT_OK);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO: need to implement some processing
		super.onActivityResult(requestCode, resultCode, data);
	}
}
