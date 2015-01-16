package com.myBlaire.service.impl;

import com.myBlaire.dao.SysUserDao;
import com.myBlaire.domain.SysUser;

public class SysUserServiceImpl implements com.myBlaire.service.SysUserService {
	private SysUserDao sysUserDao;
	
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public SysUser loginUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		return sysUserDao.loginUser(user);
	}

}
