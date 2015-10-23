package com.eebbk.joy.picture;

import com.eebbk.joy.R;

import android.widget.ImageView;

public class PictureInfo {
	
	public String title;
	
	public ImageView image;
	
	public String imageUrl;
	
	public String updateTime;
	
	public PictureInfo(){
		title = "你不会找到路,除非你敢于迷路";
		image = null;
		updateTime = "2015-10-23";
		imageUrl = String.valueOf(R.drawable.ic_launcher);
	}
	
	
}
