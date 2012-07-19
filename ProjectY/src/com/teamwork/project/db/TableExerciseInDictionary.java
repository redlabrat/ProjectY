package com.teamwork.project.db;


// Класс, описывающий таблицу упражнений из справочника в БД
// и методы работы с БД касательно этой таблицы

import java.util.Collection;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

@SuppressWarnings("unused")
public class TableExerciseInDictionary implements DatabaseTable {

	public static final String TABLE_NAME = "tbl_exercise_in_dictionary";

	public static final String[] allColumns = Projection.getAllColumns(); // private or public
	private SQLiteDatabase database;
	
	// Структура таблицы
	private static abstract class Projection implements BaseColumns { 
		private Projection() {}
		
		static final String TABLE_NAME = TableExerciseInDictionary.TABLE_NAME; 
		static final String COLUMN_ID = "_id";
		static final String COLUMN_NAME="exercise_name";
		static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		static String[] getAllColumns(){
			String[] columnNames = {COLUMN_ID,COLUMN_NAME};
			return columnNames;
		}
	}
	
	public void assignDatabase(SQLiteDatabase db) {
		database = db;
	}

	public String getSQLCreateString() {
		return "create table "
				+ TABLE_NAME + " ( "
				+ Projection.COLUMN_ID + " integer primary key autoincrement, "
				+ Projection.COLUMN_NAME + " text not null"
				+");";
	}
	
	public String getSQLDropString() {
		return "DROP TABLE IF EXISTS " + TABLE_NAME;
	}
	
	public long storeObject(DatabaseGenericObject obj) {

		ContentValues values = objectToContentValues(obj);
		
		long insertId = database.insert(TABLE_NAME, null,values);
		obj.id=insertId;
		
		return insertId;
	}


	public long updateObject(DatabaseGenericObject obj) {
		ContentValues values = objectToContentValues(obj);
		
		String id = values.getAsString(Projection.COLUMN_ID);

		long updCount = database.update(TABLE_NAME, values, Projection.COLUMN_ID + " = ?", new String[] { id } );

		return updCount;
	}

	public DatabaseGenericObject readObject(long id){
		String[] columnsToRead = allColumns;
		Cursor cursor = database.query(TABLE_NAME,columnsToRead, Projection.COLUMN_ID + " = " + id, null,null, null, null);
		cursor.moveToFirst();
		DatabaseGenericObject newObject = DatabaseGenericObject.cursorToObject(cursor);
		cursor.close();
		return newObject;
	}

	public void deleteObject(long id) {
		database.delete(TABLE_NAME, Projection.COLUMN_ID + " = " + id, null);
		Log.e("DB","Object deleted with id: " + id);
	}

	public void getAllObjects(Collection<DatabaseGenericObject> objects) {

		String[] columnsToRead = allColumns;
		
		Cursor cursor = database.query(TABLE_NAME,columnsToRead, null, null, null, null,null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			DatabaseGenericObject object = DatabaseGenericObject.cursorToObject(cursor);
			objects.add(object);
			cursor.moveToNext();
		}
		cursor.close();
	}
	
	// Делаем такое преобразование в этом классе,
	// а не в классе объекта, потому что там не будет доступна проекция БД
	public static ContentValues objectToContentValues(DatabaseGenericObject obj){
		ContentValues values = new ContentValues();
		values.put(Projection.COLUMN_ID, obj.id);
		values.put(Projection.COLUMN_NAME, obj.data1);
//		values.put(Projection.COLUMN_ISCHECKED, Boolean.toString(p.isChecked));
		return values;
	}
	
	
	
}
