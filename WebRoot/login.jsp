<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <title>登陆页面</title>
<!-- 调用CSS，JS -->
<link href="images/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	background-image: url(login1_08.gif);
	background-repeat: repeat-x;
	margin-bottom: 0px;
	font-family: "宋体";
	font-size: 12px;
	line-height: 1.5;
	font-weight: normal;
	color: #546D87;
	background-color: #BBD9F5;
}
-->
</style>

<script>
	function isLogin()
	{
		document.isLongFrom.submit();
	}
	
	function loadMethod()
	{
		var str="${request.msg}";
	
		alert(str);
	}
</script>
</head>
<body <c:if test="${request.msg!=null }" >onload="loadMethod()"</c:if> >


<div style="width:990px;margin:0 auto;">
<table width="990" height="650" border="0" align="left" cellpadding="0" cellspacing="0">
<form method="post" action="login.do" name="isLongFrom">
  <tr>
    <td width="318" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="299" align="right"><img src="images/login1_01.gif" width="318" height="299" /></td>
        </tr>
        <tr>
          <td height="351" align="right"><img src="images/login1_04.gif" width="318" height="351" /></td>
        </tr>
      </table>
    </td>
    <td width="366" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="299" background="images/login_6.gif"><img src="images/login1_02.gif" width="366" height="299" /></td>
        </tr>
        <tr>
          <td height="96" valign="top" background="images/login_9.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="25%" height="25">&nbsp;</td>
                <td width="48%" valign="top">
                  <label>
                  <input name="loginName" type="text" class="txt" size="25" value="${loginName }admin" />
                  </label>
                </td>
                <td width="27%" rowspan="2"><a href="javaScript:isLogin()"><img src="images/login_2.gif" width="57" height="55" border="0" /></a></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>
                  <input name="password" type="password" class="txt" size="25" value="admin" />
                </td>
              </tr>
              
              <tr>
                <td height="36"></td>
                <td><a href="#"><img src="images/login_3.gif" width="77" height="25" border="0" /></a>&nbsp;<a href="#"><img src="images/login_4.gif" width="77" height="25" border="0" /></a></td>
                <td>&nbsp;</td>
                <td></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td height="255" background="images/login1_07.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="129">&nbsp;</td>
              </tr>
              <tr>
                <td align="center">版权所有 &copy; <a href="http://www.865171.cn"></a> 联系QQ:8888888</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
    <td width="318" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="299" align="left" background="images/login1_03.gif">&nbsp;</td>
        </tr>
        <tr>
          <td height="351" align="left" background="images/login1_06.gif">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</div>

</body>
</html>
