package com.kash.alarmtag.models;

public class Alarm {
	private int ID;
	private String alarm_name;
	private int alarm_time_hour;
	private int alarm_time_min;
	private int nfc_flag;
	private String sound_path;
	private Boolean repeat;
	private int status;

	//Get Set for alarm names
		public void setAlarmID(int ID){
			this.ID = ID;
		}
		public int getAlarmID(){
			return this.ID;
		}
	
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
	public void setAlarmNFCFlag(int nfc_flag){
		this.nfc_flag = nfc_flag;
	}
	public int getAlarmNFCFlag(){
		return this.nfc_flag;
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
