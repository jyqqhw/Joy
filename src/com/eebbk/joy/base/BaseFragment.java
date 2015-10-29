package com.eebbk.joy.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	
	protected boolean isNetOn;
	
	
	
	public void setNetState(boolean isNetOn){
		onNetStateChanged(isNetOn);
	}
	
	protected void onNetStateChanged(boolean isNetOn){
		this.isNetOn = isNetOn;
	}

}
