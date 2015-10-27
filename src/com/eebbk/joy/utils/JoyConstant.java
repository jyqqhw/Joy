package com.eebbk.joy.utils;

public class JoyConstant {
	/**
	 * @param  这是聚合笑话数据的APP_KEY
	 */
	private static final String JUHE_JOKE_KEY = "679c82db488ed33b1e7722539f0c4362";
	
	private static final String JUHE_ROBOT_KEY = "86744c33bed567e59910826b65ab68a1";
	/**
	 * @param  请求地址及参数
	 */
	public static final String JOKE_BASE_URL = "http://japi.juhe.cn/joke/content/text.from?key="+JUHE_JOKE_KEY+"&page=1&pagesize=10";
	public static final String PICTURE_BASE_URL = "http://japi.juhe.cn/joke/img/text.from?key="+JUHE_JOKE_KEY+"&page=1&pagesize=6";
	
	public static final String ROBOT_BASE_URL = "http://op.juhe.cn/robot/index?info=你好&key="+JUHE_ROBOT_KEY;
	
	/**
	 * @param 刷新和加载的常量
	 */
	public static final int XLIST_STATUS_REFRESH = 0;
	public static final int XLIST_STATUS_LOADMORE = 1;
	
	/**
	 * @param  判断是笑话还是趣图的常量
	 */
	public static final int JOKE_TYPE_JOKE = 0;
	public static final int JOKE_TYPE_PICTURE = 1;
	
}
