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

<script src="${pageContext.request.contextPath }/js/jquery.uploadify.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/uploadify.css">
	
<script type="text/javascript">
var colorResult=$.ajax({
		     type: "post",
		     url: "${pageContext.request.contextPath}/goods/colorList.action",
		     async: false
		 }).responseText;

	$(function(){
		/**/
		$("#color").combobox(
			{
			panelWidth:"auto",
			panelHeight:"auto",
			editable:false,
			valueField:"dictKey",
			textField:"dictValue",
			data:eval("("+colorResult+")"),
			onSelect:function(record){
				$("#color").combobox("setValue",record.dictKey);
				$("#color").combobox("setText",record.dictValue);
				$("#color").val(record.dictKey);
				
				
			}
			
		});
	});
	
	function doAdd() {
		//alert("增加...");
		$('#goodId').remove();
		
		$("#staffForm").form("reset");
		$('#addStaffWindow').window("open");
	}
	function doEdit() {
		$('#goodId').remove();
		var row = $("#grid").datagrid("getSelected")
		if (row == null) {
			$.messager.alert("提示", "修改必须选择一条记录!", "info");
			return;
		}
		
		$('<input type="hidden" name="goodId" id="goodId"/>').appendTo("#staffForm");
		$("#staffForm").form("load", row);
		$("#addStaffWindow").window("open");
	}
	function doView() {		
		var json=
		{
			goodName:$("#sGoodName").val(),
			nickName:$('#sNickName').val(),
			beginDate:$('#sBeginDate').datebox('getValue'),
			endDate:$('#sEndDate').datebox('getValue'),
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
		field : 'goodId',
		checkbox : true,
	}, {
		field : 'goodName',
		title : '商品名称',
		width : 120,
		align : 'center'
	}, {
		field : 'nickName',
		title : '昵称',
		width : 120,
		align : 'center'
	},{
		field : 'originalPrice',
		title : '商品原价',
		width : 80,
		align : 'center'
	}, {
		field : 'currentPrice',
		title : '当前价格',
		width : 80,
		align : 'center'
	},{
		field : 'discount',
		title : '折扣',
		width : 40,
		align : 'center'
	},{
		field : 'isShow',
		title : '上架',
		width : 40,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == true) {
				return "是";
			} else {
				return "否";
			}
		}
	},{
		field : 'isStreetShooting',
		title : '明星街拍',
		width : 70,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == true) {
				return "是";
			} else {
				return "否";
			}
		}
	},{
		field : 'collectCount',
		title : '收藏数',
		width : 40,
		align : 'center'
	}
	,{
		field : 'smallPicture',
		title : '列表小图',
		width : 120,
		align : 'center',
		formatter:function(value,row,index){return '<img src="${pageContext.request.contextPath }/upload/'+row.smallPicture+'" style="height:50px;"/>';}
									
	},{
		field : 'streetShooting',
		title : '明星街拍图',
		width : 120,
		align : 'center',
		formatter:function(value,row,index){return '<img src="${pageContext.request.contextPath }/upload/'+row.streetShooting+'" style="height:50px;"/>';}
									
	},{
		field : 'bigPctureUrl',
		title : '大图1',
		width : 120,
		align : 'center',
		formatter:function(value,row,index){return '<img src="${pageContext.request.contextPath }/upload/'+row.bigPctureUrl+'" style="height:50px;"/>';}
									
	},{
		field : 'bigPctureUrl2',
		title : '大图2',
		width : 120,
		align : 'center',
		formatter:function(value,row,index){return '<img src="${pageContext.request.contextPath }/upload/'+row.bigPctureUrl2+'" style="height:50px;"/>';}
									
	},{
		field : 'bigPctureUrl3',
		title : '大图3',
		width : 120,
		align : 'center',
		formatter:function(value,row,index){return '<img src="${pageContext.request.contextPath }/upload/'+row.bigPctureUrl3+'" style="height:50px;"/>';}
									
	},{
		field : 'color',
		title : '颜色',
		width : 80,
		align : 'center',
		formatter:function(value,row,index){
			var json=eval("("+colorResult+")");
			for(var i=0;i<json.length;i++){
				if(json[i].dictKey==value){
					return json[i].dictValue;
				}
			}
			return '未知';
		}								
	},{
		field : 'detailed',
		title : '详细',
		width : 120,
		align : 'center'								
	},{
		field : 'goodDesc',
		title : '描述',
		width : 120,
		align : 'center'								
	},{
		field : 'production',
		title : '产地',
		width : 80,
		align : 'center'								
	},{
		field : 'materialQuality',
		title : '材质',
		width : 80,
		align : 'center'								
	},{
		field : 'buyUrl',
		title : '购买地址',
		width : 200,
		align : 'center'								
	},{
		field : 'addTime',
		title : '添加时间',
		width : 120,
		align : 'center'								
	},{
		field : 'lastUpdateTime',
		title : '修改时间',
		width : 120,
		align : 'center'								
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
			toolbar : '#searchtool',
			url : "${pageContext.request.contextPath}/goods/getList.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow,
			singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : false
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '保存商品信息',
			width : 500,
			modal : true,
			shadow : true,
			closed : true,
			height : 600,
			resizable : false
		});
			
		$("#save")
			.click(
				function(){
				if ($("#staffForm").form("validate")) {
					var url="${pageContext.request.contextPath}/goods/save.edit";
						
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
	
	
		$(function() {
			$("#uploadify_bigPctureUrl").uploadify({
				debug			: false, 

				swf 			: 'js/uploadify.swf',	//swf文件路径
				method			: 'post',	// 提交方式
				uploader		: 'upload.action', // 服务器端处理该上传请求的程序(servlet, struts2-Action)

				preventCaching	: true,		// 加随机数到URL后,防止缓存

				buttonCursor	: 'hand',	// 上传按钮Hover时的鼠标形状
			//	buttonImage		: 'img/.....png',	// 按钮的背景图片,会覆盖文字
				buttonText		: '上传'	, //按钮上显示的文字，默认”SELECTFILES”
				height			: 25	, // 30 px
				width			: 30	, // 120 px

				fileObjName		: 'filedata',	//文件对象名称, 即属性名
				fileSizeLimit	: 1000	,		// 文件大小限制, 100 KB
				fileTypeDesc	: 'any'	,	//文件类型说明 any(*.*)
				fileTypeExts	: '*.jpg;*.jpeg;*.png',		// 允许的文件类型,分号分隔
				formData		: {'id':'1', 'name':'myFile'} , //指定上传文件附带的其他数据。也动态设置。可通过getParameter()获取
				
				multi			: false ,	// 多文件上传
				progressData	: 'speed',	// 进度显示, speed-上传速度,percentage-百分比	
				queueID			: 'fileQueue_bigPctureUrl',//上传队列的DOM元素的ID号
				queueSizeLimit	: 99	,	// 队列长度
				removeCompleted : false	,	// 上传完成后是否删除队列中的对应元素
				removeTimeout	: 10	,	//上传完成后多少秒后删除队列中的进度条, 
				requeueErrors	: true,	// 上传失败后重新加入队列
				uploadLimit		: 20,	// 最多上传文件数量

				successTimeout	: 30	,//表示文件上传完成后等待服务器响应的时间。超过该时间，那么将认为上传成功。
				
				// 在文件被移除出队列时触发	
				onCancel : function(file) { alert( 'The file ' + file.name + ' was cancelled!' ); },
				
				// 在调用cancel方法且传入参数’*’时触发
				onClearQueue : function (queueItemCount) { alert( queueItemCount + ' files were removed from the queue!' ); },

				// 打开文件对话框 关闭时触发
				onDialogClose : function (queueData) {
								/*	alert(
										"文件对话窗口中选了几个文件:" + queueData.filesSelected + "---" +
										"队列中加了几个文件:" + queueData.filesQueued + "---" +
										"队列中被替换掉的文件数:" + queueData.filesReplaced + "---" +
										"取消掉的文件数:" + queueData.filesCancelled + "---" + 
										"上传出错的文件数:" + queueData.filesErrored
									); */
						
								},
				
				// 选择文件对话框打开时触发
				onDialogOpen : function () { /*alert( 'please select files' ) */ },
			
				// 没有兼容的FLASH时触发
				onFallback : function(){ alert( 'Flash was not detected!' ) },
				
				// 每次初始化一个队列时触发, 即浏览文件后, 加入一个队列
				//onInit : function (instance) { alert( 'The queue ID is ' + instance.settings.queueID ) },
			
				// 上传文件处理完成后触发（每一个文件都触发一次）, 无论成功失败
				//onUploadComplete : function(file){ alert( 'The file ' + file.name + ' uploaded finished!' ) },

				// 上传文件失败触发
				onUploadError : function(file, errorCode, errorMsg, errorString){ 
                                    /*
                                    alert(
                                        file.name + ' upload failed! ' + 
                                        'errorCode: ' + errorCode +
                                        'errorMsg:' + errorMsg +
                                        'errorString:' + errorString
                                    );*/
								},
                
                // 在每一个文件上传成功后触发
                onUploadSuccess : function(file, data, response) {
                                   // alert(
                                   //     file.name + ' is uploaded succeed!  ' +
                                   //     '  server-side returned data:' + data +
                                   //     '  response: ' + response
                                   // );
                                $('input[name="bigPctureUrl"]').val(data);
                         }
			});
		});
		$(function() {
			$("#uploadify_bigPctureUrl2").uploadify({
				debug			: false, 

				swf 			: 'js/uploadify.swf',	//swf文件路径
				method			: 'post',	// 提交方式
				uploader		: 'upload.action', // 服务器端处理该上传请求的程序(servlet, struts2-Action)

				preventCaching	: true,		// 加随机数到URL后,防止缓存

				buttonCursor	: 'hand',	// 上传按钮Hover时的鼠标形状
			//	buttonImage		: 'img/.....png',	// 按钮的背景图片,会覆盖文字
				buttonText		: '上传'	, //按钮上显示的文字，默认”SELECTFILES”
				height			: 25	, // 30 px
				width			: 30	, // 120 px

				fileObjName		: 'filedata',	//文件对象名称, 即属性名
				fileSizeLimit	: 1000	,		// 文件大小限制, 100 KB
				fileTypeDesc	: 'any'	,	//文件类型说明 any(*.*)
				fileTypeExts	: '*.jpg;*.jpeg;*.png',		// 允许的文件类型,分号分隔
				formData		: {'id':'1', 'name':'myFile'} , //指定上传文件附带的其他数据。也动态设置。可通过getParameter()获取
				
				multi			: false ,	// 多文件上传
				progressData	: 'speed',	// 进度显示, speed-上传速度,percentage-百分比	
				queueID			: 'fileQueue_bigPctureUrl2',//上传队列的DOM元素的ID号
				queueSizeLimit	: 99	,	// 队列长度
				removeCompleted : false	,	// 上传完成后是否删除队列中的对应元素
				removeTimeout	: 10	,	//上传完成后多少秒后删除队列中的进度条, 
				requeueErrors	: true,	// 上传失败后重新加入队列
				uploadLimit		: 20,	// 最多上传文件数量

				successTimeout	: 30	,//表示文件上传完成后等待服务器响应的时间。超过该时间，那么将认为上传成功。
				
				// 在文件被移除出队列时触发	
				onCancel : function(file) { alert( 'The file ' + file.name + ' was cancelled!' ); },
				
				// 在调用cancel方法且传入参数’*’时触发
				onClearQueue : function (queueItemCount) { alert( queueItemCount + ' files were removed from the queue!' ); },

				// 打开文件对话框 关闭时触发
				onDialogClose : function (queueData) {
								/*	alert(
										"文件对话窗口中选了几个文件:" + queueData.filesSelected + "---" +
										"队列中加了几个文件:" + queueData.filesQueued + "---" +
										"队列中被替换掉的文件数:" + queueData.filesReplaced + "---" +
										"取消掉的文件数:" + queueData.filesCancelled + "---" + 
										"上传出错的文件数:" + queueData.filesErrored
									); */
						
								},
				
				// 选择文件对话框打开时触发
				onDialogOpen : function () { /*alert( 'please select files' ) */ },
			
				// 没有兼容的FLASH时触发
				onFallback : function(){ alert( 'Flash was not detected!' ) },
				
				// 每次初始化一个队列时触发, 即浏览文件后, 加入一个队列
				//onInit : function (instance) { alert( 'The queue ID is ' + instance.settings.queueID ) },
			
				// 上传文件处理完成后触发（每一个文件都触发一次）, 无论成功失败
				//onUploadComplete : function(file){ alert( 'The file ' + file.name + ' uploaded finished!' ) },

				// 上传文件失败触发
				onUploadError : function(file, errorCode, errorMsg, errorString){ 
                                    /*
                                    alert(
                                        file.name + ' upload failed! ' + 
                                        'errorCode: ' + errorCode +
                                        'errorMsg:' + errorMsg +
                                        'errorString:' + errorString
                                    );*/
								},
                
                // 在每一个文件上传成功后触发
                onUploadSuccess : function(file, data, response) {
                                   // alert(
                                   //     file.name + ' is uploaded succeed!  ' +
                                   //     '  server-side returned data:' + data +
                                   //     '  response: ' + response
                                   // );
                                $('input[name="bigPctureUrl2"]').val(data);
                         }
			});
		});
		
		$(function() {
			$("#uploadify_bigPctureUrl3").uploadify({
				debug			: false, 

				swf 			: 'js/uploadify.swf',	//swf文件路径
				method			: 'post',	// 提交方式
				uploader		: 'upload.action', // 服务器端处理该上传请求的程序(servlet, struts2-Action)

				preventCaching	: true,		// 加随机数到URL后,防止缓存

				buttonCursor	: 'hand',	// 上传按钮Hover时的鼠标形状
			//	buttonImage		: 'img/.....png',	// 按钮的背景图片,会覆盖文字
				buttonText		: '上传'	, //按钮上显示的文字，默认”SELECTFILES”
				height			: 25	, // 30 px
				width			: 30	, // 120 px

				fileObjName		: 'filedata',	//文件对象名称, 即属性名
				fileSizeLimit	: 1000	,		// 文件大小限制, 100 KB
				fileTypeDesc	: 'any'	,	//文件类型说明 any(*.*)
				fileTypeExts	: '*.jpg;*.jpeg;*.png',		// 允许的文件类型,分号分隔
				formData		: {'id':'1', 'name':'myFile'} , //指定上传文件附带的其他数据。也动态设置。可通过getParameter()获取
				
				multi			: false ,	// 多文件上传
				progressData	: 'speed',	// 进度显示, speed-上传速度,percentage-百分比	
				queueID			: 'fileQueue_bigPctureUrl3',//上传队列的DOM元素的ID号
				queueSizeLimit	: 99	,	// 队列长度
				removeCompleted : false	,	// 上传完成后是否删除队列中的对应元素
				removeTimeout	: 10	,	//上传完成后多少秒后删除队列中的进度条, 
				requeueErrors	: true,	// 上传失败后重新加入队列
				uploadLimit		: 20,	// 最多上传文件数量

				successTimeout	: 30	,//表示文件上传完成后等待服务器响应的时间。超过该时间，那么将认为上传成功。
				
				// 在文件被移除出队列时触发	
				onCancel : function(file) { alert( 'The file ' + file.name + ' was cancelled!' ); },
				
				// 在调用cancel方法且传入参数’*’时触发
				onClearQueue : function (queueItemCount) { alert( queueItemCount + ' files were removed from the queue!' ); },

				// 打开文件对话框 关闭时触发
				onDialogClose : function (queueData) {
								/*	alert(
										"文件对话窗口中选了几个文件:" + queueData.filesSelected + "---" +
										"队列中加了几个文件:" + queueData.filesQueued + "---" +
										"队列中被替换掉的文件数:" + queueData.filesReplaced + "---" +
										"取消掉的文件数:" + queueData.filesCancelled + "---" + 
										"上传出错的文件数:" + queueData.filesErrored
									); */
						
								},
				
				// 选择文件对话框打开时触发
				onDialogOpen : function () { /*alert( 'please select files' ) */ },
			
				// 没有兼容的FLASH时触发
				onFallback : function(){ alert( 'Flash was not detected!' ) },
				
				// 每次初始化一个队列时触发, 即浏览文件后, 加入一个队列
				//onInit : function (instance) { alert( 'The queue ID is ' + instance.settings.queueID ) },
			
				// 上传文件处理完成后触发（每一个文件都触发一次）, 无论成功失败
				//onUploadComplete : function(file){ alert( 'The file ' + file.name + ' uploaded finished!' ) },

				// 上传文件失败触发
				onUploadError : function(file, errorCode, errorMsg, errorString){ 
                                    /*
                                    alert(
                                        file.name + ' upload failed! ' + 
                                        'errorCode: ' + errorCode +
                                        'errorMsg:' + errorMsg +
                                        'errorString:' + errorString
                                    );*/
								},
                
                // 在每一个文件上传成功后触发
                onUploadSuccess : function(file, data, response) {
                                   // alert(
                                   //     file.name + ' is uploaded succeed!  ' +
                                   //     '  server-side returned data:' + data +
                                   //     '  response: ' + response
                                   // );
                                $('input[name="bigPctureUrl3"]').val(data);
                         }
			});
		});
		
		$(function() {
			$("#uploadify_smallPicture").uploadify({
				debug			: false, 

				swf 			: 'js/uploadify.swf',	//swf文件路径
				method			: 'post',	// 提交方式
				uploader		: 'upload.action', // 服务器端处理该上传请求的程序(servlet, struts2-Action)

				preventCaching	: true,		// 加随机数到URL后,防止缓存

				buttonCursor	: 'hand',	// 上传按钮Hover时的鼠标形状
			//	buttonImage		: 'img/.....png',	// 按钮的背景图片,会覆盖文字
				buttonText		: '上传'	, //按钮上显示的文字，默认”SELECTFILES”
				height			: 25	, // 30 px
				width			: 30	, // 120 px

				fileObjName		: 'filedata',	//文件对象名称, 即属性名
				fileSizeLimit	: 1000	,		// 文件大小限制, 100 KB
				fileTypeDesc	: 'any'	,	//文件类型说明 any(*.*)
				fileTypeExts	: '*.jpg;*.jpeg;*.png',		// 允许的文件类型,分号分隔
				formData		: {'id':'1', 'name':'myFile'} , //指定上传文件附带的其他数据。也动态设置。可通过getParameter()获取
				
				multi			: false ,	// 多文件上传
				progressData	: 'speed',	// 进度显示, speed-上传速度,percentage-百分比	
				queueID			: 'fileQueue_smallPicture',//上传队列的DOM元素的ID号
				queueSizeLimit	: 99	,	// 队列长度
				removeCompleted : false	,	// 上传完成后是否删除队列中的对应元素
				removeTimeout	: 10	,	//上传完成后多少秒后删除队列中的进度条, 
				requeueErrors	: true,	// 上传失败后重新加入队列
				uploadLimit		: 20,	// 最多上传文件数量

				successTimeout	: 30	,//表示文件上传完成后等待服务器响应的时间。超过该时间，那么将认为上传成功。
				
				// 在文件被移除出队列时触发	
				onCancel : function(file) { alert( 'The file ' + file.name + ' was cancelled!' ); },
				
				// 在调用cancel方法且传入参数’*’时触发
				onClearQueue : function (queueItemCount) { alert( queueItemCount + ' files were removed from the queue!' ); },

				// 打开文件对话框 关闭时触发
				onDialogClose : function (queueData) {
								/*	alert(
										"文件对话窗口中选了几个文件:" + queueData.filesSelected + "---" +
										"队列中加了几个文件:" + queueData.filesQueued + "---" +
										"队列中被替换掉的文件数:" + queueData.filesReplaced + "---" +
										"取消掉的文件数:" + queueData.filesCancelled + "---" + 
										"上传出错的文件数:" + queueData.filesErrored
									); */
						
								},
				
				// 选择文件对话框打开时触发
				onDialogOpen : function () { /*alert( 'please select files' ) */ },
			
				// 没有兼容的FLASH时触发
				onFallback : function(){ alert( 'Flash was not detected!' ) },
				
				// 每次初始化一个队列时触发, 即浏览文件后, 加入一个队列
				//onInit : function (instance) { alert( 'The queue ID is ' + instance.settings.queueID ) },
			
				// 上传文件处理完成后触发（每一个文件都触发一次）, 无论成功失败
				//onUploadComplete : function(file){ alert( 'The file ' + file.name + ' uploaded finished!' ) },

				// 上传文件失败触发
				onUploadError : function(file, errorCode, errorMsg, errorString){ 
                                    /*
                                    alert(
                                        file.name + ' upload failed! ' + 
                                        'errorCode: ' + errorCode +
                                        'errorMsg:' + errorMsg +
                                        'errorString:' + errorString
                                    );*/
								},
                
                // 在每一个文件上传成功后触发
                onUploadSuccess : function(file, data, response) {
                                   // alert(
                                   //     file.name + ' is uploaded succeed!  ' +
                                   //     '  server-side returned data:' + data +
                                   //     '  response: ' + response
                                   // );
                                $('input[name="smallPicture"]').val(data);
                         }
			});
		});
		
		$(function() {
			$("#uploadify_streetShooting").uploadify({
				debug			: false, 

				swf 			: 'js/uploadify.swf',	//swf文件路径
				method			: 'post',	// 提交方式
				uploader		: 'upload.action', // 服务器端处理该上传请求的程序(servlet, struts2-Action)

				preventCaching	: true,		// 加随机数到URL后,防止缓存

				buttonCursor	: 'hand',	// 上传按钮Hover时的鼠标形状
			//	buttonImage		: 'img/.....png',	// 按钮的背景图片,会覆盖文字
				buttonText		: '上传'	, //按钮上显示的文字，默认”SELECTFILES”
				height			: 25	, // 30 px
				width			: 30	, // 120 px

				fileObjName		: 'filedata',	//文件对象名称, 即属性名
				fileSizeLimit	: 1000	,		// 文件大小限制, 100 KB
				fileTypeDesc	: 'any'	,	//文件类型说明 any(*.*)
				fileTypeExts	: '*.jpg;*.jpeg;*.png',		// 允许的文件类型,分号分隔
				formData		: {'id':'1', 'name':'myFile'} , //指定上传文件附带的其他数据。也动态设置。可通过getParameter()获取
				
				multi			: false ,	// 多文件上传
				progressData	: 'speed',	// 进度显示, speed-上传速度,percentage-百分比	
				queueID			: 'fileQueue_streetShooting',//上传队列的DOM元素的ID号
				queueSizeLimit	: 99	,	// 队列长度
				removeCompleted : false	,	// 上传完成后是否删除队列中的对应元素
				removeTimeout	: 10	,	//上传完成后多少秒后删除队列中的进度条, 
				requeueErrors	: true,	// 上传失败后重新加入队列
				uploadLimit		: 20,	// 最多上传文件数量

				successTimeout	: 30	,//表示文件上传完成后等待服务器响应的时间。超过该时间，那么将认为上传成功。
				
				// 在文件被移除出队列时触发	
				onCancel : function(file) { alert( 'The file ' + file.name + ' was cancelled!' ); },
				
				// 在调用cancel方法且传入参数’*’时触发
				onClearQueue : function (queueItemCount) { alert( queueItemCount + ' files were removed from the queue!' ); },

				// 打开文件对话框 关闭时触发
				onDialogClose : function (queueData) {
								/*	alert(
										"文件对话窗口中选了几个文件:" + queueData.filesSelected + "---" +
										"队列中加了几个文件:" + queueData.filesQueued + "---" +
										"队列中被替换掉的文件数:" + queueData.filesReplaced + "---" +
										"取消掉的文件数:" + queueData.filesCancelled + "---" + 
										"上传出错的文件数:" + queueData.filesErrored
									); */
						
								},
				
				// 选择文件对话框打开时触发
				onDialogOpen : function () { /*alert( 'please select files' ) */ },
			
				// 没有兼容的FLASH时触发
				onFallback : function(){ alert( 'Flash was not detected!' ) },
				
				// 每次初始化一个队列时触发, 即浏览文件后, 加入一个队列
				//onInit : function (instance) { alert( 'The queue ID is ' + instance.settings.queueID ) },
			
				// 上传文件处理完成后触发（每一个文件都触发一次）, 无论成功失败
				//onUploadComplete : function(file){ alert( 'The file ' + file.name + ' uploaded finished!' ) },

				// 上传文件失败触发
				onUploadError : function(file, errorCode, errorMsg, errorString){ 
                                    /*
                                    alert(
                                        file.name + ' upload failed! ' + 
                                        'errorCode: ' + errorCode +
                                        'errorMsg:' + errorMsg +
                                        'errorString:' + errorString
                                    );*/
								},
                
                // 在每一个文件上传成功后触发
                onUploadSuccess : function(file, data, response) {
                                   // alert(
                                   //     file.name + ' is uploaded succeed!  ' +
                                   //     '  server-side returned data:' + data +
                                   //     '  response: ' + response
                                   // );
                                $('input[name="streetShooting"]').val(data);
                         }
			});
		});
		
	$(function () {
    	 $("#sBeginDate").datetimebox({ formatter: formatDate,showSeconds:true });
    	  $("#sEndDate").datetimebox({ formatter: formatDate,showSeconds:true });
   
     //设置
  
	    $("input.combo-text").attr("readonly","readonly");
	    
	});
	
	function formatDate(date) {
	    return date.getFullYear() + "-" + (date.getMonth() +1) + "-" + date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
	function clearFind(){
		$("#sGoodName").val("");
		$("#sNickName").val("");
		$('#sBeginDate').datebox('setValue', '');
		$('#sEndDate').datebox('setValue', '');
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
		<div id="searchtool" style="padding: 5px">
			<span>商品名称:</span>
			<input id="sGoodName" name="sGoodName" data-options="panelHeight:'auto'" />
			<span>昵称:</span>
			<input id="sNickName" name="sNickName" data-options="panelHeight:'auto'" />
			<span>开始时间:</span>
			<input id="sBeginDate" name="sBeginDate" data-options="panelHeight:'auto'" />
			<span>结束时间:</span>
			<input id="sEndDate" name="sEndDate" data-options="panelHeight:'auto'" />	
			
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
						<td colspan="2">商品详细</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>商品名称</td>
						<td><input type="text" name="goodName" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>昵称</td>
						<td><input type="text" name="nickName" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>商品原价</td>
						<td><input type="text" name="originalPrice" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>当前价格</td>
						<td><input type="text" name="currentPrice" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>上架</td>
						<td>
							<select name="isShow"  class="easyui-validatebox" required="true" >
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>明星街拍</td>
						<td>
							<select name="isStreetShooting"  class="easyui-validatebox" required="true" >
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>收藏数</td>
						<td><input type="text" name="collectCount" class="easyui-validatebox" required="true" readonly="readonly" value="0"/></td>
					</tr>
					<tr>
						<td>列表小图</td>
						<td><input type="text" name="smallPicture" class="easyui-validatebox" required="true" readonly="readonly" /> <span id="uploadify_smallPicture"></span>   <div id="fileQueue_smallPicture"  style="display: none;"></td>
					</tr>
					<tr>
						<td>明星街拍图</td>
						<td><input type="text" name="streetShooting" required="true" readonly="readonly" /> <span id="uploadify_streetShooting"></span> <div id="fileQueue_streetShooting"  style="display: none;">  </td>
					</tr>
					<tr>
						<td>大图1</td>
						<td><input type="text" name="bigPctureUrl" class="easyui-validatebox" required="true" readonly="readonly" /> <span id="uploadify_bigPctureUrl"></span>  <div id="fileQueue"  style="display: none;"></div></td>
					</tr>
					<tr>
						<td>大图2</td>
						<td><input type="text" name="bigPctureUrl2"  required="true" readonly="readonly" /> <span id="uploadify_bigPctureUrl2" ></span> <div id="fileQueue_bigPctureUrl2"  style="display: none;"></td>
					</tr>
					<tr>
						<td>大图3</td>
						<td><input type="text" name="bigPctureUrl3"  required="true" readonly="readonly" /> <span id="uploadify_bigPctureUrl3"></span>   <div id="fileQueue_bigPctureUrl3"  style="display: none;"></td>
					</tr>
					<tr>
						<td>颜色</td>
						<td>
							<input id="color" class="easyui-combobox easyui-validatebox"
									name="color" data-options="panelHeight:'auto'" />
						</td>
							
					</tr>
					<tr>
						<td>详细</td>
						<td>
							<textarea style="height:50px;width:200px;" name="detailed"
							class="easyui-validatebox"
								validType="lengthXY['2','200','输入值在{2-200}之间']" required="true">
							</textarea>
						</td>
							
					</tr>
					<tr>
					<td>描述</td>
						<td>
							<textarea  style="height:50px;width:200px;" name="goodDesc"
							class="easyui-validatebox"
								validType="lengthXY['2','200','输入值在{2-200}之间']" required="true">
							</textarea>
						</td>
							
					</tr>
					<tr>
						<td>产地</td>
						<td><input type="text" name="production"  required="true"  /></td>
					</tr>
					<tr>
						<td>材质</td>
						<td><input type="text" name="materialQuality"  required="true" /></td>
					</tr>
					<tr>
						<td>购买地址</td>
						<td><input type="text" name="buyUrl"  required="true"  /></td>
					</tr>
				</table>
			</form>	
		</div>
	</div>
</body>
</html>
