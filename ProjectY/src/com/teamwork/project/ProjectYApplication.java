package com.teamwork.project;

import com.teamwork.project.data.DatabaseHelper;
import com.teamwork.project.data.WorkoutMacroCycle;

import android.app.Application;
import android.util.Log;

public class ProjectYApplication extends Application {
	public WorkoutMacroCycle trainingPlan = null;
	
	public static String str;
	private static DatabaseHelper dataSource;
	
	@Override
	public void onCreate (){
		Log.e("!!!","ProjectYApplication CREATED");
		dataSource = new DatabaseHelper(this);
		Log.e("!!!","DataSource CREATED");
	}
	
	
	public static DatabaseHelper getDataSource(){
		return dataSource;
	}
	
}
