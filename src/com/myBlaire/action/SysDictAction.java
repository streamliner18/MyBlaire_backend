package com.myBlaire.action;

import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import com.myBlaire.domain.SysDict;
import com.myBlaire.service.SysDictService;
import com.myBlaire.util.PageInfo;
import com.myBlaire.util.ResultInfo;
import com.opensymphony.xwork2.ModelDriven;

public class SysDictAction extends BaseAction implements ModelDriven<SysDict> {
	private SysDictService dictService;
	private SysDict dict = new SysDict();
	private Integer page;// 当前页数
	private Integer rows;// 行数
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}

	public void setDictService(SysDictService dictService) {
		this.dictService = dictService;
	}

	public SysDict getModel() {
		// TODO Auto-generated method stub
		return dict;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * 字典列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getList() throws Exception {
		PageInfo<SysDict> dictList = dictService.getPageInfo(dict, rows, page);
		JSONObject json = JSONObject.fromObject(dictList);
		this.responseString(json.toString());
		return null;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveDict() throws Exception {
		dictService.saveorupdateSysDict(dict);

		ResultInfo result = new ResultInfo("0", null, "保存成功!");
		this.addResultInfo(result);
		return null;
	}

	/**
	 * 修改字典数据
	 * 
	 * @return
	 */
	public String editDict()throws Exception {
		Enumeration e = (Enumeration) request.getParameterNames();
		while (e.hasMoreElements()) {
			String parName = (String) e.nextElement();
			String value = request.getParameter(parName);
			System.out.println("----" + parName);
			System.out.println("----" + value);

		}
		ResultInfo result =null;
		if(StringUtils.isBlank(type)){
			result = new ResultInfo("1", null, "类型不能为空!");
		}else{
			if(type.equals("enable")){
				dictService.upDictDisabled(dict.getDictId(), false);
				result = new ResultInfo("0", null, "启用成功!");
			}else if(type.equals("disabled")){
				dictService.upDictDisabled(dict.getDictId(), true);
				result = new ResultInfo("0", null, "禁用成功!");
			}else{
				result = new ResultInfo("1", null, "未知参数!");
			}
		}
		
		this.addResultInfo(result);
		return null;
	}
}
