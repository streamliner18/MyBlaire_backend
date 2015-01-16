package com.myBlaire.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.myBlaire.appAction.UserNormalOperationAction;
import com.myBlaire.domain.TUser;
import com.myBlaire.service.TUserService;
import com.myBlaire.util.ResultInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 该拦截器用于验证用户是否登录，如果没有登录则会跳转到登录页
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class AppInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ServletContext application = ServletActionContext.getServletContext();
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=UTF-8");
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(application);
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			Object action = invocation.getAction();
			if (action instanceof UserNormalOperationAction) {
				return invocation.invoke();
			}

			String token = ServletActionContext.getRequest().getHeader("token");

			TUserService userService = (TUserService) ctx
					.getBean("tUserService");
			TUser user = userService.getUserByCommunicationId(token);
			if (token == null || user == null) {
				JSONObject json = JSONObject.fromObject(new ResultInfo("101",
						null, "您账号在别处登录，需要重新登录"));
				response.getWriter().print(json);
				return null;

			}

			// 查看用户是否被禁用
			if (user.getIsDisable()) {
				JSONObject json = JSONObject.fromObject(new ResultInfo("101",
						null, "该用户已被停用,请联系客服"));
				response.getWriter().print(json);
				return null;
			}

			session.setAttribute("clientLoginUser", user);

			return invocation.invoke();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JSONObject json = JSONObject.fromObject(new ResultInfo("101", null,
					"系统错误,您需要重新登录"));
			response.getWriter().print(json);
		}
		return null;
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
