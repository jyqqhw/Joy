package com.eebbk.joy.joke;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eebbk.joy.R;
import com.eebbk.joy.dao.JokeDao;
import com.eebbk.joy.utils.JoyConstant;
import com.eebbk.joy.utils.L;

public class JokeAdapter extends BaseAdapter{

	private List<JokeInfo> mLists = new ArrayList<JokeInfo>();
	
	private LayoutInflater inflater;
	private JokeInfo mJokeInfo;
	private JokeDao mJokeDao;


	public JokeAdapter(Context context) {

		inflater = LayoutInflater.from(context);
		L.i("bbb","youBUG");
			mJokeDao = new JokeDao(context);
			List<JokeInfo> infos = mJokeDao.getJokeList();
			if(null != infos && infos.size() > 0 ){
				mLists.addAll(infos);
			}
			
			
			for(JokeInfo info:mLists){
				L.i("bbb",info.content);
			}
			
		L.i("bbb","youBUG1");
		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder = null;
		if(convertView == null){
			holder = new Holder();
			convertView = inflater.inflate(R.layout.item_joke, null);
			holder.content = (TextView) convertView.findViewById(R.id.tv_joke_content_show);
			//				holder.gifimage = (GifView) convertView.findViewById(R.id.iv_fun_picture_show);
//			holder.image = (ImageView) convertView.findViewById(R.id.iv_fun_picture_show);
			holder.updatetime = (TextView) convertView.findViewById(R.id.tv_joke_time_show);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		
		mJokeInfo = mLists.get(position);
		holder.content.setText("\t\t"+mJokeInfo.content);
		holder.updatetime.setText(mJokeInfo.updateTime);

		return convertView;

	}

	class Holder{
		TextView content;
		TextView updatetime;
//		ImageView image;
		//		GifView gifimage;
	}
	
	public boolean addList(List<JokeInfo>  lists){
		if(mLists == null){
			return false;
		}
		boolean added = mLists.addAll(lists);
		notifyDataSetChanged();
		return added;
	}
	
	public boolean clear(){
		if(mLists.isEmpty() || mLists == null){
			return false;
		}
		mLists.clear();
		notifyDataSetChanged();
		return true;
		
	}
	
	public void setList(List<JokeInfo> lists){
		if(null == lists || lists.isEmpty()){
			return;
		}
		
		 mLists = lists;
		 
		 notifyDataSetChanged();
		 
		 mJokeDao.deleteAll(JoyConstant.JOKE_TYPE_JOKE);
		 mJokeDao.addAllJoke(lists);
	}
	
	



}
