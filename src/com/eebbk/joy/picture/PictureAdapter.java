package com.eebbk.joy.picture;

import java.util.ArrayList;
import java.util.List;

import com.eebbk.joy.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureAdapter extends BaseAdapter {

	private List<PictureInfo> lists = new ArrayList<PictureInfo>();
	private LayoutInflater inflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions options;

	public PictureAdapter(Context context){
		inflater = LayoutInflater.from(context);
		
		for( int i=0 ; i<10 ; i++ ){
			lists.add(new PictureInfo());
		}
		
		mImageLoader = ImageLoader.getInstance();
		mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
				.build();

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
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder holder = null;
		if(null == convertView){
			
			holder = new Holder();
			convertView = inflater.inflate(R.layout.item_picture, parent , false);
			holder.title = (TextView) convertView.findViewById(R.id.picture_tv_title_show);
			holder.image = (ImageView) convertView.findViewById(R.id.picture_iv_content_show);
			holder.updateTime = (TextView) convertView.findViewById(R.id.picture_tv_time_show);
			
			convertView.setTag(holder);
			
		}else{
			holder = (Holder) convertView.getTag();
		}
		PictureInfo info = new PictureInfo();
		info = lists.get(position);
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




}
