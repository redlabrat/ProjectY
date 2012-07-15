package com.teamwork.project;

public class ExerciseSet {
	private int amountOfRepeats;
	private float weight;
	
	public ExerciseSet(int reps, float weight) {
		amountOfRepeats = reps;
		this.weight = weight;
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
	
	

}
