package com.myBlaire.appAction;

import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.myBlaire.action.BaseAction;
import com.myBlaire.domain.TUser;
import com.myBlaire.mail.SendMail;
import com.myBlaire.service.TUserService;
import com.myBlaire.util.MD5;
import com.myBlaire.util.ResultInfo;
import com.myBlaire.util.VerifyCode;
import com.opensymphony.xwork2.ModelDriven;

public class UserNormalOperationAction extends BaseAction implements ModelDriven<TUser>{
	private TUserService tUserService;
	
	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}
	private TUser user=new TUser();
	public TUser getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	/**
	 * 正常登录
	 * @return
	 * @throws Exception
	 */
	public String normalLogin()throws Exception{
		JSONObject json=null;
		if(StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
			json=JSONObject.fromObject(new ResultInfo("1", null, "所需值不能为空"));
		}else{
			user.setPassword(MD5.GetMD5Code(user.getPassword()).toString());
			TUser loginUser=tUserService.normalLogin(user);
			if(loginUser!=null){
				if(loginUser.getIsDisable()){
					json=JSONObject.fromObject(new ResultInfo("1", null, "当前用户已被禁用，请联系管理员"));	
				}else{
					session.setAttribute("clientLoginUser", loginUser);
					String token=UUID.randomUUID().toString();
					tUserService.updateUserProperty(loginUser.getUserId(), "communicationId", token);
					json=JSONObject.fromObject(new ResultInfo("0", JSONObject.fromObject("{\"token\":\""+token+"\"}"), "登录成功"));
				}
				
			}else{
				json=JSONObject.fromObject(new ResultInfo("1", null, "用户名或密码错误"));	
			}
		}
		
		this.responseString(json.toString());
		return null;
	}

	
	/**
	 * 正常注册
	 * @return
	 * @throws Exception
	 */
	public String register()throws Exception{
		JSONObject json=tUserService.register(user);
		this.responseString(json.toString());
		return null;
	}
	
	/**
	 * 第三方登录
	 * @return
	 * @throws Exception
	 */
	public String thirdPartyLogin()throws Exception{
		JSONObject json=null;
		if(StringUtils.isBlank(user.getThirdPartyType()) || StringUtils.isBlank(user.getThirdPartyToken())){
			json=JSONObject.fromObject(new ResultInfo("1", null, "所需值不能为空"));
		}else{
			TUser loginUser=tUserService.thirdPartyLogin(user);
			if(loginUser!=null){
				session.setAttribute("clientLoginUser", loginUser);
				String token=UUID.randomUUID().toString();
				tUserService.updateUserProperty(loginUser.getUserId(), "communicationId", token);
				json=JSONObject.fromObject(new ResultInfo("0", JSONObject.fromObject("{\"token\":\""+token+"\"}"), "登录成功"));
			}else{
				json=JSONObject.fromObject(new ResultInfo("1", null, "授权失败"));
			}
		}
		
		this.responseString(json.toString());
		return null;
	}
	
	
	/**
	 * 找回密码
	 * @return
	 * @throws Exception
	 */
	public String findPWD()throws Exception{
		JSONObject json=null;
		if(StringUtils.isBlank(user.getUserName())){
			json=JSONObject.fromObject(new ResultInfo("1", null, "用户名不能为空"));
		}else{
			TUser oldUser=tUserService.getUserByEmail(user.getEmail());
			if(oldUser!=null){
				String pwd = VerifyCode.generateTextCode(
						VerifyCode.TYPE_NUM_LOWER, 6,
						"0oOilJI1");
				tUserService.updateUserProperty(oldUser.getUserId(), "password", MD5.GetMD5Code(pwd).toLowerCase());
				SendMail.sendEmail(oldUser.getEmail(), pwd);
				json=JSONObject.fromObject(new ResultInfo("0", null, "您的密码已发送至注册邮箱"));
			}else{
				json=JSONObject.fromObject(new ResultInfo("1", null, "用户不存在"));
			}
			
		}
		
		
		
		this.responseString(json.toString());
		return null;
	}
}
