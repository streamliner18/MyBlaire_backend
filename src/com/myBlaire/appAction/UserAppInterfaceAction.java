package com.myBlaire.appAction;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.action.BaseAction;
import com.myBlaire.domain.TFeedback;
import com.myBlaire.domain.TGoods;
import com.myBlaire.domain.TUser;
import com.myBlaire.service.AppInterfaceService;
import com.myBlaire.util.ResultInfo;

public class UserAppInterfaceAction extends BaseAction {
	private AppInterfaceService appInterfaceService;
//	private TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
	public void setAppInterfaceService(AppInterfaceService appInterfaceService) {
		this.appInterfaceService = appInterfaceService;
	}

	/**
	 * 获取字典数据
	 * @return
	 * @throws Exception
	 */
	public String getKeyWord()throws Exception{
		List<Map<String,String>> list=appInterfaceService.getKeyWordList();
		this.addResultInfo(new ResultInfo("0", JSONArray.fromObject(list), "获取成功"));
		return null;
	}
	
	/**
	 * 最具人气
	 * @return
	 * @throws Exception
	 */
	public String getHotGoods()throws Exception{
		TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
		List<Map<String, String>> goodsMaps=appInterfaceService.getHotGoods(loginUser);
		this.addResultInfo(new ResultInfo("0", JSONArray.fromObject(goodsMaps), "获取成功"));
		return null;
	}
	
	/**
	 * 明星同款
	 * @return
	 * @throws Exception
	 */
	public String getStreetShootingGoods()throws Exception{
		List<Map<String, String>> goodsMaps=appInterfaceService.getStreetShootingGoods();
		this.addResultInfo(new ResultInfo("0", JSONArray.fromObject(goodsMaps), "获取成功"));
		return null;
	}
	/**
	 * 最新折扣
	 * @return
	 * @throws Exception
	 */
	public String newDiscount()throws Exception{
		List<Map<String, String>> maps=appInterfaceService.newDiscount();
		this.addResultInfo(new ResultInfo("0", JSONArray.fromObject(maps), "获取成功"));
		return null;
	}
	/**
	 * 获取商品列表
	 * @return
	 * @throws Exception
	 */
	public String getGoods()throws Exception{
		TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
		String priceRange=request.getParameter("priceRange");
		String discount=request.getParameter("discount");
		String color=request.getParameter("color");
		String searchContent=request.getParameter("searchContent");
//		if(StringUtils.isNotBlank(searchContent)){
//			searchContent=new String(searchContent.getBytes("ISO-8859-1"),"UTF-8");
//		}
		List<Map<String, String>> goodsMaps=appInterfaceService.getGoods(priceRange, discount, color, searchContent, loginUser);
		this.addResultInfo(new ResultInfo("0", JSONArray.fromObject(goodsMaps), "获取成功"));
		return null;
	}
	
	/**
	 * 获取商品详细
	 * @return
	 * @throws Exception
	 */
	public String getGoodDetailed()throws Exception{
		String goodId=request.getParameter("goodId");
		JSONObject json=null;
		if(StringUtils.isNotBlank(goodId)){
			TGoods good=appInterfaceService.getTGoodsByGoodId(goodId);
			if(good!=null){
				JSONObject json_result=new JSONObject();
				json_result.put("goodId", good.getGoodId().toString());
				json_result.put("goodName", good.getGoodName().toString());
				json_result.put("currentPrice", good.getCurrentPrice().toString());
				json_result.put("bigPctureUrl", good.getBigPctureUrl());
				json_result.put("bigPctureUrl2",good.getBigPctureUrl2());
				json_result.put("bigPctureUrl3", good.getBigPctureUrl3());
				json_result.put("detailed", good.getDetailed());
				json_result.put("materialQuality", good.getMaterialQuality());
				json_result.put("production", good.getProduction());
				json_result.put("goodDesc", good.getGoodDesc());
				json_result.put("color", good.getColor());
				json_result.put("buyUrl", good.getBuyUrl());
				json_result.put("imageBig", JSONArray.fromObject(appInterfaceService.getBigList(good.getImageBig())));
				
				json=JSONObject.fromObject(new ResultInfo("0", json_result, "获取成功"));
			}else{
				json=JSONObject.fromObject(new ResultInfo("1", null, "商品不信息存在"));
			}
			
			
		}else{
			json=JSONObject.fromObject(new ResultInfo("1", null, "商品id不能为空"));
		}
		this.responseString(json.toString());
		return null;
	}
	
	/**
	 * 收藏商品
	 * @return
	 * @throws Exception
	 */
	public String collectGood()throws Exception{
		TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
		String goodId=request.getParameter("goodId");
		String type=request.getParameter("type");
		JSONObject json=null;
		if(StringUtils.isBlank(goodId) || StringUtils.isBlank(type)){
			json=JSONObject.fromObject(new ResultInfo("1", null, "所需值不能为空"));
		}else{
			appInterfaceService.collectGood(goodId, loginUser, type);
			String message="保存成功";
			if(type.equals("0")){
				message="已收藏到心愿单";
			}else if(type.equals("1")){
				message="取消收藏";
			}
			json=JSONObject.fromObject(new ResultInfo("0", null, message));
		}
		this.responseString(json.toString());
		return null;
	}
	
	/**
	 * 心愿订单
	 * @return
	 * @throws Exception
	 */
	public String collectOrder()throws Exception{
		TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
		List<Map<String, String>> maps=appInterfaceService.collectOrder(loginUser);
		this.addResultInfo(new ResultInfo("0", JSONArray.fromObject(maps), "获取成功"));
		return null;
	}
	
	/**
	 * 反馈意见
	 * @return
	 * @throws Exception
	 */
	public String saveFeedback()throws Exception{
		TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
		String content=request.getParameter("content");
		JSONObject json=null;
		if(StringUtils.isBlank(content)){
			json=JSONObject.fromObject(new ResultInfo("1", null, "意见反馈内容不能为空"));
		}else{
			TFeedback feedback=new TFeedback();
			feedback.setAppVeraion("1.0.00");
			feedback.setClientVersion("1.0.00");
			feedback.setContent(content);
			feedback.setFeedbackTime(new Date());
			feedback.setFeedbackUser(loginUser.getUserId());
			
			appInterfaceService.saveorupdateTFeedback(feedback);
			json=JSONObject.fromObject(new ResultInfo("0", null, "反馈成功"));
		}
		
		this.responseString(json.toString());
		return null;
	}
	@Override
	public void validate(){
		// TODO Auto-generated method stub
		super.validate();
		TUser loginUser=(TUser) session.getAttribute("clientLoginUser");
		try {
			if(loginUser==null)
			{
				JSONObject json=JSONObject.fromObject(new ResultInfo("101", null, "登录信息获取错误，请重新登录"));
				response.getWriter().print(json);
				this.addFieldError("clientLoginUser", "clientLoginUser is null");
			}
			else{
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
