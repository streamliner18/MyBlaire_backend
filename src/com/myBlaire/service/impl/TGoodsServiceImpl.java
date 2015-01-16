package com.myBlaire.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.myBlaire.dao.TGoodsDao;
import com.myBlaire.domain.TGoods;
import com.myBlaire.service.TGoodsService;
import com.myBlaire.util.PageInfo;

public class TGoodsServiceImpl implements TGoodsService {
	private TGoodsDao tGoodsDao;
	
	public void setTGoodsDao(TGoodsDao tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}

	public void settGoodsDao(TGoodsDao tGoodsDao) {
		this.tGoodsDao = tGoodsDao;
	}

	public PageInfo<TGoods> getList(TGoods good, Integer page, Integer size,Date beginDate,Date endDate)throws Exception {
		// TODO Auto-generated method stub
		return tGoodsDao.getList(good, page, size,beginDate,endDate);
	}

	public List<Map<String, String>> getDcitList(String type)throws Exception {
		// TODO Auto-generated method stub
		return tGoodsDao.getDcitList(type);
	}

	public TGoods getGoodByGoodId(String goodId) throws Exception {
		// TODO Auto-generated method stub
		return tGoodsDao.getGoodByGoodId(goodId);
	}

	public void saveorupdateGood(TGoods good) throws Exception {
		// TODO Auto-generated method stub
		tGoodsDao.saveorupdateGood(good);
	}

	
}
