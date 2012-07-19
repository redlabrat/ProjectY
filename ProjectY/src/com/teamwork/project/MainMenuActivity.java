package com.teamwork.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainMenuActivity extends Activity implements OnClickListener {
	private Button buttonStartTraining;
	/** Called when the activity is first created.      **/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		buttonStartTraining = (Button) findViewById(R.id.buttonTraining);
		buttonStartTraining.setOnClickListener(this);
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