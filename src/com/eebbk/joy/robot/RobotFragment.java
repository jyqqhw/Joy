package com.eebbk.joy.robot;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.eebbk.joy.R;
import com.eebbk.joy.base.BaseFragment;
import com.eebbk.joy.utils.L;
import com.eebbk.joy.utils.T;

public class RobotFragment extends BaseFragment{
	
	private ListView mListView;
	private EditText mEditInput;
	private Button mBtnSubmit;
	private Context context;
	
	private RobotController mRobotController;
	private RobotAdapter mRobotAdapter;
	
	public RobotFragment(Context context) {
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_robot_main, container,false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		initView();
		initRobotController();
		
	}

	private void initView() {
		mListView = (ListView) getView().findViewById(R.id.robot_lv_content_show);
		mRobotAdapter = new RobotAdapter(context);
		mListView.setAdapter(mRobotAdapter);
		mEditInput = (EditText) getView().findViewById(R.id.robot_et_word_edit);
		mBtnSubmit = (Button) getView().findViewById(R.id.robot_btn_submit);
		mBtnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = mEditInput.getText().toString().trim();
				if(TextUtils.isEmpty(text)){
					T.shortTime(context, "消息不能为空哦!");
					return;
				}
				mEditInput.setText("");
				mRobotController.beginAnswerQuestion(text);
				RobotInfo info = new RobotInfo();
				info.isMine = true;
				info.text = text;
				mRobotAdapter.add(info);
			}
		});
		
		
		
	}
	
	
	public void initRobotController(){
		mRobotController = new RobotController(context,mRobotListener);
	}
	
	//将机器人的消息加入机器人消息列表中
	//adapter负责消息刷新
	private RobotListener mRobotListener = new RobotListener() {
		
		@Override
		public void OnGetMessageSuccess(RobotInfo info) {
			L.i("bbb","fragment准备更新界面");
			mRobotAdapter.add(info);
		}
	};

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mRobotAdapter.getRobotFromDb();
	}
	
	
	
	
	
	
	
}
