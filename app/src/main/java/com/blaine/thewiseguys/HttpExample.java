package com.blaine.thewiseguys;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by GizeleM on 8/16/2015.
 * Can get JSON information from websites like twitter. Let website do work you just need to pull the data
 */
public class HttpExample extends Activity {
    TextView httpStuff;
    HttpClient client;
    final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//This is giving me an error because the min sdk version is not high enough
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();
    }

    public JSONObject lastTweet(String username)throws ClientProtocolException, IOException, JSONException
    {
   StringBuilder url = new StringBuilder(URL);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        //Returns a number if it returns 200 sucess, 100 is information, 400 is client errror
        int status = r.getStatusLine().getStatusCode();
        //Creates some data returning the string of our entity
        if(status == 200){
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray timeline = new JSONArray(data);
            //Returns a Json Object from the array set up above
            JSONObject last = timeline.getJSONObject(0);
            return last;
        }

        else {
        Toast.makeText(HttpExample.this, "error", Toast.LENGTH_SHORT);
        return null;
        }
    }



}
