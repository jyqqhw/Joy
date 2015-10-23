package com.eebbk.joy;

import java.util.ArrayList;
import java.util.List;

import com.eebbk.joy.joke.JokeFragment;
import com.eebbk.joy.news.NewsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


public class MainActivity extends FragmentActivity {
	
	private ViewPager mViewPager ;
	private ViewPagerAdapter mViewpagerAdapter ;
	private List<Fragment> lists = new ArrayList<Fragment>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        	
        
        mViewPager = (ViewPager) findViewById(R.id.id_pager);
		mViewpagerAdapter = new ViewPagerAdapter(this,getSupportFragmentManager());
		mViewPager.setAdapter(mViewpagerAdapter);
		mViewPager.setCurrentItem(0);
        
    }


    }
