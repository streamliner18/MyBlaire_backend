package com.myBlaire.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.dao.AppInterfaceDao;
import com.myBlaire.domain.TFeedback;
import com.myBlaire.domain.TGoods;
import com.myBlaire.domain.TImageAsset;
import com.myBlaire.domain.TImageSet;
import com.myBlaire.domain.TUser;
import com.myBlaire.domain.TUserCollect;
import com.myBlaire.service.AppInterfaceService;

public class AppInterfaceServiceImpl implements AppInterfaceService{
	private AppInterfaceDao appInterfaceDao;
	
	public void setAppInterfaceDao(AppInterfaceDao appInterfaceDao) {
		this.appInterfaceDao = appInterfaceDao;
	}

	public List<Map<String, String>> getKeyWordList()throws Exception {
		// TODO Auto-generated method stub
		return appInterfaceDao.getKeyWordList();
	}

	public List<Map<String, String>> getHotGoods(TUser loginUser) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>>  goodMaps=appInterfaceDao.getHotGoodsDesc();
		List<TUserCollect> collectList=appInterfaceDao.getTUserCollectList(loginUser);
		List<Map<String, String>>  maps=new ArrayList<Map<String,String>>();
		for (int i = 0; i < goodMaps.size(); i++) {
			Map<String,String> map=goodMaps.get(i);
			Integer isCollect=0;
			for (int j = 0; j < collectList.size(); j++) {
				TUserCollect collect=collectList.get(j);
				if(collect.getTGoods().getGoodId().equals(map.get("goodId"))){
					isCollect=1;
				}
			}
			map.put("isCollect", isCollect.toString());
			
			String imageTrending=map.get("imageTrending");
			TImageSet  imageset=appInterfaceDao.getImageSet(imageTrending);
			
			if(imageset!=null && imageset.getTImageAssets().size()>0){
				map.put("imageTrending", getImageAssetSetFirst(imageset.getTImageAssets()));
			}else{
				map.put("imageTrending", "/");
			}
			maps.add(map);
		}
		
