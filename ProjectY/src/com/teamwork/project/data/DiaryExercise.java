package com.teamwork.project.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbl_diary_exercise")
public class DiaryExercise extends PersistentObject {

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ID_DICT = "in_dictionary";
	public static final String COLUMN_ID_DAY = "diary_day";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PLANNED_SETS = "planned";
	public static final String COLUMN_EXECUTED_SETS = "executed";
	public static final String COLUMN_DATE = "date";

	private final DateFormat df = new SimpleDateFormat("M/dd/yyyy hh:mm a");
	
	
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;

	@DatabaseField(columnName = COLUMN_ID_DICT, canBeNull = false)
	public Integer idDictionaryExercise;

	@DatabaseField(columnName = COLUMN_ID_DAY, canBeNull = false, index = true)
	public Integer idDiaryDay;

	@DatabaseField(columnName = COLUMN_NAME)
	private String name;

	@DatabaseField(columnName = COLUMN_PLANNED_SETS)
	public String planned;

	@DatabaseField(columnName = COLUMN_EXECUTED_SETS)
	public String executed;
	
	@DatabaseField(columnName = COLUMN_DATE)
	public Date date;

	private ArrayList<ExerciseSet> plannedSets;
	private ArrayList<ExerciseSet> executedSets;

	public DiaryExercise() {
		// needed for ORM
	}

//	public DiaryExercise(Integer dictExerciseId,Integer diaryDayId) {
//		this.idDictionaryExercise = dictExerciseId;
//		this.idDiaryDay = diaryDayId;
//		plannedSets = new ArrayList<ExerciseSet>();
//		executedSets = new ArrayList<ExerciseSet>();
//	}
	
	public DiaryExercise(DiaryDay diaryDay, WorkoutExercise workoutExercise) {
		plannedSets = new ArrayList<ExerciseSet>();
		executedSets = new ArrayList<ExerciseSet>();
		
		this.idDictionaryExercise = workoutExercise.getDictionaryExerciseId();
		planned = workoutExercise.getPlanned();
		executed = workoutExercise.getExecuted();
		
		this.idDiaryDay = diaryDay.getId();
		
		this.date = Calendar.getInstance().getTime();
	}
	

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 

	public void setPlanned(String plan) {
		this.planned = plan;
		refreshMapping();
	} 

	public String getPlanned() {
		return this.planned;
	}

	public void setExecuted(String exec) {
		this.executed = exec;
		refreshMapping();
	} 

	public String getExecuted() {
		return this.executed;
	} 
	
	
	public Integer getDiaryDayId() {
		return idDiaryDay;
	}

	public void setDiaryDayId(Integer diaryDayId) {
		this.idDiaryDay = diaryDayId;
	}

	
	
	
	// This method builds collections
	// from data about sets which are stored in strings
	// written with format "5x100@4x110@4x115"
	public void refreshMapping(){
		// Split te string on sets
		if(planned!=null){
			if( plannedSets.size()==0 ){
				for(String strSet : planned.split("@"))
				{
					// create ExerciseSet fron each
					// substring and push it in colection
					String[] set = strSet.split("x");
					addPlannedSet(new ExerciseSet(Integer.parseInt(set[0]), Float.parseFloat(set[1]), this));
					
				}
			}
		}
		// the same for executedSets
		if(executed!=null){

			if( executedSets.size()==0 ){
				for(String strSet : executed.split("@"))
				{
					String[] set = strSet.split("x");
					addExecutedSet(new ExerciseSet(Integer.parseInt(set[0]), Float.parseFloat(set[1]), this));
				}
			}
		}
		System.out.println("MAP");
	}



	public void addPlannedSet(int rep, float weight) {
		//		plannedSets.add(new ExerciseSet(rep, weight));
	}

	public void addPlannedSet(ExerciseSet set) {
		plannedSets.add(set);
	}

	public void addExecutedSet(int rep, float weight) {
		//		executedSets.add(new ExerciseSet(rep, weight));
	}

	public void addExecutedSet(ExerciseSet set) {
		executedSets.add(set);
	}

	public void getPlannedSets(ArrayList<ExerciseSet> outList) {
		//		outList.clear();
		//		outList.addAll(plannedSets);
	}

	public void getExecutedSets(ArrayList<ExerciseSet> outList) {
		//		outList.clear();
		//		outList.addAll(executedSets);
	}


	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DIARY_EX: id=").append(id);
		sb.append(", ").append("dict=").append(idDictionaryExercise);
		sb.append(", ").append("day=").append(idDiaryDay);
		sb.append(", ").append("plan=").append(planned);
		sb.append(", ").append("exec=").append(executed);
		sb.append(", ").append("date=").append(df.format(date));
		return sb.toString();
	}
}
