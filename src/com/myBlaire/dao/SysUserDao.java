package com.myBlaire.dao;

import com.myBlaire.domain.SysUser;

/**
 * 后台用户
 * @author long
 *
 */
public interface SysUserDao {
	
	/**
	 * 登录用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public SysUser loginUser(SysUser user)throws Exception;
	
}
