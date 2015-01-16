package com.myBlaire.cache;

public class CacheUtil {

	private static boolean FLUSH=false;//是否刷新缓存

	public static boolean isFLUSH() {
		return FLUSH;
	}

	public static void setFLUSH(boolean flush) {
		FLUSH = flush;
	}
	
}