package com.blaine.thewiseguys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

//This flips thorugh different view could be used for flashcard app

public class Flipper extends Activity implements OnClickListener {
	ViewFlipper flippy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
	 flippy =(ViewFlipper) findViewById(R.id.viewFlipper1);
		flippy.setOnClickListener(this);
		flippy.setFlipInterval(500);
		flippy.startFlipping();
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		flippy.showNext();
		
	}



}
