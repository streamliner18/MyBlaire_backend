package com.myBlaire.dao;

import com.myBlaire.domain.SysDict;
import com.myBlaire.util.PageInfo;

public interface SysDictDao {
	
	/**
	 * 分页显示数据字典
	 * @return
	 * @throws Exception
	 */
	public PageInfo<SysDict> getPageInfo(SysDict dict,Integer size,Integer page)throws Exception;
	
	/**
	 * 保存或修改字典数据
	 * @param dict
	 * @throws Exception
	 */
	public void saveorupdateSysDict(SysDict dict)throws Exception;
	
	/**
	 * 修改字典数据禁用状态
	 * @param dictIds
	 * @param falg
	 * @throws Exception
	 */
	public void upDictDisabled(String dictIds,boolean falg)throws Exception;
}
