package com.eebbk.joy.robot;

import java.util.ArrayList;
import java.util.List;

import com.eebbk.joy.R;
import com.eebbk.joy.dao.RobotDao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RobotAdapter extends BaseAdapter {
	
	private List<RobotInfo> lists = new ArrayList<RobotInfo>();
	private LayoutInflater mInflater;
	private RobotDao mRobotDao;
	
	public RobotAdapter(Context context){
		
		mRobotDao = new RobotDao(context);
		mInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		if(null == lists || lists.size() <= 0){
			return null;
		}
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(null == convertView){
			
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_robot, parent, false);
			
			holder.leftText = (TextView) convertView.findViewById(R.id.robot_tv_content_left);
			holder.leftLayout = (RelativeLayout) convertView.findViewById(R.id.robot_rl_message_left);
			
			holder.rightText = (TextView) convertView.findViewById(R.id.robot_tv_content_right);
			holder.rightLayout = (RelativeLayout) convertView.findViewById(R.id.robot_rl_message_right);
			
			convertView.setTag(holder);
			
		}else{
			holder = (Holder) convertView.getTag();
		}
		
		RobotInfo info = lists.get(position);
		
		//��Ϣ�����ҿ�
		if(info.isMine){
			holder.leftLayout.setVisibility(View.GONE);
			
			holder.rightLayout.setVisibility(View.VISIBLE);
			holder.rightText.setText(info.text);
		}else{
			holder.rightLayout.setVisibility(View.GONE);
			
			holder.leftLayout.setVisibility(View.VISIBLE);
			holder.leftText.setText(info.text);
		}
		
		holder.leftText.setText(info.text);
		
		return convertView;
	}
	
	
	class Holder{
		
		RelativeLayout rightLayout;
		
		TextView rightText;
		
		RelativeLayout leftLayout;
		
		TextView leftText;
		
		
	}
	
	/**
	 * ����µ���Ϣ����Ϣ�б���
	 * 
	 * @param text
	 * @param isMine
	 */
	public void add(RobotInfo info){
		if(null == info){
			return;
		}
		mRobotDao.add(info);
		lists.add(info);
		
		notifyDataSetChanged();
		
	}
	
	/**
	 * �������б������óɵ�ǰ�б�
	 * 
	 * @param list
	 */
	public void getRobotFromDb(){
		if(null == lists){
			lists = new ArrayList<RobotInfo>();
		}
		lists = mRobotDao.getList();
		notifyDataSetChanged();
		
	}
	
	

}
