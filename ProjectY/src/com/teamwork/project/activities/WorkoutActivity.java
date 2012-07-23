package com.teamwork.project.activities;

import com.teamwork.project.ProjectYApplication;
import com.teamwork.project.R;
import com.teamwork.project.R.id;
import com.teamwork.project.R.layout;
import com.teamwork.project.R.menu;
import com.teamwork.project.data.WorkoutDay;
import com.teamwork.project.data.WorkoutExercise;
import com.teamwork.project.data.WorkoutMacroCycle;
import com.teamwork.project.data.WorkoutMicroCycle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class WorkoutActivity extends Activity implements ExpandableListView.OnChildClickListener {
	private ExpandableListView planListView = null;
	private WorkoutMacroCycle plan = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		plan = ((ProjectYApplication) getApplication()).trainingPlan;
		if (plan == null) {
			plan = new WorkoutMacroCycle();
			emulateReadingPlanFromDB();
		}
		
		planListView = (ExpandableListView) findViewById(R.id.planListView);
		planListView.setAdapter(new PlanListViewAdapter(getApplicationContext(), plan));
		planListView.expandGroup(plan.getCurrentMicroCycle());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_workout, menu);
		return true;
	}

	private void emulateReadingPlanFromDB() {
		for(int k = 0; k < 2; k++) {
			WorkoutMicroCycle tempSeries = new WorkoutMicroCycle(String.format("Series %d", k+1));
			for(int j = 0; j < 5; j++) {
				WorkoutDay tempDay = new WorkoutDay(String.format("Day %d", j+1));
				for(int i = 0; i < 4; i++) {
					WorkoutExercise tempExercise = new WorkoutExercise();
					tempExercise.addPlannedSet(5 + i, 40 + i * 5);
					tempExercise.setName(String.format("Exercise %d", i+1));
					tempDay.addExercise(tempExercise);
				}
				tempSeries.addDay(tempDay);
			}
			plan.addMicroCycle(tempSeries);
		}
		plan.finishCurrentDay();
		plan.finishCurrentDay();
	}

	/**
	 * process click on child item (day of exercise)
	 * return true if click was handled
	 */
	public boolean onChildClick(ExpandableListView parent, View v,
			int sectionNo, int dayNo, long id) {
		Intent intent = new Intent(getApplicationContext(), DayProgressActivity.class);
		startActivityForResult(intent, RESULT_OK);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
