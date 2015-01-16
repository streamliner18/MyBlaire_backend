package com.myBlaire.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import com.myBlaire.dao.TUserDao;
import com.myBlaire.domain.TGoods;
import com.myBlaire.domain.TUser;
import com.myBlaire.domain.TUserCollect;
import com.myBlaire.service.TUserService;
import com.myBlaire.util.MD5;
import com.myBlaire.util.PageInfo;
import com.myBlaire.util.ResultInfo;

public class TUserServiceImpl implements TUserService {
	private TUserDao tUserDao;

	public void settUserDao(TUserDao tUserDao) {
		this.tUserDao = tUserDao;
	}

	public PageInfo<TUser> getList(TUser user, Integer page, Integer size)
			throws Exception {
		// TODO Auto-generated method stub

		return tUserDao.getList(user, page, size);
	}

	public void saveorupdateTUser(TUser user) throws Exception {
		// TODO Auto-generated method stub
		tUserDao.saveorupdateTUser(user);
	}

	public PageInfo<Map<String, String>> getUserCollectGoods(TUser user)
			throws Exception {
		// TODO Auto-generated method stub
		List<TUserCollect> list = tUserDao.queryGoodsByUser(user);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		for (TUserCollect userCollect : list) {
			Map<String, String> maps = new HashMap<String, String>();
			TGoods good = userCollect.getTGoods();
			maps.put("goodName", good.getGoodName());
			maps.put("smallPicture", good.getSmallPicture());
			maps.put("goodDesc", good.getGoodDesc());
			rows.add(maps);
		}

		return new PageInfo<Map<String, String>>(rows, list.size());
	}

	public TUser getUserByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub

		return tUserDao.getUserByUserName(userName);
	}

	public JSONObject register(TUser user) throws Exception {
		// TODO Auto-generated method stub
		JSONObject json = null;
		if (StringUtils.isBlank(user.getUserName())
				|| StringUtils.isBlank(user.getPassword())) {
			json = JSONObject.fromObject(new ResultInfo("1", null, "所需值不能为空"));
		} else {
			if (getUserByUserName(user.getUserName()) != null) {
				json = JSONObject.fromObject(new ResultInfo("1", null,
						"用户名已被注册"));
			} else {
				user.setPassword(MD5.GetMD5Code(user.getPassword()).toString());
				user.setRegDate(new Date());
				user.setIsDisable(false);
				tUserDao.saveorupdateTUser(user);
				json = JSONObject.fromObject(new ResultInfo("0", null, "注册成功"));
			}
		}
		return json;
	}

	public TUser normalLogin(TUser user) throws Exception {
		// TODO Auto-generated method stub
		return tUserDao.normalLogin(user);
	}

	public TUser thirdPartyLogin(TUser user) throws Exception {
		// TODO Auto-generated method stub
		TUser loginUser = tUserDao.thirdPartyLogin(user);
		if (loginUser == null) {
			user.setIsDisable(false);
			tUserDao.saveorupdateTUser(user);
			loginUser = tUserDao.thirdPartyLogin(user);
		}
		return loginUser;
	}

	public TUser getUserByCommunicationId(String communicationId)
			throws Exception {
		// TODO Auto-generated method stub
		return tUserDao.getUserByCommunicationId(communicationId);
	}

	public void updateUserProperty(String userId, String upName, String upVlaue)
			throws Exception {
		// TODO Auto-generated method stub
		
		tUserDao.updateUserProperty(userId, upName, upVlaue);
		
	}

	public TUser getUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return tUserDao.getUserByEmail(email);
	}

}
