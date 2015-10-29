package com.eebbk.joy.utils;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.util.Log;

public class BaseAPPManager extends Application {
	
	private boolean isInitImageLoaderInstance;
	
	public void initImageLoader(){
		if(!isInitImageLoaderInstance || !ImageLoader.getInstance().isInited()){
			ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder( getApplicationContext() )
				.threadPriority( Thread.NORM_PRIORITY - 2 )
				// ¥≈≈Ãª∫¥Ê20£Õ
				.discCacheSize( 20 * 1024 * 1024 )
				.discCacheFileNameGenerator( new Md5FileNameGenerator() )
				.tasksProcessingOrder( QueueProcessingType.LIFO )
				.memoryCacheSize( 6 * 1024 * 1024 )
				.denyCacheImageMultipleSizesInMemory()
				.build();
			ImageLoader.getInstance().init( configuration );
//			ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
			isInitImageLoaderInstance = true;
			
			Log.i("aaa", "imageloader≥ı ºªØ¡À");
		}
		
	}
	
	
	public void destroyImageLoader(){
		if(ImageLoader.getInstance().isInited()){
			ImageLoader.getInstance().destroy();
			isInitImageLoaderInstance = false;
			
		}
		
	}
	
}
