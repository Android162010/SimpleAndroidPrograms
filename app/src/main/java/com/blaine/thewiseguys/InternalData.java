package com.blaine.thewiseguys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalData extends Activity implements OnClickListener {
	EditText sharedData;
	TextView dataResults;
	FileOutputStream fos;
	String FILENAME = "InternalString";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setUpVars();

	}

	private void setUpVars() {
		// TODO Auto-generated method stub
		Button load = (Button) findViewById(R.id.bLoad);
		Button save = (Button) findViewById(R.id.bSave);

		load.setOnClickListener(this);

		save.setOnClickListener(this);

		sharedData = (EditText) findViewById(R.id.etPrefData);
		dataResults = (TextView) findViewById(R.id.tvPrefData);

		// Creates file output stream
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bLoad:
			new loadSomeStuff().execute(FILENAME);

			break;

		case R.id.bSave:
			String data = sharedData.getText().toString();
			// How to set up a file using a string name and send to file output
			// class
			/*
			 * Saving data via file File f = new File(FILENAME); try { fos = new
			 * FileOutputStream(f); //Write some data fos.close(); } catch
			 * (FileNotFoundException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			try {
				fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

				fos.write(data.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

	}

	// async class method
	//This is not a method its another class
	public class loadSomeStuff extends AsyncTask<String, Integer, String> {
		ProgressDialog dialog;

		protected void onPreExecute() {
			// example of setting up something
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();

		}

		@Override
		// async class method
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String collected = null;
			FileInputStream fis = null;

			for (int i = 0; i < 20; i++) {

				publishProgress(5);

				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis = openFileInput(FILENAME);
				// set array to however many bytes in input stream
				// -1 means we read everything from file input stream
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1) {
					collected = new String(dataArray);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		// method used for async class
		// takes i array as parameter
		//THe async class allows you to run laoding method on another thread
		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(progress[0]);

		}

		// async class mehtod
		protected void onPostExecute(String result) {
			dataResults.setText(result);
		}

	}
}
