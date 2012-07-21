package com.teamwork.project.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.teamwork.project.R;

/**
 * Database helper class used to manage the creation and upgrading of your database.
 * This class provides the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private final String LOG_TAG = DatabaseHelper.class.getCanonicalName();

	private static final String DATABASE_NAME = "projectY.db";
	private static final int DATABASE_VERSION = 1;

	private RuntimeExceptionDao<ExerciseSet, Integer> exerciseSetDao = null;
	private RuntimeExceptionDao<DictionaryExercise, Integer> dictioanryExerciseDao = null;
	private RuntimeExceptionDao<WorkoutDay, Integer> workoutDayDao = null;
	private RuntimeExceptionDao<WorkoutExercise, Integer> workoutExerciseDao = null;
	private RuntimeExceptionDao<WorkoutMicroCycle, Integer> workoutMicroCycleDao = null;
	private RuntimeExceptionDao<WorkoutMacroCycle, Integer> workoutMacroCycleDao = null;
	private RuntimeExceptionDao<DiaryEntry, Integer> diaryEntryDao = null;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
		Log.e(LOG_TAG,"Create DatabaseHelper");
	}



	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		Log.e(LOG_TAG, "onCreate");
		try {
			TableUtils.createTable(connectionSource, DictionaryExercise.class);
			TableUtils.createTable(connectionSource, WorkoutDay.class);
			TableUtils.createTable(connectionSource, WorkoutExercise.class);
			TableUtils.createTable(connectionSource, ExerciseSet.class);
			TableUtils.createTable(connectionSource, WorkoutMicroCycle.class);
			TableUtils.createTable(connectionSource, WorkoutMacroCycle.class);
			TableUtils.createTable(connectionSource, DiaryEntry.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Can't create database", e);
			throw new RuntimeException(e);
		}

		Log.e(LOG_TAG, "Filling data");
		// here we try inserting data in the on-create as a test
//		RuntimeExceptionDao<ExerciseSet, Integer> dao1 = getExerciseSetDao();
		RuntimeExceptionDao<DictionaryExercise, Integer> dao2 = getDictioanryExerciseDao();
		RuntimeExceptionDao<WorkoutExercise, Integer> dao3 = getWorkoutExerciseDao();
		RuntimeExceptionDao<WorkoutDay, Integer> dao4 = getWorkoutDayDao();
		RuntimeExceptionDao<WorkoutMicroCycle, Integer> dao5 = getWorkoutMicroCycleDao();
		RuntimeExceptionDao<WorkoutMacroCycle, Integer> dao6 = getWorkoutMacroCycleDao();
		RuntimeExceptionDao<DiaryEntry, Integer> dao7 = getDiaryEntryDao();
		Log.e(LOG_TAG, "YES0");
		
		// create some entries in the onCreate
		DictionaryExercise dex1 = new DictionaryExercise("Bench-Press","Good for chest."); 
		DictionaryExercise dex2 = new DictionaryExercise("Flights", "Best for inner part.");
		dao2.create(dex1);
		dao2.create(dex2);
		Log.e(LOG_TAG, "YES1");
		

		WorkoutMacroCycle macro = new WorkoutMacroCycle("Iron Cycle");
		dao6.create(macro); // no ID untill you store into DB
		Log.e(LOG_TAG, "YES2");
		
			WorkoutMicroCycle micro = new WorkoutMicroCycle(macro.getId());
			micro.setName("Gaining Mass");
			dao5.create(micro); // no ID untill you store into DB
			Log.e(LOG_TAG, "YES3");
			
			
					WorkoutDay wd = new WorkoutDay(micro.getId());
					wd.setName("Day Chest");
					dao4.create(wd); // no ID untill you store into DB
					Log.e(LOG_TAG, "YES4");
					
					
							WorkoutExercise wex1 = new WorkoutExercise(dex1.getId(),wd.getId());
							wex1.setPlanned("5x100@5x110@6x110");
							wex1.setExecuted("5x100@5x110@4x110@4x115");
							dao3.create(wex1);
					
							WorkoutExercise wex2 = new WorkoutExercise(dex2.getId(),wd.getId());
							wex2.setPlanned("10x10@15x20@15x30");
							wex2.setExecuted("15x10@20x20@20x40");
							dao3.create(wex2);
							
							Log.e(LOG_TAG, "YES5");
				
					wd.addExercise(wex1);
					wd.addExercise(wex2);
					dao4.update(wd);
		
					Log.e(LOG_TAG, "YES6");
	
			micro.addDay(wd);
			dao5.update(micro);
			Log.e(LOG_TAG, "YES7");
			
		macro.addMicroCycle(micro);
		dao6.update(macro);
		Log.e(LOG_TAG, "YES8");
		
		
		DiaryEntry de1 = new DiaryEntry(wex1.getId());
		dao7.create(de1);
		
		DiaryEntry de2 = new DiaryEntry(wex2.getId());
		dao7.create(de2);
		Log.e(LOG_TAG, "YES9");
		
		

//		dao1.create(new ExerciseSet(5, 100, we));
//		dao1.create(new ExerciseSet(8, 200, we));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		Log.e(LOG_TAG, "onUpgrade");
		try {
			TableUtils.dropTable(connectionSource, DictionaryExercise.class, true);
			TableUtils.dropTable(connectionSource, ExerciseSet.class, true);
			TableUtils.dropTable(connectionSource, WorkoutExercise.class, true);
			TableUtils.dropTable(connectionSource, WorkoutDay.class, true);
			TableUtils.dropTable(connectionSource, WorkoutMicroCycle.class, true);
			TableUtils.dropTable(connectionSource, WorkoutMacroCycle.class, true);
			TableUtils.dropTable(connectionSource, DiaryEntry.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}


	public RuntimeExceptionDao<ExerciseSet, Integer> getExerciseSetDao() {
		if (exerciseSetDao == null) {
			exerciseSetDao = getRuntimeExceptionDao(ExerciseSet.class);
		}
		return exerciseSetDao;
	}


	public RuntimeExceptionDao<DictionaryExercise, Integer> getDictioanryExerciseDao() {
		if (dictioanryExerciseDao == null) {
			dictioanryExerciseDao = getRuntimeExceptionDao(DictionaryExercise.class);
		}
		return dictioanryExerciseDao;
	}


	public RuntimeExceptionDao<WorkoutExercise, Integer> getWorkoutExerciseDao() {
		if (workoutExerciseDao == null) {
			workoutExerciseDao = getRuntimeExceptionDao(WorkoutExercise.class);
		}
		return workoutExerciseDao;
	}
	
	public RuntimeExceptionDao<WorkoutDay, Integer> getWorkoutDayDao() {
		if (workoutDayDao == null) {
			workoutDayDao = getRuntimeExceptionDao(WorkoutDay.class);
		}
		return workoutDayDao;
	}
	
	
	public RuntimeExceptionDao<WorkoutMicroCycle, Integer> getWorkoutMicroCycleDao() {
		if (workoutMicroCycleDao == null) {
			workoutMicroCycleDao = getRuntimeExceptionDao(WorkoutMicroCycle.class);
		}
		return workoutMicroCycleDao;
	}

	public RuntimeExceptionDao<WorkoutMacroCycle, Integer> getWorkoutMacroCycleDao() {
		if (workoutMacroCycleDao == null) {
			workoutMacroCycleDao = getRuntimeExceptionDao(WorkoutMacroCycle.class);
		}
		return workoutMacroCycleDao;
	}

	public RuntimeExceptionDao<DiaryEntry, Integer> getDiaryEntryDao() {
		if (diaryEntryDao == null) {
			diaryEntryDao = getRuntimeExceptionDao(DiaryEntry.class);
		}
		return diaryEntryDao;
	}
	

	@Override
	public void close() {
		super.close();
		exerciseSetDao = null;
		dictioanryExerciseDao = null;
		workoutExerciseDao = null;
		workoutDayDao = null;
		workoutMicroCycleDao = null;
		workoutMacroCycleDao = null;
		diaryEntryDao = null;
		Log.e(LOG_TAG,"DatabaseHelper Closed");
	}
}
