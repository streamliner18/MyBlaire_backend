package com.myBlaire.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.myBlaire.util.ResultInfo;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;
	protected int begin;

	/**
	 * 设置request session application
	 */
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		try {
			this.request.setCharacterEncoding("UTF-8");
			this.session = request.getSession();
			this.application = this.session.getServletContext();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 设置 response
	 */
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
		response.setContentType("Text/html;charset=UTF-8");
	}

	/**
	 * 请求转发
	 * 
	 * @param url
	 *            地址
	 * */
	public void forward(String url) {
		try {
			this.request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重定向
	 * 
	 * @param url
	 *            地址
	 * */
	public void redirect(String url) {
		try {
			response.sendRedirect(request.getContextPath() + "/" + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public String getIpAddr() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 返回标准错误json对象
	 * <p>
	 * method desc:
	 * </p>
	 * 
	 * @author ermingzh
	 * @date 2014-8-6
	 */
	public void addResultInfo(ResultInfo info) throws IOException {
		JSONObject jsonObj = JSONObject.fromObject(info);
		response.getWriter().write(jsonObj.toString());
	}

	public void responseString(String str) throws IOException {
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}

	/**
	 * 清除缓存
	 */
	public void flushCache(){
		Cache cache = ServletCacheAdministrator.getInstance(request.getSession().getServletContext()).getCache(request,  
	                PageContext.APPLICATION_SCOPE);  
	    cache.flushAll(new Date());  
	}
}
