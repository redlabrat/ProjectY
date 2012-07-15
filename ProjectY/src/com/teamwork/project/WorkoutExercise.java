package com.teamwork.project;

import java.util.ArrayList;

public class WorkoutExercise {

	private ArrayList<ExerciseSet> plannedSets;
	private ArrayList<ExerciseSet> executedSets;
	private String name;

	public WorkoutExercise() {
		plannedSets = new ArrayList<ExerciseSet>();
		executedSets = new ArrayList<ExerciseSet>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	public void addPlannedSet(int rep, float weight) {
		plannedSets.add(new ExerciseSet(rep, weight));
	}

	public void addPlannedSet(ExerciseSet set) {
		plannedSets.add(set);
	}
	
	public void addExecutedSet(int rep, float weight) {
		executedSets.add(new ExerciseSet(rep, weight));
	}

	public void addExecutedSet(ExerciseSet set) {
		executedSets.add(set);
	}
	
	public void getPlannedSets(ArrayList<ExerciseSet> outList) {
		outList.clear();
		outList.addAll(plannedSets);
	}

	public void getExecutedSets(ArrayList<ExerciseSet> outList) {
		outList.clear();
		outList.addAll(executedSets);
	}
}