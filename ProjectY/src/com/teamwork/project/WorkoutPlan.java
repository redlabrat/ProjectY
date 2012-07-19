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
	
	public void finishCurrentSeries() {
		this.currentSeries++;
	}
	
	public void finishCurrentDay() {
		listOfSeries.get(currentSeries).finishCurrentDay();
	}
	
	/**
	 * @return current series or -1 if there is no series were added
	 */
	public int getCurrentSeriesNumber() {
		return currentSeries;
	}
	
	public int getCurrentDayNumber() {
		return getCurrentSeries().getCurrentDayNumber();
	}
	
	public int getCurrentExerciseNumber() {
		return getCurrentSeries().getCurrentDay().getCurrentExerciseNumber();
	}

	public WorkoutSeries getCurrentSeries() {
		if (currentSeries != -1) {
			return listOfSeries.get(currentSeries);
		}
		return null;
	}

	@Override
	public String toString() {
		return "WorkoutPlan [name=" + name + ", listOfSeries=" + listOfSeries
				+ ", currentSeries=" + currentSeries + "]";
	}
}
