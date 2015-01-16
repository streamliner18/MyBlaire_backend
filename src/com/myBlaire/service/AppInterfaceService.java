package com.myBlaire.service;

import java.util.List;
import java.util.Map;

import com.myBlaire.domain.TFeedback;
import com.myBlaire.domain.TGoods;
import com.myBlaire.domain.TUser;

public interface AppInterfaceService {

	/**
	 * 获取关键字列表
	 * @return
	 */
	public List<Map<String,String>> getKeyWordList()throws Exception;
	
	/**
	 * 最具人气
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getHotGoods(TUser loginUser)throws Exception;
	
	
	/**
	 * 明星街拍
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getStreetShootingGoods()throws Exception;
	
	
	/**
	 * 获取商品列表
	 * @param priceRange
	 * @param discount
	 * @param color
	 * @param searchContent
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getGoods(String priceRange,String discount,String color,String searchContent,TUser loginUser)throws Exception;
	
	/**
	 * 获取商品详细
	 * @param goodId
	 * @return
	 * @throws Exception
	 */
	public TGoods getTGoodsByGoodId(String goodId)throws Exception;
	
	/**
	 * 收藏商品
	 * @param goodId
	 * @param loginUser
	 * @throws Exception
	 */
	public void collectGood(String goodId,TUser loginUser,String type)throws Exception;
	
	/**
	 * 心愿单
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> collectOrder(TUser loginUser)throws Exception;
	
	
	/**
	 * 保存反馈内容
	 * @param feedback
	 * @throws Exception
	 */
	public void saveorupdateTFeedback(TFeedback feedback)throws Exception;
	
	/**
	 * 获取最新折扣
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> newDiscount()throws Exception;
	
	/**
	 * 获取大图集合
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getBigList(String bigId)throws Exception;
}
