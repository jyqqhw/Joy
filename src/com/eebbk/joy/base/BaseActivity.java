package com.eebbk.joy.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.eebbk.joy.utils.BaseAPPManager;
import com.eebbk.joy.utils.NetWorkBroadcast;
import com.eebbk.joy.utils.NetWorkBroadcast.NetStateListener;

public class BaseActivity extends FragmentActivity {
	
	protected boolean isNetOn;
	protected boolean isRegistered;
	private int netType;
	
	
	private NetWorkBroadcast mNetBroadcast;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		mNetBroadcast = new NetWorkBroadcast(this, mNetListener);
		( (BaseAPPManager) getApplication()).initImageLoader();
		
	}





	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mNetBroadcast.unregisterNetworkBroadcast();
	}
	
	
	
	private NetStateListener mNetListener = new NetStateListener() {
		
		@Override
		public void onBroadcast(boolean isNetConnected) {
			onNetStateChanged(isNetConnected);
			
		}
	};
	
	
	
	
	
	public void onNetStateChanged(boolean isNetOn){
		this.isNetOn = isNetOn;
	}
	
	
	
}
