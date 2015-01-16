package com.myBlaire.domain;

/**
 * TImageAsset entity. @author MyEclipse Persistence Tools
 */

public class TImageAsset implements java.io.Serializable {

	// Fields

	private String imageAssetId;
	private TImageSet TImageSet;
	private Integer index;
	private Integer imageHeight;
	private Integer imageWidth;
	private String imageName;
	private String imageUrl;

	// Constructors

	/** default constructor */
	public TImageAsset() {
	}

	/** minimal constructor */
	public TImageAsset(TImageSet TImageSet) {
		this.TImageSet = TImageSet;
	}

	/** full constructor */
	public TImageAsset(TImageSet TImageSet, Integer index, Integer imageHeight,
			Integer imageWidth, String imageName, String imageUrl) {
		this.TImageSet = TImageSet;
		this.index = index;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.imageName = imageName;
		this.imageUrl = imageUrl;
	}

	// Property accessors

	public String getImageAssetId() {
		return this.imageAssetId;
	}

	public void setImageAssetId(String imageAssetId) {
		this.imageAssetId = imageAssetId;
	}

	public TImageSet getTImageSet() {
		return this.TImageSet;
	}

	public void setTImageSet(TImageSet TImageSet) {
		this.TImageSet = TImageSet;
	}

	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getImageHeight() {
		return this.imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Integer getImageWidth() {
		return this.imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}