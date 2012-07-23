package com.teamwork.project.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.teamwork.project.ProjectYApplication;


@DatabaseTable(tableName = "tbl_diary_day")
public class DiaryDay extends PersistentObject {

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_WORKOUT_DAY = "workout_day";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DATE = "date";
	
	private final DateFormat df = new SimpleDateFormat("M/dd/yyyy hh:mm a");
	

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;
	
	@DatabaseField(columnName = COLUMN_WORKOUT_DAY, canBeNull = false)
	public Integer idWorkoutDay;
	

	@DatabaseField(columnName = COLUMN_TITLE)
	private String title;
	

	@DatabaseField(columnName = COLUMN_DATE, index = true)
	private Date date;

	

	public DiaryDay() {
		// needed for ORM
	}
	
	public DiaryDay(WorkoutDay workoutDay) {
		this.idWorkoutDay = workoutDay.getId();
		this.title = workoutDay.getName();
		this.date = Calendar.getInstance().getTime();
	}
	
	public DiaryDay(Integer workoutDayId) {
		this.idWorkoutDay = workoutDayId;
		this.title = ProjectYApplication.getDataSource().queryForId(WorkoutDay.class,workoutDayId).getName();
		this.date = Calendar.getInstance().getTime();
	}

	public Integer getId() {
		return this.id;
	}
	
	public Integer getWorkoutDayId() {
		return idWorkoutDay;
	}

	public void setWorkoutDayId(Integer workoutDayId) {
		this.idWorkoutDay = workoutDayId ;
	}


	public String getTile() {
		return title;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DIARY_DAY: id=").append(id);
		sb.append(" day=").append(idWorkoutDay);
		sb.append("(").append(title).append(")");
		sb.append(", ").append(" date=").append(df.format(date));
		return sb.toString();
	}



}
