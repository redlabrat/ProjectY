package com.teamwork.project.activities;

//import java.util.ArrayList;

import com.teamwork.project.R;
import com.teamwork.project.R.id;
import com.teamwork.project.R.layout;
import com.teamwork.project.data.WorkoutDay;
import com.teamwork.project.data.WorkoutMacroCycle;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class PlanListViewAdapter extends BaseExpandableListAdapter {
	private Context context = null;
	private WorkoutMacroCycle plan = null;
	
	public PlanListViewAdapter(Context context, WorkoutMacroCycle planToShow) {
		this.context = context;
		plan = planToShow;
	}

	public Object getChild(int seriesNo, int dayNo) {
		return plan.getListMicroCycles().get(seriesNo).getListOfDays().get(dayNo);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int seriesNo) {
		return plan.getListMicroCycles().get(seriesNo).getListOfDays().size();
	}

	public View getChildView(int seriesNo, int dayNo,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.workout_list_day, null);
		}
		
		// getting requested day from plan
		WorkoutDay tempDay = plan.getListMicroCycles().get(seriesNo).getListOfDays().get(dayNo);
		
		TextView textDayName = (TextView) convertView.findViewById(R.id.textDayName);
		textDayName.setText(tempDay.getName());
		
		if ((dayNo < plan.getCurrentDay()) && (seriesNo <= plan.getCurrentMicroCycle())){
			CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxFinished);
			checkBox.setChecked(true);
			convertView.setBackgroundColor(Color.GRAY);
		}
		
		String exersiceStr = "Short description of exercises";
//		// writing all exercise's names in one string
//		ArrayList<WorkoutExercise> exerciseArray = new ArrayList<WorkoutExercise>();
//		tempDay.getListOfExercises(exerciseArray);
//		for(int i = 0; i < exerciseArray.size(); i++) {
//			exersiceStr += exerciseArray.get(i).getName() + "\n";
//		}
		TextView textDayEx = (TextView) convertView.findViewById(R.id.textDayExercises);
		textDayEx.setText(exersiceStr);
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
		textName.setText(plan.getListMicroCycles().get(seriesNo).getName());
		
		return convertView;
	}

	public Object getGroup(int seriesNo) {
		return plan.getListMicroCycles().get(seriesNo);
	}

	public int getGroupCount() {
		return plan.getListMicroCycles().size();
	}

	public long getGroupId(int seriesNo) {
		return seriesNo;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isChildSelectable(int seriesNo, int dayNo) {
		if ((dayNo < plan.getCurrentDay()) && (seriesNo <= plan.getCurrentMicroCycle())){
			return false;
		}
		return true;
	}
}
