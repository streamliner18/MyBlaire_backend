package com.myBlaire.dao;

import java.util.List;
import java.util.Map;

import com.myBlaire.domain.TFeedback;
import com.myBlaire.domain.TGoods;
import com.myBlaire.domain.TImageSet;
import com.myBlaire.domain.TUser;
import com.myBlaire.domain.TUserCollect;

public interface AppInterfaceDao {

	/**
	 * 获取关键字列表
	 * @return
	 */
	public List<Map<String,String>> getKeyWordList()throws Exception;
	
	/**
	 * 倒序获取收藏人数的商品
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getHotGoodsDesc()throws Exception;
	
	/**
	 * 获取user收藏的商品列表
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<TUserCollect> getTUserCollectList(TUser user)throws Exception;
	
	
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
	public List<Map<String,String>> getGoods(String priceRange,String discount,String color,String searchContent)throws Exception;
	
	/**
	 * 获取最新折扣商品
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> newDiscount()throws Exception;
	/**
	 * 增加关键字点击数量
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public void upKeyWordClickNum(String key)throws Exception;
	
	/**
	 * 获取商品详细
	 * @param goodId
	 * @return
	 * @throws Exception
	 */
	public TGoods getTGoodsByGoodId(String goodId)throws Exception;
	
	/**
	 * 获取收藏信息
	 * @param loginUser
	 * @param good
	 * @return
	 * @throws Exception
	 */
	public TUserCollect getTUSEcCollect(TUser loginUser,TGoods good)throws Exception;
	
	/**
	 * 添加收藏信息
	 * @param collect
	 * @throws Exception
	 */
	public void saveorupdateTUSEcCollect(TUserCollect collect)throws Exception;
	
	/**
	 * 删除收藏信息
	 * @param collect
	 * @throws Exception
	 */
	public void deleteTUserCollect(TUserCollect collect)throws Exception;
	
	/**
	 * 保存反馈内容
	 * @param feedback
	 * @throws Exception
	 */
	public void saveorupdateTFeedback(TFeedback feedback)throws Exception;
	
	/**
	 * 根据type添加或减少收藏数
	 * @param goodId
	 * @param type
	 * @throws Exception
	 */
	public void updateCollectNum(String goodId,String type)throws Exception;
	
	/**
	 * 获取图片图集
	 * @param setId
	 * @return
	 * @throws Exception
	 */
	public TImageSet getImageSet(String setId)throws Exception;
}
