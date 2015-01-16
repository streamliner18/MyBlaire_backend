package com.myBlaire.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.myBlaire.domain.TGoods;
import com.myBlaire.service.TGoodsService;
import com.myBlaire.util.PageInfo;
import com.myBlaire.util.ResultInfo;
import com.opensymphony.xwork2.ModelDriven;

public class TGoodsAction extends BaseAction implements ModelDriven<TGoods>{
	private TGoodsService tGoodsService;
	private Integer page;// 当前页数
	private Integer rows;// 行数
	private TGoods good=new TGoods();
	private Date beginDate,endDate;
	
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void settGoodsService(TGoodsService tGoodsService) {
		this.tGoodsService = tGoodsService;
	}
	public TGoods getModel() {
		// TODO Auto-generated method stub
		return good;
	}
	
	/**
	 * 商品列表
	 * @return
	 * @throws Exception
	 */
	public String getList()throws Exception{
		PageInfo<TGoods> pageInfo=tGoodsService.getList(good, page, rows,beginDate,endDate);
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
	
	/**
	 * 获取颜色列表
	 * @return
	 * @throws Exception
	 */
	public String getColorList()throws Exception{
		List<Map<String, String>> list=tGoodsService.getDcitList("color");
		this.responseString(JSONArray.fromObject(list).toString());
		return null;
	}

	/**
	 * 保存商品信息
	 * @return
	 * @throws Exception
	 */
	public String saveGood()throws Exception{
		good.setAddTime(new Date());
		if(StringUtils.isNotBlank(good.getGoodId())){
			TGoods oldGood=tGoodsService.getGoodByGoodId(good.getGoodId());
			good.setAddTime(oldGood.getAddTime());
			
		}
		good.setLastUpdateTime(new Date());
		good.setDiscount((good.getOriginalPrice()-(good.getOriginalPrice()-good.getCurrentPrice()))/good.getOriginalPrice());
		tGoodsService.saveorupdateGood(good);
		this.addResultInfo(new ResultInfo("0", null, "保存成功"));
		return null;
	}
	
}
