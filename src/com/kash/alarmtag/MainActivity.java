package com.kash.alarmtag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		String jsonReturn = "{'alarmList':["
				+ "{'name':'alarm1'},"
				+ "{'name':'alarm2'}]"
				+ "}";
		
		JSONObject mainObject;
		JSONArray cast = null;
		String[] values = null;
		
		try {
			mainObject = new JSONObject(jsonReturn);
			cast = mainObject.getJSONArray("alarmList");
			values=new String[cast.length()];
			for (int i=0; i<cast.length(); i++) {
			    JSONObject alarm = cast.getJSONObject(i);
			    String name = alarm.getString("name");
			    values[i] = name;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		

			
	
		

		
		
       
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
	    
	    
        // Assign adapter to ListView
        listView.setAdapter(adapter); 
	        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	 
            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
              
             // ListView Clicked item index
             int itemPosition  = position;
             
             // ListView Clicked item value
             String  itemValue    = (String) listView.getItemAtPosition(position);
                
              // Show Alert 
              Toast.makeText(getApplicationContext(),
                "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                .show();
           
            }

       }); 
  
	
		Button addAlarm = (Button) findViewById(R.id.button_addAlarm);
		// When the login button is clicked this will be trigered
		addAlarm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SetAlarm.class);
				startActivity(intent);
			}
		});

	}

}
