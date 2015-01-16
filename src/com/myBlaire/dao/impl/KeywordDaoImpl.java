package com.myBlaire.dao.impl;

import java.util.List;

import com.myBlaire.dao.BaseDao;
import com.myBlaire.dao.KeywordDao;
import com.myBlaire.domain.Keyword;
import com.myBlaire.util.PageInfo;

public class KeywordDaoImpl implements KeywordDao {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public PageInfo<Keyword> getList(Keyword keyWord, Integer page, Integer size)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from Keyword order by clickNum";
		String hql_count="select count(*) from Keyword";
		List list_count=baseDao.findAllByHQL(hql_count);
		List<Keyword> list=baseDao.findAllByHQLPage(hql, null, page, size);
		Integer total=0;
		if(list_count.size()>total){
			total=Integer.valueOf(list_count.get(0).toString());
		}
		return new PageInfo<Keyword>(list, total);
	}

	public void saveorupdateKeyWord(Keyword keyword) throws Exception {
		// TODO Auto-generated method stub
		baseDao.saveorupdate(keyword);
	}

}
