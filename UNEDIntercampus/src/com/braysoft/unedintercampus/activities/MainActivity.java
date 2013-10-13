package com.braysoft.unedintercampus.activities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.braysoft.unedintercampus.R;
import com.braysoft.services.GAEServiceTasker;
import com.braysoft.unedintercampus.entities.Video;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		this.cargarLista();
			}

	
	private void cargarLista()
	{
		try 
		{
			GAEServiceTasker tasker = new GAEServiceTasker();
			String res = tasker.execute(new URI("http://unedintercampus.appspot.com/ws.get_videos")).get();
			
			//COSAS QUE SE ESTAN PROBANDO
			ListView lstVideos = (ListView)findViewById(R.id.listaVideos);	
			List<Video> list = Video.generateList(res);

		    
		    AdaptadorVideos adapter = new AdaptadorVideos(this, list);
		    lstVideos.setAdapter(adapter);
		    
		    lstVideos.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					
					Video video = ((AdaptadorVideos)parent.getAdapter()).getItem(position);
					
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(video.getUri());
					startActivity(i);
				}
			});
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (URISyntaxException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

class AdaptadorVideos extends ArrayAdapter<Video> 
{
	Activity _context;
	List<Video> _datos;
	
	AdaptadorVideos(Activity context,List<Video> datos) 
	{
		super(context, android.R.layout.two_line_list_item, datos);
		this._context = context;
		this._datos = datos;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View item = convertView;
		if (item == null) 
		{
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			item = inflater.inflate(android.R.layout.two_line_list_item, null);
		}

		TextView lblTitle = (TextView)item.findViewById(android.R.id.text1);
		TextView lblSubTitle = (TextView)item.findViewById(android.R.id.text2);
		
		lblTitle.setText(_datos.get(position).getSubject().getName());
		lblSubTitle.setText(String.format("Tema %s Parte %s",_datos.get(position).getTopic(),_datos.get(position).getPart()));

		return(item);
	}
}