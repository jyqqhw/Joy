package com.eebbk.joy.utils;

public class JoyConstant {
	/**
	 * @param  ���Ǿۺ�Ц�����ݵ�APP_KEY
	 */
	private static final String JUHE_JOKE_KEY = "679c82db488ed33b1e7722539f0c4362";
	
	private static final String JUHE_ROBOT_KEY = "86744c33bed567e59910826b65ab68a1";
	/**
	 * @param  �����ַ������
	 */
	public static final String JOKE_BASE_URL = "http://japi.juhe.cn/joke/content/text.from?key="+JUHE_JOKE_KEY+"&page=1&pagesize=10";
	public static final String PICTURE_BASE_URL = "http://japi.juhe.cn/joke/img/text.from?key="+JUHE_JOKE_KEY+"&page=1&pagesize=6";
	
	public static final String ROBOT_BASE_URL = "http://op.juhe.cn/robot/index?info=���&key="+JUHE_ROBOT_KEY;
	
	/**
	 * @param ˢ�ºͼ��صĳ���
	 */
	public static final int XLIST_STATUS_REFRESH = 0;
	public static final int XLIST_STATUS_LOADMORE = 1;
	
	/**
	 * @param  �ж���Ц������Ȥͼ�ĳ���
	 */
	public static final int JOKE_TYPE_JOKE = 0;
	public static final int JOKE_TYPE_PICTURE = 1;
	
}
