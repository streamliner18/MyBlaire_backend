package com.myBlaire.interceptor;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;


import com.myBlaire.action.SysLoginAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 该拦截器用于验证用户是否登录，如果没有登录则会跳转到登录页
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class CheckUserInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ServletContext application = ServletActionContext.getServletContext();
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Object user = session.getAttribute("loginuser");
		response.setContentType("text/html; charset=UTF-8");

		// 如果是登录则跳过拦截器
		String result = null;
		Object action = invocation.getAction();
		/*
		 * System.out.println("action class============>actionName=" + action.getClass().getName());
		 * System.out.println("method=" + invocation.getProxy().getMethod());
		 */
		// System.out.println("args=" + invocation.in);
		/*
		 * Map<String, Object> params = invocation.getInvocationContext().getParameters();
		 * if (params != null && params.size() > 0) {
		 * for (Map.Entry<String, Object> param : params.entrySet()) {
		 * System.out.println("key=" + param.getKey() + ", value=" + param.getValue());
		 * }
		 * } else {
		 * System.out.println("params为空");
		 * }
		 */
		
		if (action instanceof SysLoginAction) {
			return invocation.invoke();
		}
		if (user == null) {

			response.getWriter().println(
					"<script>alert('您还没有登录！请登录后继续!');" + " window.top.location.href='"
							+ ServletActionContext.getRequest().getContextPath() + "/login.jsp';</script>");
			response.getWriter().close();
			return result;
		} else {
			result = invocation.invoke();
		}
		return result;
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

}
