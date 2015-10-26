package com.eebbk.joy;

import com.eebbk.joy.utils.L;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabIndicator extends LinearLayout {
	
	
	private Context context;
	
	private OnIndicatorItemClickListener mListener;
	private String[] mTitle = {"新闻","笑话","趣图","智能小七"};
	
	private boolean isFirstIn = true;
	
	public TabIndicator(Context context) {
		this(context,null);
		
	}
	
	public TabIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.context = context;
		initView();
	}
	
	/**
	 * 点击事件的监听
	 * @author jyqqhw
	 *
	 */
	public interface OnIndicatorItemClickListener{
		
		void onClick(View view,int position);
		
	}
	
	
	public void setOnIndicatorItemClickListener(OnIndicatorItemClickListener listener){
		mListener = listener;
	}
	
	private void initView(){
		
		for(int i = 0;i<4;i++){
			initSingleIndicator(i);
		}
		
	}
	
	private void initSingleIndicator(final int index){
		
		Button tv = new Button(context);
//		TextView tv = new TextView(context);
		tv.setId(0xdffeeee+index*0x4fdc);
		tv.setText(mTitle[index]);
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(22.0f);
		if(0 == index && isFirstIn){
			tv.setBackgroundResource(R.drawable.main_indicator_btn_title_pressed);
			isFirstIn = false;
		}else{
			tv.setBackgroundResource(R.drawable.main_indicator_btn_title_nornal);
		}
		
		tv.setGravity(Gravity.CENTER);
		LayoutParams params = new LayoutParams(80, 60,1);
		
		tv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if(null != mListener){
					mListener.onClick(v,index);
				}
				L.i("bbb","wo被点击了");
				
				setItemSelected(index);
				
				return false;
			}
		});
		
		addView(tv, params);
	}
	
	/**
	 * 指示所选位置
	 * 
	 * @param position
	 */
	public void setItemSelected(int position){
		
		for(int i = 0;i<4;i++){
			if(i == position){
				findViewById(0xdffeeee+position*0x4fdc).setBackgroundResource(R.drawable.main_indicator_btn_title_pressed);
				continue;
			}
			findViewById(0xdffeeee+i*0x4fdc).setBackgroundResource(R.drawable.main_indicator_btn_title_nornal);
		}
		
	}
	
	

}
