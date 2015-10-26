package com.eebbk.joy.picture;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.eebbk.giflibrary.GifImageView;
import com.eebbk.joy.R;
import com.eebbk.joy.picture.PictureController.OnPopWindowShowGif;
import com.eebbk.joy.utils.L;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class PictureAdapter extends BaseAdapter {

	private List<PictureInfo> lists = new ArrayList<PictureInfo>();
	private LayoutInflater inflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions options;
	private PictureController mController;
	private GifImageView mGifView;
	private View mView;
	private Context context;
	
	
	
	private OnImageClickListener mImageListener = new OnImageClickListener() {
		
		@Override
		public void OnImageClick(View view, int position) {
			String url = lists.get(position).imageUrl;
			mView = view;
			mController.startGetPicture(view, url);
			
		}
	};

	public PictureAdapter(Context context){
		this.context = context;
		inflater = LayoutInflater.from(context);

		for( int i=0 ; i<10 ; i++ ){
			lists.add(new PictureInfo());
		}
		
		mController = new PictureController(mGifListener);
		
		mImageLoader = ImageLoader.getInstance();
		mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
//		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.ic_launcher)
//				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory()
//				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
//				.build();
		
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory()
		.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565)
		.displayer(new FadeInBitmapDisplayer(300)).build();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		PictureInfo info = lists.get(position);

		Holder holder = null;
		if(null == convertView){

			holder = new Holder();
			convertView = inflater.inflate(R.layout.item_picture, parent , false);
			
			holder.title = (TextView) convertView.findViewById(R.id.picture_tv_title_show);
			holder.image = (ImageView) convertView.findViewById(R.id.picture_iv_content_show);
			holder.updateTime = (TextView) convertView.findViewById(R.id.picture_tv_time_show);
			holder.image.setTag(info.imageUrl);

			convertView.setTag(holder);

		}else{
			holder = (Holder) convertView.getTag();
		}
		
		holder.image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				mImageListener.OnImageClick(v,position);
				
			}
		});

		holder.title.setText("\t"+info.title);
		holder.updateTime.setText(info.updateTime);
		if(null != info.imageUrl){
			mImageLoader.displayImage(info.imageUrl, holder.image, options);
		}


		return convertView;
	}


	class Holder{

		TextView title;

		ImageView image;

		TextView updateTime;

	}
	
	
	private OnPopWindowShowGif mGifListener = new OnPopWindowShowGif() {
		
		@Override
		public void showGif(byte[] bytes) {
			showPopupWindow(bytes);
		}
	};


	void set(List<PictureInfo> infos){

		lists = infos;
		notifyDataSetChanged();

	}


	void add(List<PictureInfo> infos){
		if(lists == null){
			return;
		}
		lists.addAll(infos);
		notifyDataSetChanged();

	}

	void clear(){
		if(lists.isEmpty() || lists == null){
			return;
		}
		lists.clear();
		notifyDataSetChanged();
	}

	

	private interface OnImageClickListener{
		void OnImageClick(View view,int position);
	}
	
	/**
	 * 
	 * 弹窗显示gif
	 * 
	 */
	private void showPopupWindow(byte[] bytes){
		L.i("bbb","进入了showpop方法1");
		View contentView = inflater.inflate(R.layout.fragment_picture_popup, null);
		L.i("bbb","进入了showpop方法2");
	    
		mGifView = (GifImageView) contentView.findViewById(R.id.fragment_picture_pop_gif);
		
		mGifView.setBytes(bytes);
		mGifView.startAnimation();
		
		L.i("bbb","进入了showpop方法3");
		
		mGifView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if( mGifView.isAnimating() ){
					 mGifView.stopAnimation();
				}else{
					mGifView.startAnimation();
				}
				
			}
		});
		
		PopupWindow pop = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT, true);
		L.i("bbb","进入了showpop方法4");
		pop.setTouchable(true);
		pop.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		//这一句代码是个bug,必须要加，不加popup无法取消
		pop.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.picture_popup_bg));
		
		pop.showAsDropDown(mView, 0, - mView.getHeight() -25);
		
		pop.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				mGifView.clearAnimation();
				
			}
		});

	}
	
	
	


}
