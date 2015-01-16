package com.myBlaire.service.impl;

import com.myBlaire.dao.SysDictDao;
import com.myBlaire.domain.SysDict;
import com.myBlaire.service.SysDictService;
import com.myBlaire.util.PageInfo;

public class SysDictServiceImpl implements SysDictService{
	private SysDictDao dictDao;
	
	public void setDictDao(SysDictDao dictDao) {
		this.dictDao = dictDao;
	}

	public PageInfo<SysDict> getPageInfo(SysDict dict, Integer size,
			Integer page) throws Exception {
		// TODO Auto-generated method stub
		
		return dictDao.getPageInfo(dict, size, page);
	}

	public void saveorupdateSysDict(SysDict dict) throws Exception {
		// TODO Auto-generated method stub
		dictDao.saveorupdateSysDict(dict);
	}

	public void upDictDisabled(String dictIds, boolean falg) throws Exception {
		// TODO Auto-generated method stub
		dictDao.upDictDisabled(dictIds, falg);
	}

}
