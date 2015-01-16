package com.myBlaire.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * TImageSet entity. @author MyEclipse Persistence Tools
 */

public class TImageSet implements java.io.Serializable {

	// Fields

	private String imageSetId;
	private TGoods TGoods;
	private String usage;
	private Integer imageCount;
	private String imageSetName;
	private Set TImageAssets = new HashSet(0);

	// Constructors

	/** default constructor */
	public TImageSet() {
	}

	/** full constructor */
	public TImageSet(TGoods TGoods, String usage, Integer imageCount,
			String imageSetName, Set TImageAssets) {
		this.TGoods = TGoods;
		this.usage = usage;
		this.imageCount = imageCount;
		this.imageSetName = imageSetName;
		this.TImageAssets = TImageAssets;
	}

	// Property accessors

	public String getImageSetId() {
		return this.imageSetId;
	}

	public void setImageSetId(String imageSetId) {
		this.imageSetId = imageSetId;
	}

	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public Integer getImageCount() {
		return this.imageCount;
	}

	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}

	public String getImageSetName() {
		return this.imageSetName;
	}

	public void setImageSetName(String imageSetName) {
		this.imageSetName = imageSetName;
	}

	public Set getTImageAssets() {
		return this.TImageAssets;
	}

	public void setTImageAssets(Set TImageAssets) {
		this.TImageAssets = TImageAssets;
	}

}