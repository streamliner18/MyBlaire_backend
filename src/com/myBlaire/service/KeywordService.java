package com.myBlaire.service;

import com.myBlaire.domain.Keyword;
import com.myBlaire.util.PageInfo;

public interface KeywordService {

	/**
	 * 关键字list
	 * @param keyWord
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Keyword> getList(Keyword keyWord,Integer page,Integer size)throws Exception;
	
	/**
	 * 添加或更新关键字
	 * @param keyword
	 * @throws Exception
	 */
	public void saveorupdateKeyWord(Keyword keyword)throws Exception;
}
