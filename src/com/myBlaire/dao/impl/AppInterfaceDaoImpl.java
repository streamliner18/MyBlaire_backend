package com.myBlaire.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.dao.AppInterfaceDao;
import com.myBlaire.dao.BaseDao;
import com.myBlaire.domain.TFeedback;
import com.myBlaire.domain.TGoods;
import com.myBlaire.domain.TImageSet;
import com.myBlaire.domain.TUser;
import com.myBlaire.domain.TUserCollect;

public class AppInterfaceDaoImpl implements AppInterfaceDao {
	private BaseDao baseDao;
	
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public List<Map<String, String>> getKeyWordList()throws Exception {
		// TODO Auto-generated method stub
		String hql="select wordKey as wordKey,wordValue as wordValue from Keyword where isDisable=? order by clickNum desc";
		
		return baseDao.findMapByHQL(hql, new Object[]{false}, 1, 5);
	}


	public List<Map<String, String>> getHotGoodsDesc() throws Exception {
		// TODO Auto-generated method stub
		String hql="select good_id as goodId,good_name as goodName,cast(current_price as char(20)) as currentPrice," +
				"cast(collect_count as char(20)) as collectCount,small_picture as smallPicture,image_trending as imageTrending from t_goods where is_show=? order by collect_count desc";
		return baseDao.execquerySql2(hql, new Object[]{1}, -1, -1);
	}


	public List<TUserCollect> getTUserCollectList(TUser user) throws Exception {
		// TODO Auto-generated method stub
		String hql="from TUserCollect where TUser=?";
		
		return baseDao.findAllByHQL(hql,new Object[]{user});
	}


	public List<Map<String, String>> getStreetShootingGoods() throws Exception {
		// TODO Auto-generated method stub
		String hql="select good_id as goodId,good_name as goodName,cast(current_price as char(20)) as currentPrice,small_picture " +
				"as smallPicture,street_shooting as streetShooting,image_thumbnail as imageThumbnail,image_highlight as imageHighlight from t_goods where is_show=? and is_street_shooting=? order by collect_count desc";
		return baseDao.execquerySql2(hql, new Object[]{1,1}, -1, -1);
	}


	public List<Map<String, String>> getGoods(String priceRange,
			String discount, String color, String searchContent)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="select good_id as goodId,good_name as goodName,cast(current_price as char(20)) as currentPrice," +
				"cast(collect_count as char(20)) as collectCount,small_picture as smallPicture,image_thumbnail as imageThumbnail from t_goods where is_show=? ";
		if(StringUtils.isNotBlank(priceRange)){
			if(priceRange.equals("0")){
				hql+=" and current_price>=10000";
			}else if(priceRange.equals("1")){
				hql+=" and current_price  between 5000 and 10000 ";
			}else if(priceRange.equals("2")){
				hql+=" and current_price  between 1000 and 5000 ";
			}else if(priceRange.equals("3")){
				hql+=" and current_price<=1000";
			}
		}
		
		if(StringUtils.isNotBlank(discount)){
			Double discountnum=Double.valueOf(Double.valueOf(discount)/10);
			if(discount.equals("0")){
				hql+=" and discount<1";
			}else{
				hql+=" and discount like '"+discountnum.toString()+"%'";
			}
		}
		
		if(StringUtils.isNotBlank(color)){
			hql+=" and color='"+color+"'";
		}
		
		if(StringUtils.isNotBlank(searchContent)){
			
			hql+=" and (good_name like '%"+searchContent+"%'  or nick_name like '%"+searchContent+"%')";
		}
		hql+="order by last_update_time desc,collect_count desc";
		return baseDao.execquerySql2(hql, new Object[]{1}, -1, -1);
	}


	public void upKeyWordClickNum(String key) throws Exception {
		// TODO Auto-generated method stub
		String hql="update Keyword set clickNum=clickNum+1 where wordKey=?";
		baseDao.update(hql, new Object[]{key});
	}


	public TGoods getTGoodsByGoodId(String goodId) throws Exception {
		// TODO Auto-generated method stub
		String hql="from TGoods where goodId=?";
		List<TGoods> goods=baseDao.findAllByHQL(hql, new Object[]{goodId});
		if(goods.size()>0){
			return goods.get(0);
		}
			
		return null;
	}


	public void deleteTUserCollect(TUserCollect collect) throws Exception {
		// TODO Auto-generated method stub
		baseDao.delete(collect);
	}


	public TUserCollect getTUSEcCollect(TUser loginUser, TGoods good)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from TUserCollect where TUser=? and TGoods=?";
		List<TUserCollect> collects=baseDao.findAllByHQL(hql, new Object[]{loginUser,good});
		if(collects.size()>0){
			return collects.get(0);
		}
		return null;
	}


	public void saveorupdateTUSEcCollect(TUserCollect collect) throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(collect);
	}


	public void saveorupdateTFeedback(TFeedback feedback) throws Exception {
		// TODO Auto-generated method stub
		baseDao.saveorupdate(feedback);
	}


	public void updateCollectNum(String goodId, String type) throws Exception {
		String hql=null;
		// TODO Auto-generated method stub
		if(type.equals("0")){
			hql="update TGoods set collectCount=collectCount+1 where goodId=?";
		}else if(type.equals("1")){
			hql="update TGoods set collectCount=collectCount-1 where goodId=?";
		}
		baseDao.update(hql, new Object[]{goodId});
		
	}


	public List<Map<String, String>> newDiscount() throws Exception {
		// TODO Auto-generated method stub
		String hql="select good_id as goodId,good_name as goodName,cast(current_price as char(20)) as currentPrice," +
					"cast(collect_count as char(20)) as collectCount,small_picture as smallPicture,image_slideshow as imageSlideshow from t_goods where is_show=? and discount<1 order by discount asc,last_update_time desc ";
		List<Map<String, String>> maps=baseDao.execquerySql2(hql, new Object[]{1},1,5);
		return maps;
	}


	public TImageSet getImageSet(String setId) throws Exception {
		// TODO Auto-generated method stub
		String hql="from TImageSet where imageSetId=?";
		List<TImageSet> list=baseDao.findAllByHQL(hql,new Object[]{setId});
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
