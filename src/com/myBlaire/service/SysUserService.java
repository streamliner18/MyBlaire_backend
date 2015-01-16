package com.myBlaire.service;

import com.myBlaire.domain.SysUser;

public interface SysUserService {

	/**
	 * 登录用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public SysUser loginUser(SysUser user)throws Exception;
}
