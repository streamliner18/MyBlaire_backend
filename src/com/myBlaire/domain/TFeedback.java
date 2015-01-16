package com.myBlaire.domain;

import java.util.Date;

/**
 * TFeedback entity. @author MyEclipse Persistence Tools
 */

public class TFeedback implements java.io.Serializable {

	// Fields

	private String id;
	private String content;
	private String clientVersion;
	private String appVeraion;
	private String feedbackUser;
	private Date feedbackTime;

	// Constructors

	/** default constructor */
	public TFeedback() {
	}

	/** full constructor */
	public TFeedback(String content, String clientVersion, String appVeraion,
			String feedbackUser, Date feedbackTime) {
		this.content = content;
		this.clientVersion = clientVersion;
		this.appVeraion = appVeraion;
		this.feedbackUser = feedbackUser;
		this.feedbackTime = feedbackTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClientVersion() {
		return this.clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getAppVeraion() {
		return this.appVeraion;
	}

	public void setAppVeraion(String appVeraion) {
		this.appVeraion = appVeraion;
	}

	public String getFeedbackUser() {
		return this.feedbackUser;
	}

	public void setFeedbackUser(String feedbackUser) {
		this.feedbackUser = feedbackUser;
	}

	public Date getFeedbackTime() {
		return this.feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

}