package com.teamwork.project.db;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbl_workout_day")
public class WorkoutDay implements PersistentObject{

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_MICRO_CYCLE = "micro_cycle";
	
	
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;

	@DatabaseField(columnName = COLUMN_MICRO_CYCLE, canBeNull = false)
	public Integer idMicroCycle;
	
	@DatabaseField(columnName = COLUMN_NAME)
	private String name;
	
	private ArrayList<WorkoutExercise> listOfExercises;
	
	private int currentExercise;

	public WorkoutDay() {
		// needed for ORM
	}
	
	public WorkoutDay(String name) {
		// for returning -1 when listOfExercises is empty
		currentExercise = -1;
		listOfExercises = new ArrayList<WorkoutExercise>();
		this.name = name;
	}
	
	
	public WorkoutDay(Integer microCycleId) {
		// for returning -1 when listOfExercises is empty
		currentExercise = -1;
		listOfExercises = new ArrayList<WorkoutExercise>();
		idMicroCycle = microCycleId;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getMicroCycleId() {
		return idMicroCycle;
	}
	
	public void setMicroCycleId(Integer microCyccleId) {
		this.idMicroCycle = microCyccleId ;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addExercise(WorkoutExercise exercise) {
		listOfExercises.add(exercise);
		if (currentExercise == -1) {
			currentExercise = 0;
		}
	}
	
	public void getListOfExercises(ArrayList<WorkoutExercise> outList) {
		outList.clear();
		outList.addAll(listOfExercises);
	}

	public void finishCurrentExercise() {
		currentExercise++;
	}

	/**
	 * @return current exercise or -1 if there is no exercise were added
	 */
	public int getCurrentExercise() {
		return currentExercise;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DAY: id=").append(id);
		sb.append(", ").append("name=").append(name);
		sb.append(", ").append("micro=").append(idMicroCycle);
		return sb.toString();
	}
}
