package com.teamwork.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class WorkoutActivity extends Activity implements ExpandableListView.OnChildClickListener {
	private ExpandableListView planListView = null;
	private WorkoutPlan plan = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);
		plan = ((ProjectYApplication) getApplication()).trainingPlan;
		if (plan == null) {
			plan = new WorkoutPlan();
			emulateReadingPlanFromDB();
		}
		
		planListView = (ExpandableListView) findViewById(R.id.planListView);
		planListView.setAdapter(new PlanListViewAdapter(getApplicationContext(), plan));
		planListView.expandGroup(plan.getCurrentSeries());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_workout, menu);
		return true;
	}

	private void emulateReadingPlanFromDB() {
		for(int k = 0; k < 2; k++) {
			WorkoutSeries tempSeries = new WorkoutSeries(String.format("Series %d", k+1));
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
			plan.addSeries(tempSeries);
		}
		
	}

	/**
	 * process click on child item (day of exercise)
	 * return true if click was handled
	 */
	public boolean onChildClick(ExpandableListView parent, View v,
			int sectionNo, int dayNo, long id) {
		
		return true;
	}
}
