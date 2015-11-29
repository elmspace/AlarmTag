package com.kash.alarmtag;


import com.kash.alarmtag.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AlarmAdapter extends ArrayAdapter{
    private LayoutInflater inflater;

    public AlarmAdapter(Activity activity, String[] items){
    	super(activity, R.layout.alarm_row, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.alarm_row, parent, false);
    }

}