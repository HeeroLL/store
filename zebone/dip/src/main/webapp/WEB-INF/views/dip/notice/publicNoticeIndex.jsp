<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>公告管理</title>
<link rel="stylesheet" type="text/css"
	href="../js/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="../js/jquery/themes/icon.css">
		<style type="text/css">
			.lines-bottom .datagrid-body td {
				border-right: 1px dotted transparent;
			}
			.file {
			    position: relative;
			    display: inline-block;
			    border-radius: 4px;
			    overflow: hidden;
			    text-decoration: none;
			    text-indent: 0;
			    line-height: 20px;
			}
			.file input {
			    position: absolute;
			    font-size: 100px;
			    right: 0;
			    top: 0;
			    opacity: 0;
			}
		</style>
		<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/jquery/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
			var currentId; // 当前选中的控件id
			$(function() {
				$('#datagrid').datagrid({
					url : '../notice/getPublicNoticeDataGrid.zb',
					onDblClickRow: function(rowIndex, rowData) {
						if (rowData.status == "0") {
							showDialog('写公告','../notice/publicNoticeEdit.zb?nId=' + rowData.nId);
						} else if (rowData.status == "1") {
							showDialog('查看','../notice/publicNoticeView.zb?nId=' + rowData.nId);
						}
						
					}
				});
				$('#datagrid').datagrid('getPanel').removeClass(
						'lines-both lines-no lines-right lines-bottom')
						.addClass('lines-bottom');
			});
			//显示编辑界面
			function showDialog(title, url) {
				$('#dialog').dialog({
					title : title,
					width : 800,
					height : 600,
					cache : false,
					href : url,
					modal : true,
					onLoad: function() {
						var effectiveTime = $('#effectiveTime').attr('value');
						var noticeType = $('#noticeType').attr('value');
						if (effectiveTime != null && effectiveTime != "") {
							$('#effectiveTime').combobox('setValue',effectiveTime);	
						}
						if (noticeType != null && noticeType != "") {
							$('#noticeType').combobox('setValue',noticeType);	
						}
						var fileIndex = -1;
						$('#attachmentDiv a').each(function() {
							// 绑定右键菜单
							bindRightButton(this, '#menu');
							var id = $(this).attr("id");
							var index = parseInt(id.substr(11));
							if (fileIndex < index) {
								fileIndex = index;
							}
						});
						$("#orderNumber").val(fileIndex + 1);
						$('#attachmentDiv2 a').each(function() {
							// 绑定右键菜单
							bindRightButton(this, '#menu2');
							var id = $(this).attr("id");
							var index = parseInt(id.substr(11));
							if (fileIndex < index) {
								fileIndex = index;
							}
						});
					}
				});
			}
			// 绑定右键
			function bindRightButton(obj, menuId) {
				// 绑定右键菜单
				$(obj).bind('contextmenu',function(e) {
					e.preventDefault();
					currentId = $(this).attr("id");//设置当前选中的附件id
					$(menuId).menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				});
			}
			//查询数据
			function doSearch() {
				var noticeTitle = $('#searchNoticeTitle').textbox('getValue');
				var status = $('#searchStatus').combo('getValue');
				$('#datagrid').datagrid('load', {
					noticeTitle : noticeTitle,
					status : status
				});
			}
			//上传文件
			function uploadFile1() {
				$('#noticeForm').form('submit', {
					url : '../notice/uploadAttachment.zb',
					success : function(res) {
						var data = eval('(' + res + ')');
						if (data.success) {
							// 文件序号
							var fileIndex = parseInt($('#orderNumber').val());
						
							$.messager.show({
								title:"提示",
								msg:"上传附件成功！"
							});
							// 格式化文件长度
							var attachmentName = data.attachment.attachmentName;
							var pointIndex = attachmentName.lastIndexOf(".");
							var name = attachmentName.substring(0, pointIndex);
							//var suffix = attachmentName.substr(pointIndex + 1);
							
							var linkbutton = '<a href="#" style="height: 50px" title="' + data.attachment.attachmentName 
								+ '" aid="' + data.attachment.aId + '" id="attachment_' + fileIndex + '"><p align="left">' 
								+ '<span id="span_attachment_' + data.attachment.aId + '">' + data.attachment.viewName 
								+ '</span><br/>' + data.attachment.viewSize + '</p></a>&nbsp;';
								
							if ($('#nId').val() == null || $('#nId').val() == "") {
								$('#nId').val(data.attachment.nId);
							}
							// 添加附件到附件框中
							$('#attachmentDiv').append(linkbutton);
							
							// 渲染
							$('#attachment_' + fileIndex).linkbutton({ 
							    iconCls: data.attachment.attachmentImage,
							    plain:true
							});
							
							// 绑定右键菜单
							bindRightButton($('#attachment_' + fileIndex), '#menu');
							
							fileIndex++;
							// 设置文件的序号
							$('#orderNumber').val(fileIndex);
						} else {
							//$('#uploadFile').textbox('enable');
							$.messager.alert('错误', data.msg, 'error');
						}
					}
				});
			}
			// 是否成功保存标识
			var saveFlag = false;
			//提交表单
			function submitForm(status) {
				$('#status').val(status);
				$('#uploadFile').attr("disabled","disabled")
				$('#noticeForm').form('submit', {
					url : '../notice/saveOrUpdatePublicNotice.zb',
					success : function(res) {
						var data = eval('(' + res + ')');
						if (data.success) {
							$.messager.show({
								title:"提示",
								msg:"公告发布成功！"
							});
							saveFlag = true;
							$('#dialog').dialog('close');
							$('#datagrid').datagrid('reload');
						} else {
							$('#uploadFile').removeAttr("disabled")
							$.messager.alert('错误', data.msg, 'error');
						}
					}
				});
			}
			// 删除数据
			function deleteData(nId) {
				$.messager.confirm('确认','您确认要删除记录吗？',function(r){    
				    if (r){    
				    	// 提交数据
						$.ajax({
							url : '../notice/deletePublicNotice.zb',
							type : 'POST',
							cache : false,
							dataType : 'json',
							data : 'nId=' + nId,
							success : function(res) {
								if (res.success) {
									$.messager.show({
										title:"提示",
										msg:"公告删除成功！"
									});
									$('#datagrid').datagrid('reload');
								} else {
									$.messager.alert('失败', "公告删除失败！", 'error');
								}
							},
							error : function(XMLHttpRequest,textStatus,errorThrown) {
								$.messager.alert('异常', "服务器出现异常，数据提交失败。", 'error');
							}
						});
				    }    
				});  
			}
			// 格式化主题
			function formatTitle(row) {
				var result = "<p style='height: 30px'>主题：" + row.noticeTitle;
				result += "&nbsp;" + formatStatus(row.status);
				result += "<br/>";
				if (row.status == "0") {
					result += formatEffectiveTime(row.effectiveTime);
				} else {
					result += formatDate(row.expiryDate);
				}
				result += "</p>"
				return result;
			}
			// 格式化重要性图标
			function formatImp(value) {
				if (value == "1") {
					return '<span style="color: #969696;">●</span>';
				}
				if (value == "2") {
					return '<span style="color: red">●</span>';
				}
				return value;
			}
			// 格式化状态
			function formatStatus(value){
				if (value == "0") {
					return '<img class="easyui-tooltip" title="未发送" src="/dip/images/icons/draft.png" />';
				}
				if (value == "1") {
					return '<img class="easyui-tooltip" title="已发送" src="/dip/images/icons/sent.png" />';
				}
				return value;
			}
			// 格式化有效时长
			function formatEffectiveTime(value) {
				return "有效时长：" + value + "个月";
			}
			// 格式化日期
			function formatDate(value)
			{
				if (value != null && value.length == 8)
				{
					value = value.substr(0,4) + '-' + value.substr(4,2) + '-' + value.substr(6,2);
				}
				return "失效时间：" + value;
			}
			// 格式化图片
			function formatImg(row) {
				var value = row.status;
				if (value == "0") {
					return '<a href="#" class="easyui-linkbutton" onClick="deleteData(\'' + row.nId + '\')" data-options="plain:true">✖</a> ';
				}
				if (value == "1") {
					return '<span style="color: #969696;">✖</span>';
				}
				return value;
			}
			// 菜单点击事件
			function menuHandler(item) {
				var aid = $('#' + currentId).attr("aid"); // 获取当前附件的id
				// 删除附件
				if (item.text == "删除") {
					$.messager.confirm('确认','您确认要删除该附件吗？',function(r){    
					    if (r){    
							// 提交数据
							$.ajax({
								url : '../notice/deleteAttachment.zb',
								type : 'POST',
								cache : false,
								dataType : 'json',
								data : 'aId=' + aid,
								success : function(res) {
									if (res.success) {
										$.messager.show({
											title:"提示",
											msg:"删除附件成功！"
										});
										// 删除控件
										$('#' + currentId).remove();
									} else {
										$.messager.alert('失败', "公告删除失败！", 'error');
									}
								},
								error : function(XMLHttpRequest,textStatus,errorThrown) {
									$.messager.alert('异常', "服务器出现异常，数据提交失败。", 'error');
								}
							});
					 	}
					});
				} else if (item.text == "重命名") {
					$.messager.prompt('重命名', '请输入文件名（不要输入后缀名）', function(r) {
						if (r) {
							if (r.length > 45) {
								$.messager.alert("警告","长度不能超过46！","warning");
				        		return;
				        	}
				        	if (r == null || r == "" || r.indexOf(" ") != -1) {
				        		$.messager.alert("警告","文件名不能为空，且不能含有空格！","warning");
				        		return;
				        	}
				            if (!/^[\u4e00-\u9fa5\w]*$/.test(r)) {
				            	$.messager.alert("警告","只允许输入汉字、英文字母、数字及下划线","warning");
				            	return;
				            }
				         	// 提交数据
							$.ajax({
								url : '../notice/renameAttachment.zb',
								type : 'POST',
								cache : false,
								dataType : 'json',
								data : 'aId=' + aid + '&attachmentName=' + r,
								success : function(res) {
									if (res.success) {
										$.messager.show({
											title:"提示",
											msg:"重命名附件成功！"
										});
										// 更新控件名
										$('#span_attachment_' + aid).html(res.attachment.viewName);
										$('#' + currentId).attr("title", res.attachment.attachmentName);
									} else {
										$.messager.alert('失败', "重命名附件失败！", 'error');
									}
								},
								error : function(XMLHttpRequest,textStatus,errorThrown) {
									$.messager.alert('异常', "服务器出现异常，数据提交失败。", 'error');
								}
							});
						}
					});
				} else if (item.text == "下载") {
					$("#downloadFile").attr("src","../notice/downloadAttachment.zb?aId=" + aid);
				}
			}
		</script>
