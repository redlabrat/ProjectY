package com.teamwork.project;

import java.util.ArrayList;

public class WorkoutPlan {
	private String name;
	private ArrayList<WorkoutSeries> listOfSeries;
	private int currentSeries;
	
	public WorkoutPlan() {
		name = new String();
		// for returning -1 when listOfSeries is empty
		currentSeries = -1;
		listOfSeries = new ArrayList<WorkoutSeries>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<WorkoutSeries> getListOfSeries() {
		return listOfSeries;
	}
	
	public void getListOfSeries(ArrayList<WorkoutSeries> outList) {
		outList.clear();
		outList.addAll(listOfSeries);
	}
	
	public void addSeries(WorkoutSeries newSeries) {
		listOfSeries.add(newSeries);
		if (currentSeries == -1) {
			currentSeries = 0;
		}
	}
	
	public void setListOfSeries(ArrayList<WorkoutSeries> listOfSeries) {
		this.listOfSeries = listOfSeries;
	}
	
	/**
	 * @return current day or -1 if there is no days were added
	 */
	public int getCurrentSeries() {
		if (listOfSeries.isEmpty()) {
			return -1;
		}
		return currentSeries;
	}
	
	public void finishCurrentSeries() {
		this.currentSeries++;
	}
	
	public int getCurrentDay() {
		return listOfSeries.get(currentSeries).getCurrentDay();
	}
	
	public int getCurrentExercise() {
		int currDay = this.getCurrentDay();
		ArrayList<WorkoutDay> currSeriesArray = new ArrayList<WorkoutDay>();
		listOfSeries.get(currentSeries).getListOfDays(currSeriesArray);
		return currSeriesArray.get(currDay).getCurrentExercise();
	}

	@Override
	public String toString() {
		return "WorkoutPlan [name=" + name + ", listOfSeries=" + listOfSeries
				+ ", currentSeries=" + currentSeries + "]";
	}
}
