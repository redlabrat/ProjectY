package com.teamwork.project.db;

import java.util.ArrayList;

import com.teamwork.project.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class TestActivity extends Activity {
    
//	private SQLiteDatabase database;
	private DatabaseHelper helper;
	private ListView listView;
	private ArrayList<WorkoutMacroCycle> objects;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_test);
        
        helper = new DatabaseHelper(this);
        
        fillObjects();
        setUpList();
        
    }

	private void setUpList() {
		// Use standart ArrayAdapter for listView
		listView = (ListView) findViewById(R.id.DBTestActivityMainList);
		
		
		listView.setAdapter(new ArrayAdapter<WorkoutMacroCycle>(this,
						android.R.layout.simple_list_item_1, objects));
		
		// Make Some Toast
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position,long id) {
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_SHORT).show();				
			}
		});
	}

	// retrive data from DB
	private void fillObjects() {
		objects = (ArrayList<WorkoutMacroCycle>) helper.getWorkoutMacroCycleDao().queryForAll();
	}
}