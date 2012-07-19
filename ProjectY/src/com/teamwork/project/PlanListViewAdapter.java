package com.teamwork.project;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class PlanListViewAdapter extends BaseExpandableListAdapter {
	private Context context = null;
	private WorkoutPlan plan = null;
	
	public PlanListViewAdapter(Context context, WorkoutPlan planToShow) {
		this.context = context;
		plan = planToShow;
	}

	public Object getChild(int seriesNo, int dayNo) {
		return plan.getListOfSeries().get(seriesNo).getListOfDays().get(dayNo);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int seriesNo) {
		return plan.getListOfSeries().get(seriesNo).getListOfDays().size();
	}

	public View getChildView(int seriesNo, int dayNo,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.workout_list_day, null);
		}
		
		WorkoutDay tempDay = plan.getListOfSeries().get(seriesNo).getListOfDays().get(dayNo);
		ArrayList<WorkoutExercise> exerciseArray = new ArrayList<WorkoutExercise>();
		tempDay.getListOfExercises(exerciseArray);
		TextView textDayName = (TextView) convertView.findViewById(R.id.textDayName);
		textDayName.setText(tempDay.getName());
		
//		String exersiceStr = new String();
//		// writing all exercise's names in one string
//		for(int i = 0; i < exerciseArray.size(); i++) {
//			exersiceStr += exerciseArray.get(i).getName() + "\n";
//		}
//		TextView textDayEx = (TextView) convertView.findViewById(R.id.textDayExercises);
//		textDayEx.setText(exersiceStr);
		return convertView;
	}

	public View getGroupView(int seriesNo, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.workout_list_group, null);
		}
		if (isExpanded) {
			// some code for expanded group
		}
		
		TextView textName = (TextView) convertView.findViewById(R.id.textGroupName);
		textName.setText(plan.getListOfSeries().get(seriesNo).getName());
		
		return convertView;
	}

	public Object getGroup(int seriesNo) {
		return plan.getListOfSeries().get(seriesNo);
	}

	public int getGroupCount() {
		return plan.getListOfSeries().size();
	}

	public long getGroupId(int seriesNo) {
		return seriesNo;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isChildSelectable(int sectionNo, int dayNo) {
		return true;
	}

}