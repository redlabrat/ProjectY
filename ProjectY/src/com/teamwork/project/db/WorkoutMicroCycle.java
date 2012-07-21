package com.teamwork.project.db;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbl_micro_cycle")
public class WorkoutMicroCycle {
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_MACRO_CYCLE = "macro_cycle";
	
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;

	@DatabaseField(columnName = COLUMN_MACRO_CYCLE, canBeNull = false)
	public Integer idMacroCycle;
	
	@DatabaseField(columnName = COLUMN_NAME)
	private String name;
	
	private ArrayList<WorkoutDay> listOfDays;
	private int currentDay;
	
	public WorkoutMicroCycle() {
		// needed for ORM
	}
	
	public WorkoutMicroCycle(Integer macroCycleId) {
		this.idMacroCycle = macroCycleId;
		
		listOfDays = new ArrayList<WorkoutDay>();
		// for returning -1 when listOfDays is empty
		currentDay = -1;
	}

	

	public WorkoutMicroCycle(String name) {
		this.name = name;
		listOfDays = new ArrayList<WorkoutDay>();
		// for returning -1 when listOfDays is empty
		currentDay = -1;
	}

	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addDay(WorkoutDay day){
		listOfDays.add(day);
		if (currentDay == -1) {
			currentDay = 0;
		}
	}
	
	public ArrayList<WorkoutDay> getListOfDays() {
		return listOfDays;
	}
	
	public void getListOfDays(ArrayList<WorkoutDay> outList) {
		outList.clear();
		outList.addAll(listOfDays);
	}
	
	/**
	 * @return current day or -1 if there is no days were added
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	public void finishCurrentDay() {
		this.currentDay++;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MICRO: id=").append(id);
		sb.append(", ").append("name=").append(name);
		sb.append(", ").append("macro=").append(idMacroCycle);
		return sb.toString();
	}
}
