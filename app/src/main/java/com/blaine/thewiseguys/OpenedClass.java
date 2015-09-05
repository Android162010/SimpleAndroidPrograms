package com.blaine.thewiseguys;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener,
		OnCheckedChangeListener {

	TextView sendTv;
	TextView sendTv2, test;
	Button bReturn;
	RadioGroup selectionList;
	String gotBread;
	String setData ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		creatingMethod();
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		String et = getData.getString("name", "Blaine is");
		String values = getData.getString("list", "4");
		
		if(values.contentEquals("1")){
			sendTv.setText(et);
		}
		
	//	Bundle gotBasket = getIntent().getExtras();
//		gotBread = gotBasket.getString("key");
//		sendTv.setText(gotBread);
		

	}

	private void creatingMethod() {
		// TODO Auto-generated method stub
		sendTv = (TextView) findViewById(R.id.tvQuestion);
		sendTv2 = (TextView) findViewById(R.id.test);
		bReturn = (Button) findViewById(R.id.returnB);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		selectionList.setOnCheckedChangeListener(this);
		bReturn.setOnClickListener(this);
		

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", setData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub

		switch (arg1) {
		case R.id.rbCrazy:
			setData ="Probably Right";
			break;
		case R.id.rbSesy:
			setData ="Wrong spellin";
			break;

		case R.id.rbBoth:
			setData ="Hell yea";
			break;

		}
   sendTv2.setText(setData);
	}

}
