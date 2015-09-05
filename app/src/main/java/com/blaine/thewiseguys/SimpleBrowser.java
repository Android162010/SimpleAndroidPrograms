package com.blaine.thewiseguys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleBrowser extends Activity implements OnClickListener {
	Button go;
	Button back;
	Button forward;
	Button history;
	Button refresh;
	EditText url ;
	WebView ourBrow ;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		intialize();
		
	  ;
	}
	private void intialize() {
		// TODO Auto-generated method stub
		go = (Button) findViewById(R.id.bGo);
		back = (Button) findViewById(R.id.bBack);
		forward = (Button) findViewById(R.id.bForward);
		history = (Button)findViewById(R.id.bHistory);
		refresh = (Button)findViewById(R.id.bRefresh);
		url = (EditText)findViewById(R.id.etURL);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		
		ourBrow.getSettings().setJavaScriptEnabled(true);		
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		ourBrow.setWebViewClient(new ourViewClient());
		try{
		ourBrow.loadUrl("http://www.mybringback.com");
		}
		catch(Exception e){}
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		history.setOnClickListener(this);
		refresh.setOnClickListener(this);
		history.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bGo:
		String theWebsite = url.getText().toString();
		ourBrow.loadUrl(theWebsite);
		//Hides keyboard after loading site in go button
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(url.getWindowToken(), 0);	
		break ;
		
		case R.id.bForward:
			if(ourBrow.canGoForward())
			ourBrow.goForward();
			break ;
			
		case R.id.bBack:
			if(ourBrow.canGoBack())
			ourBrow.goBack();
			break ;
			
		case R.id.bHistory:
			ourBrow.clearHistory();
			break ;
			
		case R.id.bRefresh:
			ourBrow.reload();
			break ;
		
		}
		
	}

}
