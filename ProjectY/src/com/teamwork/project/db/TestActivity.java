package com.teamwork.project.db;

import java.util.ArrayList;

import com.teamwork.project.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TestActivity extends Activity{
	private String LOG_TAG = TestActivity.class.getName() + " ";
	
	ListView lvMain;
	public ArrayList<DatabaseGenericObject> objects;
	AdapterObjects adapter;

	boolean isDataMade = false;
	YDataSource datasource;
	
	
	/** Called when the activity is first created. */
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.db_activity_test);
	        Log.e(LOG_TAG, "db_activity_test: Started");
	        
			lvMain = (ListView) findViewById(R.id.DbTestActivityListObjects);

			objects=new ArrayList<DatabaseGenericObject>();

			datasource = new YDataSource(this);
			datasource.open();

			datasource.getAllExercises(objects);
			adapter = new AdapterObjects(this);

			lvMain.setAdapter(adapter);


			lvMain.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "+ id);
				}
			});
	        
	    }
	 

		
		public long addObjectToDB(View view){
			DatabaseGenericObject obj = new DatabaseGenericObject();
			long id = datasource.storeExercise(obj);
			obj.id = id;
			objects.add(obj);
			adapter.notifyDataSetChanged();
			return id;
		}




		public void deleteObjectFromDB(View v) {
			for (int i=0; i<objects.size(); i++) {
				DatabaseGenericObject obj = objects.get(i);
				if (obj.isChecked){
					datasource.deleteExercise(obj);
					objects.remove(i);
					i--;
				}
			}
			adapter.notifyDataSetChanged();
			Toast.makeText(this, "Deleting obect", Toast.LENGTH_LONG).show();
		}

		public void updateDB(View v) {
			Toast.makeText(this, "updateDB", Toast.LENGTH_LONG).show();
		}


		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
			super.onActivityResult(requestCode, resultCode, intent);

			DatabaseGenericObject obj = (DatabaseGenericObject) intent.getParcelableExtra(DatabaseGenericObject.class.getCanonicalName());
			for(int i=0; i<objects.size(); i++)
				if(objects.get(i).id==obj.id){
					objects.set(i, obj);
					datasource.updateExercise(obj);
					break;
				}
			adapter.notifyDataSetChanged();
		}

	 
	 

}