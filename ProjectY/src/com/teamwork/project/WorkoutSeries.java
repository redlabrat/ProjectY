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
	public int getCurrentDayNumber() {
		return currentDay;
	}
	
	public WorkoutDay getCurrentDay() {
		if (currentDay != -1) {
			return listOfDays.get(currentDay);
		}
		return null;
	}

	public void finishCurrentDay() {
		this.currentDay++;
	}

	@Override
	public String toString() {
		return "WorkoutSeries [name=" + name + ", listOfDays=" + listOfDays
				+ ", currentDay=" + currentDay + "]";
	}
}
