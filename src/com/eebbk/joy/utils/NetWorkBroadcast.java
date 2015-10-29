package com.eebbk.joy.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkBroadcast {
	
	private Context mContext;
	private NetStateListener mNetListener;
	private boolean isRegistered = false;
	private ConnectivityManager mConnectivityManager;
	private boolean isNetConnected;
	private int netState = 0;
	
	private BroadcastReceiver mNetBReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			onReceiveIntent(context);
			
		}
	};
	
	
	public NetWorkBroadcast(Context context,NetStateListener netListener){
		mContext = context;
		mNetListener = netListener;
		registerNetworkBroadcast(context);
		
		
	}
	
	
	private void registerNetworkBroadcast(Context context){
		
		if(null != mNetBReceiver && null != context && !isRegistered){
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
			context.registerReceiver(mNetBReceiver, intentFilter);
			isRegistered = true;
		}
		
		
	}
	
	
	public void unregisterNetworkBroadcast(){
		if(null != mContext && null != mNetBReceiver && isRegistered){
			mContext.unregisterReceiver(mNetBReceiver);
			isRegistered = false;
		}
		
	}
	
	
	
	public void onReceiveIntent(Context context){
		
			
//			NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			
			if(null == networkInfo){
				netState = 0;
				isNetConnected = false;
			}else{
				
				int type = networkInfo.getType();
				if(type == ConnectivityManager.TYPE_WIFI){
					netState = 1;
					isNetConnected = true;
				}else if(type == ConnectivityManager.TYPE_MOBILE){
					netState = 2;
					isNetConnected = true;
					
				}else{
					netState = 0;
					isNetConnected = false;
				}
				
				
			}
			
			mNetListener.onBroadcast(isNetConnected);
			
		
		
	}
	
	
	public interface NetStateListener{
		void onBroadcast(boolean isNetConnected);
	}
	
	
}
