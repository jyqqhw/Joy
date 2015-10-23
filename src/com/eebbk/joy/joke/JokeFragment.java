package com.eebbk.joy.joke;

import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eebbk.joy.R;
import com.eebbk.joy.base.BaseFragment;
import com.eebbk.joy.utils.JoyConstant;

public class JokeFragment extends BaseFragment implements IXListViewLoadMore, IXListViewRefreshListener{

	private XListView mXListView = null;
	private JokeAdapter mJokeAdapter;

	private JokeController mJokeController;
	private Context context;
	private boolean isFirstIn = true;
	private boolean isVisible =false;

	public JokeFragment(Context context) {
		this.context = context;

		mJokeAdapter = new JokeAdapter(context);

	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		isVisible = isVisibleToUser;
		if(isVisible){
			if(isFirstIn){
				mXListView.startRefresh();
				isFirstIn = false;
			}else{
				mXListView.NotRefreshAtBegin();
			}
			
		}
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_joke_main, container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initJokeController();
		initXListView();


	}

	private void initJokeController() {
		mJokeController = new JokeController(context, mJokeListener);

	}

	public void initXListView(){
		mXListView = (XListView) getView().findViewById(R.id.xlv_joke_main_show);

		mXListView.setAdapter(mJokeAdapter);
		mXListView.setPullRefreshEnable(this);
		mXListView.setPullLoadEnable(this);
		mXListView.setRefreshTime("2015-10-23");
		

	}

	@Override
	public void onRefresh() {
		mJokeController.doRefreshOrLoad(JoyConstant.XLIST_STATUS_REFRESH);
	}

	@Override
	public void onLoadMore() {
		mJokeController.doRefreshOrLoad(JoyConstant.XLIST_STATUS_LOADMORE);
	}

	private JokeListener mJokeListener = new JokeListener() {

		@Override
		public void OnRefreshSuccess(List<JokeInfo> lists) {
			// TODO Auto-generated method stub
			if(lists != null && lists.size()>0){
				mJokeAdapter.setList(lists);
			}
			
			mXListView.stopRefresh();
			
			
		}

		@Override
		public void OnRefreshFailed() {


		}

		@Override
		public void OnLoadMorefailed() {
			// TODO Auto-generated method stub

		}

		@Override
		public void OnLoadMoreSuccess(List<JokeInfo> lists) {
			if(lists != null && lists.size()>0){
				mJokeAdapter.addList(lists);
			}
			mXListView.stopLoadMore();
			
		}

	};




}
