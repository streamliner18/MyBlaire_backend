package com.myBlaire.util;

public class ResultInfo  {
	private String code;
	private Object result;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResultInfo(String code, Object result, String message) {
		super();
		this.code = code;
		this.result = result;
		this.message = message;
	}
	
	
}
