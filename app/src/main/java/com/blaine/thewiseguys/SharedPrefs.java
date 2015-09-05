package com.blaine.thewiseguys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	public static String filename = "MySharedString";
	SharedPreferences someData ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setUpVars();
		someData = getSharedPreferences(filename, 0);
	}

	private void setUpVars() {
		// TODO Auto-generated method stub
		Button load = (Button) findViewById(R.id.bLoad);
		Button save = (Button) findViewById(R.id.bSave);

		load.setOnClickListener(this);

		save.setOnClickListener(this);

		sharedData = (EditText) findViewById(R.id.etPrefData);
		dataResults = (TextView) findViewById(R.id.tvPrefData);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bLoad:
			
			someData = getSharedPreferences(filename, 0);
			String dataReturned = someData.getString("sharedString", "Couldn't load data");
			dataResults.setText(dataReturned);
			break;
			
			
		
		
		
		
		case R.id.bSave:
			String stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("sharedString", stringData);
			editor.commit();
			break; 
			
		}

	}

}
