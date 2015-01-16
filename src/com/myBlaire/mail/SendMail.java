package com.myBlaire.mail;

public class SendMail {

	public static void sendEmail(String email,String newPwd){
		Mail mail=new Mail();
		mail.setSmtpHost("smtp.163.com");/** 设置SMTP **/
		String mailFrom="myblaire@163.com";
		String password="mb12345";
		mail.setFrom(mailFrom);
		mail.setSmtpAuthentication(mailFrom, password);/** 账号及密码 **/
		mail.setTo(email);/** 发送给谁 **/
		mail.setContentType(Mail.MODE_HTML);
		
		mail.setSubject("找回密码");/** 邮件主题 **/
		mail.setBody("您的新密码是:"+newPwd);/** 邮件内容 **/
		mail.send();
	}
}
