package com.eebbk.joy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class JoyNet {
	
	private JoyNet(){
	
	}
	
	/**
	 * 
	 * @param url
	 * @return   json����
	 *
	 * @ ����������������
	 */
	public static String requestJsonFromUrl(String url){
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		String jsonResult = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == 200){
				HttpEntity entity = response.getEntity();
				jsonResult = EntityUtils.toString(entity,"utf-8");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	
	/**
	 * Ц��ģ���url��װ��,��������ָ��ҳ������
	 * 
	 * @param url �����ַ
	 * @param page ����ҳ��
	 * @return ������url��ַ
	 */
//	private  String mJokeUrl = "http://japi.juhe.cn/joke/content/text.from?key="+
//			JoyConstant.JOKE_KEY+"&page="+mRequestPager+"&pagesize=10";
	public static String combineCertainPageUrl(String url,int page){
		Pattern pattern = Pattern.compile("(page=[1-9]{1,4})");
		String[] strs = pattern.split(url);
		String requestUrl = strs[0]+"page="+page+strs[1];
		L.i("���������ҳ����Ϊ:"+requestUrl);
		return requestUrl;
	}
	
	
}
