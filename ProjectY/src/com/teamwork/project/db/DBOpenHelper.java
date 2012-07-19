package com.teamwork.project.db;

// Открываем БД

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBOpenHelper extends SQLiteOpenHelper{
	private String LOG_TAG = DBOpenHelper.class.getName() + " ";
	
	DatabaseSchema sch;
	
	public DBOpenHelper(Context context) {
		super(context, YDataSource.DATABASE_NAME, null, YDataSource.DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	public void assignSchema(DatabaseSchema schema){
		this.sch = schema;
	}
	

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.e(LOG_TAG,"onCreate: " + sch.tblExerciseInDictionary.getSQLCreateString());
		database.execSQL(sch.tblExerciseInDictionary.getSQLCreateString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e(LOG_TAG,"onUpgrade:" + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL(sch.tblExerciseInDictionary.getSQLDropString());
		onCreate(db);
	}
}