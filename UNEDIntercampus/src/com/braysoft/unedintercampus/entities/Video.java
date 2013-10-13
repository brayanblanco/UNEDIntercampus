package com.braysoft.unedintercampus.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.Uri;

public class Video implements Comparable<Video>
{
	private Subject _subject;
	private int _topic;
	private int _part;
	private Uri _uri;
	
	public Video(JSONObject JSONVideo)
	{
		try 
		{
			String scode = JSONVideo.getString("subject_code");
			String sname = JSONVideo.getString("subject_name");
			
			
			_subject = new Subject(scode, sname);
			_topic = JSONVideo.getInt("video_topic");
			_part = JSONVideo.getInt("video_part");
			_uri = Uri.parse(JSONVideo.getString("video_url"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Video()
	{
		
	}

	public Video(Subject subject, int topic, int part, Uri uri)
	{
		
	}

	public Subject getSubject() {
		return _subject;
	}

	public void setSubject(Subject subject) {
		_subject = subject;
	}

	public int getTopic() {
		return _topic;
	}

	public void setTopic(int topic) {
		_topic = topic;
	}

	public int getPart() {
		return _part;
	}

	public void setPart(int part) {
		_part = part;
	}

	public Uri getUri() {
		return _uri;
	}

	public void setUri(Uri uri) {
		_uri = uri;
	}
	
	public static List<Video> generateList(String response)
	{
		try
		{
			JSONObject jsonResponse = new JSONObject(new JSONTokener(response));
		    JSONArray jsonArray = new JSONArray( new JSONTokener(jsonResponse.get("videos").toString()));
		    
		    List<Video> list = new ArrayList<Video>();
		    
		    for(int i=0;i<jsonArray.length();i++)
		    	list.add(new Video(jsonArray.getJSONObject(i)));
		    
	    
	    
		    Collections.sort(list);
		    return list;
		}
		catch(JSONException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int compareTo(Video video) 
	{
		int retValue;
		
		if(this._subject.getCode().compareTo(video._subject.getCode()) == 0)
		{
			if(Integer.valueOf(this._topic).compareTo(video._topic)==0)
			{
				retValue = Integer.valueOf(this._part).compareTo(video._part);
			}
			else retValue = Integer.valueOf(this._topic).compareTo(video._topic);
		}
		else retValue = this._subject.getCode().compareTo(video._subject.getCode());
		
		return retValue;
	}
	
	
}
