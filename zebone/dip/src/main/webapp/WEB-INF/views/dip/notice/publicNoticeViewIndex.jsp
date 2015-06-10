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
					queryParams:{
						status:'1'
					},
					onDblClickRow: function(rowIndex, rowData) {
						showDialog('查看','../notice/publicNoticeView.zb?nId=' + rowData.nId);
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
						$('#attachmentDiv2 a').each(function() {
							// 绑定右键菜单
							bindRightButton(this, '#menu2');
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
				$('#datagrid').datagrid('load', {
					noticeTitle : noticeTitle,
					status:'1'
				});
			}
			// 格式化主题
			function formatTitle(row) {
				return "主题：" + row.noticeTitle;
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
				if (item.text == "下载") {
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
				<th field="publishPersonName" width="30px"></th>
				<th field="noticeType" width="10px" 
					data-options="formatter:function(value,row,index){return formatImp(value)}"></th>
				<th field="noticeTitle" data-options="hidden:true"></th>
				<th field="title" width="300px"
					data-options="formatter:function(value,row,index){return formatTitle(row)}"></th>
				<th field="status" data-options="hidden:true"></th>
				<th field="effectiveTime" data-options="hidden:true"></th>
				<th field="expiryDate" data-options="hidden:true"></th>
				<th field="publishTime" width="120px"></th>
			</tr>
		</thead>
	</table>
	<div id="tableTools" style="padding: 5px">
		<table width="100%">
			<tr>
				<td><input id="searchNoticeTitle" class="easyui-textbox"
					data-options="prompt:'请输入主题查询'" style="width:150px" />
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					plain="true" onclick="javascript:doSearch()"></a></td>
			</tr>
		</table>
	</div>
	<div id="dialog"></div>
</body>
</html>
