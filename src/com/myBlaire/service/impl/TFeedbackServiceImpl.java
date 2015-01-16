package com.myBlaire.service.impl;

import java.util.Map;

import com.myBlaire.dao.TFeedbackDao;
import com.myBlaire.service.TFeedbackService;
import com.myBlaire.util.PageInfo;

public class TFeedbackServiceImpl implements TFeedbackService {
	private TFeedbackDao tFeedbackDao;
	
	public void settFeedbackDao(TFeedbackDao tFeedbackDao) {
		this.tFeedbackDao = tFeedbackDao;
	}

	public PageInfo<Map<String, String>> getList(Integer page, Integer size)
			throws Exception {
		// TODO Auto-generated method stub
		return tFeedbackDao.getList(page, size);
	}

}
