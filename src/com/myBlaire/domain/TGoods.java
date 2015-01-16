package com.myBlaire.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TGoods entity. @author MyEclipse Persistence Tools
 */

public class TGoods implements java.io.Serializable {

	// Fields

	private String goodId;
	private String goodName;
	private String nickName;
	private Double originalPrice;
	private Double currentPrice;
	private Boolean isShow;
	private Boolean isStreetShooting;
	private String bigPctureUrl;
	private String bigPctureUrl2;
	private String bigPctureUrl3;
	private Integer collectCount;
	private String smallPicture;
	private String streetShooting;
	private String color;
	private Double discount;
	private String detailed;
	private String goodDesc;
	private String production;
	private String materialQuality;
	private String buyUrl;
	private Date addTime;
	private Date lastUpdateTime;
	private String imageStreet;
	private String imageThumbnail;
	private String imageBig;
	
	private String imageSlideshow;
	private String imageTrending;
	private String imageHighlight;
	// Constructors

	/** default constructor */
	public TGoods() {
	}

	/** minimal constructor */
	public TGoods(String goodName, String nickName, Double originalPrice,
			Double currentPrice) {
		this.goodName = goodName;
		this.nickName = nickName;
		this.originalPrice = originalPrice;
		this.currentPrice = currentPrice;
	}

	/** full constructor */
	public TGoods(String goodName, String nickName, Double originalPrice,
			Double currentPrice, Boolean isShow, Boolean isStreetShooting,
			String bigPctureUrl, String bigPctureUrl2, String bigPctureUrl3,
			Integer collectCount, String smallPicture, String streetShooting,
			String color, Double discount, String detailed, String goodDesc,
			String production, String materialQuality, String buyUrl,
			Date addTime, Date lastUpdateTime) {
		this.goodName = goodName;
		this.nickName = nickName;
		this.originalPrice = originalPrice;
		this.currentPrice = currentPrice;
		this.isShow = isShow;
		this.isStreetShooting = isStreetShooting;
		this.bigPctureUrl = bigPctureUrl;
		this.bigPctureUrl2 = bigPctureUrl2;
		this.bigPctureUrl3 = bigPctureUrl3;
		this.collectCount = collectCount;
		this.smallPicture = smallPicture;
		this.streetShooting = streetShooting;
		this.color = color;
		this.discount = discount;
		this.detailed = detailed;
		this.goodDesc = goodDesc;
		this.production = production;
		this.materialQuality = materialQuality;
		this.buyUrl = buyUrl;
		this.addTime = addTime;
		this.lastUpdateTime = lastUpdateTime;
		
	}

	// Property accessors

	public String getGoodId() {
		return this.goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return this.goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Double getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getCurrentPrice() {
		return this.currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Boolean getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Boolean getIsStreetShooting() {
		return this.isStreetShooting;
	}

	public void setIsStreetShooting(Boolean isStreetShooting) {
		this.isStreetShooting = isStreetShooting;
	}

	public String getBigPctureUrl() {
		return this.bigPctureUrl;
	}

	public void setBigPctureUrl(String bigPctureUrl) {
		this.bigPctureUrl = bigPctureUrl;
	}

	public String getBigPctureUrl2() {
		return this.bigPctureUrl2;
	}

	public void setBigPctureUrl2(String bigPctureUrl2) {
		this.bigPctureUrl2 = bigPctureUrl2;
	}

	public String getBigPctureUrl3() {
		return this.bigPctureUrl3;
	}

	public void setBigPctureUrl3(String bigPctureUrl3) {
		this.bigPctureUrl3 = bigPctureUrl3;
	}

	public Integer getCollectCount() {
		return this.collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public String getSmallPicture() {
		return this.smallPicture;
	}

	public void setSmallPicture(String smallPicture) {
		this.smallPicture = smallPicture;
	}

	public String getStreetShooting() {
		return this.streetShooting;
	}

	public void setStreetShooting(String streetShooting) {
		this.streetShooting = streetShooting;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDetailed() {
		return this.detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public String getGoodDesc() {
		return this.goodDesc;
	}

	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}

	public String getProduction() {
		return this.production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getMaterialQuality() {
		return this.materialQuality;
	}

	public void setMaterialQuality(String materialQuality) {
		this.materialQuality = materialQuality;
	}

	public String getBuyUrl() {
		return this.buyUrl;
	}

	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getImageStreet() {
		return imageStreet;
	}

	public void setImageStreet(String imageStreet) {
		this.imageStreet = imageStreet;
	}

	public String getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(String imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public String getImageBig() {
		return imageBig;
	}

	public void setImageBig(String imageBig) {
		this.imageBig = imageBig;
	}

	public String getImageSlideshow() {
		return imageSlideshow;
	}

	public void setImageSlideshow(String imageSlideshow) {
		this.imageSlideshow = imageSlideshow;
	}

	public String getImageTrending() {
		return imageTrending;
	}

	public void setImageTrending(String imageTrending) {
		this.imageTrending = imageTrending;
	}

	public String getImageHighlight() {
		return imageHighlight;
	}

	public void setImageHighlight(String imageHighlight) {
		this.imageHighlight = imageHighlight;
	}
	

}