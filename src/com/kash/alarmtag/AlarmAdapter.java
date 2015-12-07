package com.kash.alarmtag;


import java.util.List;

import com.kash.alarmtag.R;
import com.kash.alarmtag.models.Alarm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlarmAdapter extends ArrayAdapter{
    private LayoutInflater inflater;
    private List<Alarm> alarms_array;
    private Alarm temp_alarm;
    public AlarmAdapter(Activity activity, List<Alarm> alarms){
    	super(activity, R.layout.alarm_row, alarms);
    	alarms_array=alarms;
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    	if (convertView == null) {
            convertView = inflater.inflate(R.layout.alarm_row, null);

            //set title accordingly
            if (position == 0 ) {
                //insert some text here
            }

        }
    	temp_alarm = alarms_array.get(position);

        TextView alarm_name = (TextView) convertView.findViewById(R.id.name);
        alarm_name.setText(temp_alarm.getAlarmName());

        TextView alarm_time = (TextView) convertView.findViewById(R.id.time);
        alarm_time.setText(temp_alarm.getAlarmTimeHour() + ":" + temp_alarm.getAlarmTimeMin() + "am");
        
        ImageView alarm_set = (ImageView) convertView.findViewById(R.id.alarm_set);
		alarm_set.setOnClickListener(new OnClickListener(){
		    public void onClick(View view) {
		    	ImageView imageView = (ImageView) view;			    	  
		    	Integer integer = (Integer) imageView.getTag();
		    	   integer = integer == null ? 0 : integer;
		    	   switch(integer) {
		    	    case R.drawable.alarm_off:
		    	     imageView.setImageResource(R.drawable.alarm_on);
		    	     imageView.setTag(R.drawable.alarm_on);
		    	     break;
		    	    case R.drawable.alarm_on:
		    	    	imageView.setImageResource(R.drawable.nfc);
			    	    imageView.setTag(R.drawable.nfc);
			    	    break;
		    	    case R.drawable.nfc:
		    	    	imageView.setImageResource(R.drawable.alarm_off);
			    	    imageView.setTag(R.drawable.alarm_off);
			    	    break;
		    	    default:
		    	     imageView.setImageResource(R.drawable.alarm_off);
		    	     imageView.setTag(R.drawable.alarm_off);
		    	     break;
		    	  }
		    	  
		    }
		});
        
        
        return convertView;
    }

}