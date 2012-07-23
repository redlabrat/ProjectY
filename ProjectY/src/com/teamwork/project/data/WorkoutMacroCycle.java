package com.teamwork.project.data;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tbl_macro_cycle")
public class WorkoutMacroCycle extends PersistentObject  {
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	public Integer id;
	
	@DatabaseField(columnName = COLUMN_NAME)
	private String name;
	
	private ArrayList<WorkoutMicroCycle> listMicroCycles;
	private int currentMicroCycle;
	
	public WorkoutMacroCycle() {
		// needed for ORM
	}
	
	public WorkoutMacroCycle(String name) {
		this.name = name;
		// for returning -1 when listOfSeries is empty
		currentMicroCycle = -1;
		listMicroCycles = new ArrayList<WorkoutMicroCycle>();
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
	
	public ArrayList<WorkoutMicroCycle> getListMicroCycles() {
		return listMicroCycles;
	}
	
	public void getListMicroCycles(ArrayList<WorkoutMicroCycle> outList) {
		outList.clear();
		outList.addAll(listMicroCycles);
	}
	
	public void addMicroCycle(WorkoutMicroCycle newMicroCycle) {
		listMicroCycles.add(newMicroCycle);
		if (currentMicroCycle == -1) {
			currentMicroCycle = 0;
		}
	}
	
	public void setListMicroCycles(ArrayList<WorkoutMicroCycle> list) {
		this.listMicroCycles = list;
	}
	
	/**
	 * @return current series or -1 if there is no series were added
	 */
	public int getCurrentMicroCycle() {
		return currentMicroCycle;
	}
	
	public void finishCurrentMicroCycle() {
		this.currentMicroCycle++;
	}
	
	public void finishCurrentDay() {
		listMicroCycles.get(currentMicroCycle).finishCurrentDay();
	}
	
	public int getCurrentDay() {
		return listMicroCycles.get(currentMicroCycle).getCurrentDay();
	}
	
	public int getCurrentExercise() {
		int currDay = this.getCurrentDay();
		ArrayList<WorkoutDay> currSeriesArray = new ArrayList<WorkoutDay>();
		listMicroCycles.get(currentMicroCycle).getListOfDays(currSeriesArray);
		return currSeriesArray.get(currDay).getCurrentExercise();
	}



	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MACRO: id=").append(id);
		sb.append(", ").append("name=").append(name);
		return sb.toString();
	}
}
