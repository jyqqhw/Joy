package com.eebbk.joy.picture;

import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;

import com.eebbk.giflibrary.GifImageView;
import com.eebbk.joy.R;
import com.eebbk.joy.base.BaseFragment;
import com.eebbk.joy.utils.JoyConstant;
import com.eebbk.joy.utils.L;
import com.eebbk.joy.utils.T;

public class PictureFragment extends BaseFragment implements IXListViewRefreshListener, IXListViewLoadMore {

	private XListView mXListView;

	private boolean isFirstIn = true;
	private PictureAdapter mPicAdapter;
	private PictureController mPicController;
	private boolean isVisible = false;
	private Context context;
	
	
	private PictureListener mPicListener = new PictureListener() {
		
		@Override
		public void OnRefreshSuccess(List<PictureInfo> infos) {
			
			if(!infos.isEmpty() ||  null != infos){
				mPicAdapter.clear();
				mPicAdapter.set(infos);
			}
			mXListView.stopRefresh();
			
		}
		
		@Override
		public void OnLoadMoreSuccess(List<PictureInfo> infos) {
			
			if(!infos.isEmpty() || null != infos ){
				mPicAdapter.add(infos);
			}
			mXListView.stopLoadMore();
			
		}
	};
	


	public PictureFragment(Context context) {
		
		this.context = context;
	}


//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		// TODO Auto-generated method stub
//		super.setUserVisibleHint(isVisibleToUser);
//		isVisible = isVisibleToUser;
//		if(isVisible){
//			if(isFirstIn){
//				mXListView.startRefresh();
//				isFirstIn = false;
//			}else{
//				mXListView.NotRefreshAtBegin();
//			}
//
//		}
//
//	}


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
		mXListView = (XListView) getView().findViewById(R.id.xlv_picture_main_show);
		mPicAdapter  = new PictureAdapter(context);
		mXListView.setAdapter(mPicAdapter);

		mXListView.setPullRefreshEnable(this);
		mXListView.setPullLoadEnable(this);
		mXListView.setRefreshTime("2015-10-23");

		if(isFirstIn){
			mXListView.startRefresh();
			isFirstIn = false;
		}else{
			mXListView.NotRefreshAtBegin();
		}

	}




	@Override
	public void onLoadMore() {
		if(isNetOn){
			mPicController.doRefreshOrLoad(JoyConstant.XLIST_STATUS_LOADMORE,isNetOn);
		}else{
			T.shortTime(context,"加载更多失败,请连接网络!");
			mXListView.stopLoadMore();
		}
		

	}

	@Override
	public void onRefresh() {
		if(isNetOn){
			mPicController.doRefreshOrLoad(JoyConstant.XLIST_STATUS_REFRESH,isNetOn);		
		}else{
			mXListView.stopRefresh();
		}
		
	}
	
	
	@Override
	protected void onNetStateChanged(boolean isNetOn) {
		// TODO Auto-generated method stub
		super.onNetStateChanged(isNetOn);
		
//		mPicController.setNetState(isNetOn);
	}
	
	
}
