package com.teamwork.project;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class DayProgressListViewAdapter extends BaseExpandableListAdapter {
	private Context context = null;
	private ArrayList<WorkoutExercise> array = null;
	
	public DayProgressListViewAdapter(Context context, ArrayList<WorkoutExercise> arrayToShow) {
		this.context = context;
		this.array = arrayToShow;
	}

	public Object getChild(int exerciseNo, int setNo) {
		return array.get(exerciseNo).getPlannedSets().get(setNo);
	}

	public long getChildId(int exerciseNo, int setNo) {
		return setNo;
	}

	public int getChildrenCount(int exerciseNo) {
		return array.get(exerciseNo).getPlannedSets().size();
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.workout_list_day, null);
		}
		
		return convertView;
	}

	public Object getGroup(int exerciseNo) {
		return array.get(exerciseNo);
	}

	public int getGroupCount() {
		return array.size();
	}

	public long getGroupId(int exerciseNo) {
		return exerciseNo;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
