package com.kash.alarmtag.models;

public class Alarm {
	private String alarm_name;
	private int alarm_time_hour;
	private int alarm_time_min;
	private String nfc_name;
	private String nfc_id; //May change this
	private String sound_path;
	private Boolean repeat;
	private int status;

	//Get Set for alarm names
	public void setAlarmName(String name){
		this.alarm_name = name;
	}
	public String getAlarmName(){
		return this.alarm_name;
	}
	
	//Get Set for alarm time
	public void setAlarmTime(int hour,int min){
		this.alarm_time_hour = hour;
		this.alarm_time_min = min;
	}
	public int getAlarmTimeHour(){
		return this.alarm_time_hour;
	}
	public int getAlarmTimeMin(){
		return this.alarm_time_min;
	}
	
	//Get Set for the NFC info
	public void setAlarmNFC(String nfc_name,String nfc_id){
		this.nfc_name = nfc_name;
		this.nfc_id = nfc_id;
	}
	public String getAlarmNFCName(){
		return this.nfc_name;
	}
	public String getAlarmNFCID(){
		return this.nfc_id;
	}
	
	//Get Set for the alarm properties
	public void setAlarmProperties(String sound_path,Boolean repeat,int status){
		this.sound_path = sound_path;
		this.repeat = repeat;
		this.status = status;

	}
	public String getAlarmSoundPath(){
		return this.sound_path;
	}
	public Boolean getAlarmRepeat(){
		return this.repeat;
	}
	public int getAlarmStatus(){
		return this.status;
	}

	
}
