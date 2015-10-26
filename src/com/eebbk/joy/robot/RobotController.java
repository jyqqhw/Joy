package com.eebbk.joy.robot;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.eebbk.joy.utils.JoyConstant;
import com.eebbk.joy.utils.JoyNet;
import com.eebbk.joy.utils.L;

public class RobotController {

	private RobotListener mListener;

	public RobotController(Context context,RobotListener mListener){
		this.mListener = mListener;
	}


	public void beginAnswerQuestion(String question){
		if(TextUtils.isEmpty(question)){
			return;
		}
		try {
			new RobotAsyncTask().execute(question);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}



	class RobotAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String url = JoyNet.combineRobotMessageUrl(JoyConstant.ROBOT_BASE_URL, params[0]);
			return JoyNet.requestJsonFromUrl(url);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			RobotInfo info = parseJsonToRobotAnswer(result);
				mListener.OnGetMessageSuccess(info);

		}


	}



	//将json数据解析成机器人回复信息
	public  RobotInfo parseJsonToRobotAnswer(String json){
		RobotInfo info = null;
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.getInt("error_code") == 0){
				JSONObject object = jsonObject.getJSONObject("result");
				info = new RobotInfo();
				info.code = object.getString("code");
				info.text =  object.getString("text");
				info.isMine = true;
				L.i("bbb","我获得了robot数据:"+info.code +"\t"+info.text+"\t"+info.isMine);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;

	}




}
