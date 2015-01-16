package com.myBlaire.domain;

/**
 * Keyword entity. @author MyEclipse Persistence Tools
 */

public class Keyword implements java.io.Serializable {

	// Fields

	private String id;
	private String 	wordKey;
	private String wordValue;
	private Integer clickNum;
	private Boolean isDisable;

	// Constructors

	/** default constructor */
	public Keyword() {
	}

	/** full constructor */
	public Keyword(String wordKey, String wordValue, Integer clickNum,
			Boolean isDisable) {
		this.wordKey = wordKey;
		this.wordValue = wordValue;
		this.clickNum = clickNum;
		this.isDisable = isDisable;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWordKey() {
		return this.wordKey;
	}

	public void setWordKey(String wordKey) {
		this.wordKey = wordKey;
	}

	public String getWordValue() {
		return this.wordValue;
	}

	public void setWordValue(String wordValue) {
		this.wordValue = wordValue;
	}

	public Integer getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Boolean getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(Boolean isDisable) {
		this.isDisable = isDisable;
	}

}