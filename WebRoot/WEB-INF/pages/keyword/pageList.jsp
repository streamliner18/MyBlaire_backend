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
<script type="text/javascript">
	function doAdd() {
		//alert("增加...");
		$('#id').remove();
		
		$("#staffForm").form("reset");
		$('#addStaffWindow').window("open");
	}
	function doEdit() {
		var row = $("#grid").datagrid("getSelected")
		if (row == null) {
			$.messager.alert("提示", "修改必须选择一条记录!", "info");
			return;
		}
		$('#id').remove();
		
		
	
		$('<input type="hidden" name="id" id="id"/>').appendTo("#staffForm");
		$("#staffForm").form("load", row);
		if(row.isDisable==true){
			$("select[name='isDisable']").val("true");
		}
			
		$("#addStaffWindow").window("open");
	}
	function doView() {
		alert("查看...");
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
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'wordKey',
		title : '键',
		width : 120,
		align : 'center'
	}, {
		field : 'wordValue',
		title : '值',
		width : 120,
		align : 'center'
	}, {
		field : 'clickNum',
		title : '点击数',
		width : 120,
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
	}] ];

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
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/keyword/getList.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doEdit,
			singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : false
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加关键字',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		$("#save")
			.click(
				function(){
				if ($("#staffForm").form("validate")) {
					var url="${pageContext.request.contextPath}/keyword/saveKeyWord.edit";
					
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
							error:function(XMLHttpRequest,textStatus,errorThrown){}
							});
	
					}
				});

	});

	function doDblClickRow(rowIndex, rowData) {
		//修改
		$("#staffForm").form("load", rowData);
		$('#addStaffWindow').window("open");
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
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
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>键</td>
						<td><input type="text" name="wordKey" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>值</td>
						<td><input type="text" name="wordValue" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>点击数</td>
						<td><input type="text" name="clickNum" class="easyui-validatebox" required="true" value="0" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>是否禁用</td>
						<td>
							<select name="isDisable"  class="easyui-validatebox"
									required="true">
									<option value="false">
										否
									</option>
									<option value="true">
										是
									</option>
								</select>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
</body>
</html>
