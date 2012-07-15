package com.teamwork.project;

import java.util.ArrayList;

public class WorkoutSeries {
	private String name;
	private ArrayList<WorkoutDay> listOfDays;
	private int currentDay;
	
	public WorkoutSeries(String seriesName) {
		name = seriesName;
		listOfDays = new ArrayList<WorkoutDay>();
		// for returning -1 when listOfDays is empty
		currentDay = -1;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addDay(WorkoutDay day){
		listOfDays.add(day);
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
}
