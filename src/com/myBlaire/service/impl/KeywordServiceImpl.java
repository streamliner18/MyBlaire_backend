package com.myBlaire.service.impl;

import com.myBlaire.dao.KeywordDao;
import com.myBlaire.domain.Keyword;
import com.myBlaire.service.KeywordService;
import com.myBlaire.util.PageInfo;

public class KeywordServiceImpl implements KeywordService {
	private KeywordDao keywordDao;
	
	public void setKeywordDao(KeywordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

	public PageInfo<Keyword> getList(Keyword keyWord, Integer page, Integer size)
			throws Exception {
		// TODO Auto-generated method stub
		return keywordDao.getList(keyWord, page, size);
	}

	public void saveorupdateKeyWord(Keyword keyword) throws Exception {
		// TODO Auto-generated method stub
		keywordDao.saveorupdateKeyWord(keyword);
	}

}
