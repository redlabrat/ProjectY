package com.teamwork.project;

import java.util.ArrayList;

public class WorkoutDay {
	
	private String name;
	private ArrayList<WorkoutExercise> listOfExercises;
	private int currentExercise;
	
	public WorkoutDay(String dayName) {
		// for returning -1 when listOfExercises is empty
		currentExercise = -1;
		listOfExercises = new ArrayList<WorkoutExercise>();
		name = dayName;
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
		return "WorkoutDay [name=" + name + ", listOfExercises="
				+ listOfExercises + ", currentExercise=" + currentExercise
				+ "]";
	}
}
