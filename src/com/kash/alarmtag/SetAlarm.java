package com.kash.alarmtag;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class SetAlarm extends Activity {

	int AlarmIDNumber;
	EditText AlarmName_editText;
	String AlarmName;
	TimePicker timePicker;
	int Hour, Minute, AMPM;
	String file;
	Random randomGenerator = new Random();

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_alarm);

		AlarmIDNumber = getIntent().getExtras().getInt("AlarmIDNumber");
		if (AlarmIDNumber == 0) {
			AlarmIDNumber = randomGenerator.nextInt(1000) + 1; // Random between
																// 1 and 1001
		} else {
			// We will use the same AlarmIDNumber, since it will be a edit
			// request
		}

		AlarmName_editText = (EditText) findViewById(R.id.editText_alarmName);
		AlarmName = AlarmName_editText.getText().toString();
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);

		Hour = timePicker.getCurrentHour();
		Minute = timePicker.getCurrentMinute();

		Button addAlarm = (Button) findViewById(R.id.button_addAlarm);

		addAlarm.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {

				JSONObject AlarmIdentifier = new JSONObject();
				JSONObject AlarmDetails = new JSONObject();
				try {
					AlarmDetails.put("Name", AlarmName);
					AlarmDetails.put("Hour", Hour);
					AlarmDetails.put("Minute", Minute);
					AlarmIdentifier.put("AlarmIDNumber", AlarmIDNumber);
				} catch (Exception e) {
					// Something went wrong!
				}
				
				// try-with-resources statement based on post comment below :)
				File directory = getFilesDir ();
				file = "Alrams.txt";
				try  {
					String content = AlarmIdentifier.toString();
					FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
					fos.write(content.getBytes());
					fos.close();
				}catch(Exception e){
					Context context = getApplicationContext();
					CharSequence text = "Something is wrong!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				
		
				

				// Intent intent = new Intent(SetAlarm.this,
				// MainActivity.class);
				// startActivity(intent);

			}
		});

		
		
	
		
		
	}
}
