package com.teamwork.project.data;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// in this collection stored all the DAO objects
	// for persisted classes
	@SuppressWarnings("rawtypes")
	private Map daoHash;
	
	
	
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
			TableUtils.createTable(connectionSource, DiaryDay.class);
			TableUtils.createTable(connectionSource, DiaryExercise.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Can't create database", e);
			throw new RuntimeException(e);
		}

		Log.e(LOG_TAG, "Filling data");
		// here we try inserting data in the on-create as a test
//		RuntimeExceptionDao<ExerciseSet, Integer> dao1 = getExerciseSetDao();

		
		// create some entries in the onCreate
		DictionaryExercise dex1 = new DictionaryExercise("Bench-Press","Good for chest."); 
		DictionaryExercise dex2 = new DictionaryExercise("Flights", "Best for inner part.");
		DictionaryExercise dex3 = new DictionaryExercise("Pull-Up", "Best for back.");
		DictionaryExercise dex4 = new DictionaryExercise("Dead lift", "Do it hard.");
		dex1.storeToDB();
		dex2.storeToDB();
		dex3.storeToDB();
		dex4.storeToDB();
		

		WorkoutMacroCycle macro = new WorkoutMacroCycle("Iron Cycle");
		macro.storeToDB();
		
			WorkoutMicroCycle micro = new WorkoutMicroCycle(macro.getId());
			micro.setName("Gaining Mass");
			micro.storeToDB();
			
			
					WorkoutDay wd1 = new WorkoutDay(micro.getId());
					wd1.setName("Day Chest");
					wd1.storeToDB();
					
							WorkoutExercise wex1 = new WorkoutExercise(dex1.getId(),wd1.getId());
							wex1.setPlanned("5x100@5x110@6x110");
							wex1.setExecuted("5x100@5x110@4x110@4x115");
							wex1.storeToDB();
					
							WorkoutExercise wex2 = new WorkoutExercise(dex2.getId(),wd1.getId());
							wex2.setPlanned("10x10@15x20@15x30");
							wex2.setExecuted("15x10@20x20@20x40");
							wex2.storeToDB();
							
				
					wd1.addExercise(wex1);
					wd1.addExercise(wex2);

					WorkoutDay wd2 = new WorkoutDay(micro.getId());
					wd2.setName("Day Back");
					wd2.storeToDB();
					
					
							WorkoutExercise wex3 = new WorkoutExercise(dex3.getId(),wd2.getId());
							wex3.setPlanned("10x0@12x0@15x0");
							wex3.setExecuted("10x0@12x0@15x0");
							wex3.storeToDB();
					
							WorkoutExercise wex4 = new WorkoutExercise(dex4.getId(),wd2.getId());
							wex4.setPlanned("10x100@5x120@2x150");
							wex4.setExecuted("10x100@5x120@2x150");
							wex4.storeToDB();
				
					wd2.addExercise(wex3);
					wd2.addExercise(wex3);

					
			micro.addDay(wd1);
			micro.addDay(wd2);
			
		macro.addMicroCycle(micro);
		
					
		// Creating some DIARY-LOG entries			
					
		Calendar cal = Calendar.getInstance();
		
		DiaryDay dd1 = new DiaryDay(wd1);
		cal.set(2012, 6, 10);
		dd1.setDate(cal.getTime());
		dd1.storeToDB();
		
			for(WorkoutExercise we : wd1.getListOfExercises()){
				DiaryExercise de = new DiaryExercise(dd1,we);
				de.storeToDB();
			}		

		
		DiaryDay dd2 = new DiaryDay(wd2);
		cal.set(2012, 6, 11);
		dd2.setDate(cal.getTime());
		dd2.storeToDB();

			for(WorkoutExercise we : wd2.getListOfExercises()){
				DiaryExercise de = new DiaryExercise(dd2,we);
				de.storeToDB();
			}		

			
		DiaryDay dd3 = new DiaryDay(wd1);
		cal.set(2012, 6, 12);
		dd3.setDate(cal.getTime());
		dd3.storeToDB();
	
			for(WorkoutExercise we : wd1.getListOfExercises()){
				DiaryExercise de = new DiaryExercise(dd3,we);
				de.storeToDB();
			}		


		
		DiaryDay dd4 = new DiaryDay(wd2);
		cal.set(2012, 6, 13);
		dd4.setDate(cal.getTime());
		dd4.storeToDB();

			for(WorkoutExercise we : wd2.getListOfExercises()){
				DiaryExercise de = new DiaryExercise(dd4,we);
				de.storeToDB();
			}		

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
			TableUtils.dropTable(connectionSource, DiaryDay.class, true);
			TableUtils.dropTable(connectionSource, DiaryExercise.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> RuntimeExceptionDao<T, Integer> dao(Class<T> c){
		if (daoHash == null)
			daoHash =  new HashMap<String, RuntimeExceptionDao<T, Integer>>();
		
		if(!daoHash.containsKey(c.toString()))
			daoHash.put(c.toString(),getRuntimeExceptionDao(c));

		return (RuntimeExceptionDao<T, Integer>) daoHash.get(c.toString());
	} 
	
	// QUERY METHODS FOR WORKING WITH DATA IN DB

	public List<DiaryExercise> queryDiaryExercisesForRelatedDiaryDayId(Integer diaryDayId){
		List<DiaryExercise> resultSet = null;
		try {
			resultSet = dao(DiaryExercise.class).queryBuilder().where().eq(DiaryExercise.COLUMN_ID_DAY, diaryDayId).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public List<DiaryDay> queryDiaryDaysForTimeInterval(Calendar start, Calendar end){
		List<DiaryDay> resultSet = null;
		start.add(Calendar.DAY_OF_MONTH, -1);
		end.add(Calendar.DAY_OF_MONTH, +1);
		
		try {
			resultSet = dao(DiaryDay.class).queryBuilder().where().between(DiaryDay.COLUMN_DATE, start.getTime(), end.getTime()).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	// General methods for every class
	public <T extends PersistentObject> void  queryStore(T object){
		object.storeToDB();
	}
	
	
	public <T extends PersistentObject> void  queryUpdate(T object){
		object.updateInDB();
	}
	
	
	public <T extends PersistentObject> void  queryDelete(T object){
		object.deleteFromDB();
	}
	// END OF General methods for every class
	

	
	// Building the objects from DataBase by locatig them by ID
	public  <T extends PersistentObject> T queryForId(Class<T> c,Integer id){
		return dao(c).queryForId(id);
	}
	// END OF Building the objects from DataBase by locatig them by ID	
	
	
	// END OF QUERY METHODS
	
	
	

	@Override
	public void close() {
		super.close();
		daoHash.clear();
		daoHash = null;
		Log.e(LOG_TAG,"DatabaseHelper Closed");
	}
}
