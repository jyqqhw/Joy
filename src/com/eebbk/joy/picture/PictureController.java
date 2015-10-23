package com.eebbk.joy.picture;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eebbk.joy.utils.JoyConstant;
import com.eebbk.joy.utils.JoyNet;
import com.eebbk.joy.utils.L;

import android.content.Context;
import android.os.AsyncTask;

public class PictureController {

	private PictureListener picListener;
	private int mRequestPager = 1;

	public PictureController(Context context,PictureListener pictureListener){

		this.picListener = pictureListener;

	}

	public void doRefreshOrLoad(int flag){

		switch (flag) {
		case JoyConstant.XLIST_STATUS_REFRESH:
			new MyAsyncTask().execute(mRequestPager = 1);

			break;

		case JoyConstant.XLIST_STATUS_LOADMORE:
			new MyAsyncTask().execute(++mRequestPager);
			break;

		default:
			break;
		}
	}


	class MyAsyncTask extends AsyncTask<Integer, Void, String>{

		@Override
		protected String doInBackground(Integer... params) {
			String url = JoyNet.combineCertainPageUrl(JoyConstant.PICTURE_BASE_URL, params[0]);
			return JoyNet.requestJsonFromUrl(url);

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			List<PictureInfo> infos = parseJsonToJokeInfoList(result);
			if(1 == mRequestPager){
				picListener.OnRefreshSuccess(infos);
			}else{
				picListener.OnLoadMoreSuccess(infos);
			}
		}

	}


	//将json数据解析成趣图信息
	public  List<PictureInfo> parseJsonToJokeInfoList(String json){
		List<PictureInfo> infos = new ArrayList<PictureInfo>();

		try {
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.getInt("error_code") == 0){
				JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
				int jsonLength = jsonArray.length();
				L.i("jsonarray的长度:"+jsonLength);
				for(int i=0;i<jsonLength;i++){
					PictureInfo info = new PictureInfo();
					JSONObject object = jsonArray.getJSONObject(i);
					info.title = object.getString("content");
					info.updateTime =  object.getString("updatetime");
					info.imageUrl = object.getString("url");

					infos.add(info);
					info = null;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infos;
	}




}
