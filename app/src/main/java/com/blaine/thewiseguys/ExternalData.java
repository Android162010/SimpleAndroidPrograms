package com.blaine.thewiseguys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener,
		OnClickListener {
	private TextView canRead, canWrite;
	private String state;
	boolean canW, canR;
	Spinner spinner;
	String[] paths = { "Music", "Pictures", "Downloads" };
	File path = null;
	File file = null;
	EditText saveFile;
	Button confirm, save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canWrite.setText("false");
		canRead.setText("false");
		confirm = (Button) findViewById(R.id.bConfirmSaveAs);
		save = (Button) findViewById(R.id.bSaveFile);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		checkState();
		state = Environment.getExternalStorageState();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

	}

	private void checkState() {
		// TODO Auto-generated method stub
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// this means you can read and write
			canWrite.setText("true");
			canRead.setText("true");
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// read but cannot write
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;
		}
		// means you cant do either
		else {
			canWrite.setText("false");
			canRead.setText("false");
			canW = canR = false;
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int postion = spinner.getSelectedItemPosition();
		switch (postion) {
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;

		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;

		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;

		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		// Need file to tell yoy which folder anjd which specific task
		case R.id.bSaveFile:
			String f = saveFile.getText().toString();
			//Must add png or do it manually when naming file to open
			file = new File(path, f + ".png");

			checkState();
			if (canW == canR == true) {
				
				path.mkdirs();

				try {

					InputStream is = getResources().openRawResource(
							R.drawable.ball);

					OutputStream os = new FileOutputStream(file);
					
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
					t.show();
					
					//Updates files for the user to use
					MediaScannerConnection.scanFile(ExternalData.this, 
							new String[]{file.toString()},
					null, 
					new MediaScannerConnection.OnScanCompletedListener() {
						
						public void onScanCompleted(String path, Uri uri) {
							// TODO Auto-generated method stub
							Toast t = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_SHORT);
							
						}
					});
							
							
							
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			break;

		case R.id.bConfirmSaveAs:
			save.setVisibility(View.VISIBLE);
			break;

		}

	}

}
