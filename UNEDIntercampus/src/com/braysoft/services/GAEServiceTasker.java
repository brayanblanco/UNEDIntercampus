package com.braysoft.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class GAEServiceTasker extends AsyncTask<URI, Integer, String> 
{

	@Override
	protected String doInBackground(URI... params) 
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(params[0]);
		httpPost.setHeader("content-type",
                "application/json");
		
		try 
		{
			/*
				Env??o de datos??
			
			 	JSONObject data = new JSONObject();
                data.put("URL", str);

                StringEntity entity = new StringEntity(data
                        .toString(), HTTP.UTF_8);
                entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(entity);
                        
			 */
            client.execute(httpPost);
			HttpResponse response = client.execute(httpPost);
			
			Reader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		    StringBuilder builder= new StringBuilder();
		    char[] buf = new char[1000];
		    int l = 0;
		    while (l >= 0) 
		    {
		        builder.append(buf, 0, l);
		        l = in.read(buf);
		    }

			return builder.toString();
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}
}
