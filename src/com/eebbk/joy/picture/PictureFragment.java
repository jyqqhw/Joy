package com.eebbk.joy.picture;

import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eebbk.joy.R;
import com.eebbk.joy.base.BaseFragment;
import com.eebbk.joy.utils.JoyConstant;

public class PictureFragment extends BaseFragment implements IXListViewRefreshListener, IXListViewLoadMore {
	
	private XListView mXlistView;
	
	private Context context;
	private boolean isFirstIn = true;
	private PictureAdapter mPicAdapter;
	private PictureController mPicController;
	private boolean isVisible = false;
	
	private PictureListener mPicListener = new PictureListener() {
		
		@Override
		public void OnRefreshSuccess(List<PictureInfo> infos) {
			if(!infos.isEmpty() ||  null != infos){
				mPicAdapter.clear();
				mPicAdapter.set(infos);
			}
			mXlistView.stopRefresh();
			
		}
		
		@Override
		public void OnLoadMoreSuccess(List<PictureInfo> infos) {
			if(!infos.isEmpty() || null != infos ){
				mPicAdapter.add(infos);
			}
				mXlistView.stopLoadMore();
		}
	};
	
	public PictureFragment(Context context) {
		this.context = context;
	}
	
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		isVisible = isVisibleToUser;
		if(isVisible){
			if(isFirstIn){
				mXlistView.startRefresh();
				isFirstIn = false;
			}else{
				mXlistView.NotRefreshAtBegin();
			}
			
		}
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_picture_main, container,false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		
		initPicController(context);
		initXListView();
		
	}
	
	void initPicController(Context context){
		mPicController = new PictureController(context,mPicListener);
	}
	
	
	void initXListView(){
		mXlistView = (XListView) getView().findViewById(R.id.xlv_picture_main_show);
		mPicAdapter  = new PictureAdapter(context);
		mXlistView.setAdapter(mPicAdapter);
		
		mXlistView.setPullRefreshEnable(this);
		mXlistView.setPullLoadEnable(this);
		mXlistView.setRefreshTime("2015-10-23");
		
		if(isFirstIn){
			mXlistView.startRefresh();
			isFirstIn = false;
		}else{
			mXlistView.NotRefreshAtBegin();
		}
		
	}
	
	
	

	@Override
	public void onLoadMore() {
		mPicController.doRefreshOrLoad(JoyConstant.XLIST_STATUS_LOADMORE);
		
	}

	@Override
	public void onRefresh() {
		mPicController.doRefreshOrLoad(JoyConstant.XLIST_STATUS_REFRESH);		
	}
	
	
	
	
}
