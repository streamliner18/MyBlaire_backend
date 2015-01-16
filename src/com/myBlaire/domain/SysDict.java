package com.myBlaire.domain;

/**
 * SysDict entity. @author MyEclipse Persistence Tools
 */

public class SysDict implements java.io.Serializable {

	// Fields

	private String dictId;
	private String dictKey;
	private String dictValue;
	private String dictType;
	private Boolean dictDisabled;
	private Integer colOrder;

	// Constructors

	/** default constructor */
	public SysDict() {
	}

	/** full constructor */
	public SysDict(String dictKey, String dictValue, String dictType,
			Boolean dictDisabled, Integer colOrder) {
		this.dictKey = dictKey;
		this.dictValue = dictValue;
		this.dictType = dictType;
		this.dictDisabled = dictDisabled;
		this.colOrder = colOrder;
	}

	// Property accessors

	public String getDictId() {
		return this.dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictKey() {
		return this.dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictValue() {
		return this.dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public Boolean getDictDisabled() {
		return this.dictDisabled;
	}

	public void setDictDisabled(Boolean dictDisabled) {
		this.dictDisabled = dictDisabled;
	}

	public Integer getColOrder() {
		return this.colOrder;
	}

	public void setColOrder(Integer colOrder) {
		this.colOrder = colOrder;
	}

}