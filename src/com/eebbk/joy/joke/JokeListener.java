package com.eebbk.joy.joke;

import java.util.List;

public interface JokeListener {
	
	void OnRefreshSuccess(List<JokeInfo> lists);
	void OnLoadMoreSuccess(List<JokeInfo> lists);
	void OnRefreshFailed();
	void OnLoadMorefailed();
}
