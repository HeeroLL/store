<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>辅助功能/消息中心</title>
<link rel="stylesheet" type="text/css"
	href="../js/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="../js/jquery/themes/icon.css">
		<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/jquery/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
		$(function() {
			$('#datagrid').datagrid({
				url : '../auditlog/getAuditlogDataGrid.zb'
			});
		});
		//格式化日期
		function formatDate2(value) {
			if (value != null && value.length == 8) {
				value = value.substr(0,4) + '-' + value.substr(4,2) + '-' + value.substr(6,2);
			}
			return value;
		}
		//查询数据
		function doSearch() {
			var beginDate = formatDate($('#beginDate').combo('getValue'));
			var endDate = formatDate($('#endDate').combo('getValue'));
			var eventTypeId = $('#eventTypeId').combo('getValue');
			var optTypeId = $('#optTypeId').combo('getValue');
			var searchResult = $('#searchResult').combo('getValue');
			var searchPersonName = $('#searchPersonName').textbox('getValue');
			var searchOrgCode = $('#searchOrgCode').combo('getValue');
			$('#datagrid').datagrid('load', {
				beginDate : beginDate,
				endDate : endDate,
				eventTypeId : eventTypeId,
				optTypeId : optTypeId,
				result : searchResult,
				personName : searchPersonName,
				orgCode : searchOrgCode
			});
		}
		</script>
</head>
<body class="easyui-layout"> 
<div style="padding:5px;" data-options="region:'north'">
   	辅助功能/消息中心
</div>
<div data-options="region:'center'" style="padding: 5px">   	
   	<table style="padding: 5px;" id="datagrid" fitColumns="true" fit="true" singleSelect="true"
		toolbar="#tableTools" idfield="logId" pagination="true">
		<thead>
			<tr>
				<th field="logId" data-options="hidden:true"></th>
				<th field="createDate" width="20px"
					data-options="formatter:function(value,row,index){return formatDate2(value)}">时间</th>
				<th field="eventType" width="20px">事件类型</th>
				<th field="optType" width="20px">操作类型</th>
				<th field="orgName" width="30px">操作机构</th>
				<th field="personName" width="20px">操作人员</th>
				<th field="optObject" width="80px">操作对象</th>
				<th field="result" width="20px">结果</th>
				<th field="description" width="100px">描述</th>
			</tr>
		</thead>
	</table>
	<div id="tableTools" style="padding: 5px">
		日期 <input id="beginDate" type="text" class="easyui-datebox" /> - 
		<input id="endDate" type="text" class="easyui-datebox" /> 
		事件类型  <select id="eventTypeId" class="easyui-combobox" style="width: 150px"
					data-options="editable:false,valueField: 'id',textField: 'name',url:'../auditlog/getEventType.zb'">
				</select>
		操作类型 <select id="optTypeId" class="easyui-combobox" style="width: 150px"
					data-options="editable:false,valueField: 'id',textField: 'name',url:'../auditlog/getOptType.zb'">
				</select>
		结果 <select id="searchResult" class="easyui-combobox" style="width: 80px"
				data-options="editable:false,panelHeight:75">
				<option value="">全部</option>
				<option value="正常">正常</option>
				<option value="异常">异常</option>
			</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					plain="true" onclick="javascript:doSearch()">查询</a>		
		<br/>
		操作人员 <input id="searchPersonName" class="easyui-textbox"
					data-options="prompt:'请输入姓名'" style="width:150px" />
	    <select id="searchOrgCode" class="easyui-combobox" style="width: 80px"
			data-options="editable:false,panelHeight:75">
			<option value="">选择机构</option>
			<option value="1000001">测试机构</option>
		</select>
	</div>
</div>
</body>
</html>
