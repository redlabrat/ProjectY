package com.teamwork.project.db;

// структура базы данных.
// таблицы там... может еще чего...


import android.database.sqlite.SQLiteDatabase;

@SuppressWarnings("unused")
public class DatabaseSchema {
	private SQLiteDatabase database;
	
	final TableExerciseInDictionary tblExerciseInDictionary = new TableExerciseInDictionary();
//	final TableWorkoutExercise 		tblWorkoutExercise		= new TableWorkoutExercise();
//	final TableExerciseInDictionary tblDiaryEntry			= new TableDiaryEntry();
	
	public void assignDatabase(SQLiteDatabase db){
		database = db;
		tblExerciseInDictionary.assignDatabase(db);
//		tblWorkoutExercise.assignDatabase(db);
//		tblDiaryEntry.assignDatabase(db);
	}

}
