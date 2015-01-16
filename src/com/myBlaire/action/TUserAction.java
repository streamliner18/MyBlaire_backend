package com.myBlaire.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.myBlaire.domain.TUser;
import com.myBlaire.service.TUserService;
import com.myBlaire.util.MD5;
import com.myBlaire.util.PageInfo;
import com.myBlaire.util.ResultInfo;
import com.opensymphony.xwork2.ModelDriven;

public class TUserAction extends BaseAction implements ModelDriven<TUser> {
	private TUser user=new TUser();
	private TUserService tUserService;
	private Integer page;// 当前页数
	private Integer rows;// 行数
	
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public TUser getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}

	/**
	 * 会员用户列表
	 * @return
	 * @throws Exception
	 */
	public String getList()throws Exception{
		
		PageInfo<TUser> pageInfo=tUserService.getList(user, page, rows);
		JsonConfig jsonConfig=new JsonConfig(); 
		jsonConfig.setExcludes(new String[]{"TGoods"});
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

	/**
	 * 保存会员用户
	 * @return
	 * @throws Exception
	 */
	public String saveUser()throws Exception{
		if(user.getPassword().length()<32){
			user.setPassword(MD5.GetMD5Code(user.getPassword()));
		}
		tUserService.saveorupdateTUser(user);
		ResultInfo result = new ResultInfo("0", null, "保存成功!");
		this.addResultInfo(result);
		return null;
	}
	
	/**
	 * 获取收藏列表
	 * @return
	 * @throws Exception
	 */
	public String getCollectList()throws Exception{
		PageInfo<Map<String,String>> pageInfo=tUserService.getUserCollectGoods(user);
		JSONObject josn=JSONObject.fromObject(pageInfo);
		this.responseString(JSONObject.fromObject(pageInfo).toString());
		return null;
	}
}
