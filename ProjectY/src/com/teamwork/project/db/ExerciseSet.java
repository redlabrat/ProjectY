package com.teamwork.project.db;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbl_exercise_set")
public class ExerciseSet {

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_WORKOUT_EXERCISE_ID = "workout_exercise_id";
	public static final String COLUMN_REPS = "reps_amount";
	public static final String COLUMN_WEIGHT = "weight";
	
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;
	
	@DatabaseField(columnName = COLUMN_WORKOUT_EXERCISE_ID,canBeNull = false)
	private Integer idWorkoutExercise;
	
	@DatabaseField(columnName = COLUMN_REPS)
	private Integer amountOfRepeats;
	
	@DatabaseField(columnName = COLUMN_WEIGHT)
	private Float weight;
	

	ExerciseSet() {
		// needed by ormlite
	}
	
	public ExerciseSet(int reps, float weight, WorkoutExercise we) {
		amountOfRepeats = reps;
		this.weight = weight;
		this.idWorkoutExercise = we.getId();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public int getAmountOfRepeats() {
		return amountOfRepeats;
	}

	public void setAmountOfRepeats(int amountOfRepeats) {
		this.amountOfRepeats = amountOfRepeats;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id);
		sb.append(", ").append("weight=").append(weight);
		sb.append(", ").append("reps=").append(amountOfRepeats);
		return sb.toString();
	}
	

}
