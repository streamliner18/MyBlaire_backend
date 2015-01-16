package com.myBlaire.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.dao.BaseDao;
import com.myBlaire.dao.TUserDao;
import com.myBlaire.domain.TUser;
import com.myBlaire.domain.TUserCollect;
import com.myBlaire.util.DateUtil;
import com.myBlaire.util.PageInfo;

public class TUserDaoImpl implements TUserDao {
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public PageInfo<TUser> getList(TUser user, Integer page, Integer size)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "from TUser where 1=1";
		String count_hql = "select count(*) from TUser where 1=1";
		if (StringUtils.isNotBlank(user.getUserName())) {
			hql += " and userName like '%" + user.getUserName() + "%'";
			count_hql += " and userName like '%" + user.getUserName() + "%'";
		}
		if (StringUtils.isNotBlank(user.getEmail())) {
			hql += " and email like '%" + user.getEmail() + "%'";
			count_hql += " and email like '%" + user.getEmail() + "%'";
		}
		if (user.getRegDate() != null) {
			hql += " and regDate='"
					+ DateUtil.dateConvertYMDHMS(user.getRegDate()) + "'";
			count_hql += " and regDate='"
					+ DateUtil.dateConvertYMDHMS(user.getRegDate()) + "'";
		}
		List count_list = baseDao.findAllByHQL(count_hql);
		List<TUser> rows = baseDao.findAllByHQL(hql);
		Integer total = 0;
		if (count_list.size() > 0) {
			total = Integer.valueOf(count_list.get(0).toString());
		}
		return new PageInfo<TUser>(rows, total);
	}

	public void saveorupdateTUser(TUser user) throws Exception {
		// TODO Auto-generated method stub
		baseDao.saveorupdate(user);
	}

	public List<TUserCollect> queryGoodsByUser(TUser user) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from TUserCollect where TUser=?";

		return baseDao.findAllByHQL(hql, new Object[] { user });
	}

	public TUser getUserByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		List<TUser> userList = baseDao.findAllByProperty(TUser.class,
				"userName", userName);
		if (userList.size() > 0)
			return userList.get(0);
		return null;
	}

	public TUser normalLogin(TUser user) throws Exception {
		// TODO Auto-generated method stub
		List<TUser> userList = baseDao.findAllByProperties(TUser.class,
				new String[] { "userName", "password" }, new Object[] {
						user.getUserName(), user.getPassword() });
		if (userList.size() > 0)
			return userList.get(0);
		return null;
	}

	public TUser thirdPartyLogin(TUser user) throws Exception {
		// TODO Auto-generated method stub
		List<TUser> userList = baseDao.findAllByProperties(TUser.class,
				new String[] { "thirdPartyType", "thirdPartyToken" },
				new Object[] { user.getThirdPartyType(),
						user.getThirdPartyToken() });
		if (userList.size() > 0)
			return userList.get(0);
		return null;
	}

	public TUser getUserByCommunicationId(String communicationId)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "from TUser where communicationId=?";
		List<TUser> userList = baseDao.findAllByHQL(hql,
				new Object[] { communicationId });
		if (userList.size() > 0)
			return userList.get(0);
		return null;
	}

	public void updateUserProperty(String userId, String upName, String upVlaue)throws Exception {
		// TODO Auto-generated method stub
		String hql="update TUser set "+upName+"=?  where  userId=?";
		baseDao.update(hql, new Object[]{upVlaue,userId});
	}

	public TUser getUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		List<TUser> userList = baseDao.findAllByProperty(TUser.class,
				"email", email);
		if (userList.size() > 0)
			return userList.get(0);
		return null;
	}

}
