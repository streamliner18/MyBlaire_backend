package com.myBlaire.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.dao.BaseDao;
import com.myBlaire.dao.SysDictDao;
import com.myBlaire.domain.SysDict;
import com.myBlaire.util.PageInfo;

public class SysDictDaoImpl implements SysDictDao {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public PageInfo<SysDict> getPageInfo(SysDict dict, Integer size,
			Integer page) throws Exception {
		// TODO Auto-generated method stub
		String hql="from SysDict where 1=1";
		String hql_count="select count(*) from SysDict where 1=1";
		if(StringUtils.isNotBlank(dict.getDictType())){
			hql+=" and dictType='"+dict.getDictType()+"'";
			hql_count+=" and dictType='"+dict.getDictType()+"'";
		}
		hql+="order by colOrder";
		List<SysDict> list=baseDao.findAllByHQLPage(hql, null, page, size);
		List list_count=baseDao.findAllByHQL(hql_count);
		Integer count=0;
		if(list_count.size()>0){
			count=Integer.valueOf(list_count.get(0).toString());
		}
		return new PageInfo<SysDict>(list, count);
	}

	public void saveorupdateSysDict(SysDict dict) throws Exception {
		// TODO Auto-generated method stub
		baseDao.saveorupdate(dict);
	}

	public void upDictDisabled(String dictIds, boolean falg) throws Exception {
		// TODO Auto-generated method stub
		String hql="update SysDict set dictDisabled=? where dictId in ("+dictIds+")";
		baseDao.update(hql, new Object[]{falg});
	}

}