		return maps;
	}

	public List<Map<String, String>> getStreetShootingGoods() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> goodMaps=appInterfaceDao.getStreetShootingGoods();
		List<Map<String, String>>  maps=new ArrayList<Map<String,String>>();
		for (int i = 0; i < goodMaps.size(); i++) {
			Map<String,String> map=goodMaps.get(i);
			String imageThumbnail=map.get("imageThumbnail");
			TImageSet  imageset_Thumbnai=appInterfaceDao.getImageSet(imageThumbnail);
			if(imageset_Thumbnai!=null && imageset_Thumbnai.getTImageAssets().size()>0){
				map.put("imageThumbnail", getImageAssetSetFirst(imageset_Thumbnai.getTImageAssets()));
			}else{
				map.put("imageThumbnail", "/");
			}
			
			String imageHighlight=map.get("imageHighlight");
			TImageSet  imageset_Highlight=appInterfaceDao.getImageSet(imageHighlight);
			if(imageset_Highlight!=null && imageset_Highlight.getTImageAssets().size()>0){
				map.put("imageHighlight", getImageAssetSetFirst(imageset_Highlight.getTImageAssets()));
			}else{
				map.put("imageHighlight", "/");
			}
			maps.add(map);
		}
		return maps;
	}

	public List<Map<String, String>> getGoods(String priceRange,
			String discount, String color, String searchContent, TUser loginUser)
			throws Exception {
		// TODO Auto-generated method stub
		appInterfaceDao.upKeyWordClickNum(searchContent);
		List<Map<String, String>> goodMaps=appInterfaceDao.getGoods(priceRange, discount, color, searchContent);
		List<TUserCollect> collectList=appInterfaceDao.getTUserCollectList(loginUser);
		List<Map<String, String>>  maps=new ArrayList<Map<String,String>>();
		for (int i = 0; i < goodMaps.size(); i++) {
			Map<String,String> map=goodMaps.get(i);
			Integer isCollect=0;
			for (int j = 0; j < collectList.size(); j++) {
				TUserCollect collect=collectList.get(j);
				if(collect.getTGoods().getGoodId().equals(map.get("goodId"))){
					isCollect=1;
				}
			}
			map.put("isCollect", isCollect.toString());
			
			String imageThumbnail=map.get("imageThumbnail");
			TImageSet  imageset_Thumbnai=appInterfaceDao.getImageSet(imageThumbnail);
			if(imageset_Thumbnai!=null && imageset_Thumbnai.getTImageAssets().size()>0){
				map.put("imageThumbnail", getImageAssetSetFirst(imageset_Thumbnai.getTImageAssets()));
			}else{
				map.put("imageThumbnail", "/");
			}
			
			maps.add(map);
		}
		
		return maps;
	}

	public TGoods getTGoodsByGoodId(String goodId) throws Exception {
		// TODO Auto-generated method stub
		return appInterfaceDao.getTGoodsByGoodId(goodId);
	}

	public void collectGood(String goodId, TUser loginUser, String type)
			throws Exception {
		// TODO Auto-generated method stub
		TGoods good=new TGoods();
		good.setGoodId(goodId);
		TUserCollect collect=appInterfaceDao.getTUSEcCollect(loginUser, good);
		if(type.equals("0")){
			if(collect==null){
				collect=new TUserCollect();
				collect.setTUser(loginUser);
				collect.setTGoods(good);
				appInterfaceDao.saveorupdateTUSEcCollect(collect);
			}
		}else if(type.equals("1")){
			if(collect!=null){
				appInterfaceDao.deleteTUserCollect(collect);
			}
		}
		
		appInterfaceDao.updateCollectNum(goodId, type);
	}

	public List<Map<String, String>> collectOrder(TUser loginUser)
			throws Exception {
		// TODO Auto-generated method stub
		List<TUserCollect> collects=appInterfaceDao.getTUserCollectList(loginUser);
		List<Map<String, String>> maps=new ArrayList<Map<String,String>>();
		for (int i = 0; i < collects.size(); i++) {
			TGoods good=collects.get(i).getTGoods();
			
			Map<String, String> map=new HashMap<String, String>();
			map.put("goodId", good.getGoodId().toString());
			map.put("goodName", good.getGoodName().toString());
			map.put("currentPrice", good.getCurrentPrice().toString());
			map.put("smallPicture", good.getSmallPicture().toString());
			map.put("isCollect", "1");
			
			String imageThumbnail=good.getImageThumbnail();
			TImageSet  imageset_Thumbnai=appInterfaceDao.getImageSet(imageThumbnail);
			if(imageset_Thumbnai!=null && imageset_Thumbnai.getTImageAssets().size()>0){
				map.put("imageThumbnail", getImageAssetSetFirst(imageset_Thumbnai.getTImageAssets()));
			}else{
				map.put("imageThumbnail", "/");
			}
			maps.add(map);
		}
	
		return maps;
	}

	public void saveorupdateTFeedback(TFeedback feedback) throws Exception {
		// TODO Auto-generated method stub
		appInterfaceDao.saveorupdateTFeedback(feedback);	
	}

	public List<Map<String, String>> newDiscount() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> maps=new ArrayList<Map<String,String>>();
		List<Map<String,String>> goodMaps=appInterfaceDao.newDiscount();
		for (int i = 0; i < goodMaps.size(); i++) {
			Map<String,String> map=goodMaps.get(i);
			String imageSlideshow=map.get("imageSlideshow");
			TImageSet  imageset_Slideshow=appInterfaceDao.getImageSet(imageSlideshow);
			if(imageset_Slideshow!=null && imageset_Slideshow.getTImageAssets().size()>0){
				map.put("imageSlideshow", getImageAssetSetFirst(imageset_Slideshow.getTImageAssets()));
			}else{
				map.put("imageSlideshow", "/");
			}
			maps.add(map);
		}
		return maps;
	}
	
	
	
	public String getImageAssetSetFirst(Set set){
		Iterator<TImageAsset> it = set.iterator();
		Integer index=0;
		TImageAsset imageAsset=null;
		while (it.hasNext()&& index==0) {
			imageAsset = it.next();
			if(imageAsset.getIndex()==1){
				 
				 index++;
			}
		 
		}
		
		return imageAsset.getImageUrl();
	}
	
	public List<Map<String,String>> getImageAssetSetBig(Set set){
		List<Map<String,String>> maps=new ArrayList<Map<String,String>>();
		Iterator<TImageAsset> it = set.iterator();
		while (it.hasNext()) {
			Map<String,String> map=new HashMap<String, String>(0);
			TImageAsset imageAsset = it.next();
			map.put("imageUrl", imageAsset.getImageUrl());
			maps.add(map);	
		}
		
		return maps;
	}

	public List<Map<String, String>> getBigList(String bigId) throws Exception {
		// TODO Auto-generated method stub
		TImageSet  imageset_bid=appInterfaceDao.getImageSet(bigId);
		if(imageset_bid!=null){
			return getImageAssetSetBig(imageset_bid.getTImageAssets());
		}else{
			return new ArrayList<Map<String,String>>();
		}
		
	}

}