</head>
<body>
	<iframe id="downloadFile" src="" style="display: none;"></iframe>
	<table style="padding: 5px;" id="datagrid" fitColumns="true" fit="true" singleSelect="true"
		showHeader="false" toolbar="#tableTools" idfield="nId"
		pagination="true">
		<thead>
			<tr>
				<th field="nId" data-options="hidden:true"></th>
				<th field="empty" width="20px"></th>
				<th field="noticeType" width="10px" 
					data-options="formatter:function(value,row,index){return formatImp(value)}"></th>
				<th field="noticeTitle" data-options="hidden:true"></th>
				<th field="title" width="300px"
					data-options="formatter:function(value,row,index){return formatTitle(row)}"></th>
				<th field="status" data-options="hidden:true"></th>
				<th field="effectiveTime" data-options="hidden:true"></th>
				<th field="expiryDate" data-options="hidden:true"></th>
				<th field="publishTime" width="120px"></th>
				<th field="delFlag" width="100px" data-options="formatter:function(value,row,index){return formatImg(row)}"></th>
			</tr>
		</thead>
	</table>
	<div id="tableTools" style="padding: 5px">
		<table width="100%">
			<tr>
				<td><input id="searchNoticeTitle" class="easyui-textbox"
					data-options="prompt:'请输入主题查询'" style="width:150px" /> <select
					id="searchStatus" class="easyui-combobox" style="width: 80px"
					data-options="editable:false,panelHeight:75">
						<option value="">全部</option>
						<option value="0">未发布</option>
						<option value="1">已发布</option>
				</select> <a href="#" class="easyui-linkbutton" iconCls="icon-search"
					plain="true" onclick="javascript:doSearch()"></a></td>
				<td align="right"><a id="btn" href="#"
					onclick="javascript:showDialog('写公告','../notice/publicNoticeEdit.zb')"
					class="easyui-linkbutton">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="dialog"></div>
</body>
</html>
