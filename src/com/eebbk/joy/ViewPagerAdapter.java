package com.eebbk.joy;

import java.util.ArrayList;
import java.util.List;

import com.eebbk.joy.joke.JokeFragment;
import com.eebbk.joy.news.NewsFragment;
import com.eebbk.joy.picture.PictureFragment;
import com.eebbk.joy.robot.RobotFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ViewPagerAdapter extends FragmentPagerAdapter{
	
	private List<Fragment> lists = new ArrayList<Fragment>();
	private NewsFragment news;
	private JokeFragment joke;
	private PictureFragment picture;
	private RobotFragment robot;
	
	
	public ViewPagerAdapter(Context context,FragmentManager fm) {
		super(fm);
		initLists(context);
		
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
//		JokeFragment jokeFragment = new JokeFragment(context);
		return lists.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}
	
	void initLists(Context context){
		news = new NewsFragment();
		joke = new JokeFragment(context);
		picture = new PictureFragment(context);
		robot = new RobotFragment(context);
		lists.add(news);
		lists.add(joke);
		lists.add(picture);
		lists.add(robot);
		
	}
	
	
	public void notifyNetChanged(boolean isNetOn){
		
		Log.i("aaa", "vpAdapter在发消息了0"+isNetOn);
		
		if(null != news){
			news.setNetState(isNetOn);
		}
		Log.i("aaa", "vpAdapter在发消息了1"+isNetOn);
		if(null != joke){
			Log.i("aaa", "joke不为空啊"+joke.toString());
			joke.setNetState(isNetOn);
		}
		Log.i("aaa", "vpAdapter在发消息了2"+isNetOn);
		if(null != picture){
			picture.setNetState(isNetOn);
		}
		Log.i("aaa", "vpAdapter在发消息了3"+isNetOn);
		if(null != robot){
			robot.setNetState(isNetOn);
		}
		
		Log.i("aaa", "vpAdapter在发消息了4"+isNetOn);
		
	}
	
	
	

}
