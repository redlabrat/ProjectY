package com.teamwork.project.db;

import java.util.Collection;

import javax.xml.validation.Schema;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@SuppressWarnings("unused")
public class YDataSource {
	private String LOG_TAG = YDataSource.class.getName() + " ";
	
	static final String DATABASE_NAME = "projectY.db";
	static final int 	DATABASE_VERSION = 2;
	
	
	private SQLiteDatabase database;
	private DBOpenHelper dbHelper;
	private DatabaseSchema sch;
	

	public YDataSource(Context context) {
		sch = new DatabaseSchema();
		dbHelper = new DBOpenHelper(context);
		dbHelper.assignSchema(sch);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		if(null!=database)
			sch.assignDatabase(database);
	}

	public void close() {
		dbHelper.close();
	}

	// Methods to work with Exercise_in_DB
	public void getAllExercises(Collection<DatabaseGenericObject> objects){
		sch.tblExerciseInDictionary.getAllObjects(objects);
	}
	
	public long storeExercise(DatabaseGenericObject obj) {
		return sch.tblExerciseInDictionary.storeObject(obj);
	}
	
	public long updateExercise(DatabaseGenericObject obj) {
		return sch.tblExerciseInDictionary.updateObject(obj);
	}
	
	public void deleteExercise(DatabaseGenericObject obj){
		sch.tblExerciseInDictionary.deleteObject(obj.id);
	}
	// END OF Methods to work with Exercise_in_DB
	
	
	
	

	


}