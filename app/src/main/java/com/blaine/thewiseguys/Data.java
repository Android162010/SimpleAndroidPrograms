package com.blaine.thewiseguys;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {
	TextView tv;
	Button safrB;
	Button saB;
	EditText et;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		creatingMethod();
		
		
	}
	
	
	
	private void creatingMethod() {
		// TODO Auto-generated method stub
		tv = (TextView) findViewById(R.id.tvGot);
		safrB = (Button) findViewById(R.id.bSAFR);
		saB = (Button) findViewById(R.id.bSA);
		et = (EditText)findViewById(R.id.etSend);
		safrB.setOnClickListener(this);

		saB.setOnClickListener(this);
		
		
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bSA:
			String bread= et.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("key", bread);
			Intent a = new Intent(Data.this, OpenedClass.class);
			a.putExtras(basket);
			startActivity(a);
			
			break;
		case R.id.bSAFR:
			Intent i = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(i, 0);

			break;
		}
			

		}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == RESULT_OK){
		Bundle basket = data.getExtras();
		String s = basket.getString("answer");
		tv.setText(s);
	}
	}
	
		
}
