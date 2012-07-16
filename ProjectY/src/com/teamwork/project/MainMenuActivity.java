package com.teamwork.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


public class MainMenuActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. 
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.e("!!!!!","Hello Sasha!");
    }

	public void onClick(View v) {
		if (v.getId() == R.id.buttonTraining) {
			Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
			startActivityForResult(intent, RESULT_OK);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}