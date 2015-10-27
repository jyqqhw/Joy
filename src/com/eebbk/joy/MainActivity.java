package com.eebbk.joy;

import java.util.ArrayList;
import java.util.List;

import com.eebbk.joy.TabIndicator.OnIndicatorItemClickListener;
import com.eebbk.joy.joke.JokeFragment;
import com.eebbk.joy.news.NewsFragment;

import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends FragmentActivity implements OnPageChangeListener{

	private ViewPager mViewPager ;
	private ViewPagerAdapter mViewpagerAdapter ;
	private List<Fragment> lists = new ArrayList<Fragment>();
	private TabIndicator mTabIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		getWindow().setBackgroundDrawable(null);
		mTabIndicator = (TabIndicator) findViewById(R.id.ll_indicator);
		mViewPager = (ViewPager) findViewById(R.id.id_pager);
		mViewpagerAdapter = new ViewPagerAdapter(this,getSupportFragmentManager());
		mViewPager.setAdapter(mViewpagerAdapter);
		mViewPager.setCurrentItem(0);
		
		
		mViewPager.setOnPageChangeListener(this);
		mTabIndicator.setOnIndicatorItemClickListener(new OnIndicatorItemClickListener() {
			
			@Override
			public void onClick(View view, int position) {
				mViewPager.setCurrentItem(position);
				
			}
		});
		
	}

	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		mTabIndicator.setItemSelected(arg0);

	}


}
