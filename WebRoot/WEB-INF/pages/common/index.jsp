<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蜜包管理系统</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 导入ztree类库 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
	if (window.top != window) {
		top.location.href = window.location.href;
	}
	// 初始化ztree菜单
	$(function() {
		var setting = {
			data : {
				simpleData : { // 简单数据 
					enable: true,
					pIdKey: "pid"
				}
			},
			callback : {
				onClick : onClick
			}
		};

		var setting2 = {
				data : {
					simpleData : { // 简单数据 
						enable: true,
						pIdKey: "pid"
					}
				},
				callback : {
					onClick : onClick
				}
			};
		
		// 基本功能菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/json/menu.json',
			type : 'GET',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#treeMenu"), setting2, zNodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});



		// 系统管理菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/json/admin.json',
			type : 'GET',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#adminMenu"), setting, zNodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});

		// 页面加载后 右下角 弹出窗口
		/**************/
		window
				.setTimeout(
						function() {
							$.messager
									.show({
										title : "消息提示",
										msg : '欢迎登录，==${sessionScope.loginuser.userName }！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
										timeout : 5000
									});
						}, 3000);
		/*************/

		$("#btnCancel").click(function() {
			$('#editPwdWindow').window('close');
		});

		$("#btnEp").click(function() {
			var newPass = $("#txtNewPass").val().trim();
			var RePass = $("#txtRePass").val().trim();

			if (newPass == null || newPass.length == "") {
				$.messager.alert("警告", "请输入密码!", "warning");
				$("#txtNewPass").focus();
				return;
			}
			if (newPass != RePass) {
				$.messager.alert("警告", "两次密码输入不一致!", "warning");
				$("#txtNewPass").focus();
				return;
			}
			$.post("${pageContext.request.contextPath}/updatePassword.do", {
				'password' : newPass
			}, function(data) {
				if (data.success) {
					$.messager.alert("系统提示", data.msg, "info");
				} else {
					$.messager.alert("错误", data.msg, "error");
				}
				$('#editPwdWindow').window('close');

			});
		});
	});

	function onClick(event, treeId, treeNode, clickFlag) {
		// 判断树菜单节点是否含有 page属性
		if (treeNode.page != undefined && treeNode.page != "") {
			if ($("#tabs").tabs('exists', treeNode.name)) {// 判断tab是否存在
				$('#tabs').tabs('select', treeNode.name); // 切换tab
			} else {
				// 开启一个新的tab页面
				var content = '<div style="width:100%;height:100%;overflow:hidden;">'
						+ '<iframe src="'
						+ treeNode.page
						+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';

				$('#tabs').tabs('add', {
					title : treeNode.name,
					content : content,
					closable : true
				});
				
				tabClose();
    			tabCloseEven();
			}
		}
	}

	/*******顶部特效 *******/
	/**
	 * 更换EasyUI主题的方法
	 * @param themeName
	 * 主题名称
	 */
	changeTheme = function(themeName) {
		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/'
				+ themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);
		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for (var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			}
		}
	};
	// 退出登录
	/* 	function logoutFun() {
	 $.messager
	 .confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
	 if (isConfirm) {
	 location.href = '${pageContext.request.contextPath }/login.jsp';
	 }
	 });
	 } */

	function logoutFun() {
		$.messager.confirm("系统提示", "您确认要退出系统吗?", function(isConfirm) {
			if (isConfirm) {
				location.href = "${pageContext.request.contextPath}/logout.edit";
			}
		});
	}

	// 修改密码
	function editPassword() {
		$('#editPwdWindow').window('open');
	}
	// 版权信息
	function showAbout() {
		$.messager.alert("金饭碗 v1.0",
				"设计:<br/> 管理员邮箱:  <br/> QQ:");
	}
	
	function upPwdFromSubmit()
	{
		$("#upPwdFrom").submit();
	}
	
