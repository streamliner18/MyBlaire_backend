package com.myBlaire.action;

import net.sf.json.JSONObject;

import com.myBlaire.domain.Keyword;
import com.myBlaire.service.KeywordService;
import com.myBlaire.util.PageInfo;
import com.myBlaire.util.ResultInfo;
import com.opensymphony.xwork2.ModelDriven;

public class KeywordAction extends BaseAction implements ModelDriven<Keyword>{
	private Keyword keyword=new Keyword();
	private KeywordService keywordService;
	private Integer page;// 当前页数
	private Integer rows;// 行数
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}
	public Keyword getModel() {
		// TODO Auto-generated method stub
		return keyword;
	}
	
	/**
	 * 关键字列表
	 * @return
	 * @throws Exception
	 */
	public String getList()throws Exception{
		PageInfo<Keyword> pageInfo=keywordService.getList(keyword, page, rows);
		System.out.println(JSONObject.fromObject(pageInfo).toString());
		this.responseString(JSONObject.fromObject(pageInfo).toString());
		return null;
	}
	
	/**
	 * 添加关键字
	 * @return
	 * @throws Exception
	 */
	public String saveKeyWord()throws Exception{
		keywordService.saveorupdateKeyWord(keyword);
		
		ResultInfo result = new ResultInfo("0", null, "保存成功!");
		this.addResultInfo(result);
		return null;
	}
	
}
