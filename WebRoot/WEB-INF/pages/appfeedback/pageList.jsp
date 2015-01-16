<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>金百万-金饭碗-app吐槽回复管理</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/validate/jquery.validatebox.extends.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		$('#labelId').remove();
		$("#staffForm").form("reset");
		$('#addStaffWindow').window("open");
	}
	function doEdit(){
		$("#labelId").remove();
		var row=$("#grid").datagrid("getSelected");
		if(row==null){
			$.messager.alert("提示","修改必须选择一条记录!","info");
			return;
		}
		$('<input type="hidden" name="labelId" id="labelId"/>').appendTo(
			"#staffForm");
		$("#staffForm").form("load",row);
		$("#addStaffWindow").window("open");
	}
	function doView(){
		$("#fm").form("reset");
		$('#dlg').window("open");
	}
	function doDel(){
		var row=$("#grid").datagrid("getSelected");
		if(row==null){
			$.messager.alert("提示","必须选择一条记录!","info");
			return;
		}
		$.messager.confirm("操作提示","您确定要执行操作吗？",function(data){
			if(data){
				$("#delFrom").form("load",row);
				$("#delFrom").submit();
			}
		});
	}
	//工具栏
	var toolbar=[
		{
		id:'button-view',
		text:'查询',
		iconCls:'icon-search',
		handler:doView
		} ];
	//暂时不设置
	toolbar = null;
	// 定义列
	var columns=[[
		{
		field:'id',
		hidden:true
		},
		{
			field:'userName',
			title:'吐槽用户',
			width:120,
			align:'center'
			},
		{
		field:'content',
		title:'吐槽内容',
		width:220,
		align:'left'
		},
		{
		field:'clientVersion',
		title:'客户端版本',
		width:120,
		align:'center'
		},
		{
		field:'appVeraion',
		title:'app版本',
		width:120,
		align:'center'
		},
		
		{
		field:'feedbackTime',
		title:'吐槽时间',
		width:160,
		align:'center'
		} ] ];
	$(function(){
		var msg="${param.msg}";
		if(msg!=""){
			if(msg=="2"){
				$.messager.alert("提示","修改失败","info");
			}
		}
		$("body").css(
			{
				visibility:"visible"
			});
		$("#querySubmit").click(function(){
			if($("#fm").form("validate")){
				$('#grid').datagrid('load',
					{
					name:$('#nameId').val(),
					status:$('#statusId').val()
					});
				$('#dlg').dialog('close');
			}
		});
		// 内部用户信息表格
		$('#grid')
			.datagrid(
					{
					iconCls:'icon-forward',
					fit:true,
					border:false,
					rownumbers:true,
					striped:true,
					pageList:[30,50,100 ],
					pageSize:30,
					pagination:true,
					//toolbar:"#searchtool",
					url:"${pageContext.request.contextPath}/feedback/getList.action",
					idField:'id',
					columns:columns,
					singleSelect:true,
					checkOnSelect:false,
					selectOnCheck:false
					});
		// 添加内部用户信息窗口
		$('#addStaffWindow').window(
			{
			title:'标签信息',
			width:400,
			modal:true,
			shadow:true,
			closed:true,
			height:400,
			resizable:false
			});
		$('#dlg').window(
			{
			title:'标签信息',
			shadow:true
			});
		//添加用户的保存按钮事件，提交表单
		$("#save").click(function(){
			if($("#staffForm").form("validate")){
				$("#staffForm").submit();
			}
		});
		$("#queryButton").click(function(){
			$('#dlg').dialog('close');
		});
	});
	function doDblClickRow(rowIndex,rowData){
		doEdit();
	}
	
	function findData(){
        $('#grid').datagrid('load',{  
        	clientVersion:$('#clientVersion').val(),  
        	appVeraion:$('#appVersion').val(),
        });
    }
	
	function clearFind(){
		$('#clientVersion').val('');
	    $('#appVersion').val('');
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">

	<div region="center" border="false">
		<table id="grid"></table>
	</div>

	<div class="easyui-window" title="修改" id="addStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
			</div>
		</div>
		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="staffForm"
				action="${pageContext.request.contextPath}/sysConf/appLabelSave.save"
				method="post">

				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">标签信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>标签名称</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							required="true" validType="CHS" /></td>
					</tr>
					<tr>
						<td>标签状态</td>
						<td><select name="status" class="easyui-validatebox">
								<option value="true">启用</option>
								<option value="false">禁用</option>
						</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:280px;padding:10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">app标签信息查询</div>
		<form id="fm" method="post"
			action="${pageContext.request.contextPath}/sysConf/appLabelPageList.edit">
			<div class="fitem">
				<label>标签名称:</label> <input name="name" class="easyui-textbox"
					id="nameId">
			</div>
			<div class="fitem">
				<label>标签状态:</label>
				<select name="status" class="easyui-validatebox"
					id="statusId">
						<option value="true">启用</option>
						<option value="false">禁用</option>
				</select>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a class="easyui-linkbutton c6" id="querySubmit" iconCls="icon-ok"
			style="width:90px">提交</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel" id="queryButton"
			style="width:90px">取消</a>
	</div>

	<div id="hiddenDiv" style="display:none;">
		<form name="hidden_form" id="delFrom"
			action="${pageContext.request.contextPath}/sysConf/appLabelDel.edit">
			<input type="hidden" name="labelId" />
		</form>
	</div>
<div id="searchtool" style="padding:5px">  
        <span>客户端版本:</span><input type="text" id="clientVersion" size=18 />  
        <span>app版本:</span><input type="text" id="appVersion" size=18 />  
        <a onclick="javascript:findData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> <a onclick="javascript:clearFind();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">清除查询</a> 
    </div>  
</body>
</html>