function tabClose()
{
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children("span").text();
        if(subtitle!="消息中心"){
       		 $('#tabs').tabs('close',subtitle);
       		}
    })

    $(".tabs-inner").bind('contextmenu',function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY,
        });
        
        var subtitle =$(this).children("span").text();
        $('#mm').data("currtab",subtitle);
        
        return false;
    });
}

//绑定右键菜单事件
function tabCloseEven()
{
    //关闭当前
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        if(currtab_title!='消息中心'){
        	 $('#tabs').tabs('close',currtab_title);
        }
       
    })
    //全部关闭
    $('#mm-tabcloseall').click(function(){
        $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t!="消息中心"){
           	 	$('#tabs').tabs('close',t);
            }
            
        });    
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t!=currtab_title)
            	if(t!="消息中心"){
                	$('#tabs').tabs('close',t);
                }
        });    
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function(){
        var nextall = $('.tabs-selected').nextAll();
       // alert(nextall.length);
        if(nextall.length==0){
            //msgShow('系统提示','后边没有啦~~','error');
           // alert('后边没有啦~~');
            //$.messager.alert('操作提示','后边没有啦~~','info'); 
            return false;
        }
        nextall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            	if(t!="消息中心"){	
            		$('#tabs').tabs('close',t);
            	}
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        if(prevall.length==0){
            //alert('到头了，前边没有啦~~');
            //$.messager.alert('操作提示','到头了，前边没有啦~~','info');   
            return false;
        }
        prevall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            if(t!="消息中心"){
           		 $('#tabs').tabs('close',t);
           	}
        });
        return false;
    });
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:80px;padding:10px;background:url('${pageContext.request.contextPath }/images/header_bg.png') no-repeat right;">
		<div>
			<img src="${pageContext.request.contextPath }/images/logo.png" border="0">
		</div>
		<div id="sessionInfoDiv" style="position: absolute; right: 5px; top: 10px;">
			[<strong>==${sessionScope.loginuser.userName }==</strong>]，欢迎你！您使用[<strong>${requestScope.loginip }</strong>]IP登录！
		</div>
		<div style="position: absolute; right: 5px; bottom: 10px;">
			<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();">修改密码</div>
			<div onclick="showAbout();">联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();">退出系统</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'" style="width: 200px">
		<div class="easyui-accordion" fit="true" border="false">
			<div title="基本功能" data-options="iconCls:'icon-mini-add'" style="overflow: auto">
				<ul id="treeMenu" class="ztree"></ul>
			</div>
			<!--  <div title="系统管理" data-options="iconCls:'icon-mini-add'" style="overflow: auto">
				<ul id="adminMenu" class="ztree"></ul>
			</div>-->
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" fit="true" class="easyui-tabs" border="false">
			<div title="消息中心" id="subWarp" style="width: 100%; height: 100%; overflow: hidden">
				<iframe src="${pageContext.request.contextPath }/page?module=common&resource=home" style="width: 100%; height: 100%; border: 0;"></iframe>
				<%--				这里显示公告栏、预警信息和代办事宜--%>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:50px;padding:10px;background:url('${pageContext.request.contextPath }/images/header_bg.png') no-repeat right;">
		<table style="width: 100%;">
			<tbody>
				<tr>
					<td style="width: 300px;">
						<div style="color: #999; font-size: 8pt;">
							蜜包 | Powered by <a href="">蜜包</a>
						</div>
					</td>
					<td style="width: *;" class="co1"><span id="online" style="background: url(${pageContext.request.contextPath }/images/online.png) no-repeat left;padding-left:18px;margin-left:3px;font-size:8pt;color:#005590;">在线人数:1</span></td>
				</tr>
			</tbody>
		</table>
	</div>

	<!--修改密码窗口-->
	<div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false" maximizable="false" icon="icon-save" style="width: 300px; height: 160px; padding: 5px; background: #fafafa">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
			<form id="upPwdFrom" action="${pageContext.request.contextPath}/role/upPwd.edit" method="post" >
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" name="password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" name="password1" class="txt01" /></td>
					</tr>
				</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:upPwdFromSubmit()">确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>
	
<div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        
</div>
</body>
</html>