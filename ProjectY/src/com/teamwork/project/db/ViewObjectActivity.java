package com.teamwork.project.db;

import com.teamwork.project.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class ViewObjectActivity extends Activity {

	final int DLG_DATA1  = 1;
//	final int DLG_DATA2  = 2;
//	final int DLG_DATA3  = 3;
//	final int DLG_DATA4  = 4;

	DatabaseGenericObject item;
	TextView vData1;
//	TextView vData2;
//	TextView vData3;
//	TextView vData4;
	Button btnBack;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_activity_view_object);

		Intent intent = getIntent();
		item = (DatabaseGenericObject) intent.getParcelableExtra(DatabaseGenericObject.class.getCanonicalName());

		vData1 = (EditText) findViewById(R.id.DBActivityViewObject_ObjectData1);
		vData1.setText(item.data1);

//		vData2 = (EditText) findViewById(R.id.DBActivityViewObject_ObjectData1);
//		vData2.setText(item.data1);
//		vData2.setOnClickListener(this);
//
//		
//		vData3 = (EditText) findViewById(R.id.DBActivityViewObject_ObjectData1);
//		vData3.setText(item.data1);
//		vData3.setOnClickListener(this);
//
//		
//		vData4 = (EditText) findViewById(R.id.DBActivityViewObject_ObjectData1);
//		vData4.setText(item.data1);
//		vData4.setOnClickListener(this);

		
		CheckBox cbChk = (CheckBox) findViewById(R.id.DBActivityViewObject_ObjectCheck);
		cbChk.setChecked(item.isChecked);
		cbChk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				item.isChecked = isChecked;
			}
		});


		btnBack = (Button) findViewById(R.id.DBActivityViewObject_BtnBack);
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), TestActivity.class);
				intent.putExtra(DatabaseGenericObject.class.getCanonicalName(), item);
				setResult(100, intent);
				finish();
			}
		});
	}


}