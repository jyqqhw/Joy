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
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ViewPagerAdapter extends FragmentPagerAdapter{
	
	private List<Fragment> lists = new ArrayList<Fragment>();
	
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
		NewsFragment news = new NewsFragment();
		JokeFragment joke = new JokeFragment(context);
		PictureFragment picture = new PictureFragment(context);
		RobotFragment robot = new RobotFragment();
		lists.add(news);
		lists.add(joke);
		lists.add(picture);
		lists.add(robot);
		
	}
	
	
	

}
