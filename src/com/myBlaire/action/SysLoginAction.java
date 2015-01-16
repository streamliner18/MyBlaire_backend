package com.myBlaire.action;

import com.myBlaire.domain.SysUser;
import com.myBlaire.service.SysUserService;
import com.myBlaire.util.MD5;
import com.opensymphony.xwork2.ModelDriven;

public class SysLoginAction extends BaseAction implements ModelDriven<SysUser> {
	private SysUserService sysUserService;
	private SysUser user = new SysUser();

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysUser getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	/**
	 * 首次访问
	 * 
	 * @return
	 */
	public String firstLoadPage() {
		Object user = session.getAttribute("loginuser");
		if (user != null) {
			request.setAttribute("loginip", getIpAddr());
			return "success";
		}
		return "input";
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		user.setPassword(MD5.GetMD5Code(user.getPassword()).toLowerCase());
		SysUser loginuser = sysUserService.loginUser(user);
		if (loginuser != null) {
			request.setAttribute("loginip", getIpAddr());
			session.setAttribute("loginuser", loginuser);
			return "success";
		}
		request.setAttribute("msg",  "您的用户名或密码错误！！！");

		return "input";
	}

	/**
	 * 验证登录用户
	 * 
	 * @throws Exception
	 */
	public void validateLogin() throws Exception {
		Object login_obj = session.getAttribute("loginuser");
		if (login_obj == null) {
			if ((user.getLoginName() == null || user.getLoginName().equals(""))
					&& (user.getPassword() == null || user.getPassword()
							.equals(""))) {
				this.addFieldError("user", "userError");
				request.setAttribute("msg", "您未输入用户名密码！");

			} else {
				if (!user.getLoginName().matches("^[0-9a-zA-Z]{3,15}$")) {
					this.addFieldError("username", "username error");
					request.setAttribute("msg", "用户名只能为英文字母或数字，且长度在3-15之间！");
				}
				if (user.getPassword().length() < 3
						|| user.getPassword().length() > 15) {
					this.addFieldError("password", "password error");
					request.setAttribute("msg", "密码长度只能为3-15位！");
				}
			}
		}

	}

	/**
	 * 退出登陆
	 * 
	 * @return
	 */
	public String logout() {
		session.removeAttribute("loginuser");
		return "success";
	}

}
