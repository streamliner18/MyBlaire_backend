package com.myBlaire.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.myBlaire.domain.TGoods;
import com.myBlaire.util.PageInfo;

public interface TGoodsDao {
	
	/**
	 * 获取商品列表
	 * @param good
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<TGoods> getList(TGoods good,Integer page,Integer size,Date beginDate,Date endDate)throws Exception;
	
	/**
	 * 根据类型获得字典数据
	 * @param type
	 * @return
	 */
	public List<Map<String,String>> getDcitList(String type)throws Exception;
	
	/**
	 * 保存商品信息
	 * @param good
	 * @throws Exception
	 */
	public void saveorupdateGood(TGoods good)throws Exception;
	
	/**
	 * 根据id获取商品信息
	 * @param goodId
	 * @return
	 * @throws Exception
	 */
	public TGoods getGoodByGoodId(String goodId)throws Exception;
}
