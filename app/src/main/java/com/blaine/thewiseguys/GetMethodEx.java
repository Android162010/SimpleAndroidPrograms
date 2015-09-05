package com.blaine.thewiseguys;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by GizeleM on 8/16/2015.
 * This is a class used to get information from the internet via the Http client set up in the code. Refer to new boston tutroial 147 for more info.

 */
public class GetMethodEx {


    public String getInternetData () throws Exception {

        //This is setup to read some of the information that were getting
        BufferedReader in = null;
        String data = null;


        //Allows the user to acess information that is not internal or in the application. So we can rely on the website instead of trying to handle all data in app.
        //This is an example using Http get
        try{
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://www.mybringback.com");
            HttpGet request = new HttpGet();
            request.setURI(website);

            //This is the most important method to make everything happen, it calls execute method on client
            // Takes getMethod called request
            HttpResponse response = client.execute(request);

            //This gets the response or reads it
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");

            while((l = in.readLine()) != null){
                sb.append(l + nl);
            }
            in.close();
            data = sb.toString();
            return data;

        }
       finally {
            if (in != null){
                try {in.close();
                    return data;
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }


}
