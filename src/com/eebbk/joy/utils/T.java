package com.eebbk.joy.utils;

import android.content.Context;
import android.widget.Toast;

public class T {
	
	public static void shortTime(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static void longTime(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	
}
