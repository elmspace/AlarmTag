package com.kash.alarmtag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SetAlarm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_alarm);
		
		
		
		Button addAlarm = (Button) findViewById(R.id.button_addAlarm);
		// When the login button is clicked this will be trigered
		addAlarm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetAlarm.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		
	}


}
