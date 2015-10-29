package com.eebbk.joy.base;

public class BaseController {
	
	protected boolean isNetOn;
	
	
	public void setNetState(boolean isNetOn){
		onNetStateChanged(isNetOn);
	}
	
	protected void onNetStateChanged(boolean isNetOn){
		this.isNetOn = isNetOn;
	}
	
}
