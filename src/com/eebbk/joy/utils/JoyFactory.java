package com.eebbk.joy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class JoyFactory {

	private JoyFactory(){

	}

	public static Bitmap fromUrlToBitmap(String imagePath){
		InputStream is = null;
		Bitmap bitmap = null;
		try {
			URL url = new URL(imagePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {

				is = conn.getInputStream();
				BitmapFactory.Options opts = new Options();
				opts.inJustDecodeBounds= true;
				opts.inSampleSize = 2;
				opts.inJustDecodeBounds = false;
				bitmap  =  BitmapFactory.decodeStream(is, null, opts);
				Log.i("aaa", "ok,��������");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bitmap;
	}


	public static InputStream fromBitmapToInputstream(Bitmap bitmap){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}


	public static List<Map<String, Object>> parseJson(String json){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.getInt("error_code") == 0){
				JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
				int jLength = jsonArray.length();
				for(int i=0;i<jLength;i++){
					Map<String, Object> map = new HashMap<String, Object>();
					JSONObject object = jsonArray.getJSONObject(i);
					map.put("funContent", object.getString("content"));
					map.put("funTime", object.getString("updatetime"));
					map.put("url", object.getString("url"));
					//					Bitmap b = fromUrlToBitmap(object.getString("url"));
					//					map.put("url", b);
					Log.i("aaa", object.getString("url"));
					list.add(map);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		mAdapter = new SimpleAdapter(JokeColdDetails.this, list, R.layout.item_joke,
		//				new String[]{"coldContent","coldTime"}, new int[]{R.id.tv_cold_content_show,
		//			R.id.tv_cold_time_show});
		//		mAdapter.notifyDataSetChanged();
		return list;
	}



}
