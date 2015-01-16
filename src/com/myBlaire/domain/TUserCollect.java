package com.myBlaire.domain;

/**
 * TUserCollect entity. @author MyEclipse Persistence Tools
 */

public class TUserCollect implements java.io.Serializable {

	// Fields

	private String collectId;
	private TUser TUser;
	private TGoods TGoods;

	// Constructors

	/** default constructor */
	public TUserCollect() {
	}

	/** minimal constructor */
	public TUserCollect(TUser TUser) {
		this.TUser = TUser;
	}

	/** full constructor */
	public TUserCollect(TUser TUser, TGoods TGoods) {
		this.TUser = TUser;
		this.TGoods = TGoods;
	}

	// Property accessors

	public String getCollectId() {
		return this.collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public TGoods getTGoods() {
		return this.TGoods;
	}

	public void setTGoods(TGoods TGoods) {
		this.TGoods = TGoods;
	}

}