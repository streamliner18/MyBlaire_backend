package com.myBlaire.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String password;
	private String email;
	private Date regDate;
	private Boolean isDisable;
	private String deviceType;
	private String deviceToken;
	private String thirdPartyType;
	private String thirdPartyToken;
	private String communicationId;
	private Set TGoods = new HashSet(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** full constructor */
	public TUser(String userName, String password, String email,
			Date regDate, Boolean isDisable, String deviceType,
			String deviceToken, String thirdPartyType, String thirdPartyToken,
			Set TGoods) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.regDate = regDate;
		this.isDisable = isDisable;
		this.deviceType = deviceType;
		this.deviceToken = deviceToken;
		this.thirdPartyType = thirdPartyType;
		this.thirdPartyToken = thirdPartyToken;
		this.TGoods = TGoods;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Boolean getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(Boolean isDisable) {
		this.isDisable = isDisable;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return this.deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getThirdPartyType() {
		return this.thirdPartyType;
	}

	public void setThirdPartyType(String thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}

	public String getThirdPartyToken() {
		return this.thirdPartyToken;
	}

	public void setThirdPartyToken(String thirdPartyToken) {
		this.thirdPartyToken = thirdPartyToken;
	}

	public Set getTGoods() {
		return TGoods;
	}

	public void setTGoods(Set tGoods) {
		TGoods = tGoods;
	}

	public String getCommunicationId() {
		return communicationId;
	}

	public void setCommunicationId(String communicationId) {
		this.communicationId = communicationId;
	}

	

}