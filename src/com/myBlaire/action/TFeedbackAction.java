package com.myBlaire.action;

import java.text.SimpleDateFormat;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.myBlaire.service.TFeedbackService;
import com.myBlaire.util.PageInfo;

public class TFeedbackAction extends BaseAction {
	private TFeedbackService tFeedbackService;
	private Integer page;// 当前页数
	private Integer rows;// 行数
	
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void settFeedbackService(TFeedbackService tFeedbackService) {
		this.tFeedbackService = tFeedbackService;
	}

	public String getList()throws Exception{
		PageInfo<Map<String,String>> pageInfo=tFeedbackService.getList(page, rows);
		JsonConfig jsonConfig=new JsonConfig(); 
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
			  private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			    return  value == null ?"" : sd.format(value);
			 }
			  
			  public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			    return null;
			  }
			});
		
		this.responseString(JSONObject.fromObject(pageInfo,jsonConfig).toString());
		return null;
	}
}
