<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/validate/jquery.validatebox.extends.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath }/js/easyui/plugins/jquery.datagrid-detailview.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function getCurrTime(){
		var d = new Date();
		var vYear = d.getFullYear();
		var vMon = d.getMonth() + 1;
		var vDay = d.getDate();
		var h = d.getHours(); 
		var m = d.getMinutes(); 
		var se = d.getSeconds(); 
		
		return vYear+"-"+vMon+"-"+vDay+" "+h+":"+m+":"+se;
	}
	
	function doAdd() {
		//alert("增加...");
		$('#userId').remove();
		$('#deviceType').remove();
		$('#deviceToken').remove();
		$('#thirdPartyType').remove();
		$('#thirdPartyToken').remove();
		
		
		$("#staffForm").form("reset");
		
		$('input[name="regDate"]').val(getCurrTime());
		$('#addStaffWindow').window("open");
	}
	function doEdit() {
		var row = $("#grid").datagrid("getSelected")
		if (row == null) {
			$.messager.alert("提示", "修改必须选择一条记录!", "info");
			return;
		}
		$('#userId').remove();
		$('#deviceType').remove();
		$('#deviceToken').remove();
		$('#thirdPartyType').remove();
		$('#thirdPartyToken').remove();
		
		$('<input type="hidden" name="userId" id="userId"/>').appendTo("#staffForm");
		$('<input type="hidden" name="deviceType" id="deviceType"/>').appendTo("#staffForm");
		$('<input type="hidden" name="deviceToken" id="deviceToken"/>').appendTo("#staffForm");
		$('<input type="hidden" name="thirdPartyType" id="thirdPartyType"/>').appendTo("#staffForm");
		$('<input type="hidden" name="thirdPartyToken" id="thirdPartyToken"/>').appendTo("#staffForm");

		$("#staffForm").form("load", row);
		if(row.isDisable==true){
			$("select[name='isDisable']").val("true");
		}
		$("#addStaffWindow").window("open");
	}
	function doView() {
		var json=
		{
			userName:$("#sUserName").val(),
			regDate:$('input[name="sRegDate"]').val(),
			email:$("#sEmail").val()
		};
		$("#grid").datagrid('load',json);
	}

	function doDelete() {
		var rows = $("#grid").datagrid("getChecked");
		if (rows.length == 0) {
			$.messager.alert("提示", "请选择要作废的数据!", "info");
			return;
		}
	
		$("#delTag").val("1");
		$("#delTagFrom").submit();
	}

	function doRestore() {
		var rows = $("#grid").datagrid("getChecked");
		if (rows.length == 0) {
			$.messager.alert("提示", "请选择要还原的数据!", "info");
			return;
		}
		$("#delTag").val("0");
		$("#delTagFrom").submit();
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	} ];
	// 定义列
	var columns = [ [ {
		field : 'userId',
		hidden:true
	}, {
		field : 'userName',
		title : '用户名称',
		width : 200,
		align : 'center'
	}, {
		field : 'password',
		title : '密码',
		width : 120,
		align : 'center',
		hidden: true
	}, {
		field : 'email',
		title : '邮箱',
		width : 200,
		align : 'center'
	},{
		field : 'regDate',
		title : '注册时间',
		width : 200,
		align : 'center'
	}, {
		field : 'isDisable',
		title : '禁用',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == true) {
				return "是";
			} else {
				return "否";
			}
		}
	}, {
		field : 'deviceType',
		title : '登录类型',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "IOS") {
				return "苹果";
			} else if(data=="android") {
				return "安卓";
			}else{
				return "未知";
			}
		}
	}, {
		field : 'deviceToken',
		title : '登录token',
		width : 120,
		align : 'center',
		hidden: true
	}, {
		field : 'thirdPartyType',
		title : '第三方登录类型',
		width : 200,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "QQ") {
				return "QQ";
			} else if(data=="weibo") {
				return "微博";
			}else{
				return "未知";
			}
		}
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList : [ 30, 50, 100 ],
			pageSize : 30,
			pagination : true,
			toolbar : '#searchtool',
			url : "${pageContext.request.contextPath}/user/getList.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doEdit,
			singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : false,
			view:detailview,
			detailFormatter:function(index,row){
						return '<div style="padding:2px"><table class="ddv"></table></div>';
					},
			onExpandRow:function(index,row){
						var ddv=$(this).datagrid('getRowDetail',index).find('table.ddv');
						ddv
							.datagrid(
								{
								url:"${pageContext.request.contextPath}/user/getCollectList.action?userId="
									+row.userId,
								fitColumns:true,
								singleSelect:true,
								rownumbers:true,
								height:'auto',
								columns:[[
									{
									field:'goodName',
									title:'商品名称',
									width:50,
									align:'center'
									},
									{
									field:'smallPicture',
									title:'图片',
									width:60,
									align:'center',
									formatter:function(value,row,index){return '<img src="${pageContext.request.contextPath }/upload/'+row.smallPicture+'" style="height:50px;"/>';}
									},
									{
									field:'goodDesc',
									title:'备注',
									width:50,
									align:'center'
									} ] ],
								onResize:function(){
									$('#grid').datagrid('fixDetailRowHeight',index);
								},
								onLoadSuccess:function(){
									setTimeout(function(){
										$('#grid').datagrid('fixDetailRowHeight',index);
									},0);
								}
								});
						$('#grid').datagrid('fixDetailRowHeight',index);
					}
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加取派员',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		//保存	
		$("#save")
			.click(
				function(){
				if ($("#staffForm").form("validate")) {
					var url="${pageContext.request.contextPath}/user/saveUser.edit";
	
						$.ajax(
							{
							url:url,
							type:"post",
							data:$("#staffForm").serialize(),
							dataType:"json",
							success:function(data){
								$("#addStaffWindow").window("close");
								//储值成功才会刷新
								if(data.code=="0"){
									$("#grid").datagrid("reload");
								}
								$.messager.show(
									{
									title:'提示',
									msg:data.message,
									timeout:2200,
									showType:'slide',
									style:
										{
											top:document.body.scrollTop
												+document.documentElement.scrollTop,
										}
									});
							},
							error:function(XMLHttpRequest,textStatus,errorThrown){
									$.messager.show(
									{
									title:'提示',
									msg:"保存失败",
									timeout:2200,
									showType:'slide',
									style:
										{
											top:document.body.scrollTop
												+document.documentElement.scrollTop,
										}
									});
							
								}
							});
	
					}
				});

	});

	function doDblClickRow(rowIndex, rowData) {
		//修改
		$("#staffForm").form("load", rowData);
		$('#addStaffWindow').window("open");
	}
	
	$(function () {
    	 $("#sRegDate").datetimebox({ formatter: formatDate,showSeconds:true });
   
     //设置
  
	    $("input.combo-text").attr("readonly","readonly");
	    
	});
	
	function formatDate(date) {
	    return date.getFullYear() + "-" + (date.getMonth() +1) + "-" + date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
	
	function clearFind(){
		$("#sUserName").val("");
		$("#sEmail").val("");
		$('#sRegDate').datebox('setValue', '');

	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
		<div id="searchtool" style="padding: 5px">
			<span>用户名:</span>
			<input id="sUserName" name="sUserName" data-options="panelHeight:'auto'" />
			<span>邮箱:</span>
			<input id="sEmail" name="sEmail" data-options="panelHeight:'auto'" />
			<span>注册时间:</span>
			<input id="sRegDate" name="sRegDate" data-options="panelHeight:'auto'" />
				
			<a onclick="javascript:doView();" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'">查询</a>
			<a onclick="javascript:clearFind()" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'">清除查询</a>
			<a onclick="javascript:doAdd()" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'">添加</a>
			<a onclick="javascript:doEdit()" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'">修改</a>
			
		</div>


	<form id="delTagFrom" action="${pageContext.request.contextPath}/staff_updateDelTag.do" method="post">
		<div region="center" border="false">
			<input id="delTag" name="delTag" type="hidden" value="1" />
			<table id="grid"></table>
		</div>
	</form>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;" border="false">
			<form id="staffForm" action="${pageContext.request.contextPath}/staff_save.do" method="post">
				
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">用户信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>用户名</td>
						<td><input type="text" name="userName" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" name="password" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input type="text" name="email" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>注册时间</td>
						<td><input type="text" name="regDate" class="easyui-validatebox" required="true" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>禁用</td>
						<td>
							<select name="isDisable">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
