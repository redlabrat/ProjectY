package com.teamwork.project.db;

// Класс нужен всего лишь для отображения
// объектов в ЛистВью
// проходим мимо, ничего серьезного


import java.util.ArrayList;

import com.teamwork.project.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class AdapterObjects extends BaseAdapter{
	ArrayList<DatabaseGenericObject> objects;
	TestActivity activity;

	int[] colors;

	public AdapterObjects(Activity activity) {
		colors = new int[2];
		colors[0] = Color.GREEN;
		colors[1] = Color.MAGENTA;

		this.activity = (TestActivity) activity;

		objects = this.activity.objects;
	}


	public View getView(int position, View convertView, ViewGroup parent) {

		final DatabaseGenericObject item = objects.get(position);

		LayoutInflater inflater = activity.getLayoutInflater();
		View view = null;

		if (convertView == null){
			view = inflater.inflate(R.layout.db_layout_list_item_for_test_activity,null);
		}else{
			view = convertView;
		}

		TextView vData1 = (TextView) view.findViewById(R.id.DBLayoutListItemForTestActivity_ObjectData1);
		vData1.setText(item.data1);

		
		CheckBox cbChk = (CheckBox) view.findViewById(R.id.DBLayoutListItemForTestActivity_ObjectCheck);
		cbChk.setOnCheckedChangeListener(myCheckChangList);
		cbChk.setTag(position);
		cbChk.setChecked(item.isChecked);

		view.setBackgroundColor(colors[position%2]);

		if(item.isChecked) view.setBackgroundColor(Color.BLUE);

		view.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(activity.getApplicationContext(), ViewObjectActivity.class);
				intent.putExtra(DatabaseGenericObject.class.getCanonicalName(), item);
				activity.startActivityForResult(intent,100);	
			}
		});

		return view;
	}


	OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			DatabaseGenericObject item = getObject(position);
			item.isChecked = isChecked;
			View v = (View)buttonView.getParent().getParent();
			if(isChecked){
				v.setBackgroundColor(Color.BLUE);

			} else{
				v.setBackgroundColor(colors[position % 2]);
			}
		}

	};


	public int getCount() {
		return objects.size();
	}

	public Object getItem(int position) {
		return objects.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	DatabaseGenericObject getObject(int position) {
		return ((DatabaseGenericObject) getItem(position));
	}

}