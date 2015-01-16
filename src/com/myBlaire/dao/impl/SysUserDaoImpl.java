package com.myBlaire.dao.impl;

import java.util.List;

import com.myBlaire.dao.BaseDao;
import com.myBlaire.dao.SysUserDao;
import com.myBlaire.domain.SysUser;

public class SysUserDaoImpl implements SysUserDao {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public SysUser loginUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		String hql="from SysUser where loginName=? and password=?";
		List<SysUser> list=baseDao.findAllByHQL(hql,new Object[]{user.getLoginName(),user.getPassword()});
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
