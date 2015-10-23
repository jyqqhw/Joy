package com.eebbk.joy.picture;

import java.util.List;

public interface PictureListener {
	
	void OnRefreshSuccess(List<PictureInfo> infos);
	void OnLoadMoreSuccess(List<PictureInfo> infos);
	
}
