package com.teamwork.project.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.teamwork.project.ProjectYApplication;
import com.teamwork.project.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class TestActivity extends Activity {
    
//	private SQLiteDatabase database;
	private DatabaseHelper dataSource;
	private ListView listView;
	
	@SuppressWarnings("rawtypes")
	private List objects;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_test);
        
        dataSource = ProjectYApplication.getDataSource();
//        
//        for(int i=0; i<5; i++){
//        	WorkoutExercise wex1 = new WorkoutExercise(1,1);
//			wex1.setPlanned("5x100@5x110@6x110");
//			wex1.setExecuted("5x100@5x110@4x110@4x115");
//			wex1.storeToDB();
//			
//			new DiaryEntry(wex1.getId(), 1).storeToDB(); 
//        }
//        
        
        fillObjects();
        setUpList();
        
    }

	
	@SuppressWarnings("unchecked")
	private void setUpList() {
		// Use standart ArrayAdapter for listView
		listView = (ListView) findViewById(R.id.DBTestActivityMainList);
		
		listView.setAdapter(new ArrayAdapter<PersistentObject>(this, android.R.layout.simple_list_item_1, objects));
		
		// Make Some Toast
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position,long id) {
				Integer idDiaryday = ((DiaryDay) objects.get(position)).getId();
				@SuppressWarnings("rawtypes")
				List list = dataSource.queryDiaryExercisesForRelatedDiaryDayId(idDiaryday);
				String str = list.toString();
				Toast.makeText(getApplicationContext(), str,Toast.LENGTH_LONG).show();
			}
		});
	}

	// retrive data from DB
	private void fillObjects() {
		Calendar start = Calendar.getInstance();
		start.set(2012, 6, 1);
		
		Calendar end = Calendar.getInstance();
//		end.set(2012, 6, 12);
		
		objects = dataSource.queryDiaryDaysForTimeInterval(start, end);

		
	}
}