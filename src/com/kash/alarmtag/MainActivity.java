package com.kash.alarmtag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listView;

	// Global variables

	// AlarmIDNumber is used to identify different alarms, it will be used to
	// modify alarms
	int AlarmIDNumber = 0;

	//Make some simple file I/O handling functions
	private void writeToFile(String data,String filename) { //Write data to a file
	    try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (IOException e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
	

	private String readFromFile(String filename) { //Read data from a file
	    String ret = "";
	    try {
	        InputStream inputStream = openFileInput(filename);
	
	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();
	
	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }
	
	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }
	
	    return ret;
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Determine if the config file exists
		File file = new File("alarm_config.txt");
		if(!file.exists()){//If the file does not exists we initialize an empty set of alarms
			String init_config = "[]";
			writeToFile(init_config,"alarm_config.txt");
		}
		
		//Now let's read the file string
		String alarm_list = readFromFile("alarm_config.txt");
				
		//DEBUG AREA: Making a default set of alarms =============
		alarm_list = "[{" + 
					"\"alarm_name\":\"Call Gerald\"," +
					"\"alarm_time_hour\":\"15\"," +
					"\"alarm_time_min\" : \"30\"," +
					"\"nfc_id\":\"FFC:234:dsf:322\"," +
					"\"sound_path\": \"dfgsdg\"," +
					"\"repeat\": \"True\"," +
					"\"status\":\"4\"}," +
					
					"{\"alarm_name\":\"Call Argus\"," +
					"\"alarm_time_hour\":\"15\"," +
					"\"alarm_time_min\" : \"30\"," +
					"\"nfc_id\":\"FFC:234:dsf:322\"," +
					"\"sound_path\": \"dfgsdg\"," +
					"\"repeat\": \"True\"," +
					"\"status\":\"4\"}" +

		   		"]";
		
		//Example alarm json data:
		//[  
		//   {  //ALARM FOR 3:30pm
		//      "alarm_name":"Call Gerald",
		//      "alarm_time_hour":"15",
		//		"alarm_time_min" : "30",
		//      "nfc_id":"FFC:234:dsf:322",
		//		"sound_path": "somepath\/to/\sound.mp3",
		//		"repeat": "True",
		//      "status":"4"
		//   },
		//   {  
		//      "name":"Devil Hacker",
		//      "img":"pictures\/smallest\/belitapic.jpg",
		//      "username":"@dvHack",
		//      "user_id":"1"
		//   }
		//]
		
		
		
		String[] alarm_name_array = null;
		
		try {
			JSONArray alarm_json = new JSONArray(alarm_list);
			alarm_name_array = new String[alarm_json.length()];
			for (int i=0;i<alarm_json.length();i++){
				JSONObject alarm_object = (JSONObject)alarm_json.getJSONObject(i);
				alarm_name_array[i] = alarm_object.getString("alarm_name");
			}
		  
		}catch(Exception e)
		{
		        e.printStackTrace();
		}
		
		

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, alarm_name_array);

		listView = (ListView) findViewById(R.id.list);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition = position;

				// ListView Clicked item value
				String itemValue = (String) listView
						.getItemAtPosition(position);

				// Show Alert
				Toast.makeText(
						getApplicationContext(),
						"Position :" + itemPosition + "  ListItem : "
								+ itemValue, Toast.LENGTH_LONG).show();

			}

		});

		Button addAlarm = (Button) findViewById(R.id.button_addAlarm);
		// When the login button is clicked this will be trigered
		addAlarm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Calling the SetAlarm activity
				// We send the alarm id number, if we want to modify an alarm
				// it is initially set to 0, this is if we want to set a new alarm
				Intent intent = new Intent(MainActivity.this, SetAlarm.class);
				intent.putExtra("AlarmIDNumber", AlarmIDNumber);
				startActivity(intent);
			}
		});

	}

}
