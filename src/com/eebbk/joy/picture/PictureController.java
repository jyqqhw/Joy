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

import com.eebbk.giflibrary.GifImageView;
import com.eebbk.joy.R;
import com.eebbk.joy.base.BaseController;
import com.eebbk.joy.utils.JoyConstant;
import com.eebbk.joy.utils.JoyNet;
import com.eebbk.joy.utils.L;

import android.content.Context;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;

public class PictureController extends BaseController{

	private PictureListener picListener;
	private int mRequestPager = 1;
	private View mView;
	private OnPopWindowShowGif gifListener;


	public PictureController(Context context,PictureListener pictureListener){

		this.picListener = pictureListener;

	}


	public PictureController(OnPopWindowShowGif gifListener) {
		this.gifListener = gifListener;
	}

	public void doRefreshOrLoad(int flag,boolean isNetOn){
		if(!isNetOn){
			return;
		}
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
			String url = JoyNet.combineJokePageUrl(JoyConstant.PICTURE_BASE_URL, params[0]);
			return JoyNet.requestJsonFromUrl(url);

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			List<PictureInfo> infos = parseJsonToPicInfoList(result);
			if(1 == mRequestPager){
				picListener.OnRefreshSuccess(infos);
			}else{
				picListener.OnLoadMoreSuccess(infos);
			}
		}

	}

	class GifTask extends AsyncTask<String, Integer, byte[]>{

		@Override
		protected byte[] doInBackground(String... params) {

			L.i("bbb","我在AsyncTask得到了url,它的值为："+params[0]);
			byte[] bytes = JoyNet.fromUrlToBytes(params[0]);

			return bytes;
		}

		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if(null != result){
				gifListener.showGif(result);
			}

		}

	}


	//将json数据解析成趣图信息
	public  List<PictureInfo> parseJsonToPicInfoList(String json){
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
	
	
	@Override
	protected void onNetStateChanged(boolean isNetOn) {
		// TODO Auto-generated method stub
		super.onNetStateChanged(isNetOn);
	}

	public void startGetPicture(View view,String url){

		new GifTask().execute(url);

	}

	public interface OnPopWindowShowGif{
		void showGif(byte[] bytes);
	}




}
