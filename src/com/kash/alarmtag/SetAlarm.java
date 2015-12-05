package com.kash.alarmtag;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class SetAlarm extends Activity {

	int AlarmIDNumber;
	private EditText AlarmName_editText;
	private String AlarmName;
	private TimePicker timePicker;
	private int Hour, Minute;
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
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);

		Button addAlarm = (Button) findViewById(R.id.button_addAlarm);
		addAlarm.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {

				AlarmName = AlarmName_editText.getText().toString();
				Hour = timePicker.getCurrentHour();
				Minute = timePicker.getCurrentMinute();

				JSONObject AlarmDetails = new JSONObject();
				try {
					AlarmDetails.put("ID", AlarmIDNumber);
					AlarmDetails.put("alarm_name", AlarmName);
					AlarmDetails.put("alarm_time_hour", Hour);
					AlarmDetails.put("alarm_time_min", Minute);
					AlarmDetails.put("nfc_flag", "NA");
					AlarmDetails.put("sound_path", "NA");
					AlarmDetails.put("repeat", "NA");
					AlarmDetails.put("status", "NA");
					

				} catch (Exception e) {
					Context context = getApplicationContext();
					CharSequence text = "Could not put information into JaSon format!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}

				// try-with-resources statement based on post comment below :)
				// File directory = getFilesDir();
				file = "alarm_config.txt";
				try {
					String content = AlarmDetails.toString();
					FileOutputStream fos = openFileOutput(file,
							Context.MODE_APPEND);
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					osw.write( content + ",\n");
					osw.flush();
					osw.close();
					fos.close();

					Context context = getApplicationContext();
					CharSequence text = "Your alarm has been added.";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				} catch (Exception e) {
					Context context = getApplicationContext();
					CharSequence text = "Can not wtite to the file!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}

				Intent intent = new Intent(SetAlarm.this, MainActivity.class);
				startActivity(intent);

			}
		});

	}
}
