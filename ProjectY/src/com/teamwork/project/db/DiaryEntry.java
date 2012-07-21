package com.teamwork.project.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "tbl_diary_entry")
public class DiaryEntry implements PersistentObject {

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_WORK_EXERCISE = "workout_exercise";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TIME = "time";
	
	private final DateFormat df = new SimpleDateFormat("M/dd/yyyy hh:mm a");
	

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;

	@DatabaseField(columnName = COLUMN_WORK_EXERCISE, canBeNull = false)
	public Integer idWorkoutExercise;

	@DatabaseField(columnName = COLUMN_DATE)
	private Date date;
	
	@DatabaseField(columnName = COLUMN_TIME)
	private String time;
	

	public DiaryEntry() {
		// needed for ORM
	}
	
	public DiaryEntry(Integer workoutExerciseId) {
		this.idWorkoutExercise = workoutExerciseId;
		this.date = new Date();
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getWorkoutExerciseId() {
		return idWorkoutExercise;
	}

	public void setWorkoutExerciseId(Integer workoutExerciseId) {
		this.idWorkoutExercise = workoutExerciseId ;
	}




	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ENTRY: id=").append(id);
		sb.append(", ").append("date=").append(df.format(date));
		return sb.toString();
	}

}
