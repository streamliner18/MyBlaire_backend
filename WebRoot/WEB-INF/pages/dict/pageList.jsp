<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>字典管理</title>
		<!-- 导入jquery核心类库 -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
		<!-- 导入easyui类库 -->
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/css/default.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
		<script
			src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
			type="text/javascript"></script>
		<script type="text/javascript">
	var dictType=$.ajax({
		     type: "post",
		     url: "${pageContext.request.contextPath}/json/dictType.json",
		     async: false
		 }).responseText;

		


	$(function(){
		/**/
		$("#sdictType").combobox(
			{
			panelWidth:"auto",
			panelHeight:"auto",
			editable:false,
			valueField:"dictType",
			textField:"value",
			data:eval("("+dictType+")"),
			onSelect:function(record){
				var arr  = record.value.split("-");
				var path  = arr[arr.length-1];

				$("#sdictType").combobox("setValue",record.dictType);
				$("#sdictType").combobox("setText",record.value);
				$("#sdictType").val(record.dictType);
			}
			
			});
	
	});
	function doAdd() {
		//alert("增加...");
		$('#dictId').remove();
		
		$("#staffForm").form("reset");
		$('#addStaffWindow').window("open");
	}
	function doEdit() {
		var row = $("#grid").datagrid("getSelected")
		if (row == null) {
			$.messager.alert("提示", "修改必须选择一条记录!", "info");
			return;
		}
		$('#dictId').remove();
		
		$('<input type="hidden" name="dictId" id="dictId"/>').appendTo("#staffForm");
		$("#staffForm").form("load", row);
		
		if(row.dictDisabled==true){
			$("select[name='dictDisabled']").val("true");
		}
		$("#addStaffWindow").window("open");
	}
	function doView() {
			var json=
			{
				dictType:$("#sdictType").combobox("getValue"),
			};
		$("#grid").datagrid('load',json);
	}

	function clearFind(){
		$("#sdictType").combobox("setValue","");
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
	
	function doDisabled(){
		var rows = $("#grid").datagrid("getChecked");
		
		if (rows.length == 0) {
			$.messager.alert("提示", "请选择要禁用的数据!", "info");
			return;
		}
		$.messager.confirm("操作提示","您确定要执行操作吗？",function(data){
			if(data){
				var items=$("input[name='dictId']:checked");
				var str="dictId=";
				for( var i=0;i<items.length;i++){
					str+="'"+items[i].value+"'";
					if(i!=items.length-1){
						str+=',';
					}
				}
				
				
				str+="&type=disabled";
				var url="${pageContext.request.contextPath}/dict/editDict.edit";
				dopost(url,str);
						
			}else{}
		});
		 $("#grid").datagrid("clearSelections");
		
	}
	
	function doEnable(){
		var rows = $("#grid").datagrid("getChecked");
		
		if (rows.length == 0) {
			$.messager.alert("提示", "请选择要启用的数据!", "info");
			return;
		}
		$.messager.confirm("操作提示","您确定要执行操作吗？",function(data){
			if(data){
				var items=$("input[name='dictId']:checked");
				var str="dictId=";
				for( var i=0;i<items.length;i++){
					str+="'"+items[i].value+"'";
					if(i!=items.length-1){
						str+=',';
					}
				}
				
				str+="&type=enable";
				var url="${pageContext.request.contextPath}/dict/editDict.edit";
				dopost(url,str);		
			}else{}
		});
	} 
	
	function dopost(url,datastr){
		$.ajax(
			{
				url:url,
				type:"post",
				data:datastr,
				dataType:"json",
				success:function(data){
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
		field : 'dictId',
		checkbox : true,
	}, {
		field : 'dictType',
		title : '类型',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == 'color') {
				return "颜色";
			} else {
				return "未知";
			}
		}
	}, {
		field : 'dictKey',
		title : '键',
		width : 120,
		align : 'center'
	}
	, {
		field : 'dictValue',
		title : '值',
		width : 120,
		align : 'center'
	}, {
		field : 'dictDisabled',
		title : '是否禁用',
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
		field : 'colOrder',
		title : '分组',
		width : 120,
		align : 'center'
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
			url : "${pageContext.request.contextPath}/dict/getList.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doEdit,
			singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : false,
			
		});

		// 添加字典数据
		$('#addStaffWindow').window({
			title : '字典数据',
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
					var url="${pageContext.request.contextPath}/dict/saveDict.edit";
	
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

		<div id="searchtool" style="padding: 5px">
			<span>类型:</span>
			<input id="sdictType" class="easyui-combobox easyui-validatebox"
				name="sdictType" data-options="panelHeight:'auto'" />
			<a onclick="javascript:doView();" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'">查询</a>
			<a onclick="javascript:clearFind()" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'">清除查询</a>
			<a onclick="javascript:doAdd()" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'">添加</a>
			<a onclick="javascript:doEdit()" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'">修改</a>
			<a onclick="javascript:doEnable()" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">启用</a>
			<a onclick="javascript:doDisabled()" class="easyui-linkbutton"
				data-options="iconCls:'icon-no'">禁用</a>
		</div>


		<form id="delTagFrom"
			action="${pageContext.request.contextPath}/staff_updateDelTag.do"
			method="post">
			<div region="center" border="false">
				<input id="delTag" name="delTag" type="hidden" value="1" />
				<table id="grid"></table>
			</div>
		</form>
		<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow"
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
					action="${pageContext.request.contextPath}/staff_save.do"
					method="post">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">
								字典数据
							</td>
						</tr>
						<!-- TODO 这里完善收派员添加 table -->
						<tr>
							<td>
								类型
							</td>
							<td>
								<select name="dictType" class="easyui-validatebox"
									required="true">
									<option value="color">
										颜色
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								键
							</td>
							<td>
								<input type="text" name="dictKey" class="easyui-validatebox"
									required="true" />
							</td>
						</tr>
						<tr>
							<td>
								值
							</td>
							<td>
								<input type="text" name="dictValue" class="easyui-validatebox"
									required="true" />
							</td>
						</tr>
						<tr>
							<td>
								是否禁用
							</td>
							<td>
								<select name="dictDisabled" class="easyui-validatebox"
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
						<tr>
							<td>
								分组
							</td>
							<td>
								<input type="text" name="colOrder" class="easyui-validatebox"
									required="true" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
