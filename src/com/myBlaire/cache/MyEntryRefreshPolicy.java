package com.myBlaire.cache;

import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.EntryRefreshPolicy;

public class MyEntryRefreshPolicy implements EntryRefreshPolicy {

	public boolean needsRefresh(CacheEntry entry) {
		// TODO Auto-generated method stub
		if(CacheUtil.isFLUSH()){//判断是否需要刷新缓存
			CacheUtil.setFLUSH(false);//刷新缓存后把需要刷新状态设为false
			return true;
		}
		return false;
	}

}