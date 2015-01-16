package com.myBlaire.action;

public class PageAction {
	
	private String module;
	private String resource;
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getPage(){
		return "success";
	}
	
}
