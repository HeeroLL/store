<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>EHRView</title>
		<!-- 指向http://xxx.xxx.xxx:xxxx/cmstudio/ -->
		<base href='http://192.168.1.199:8080/EHRView/'/>
		<link rel="shortcut icon" type="image/x-icon" href="resources/five.ico"/>
		<!--Start importing the jquery files -->
		<script src="resources/vendor/jquery_1.8/jquery-1.8.3.min.js" type="text/javascript"></script>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<link rel="stylesheet" type="text/css" href="resources/vendor/easyui_1.3.2/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="resources/vendor/easyui_1.3.2/themes/bootstrap/easyui.css" />
		<script src="resources/vendor/easyui_1.3.2/jquery.easyui.min.js" type="text/javascript"></script>
		<script src="resources/vendor/easyui_1.3.2/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<!--End importing the jeasyui files -->
		<link rel="stylesheet" type="text/css" href="resources/vendor/ztree_3.5/css/zTreeStyle.css" />
		<script src="resources/vendor/ztree_3.5/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
		<script src="resources/vendor/underscore/underscore.js"></script>
		<script src="resources/vendor/backbone/backbone.js"></script>
		<script type="text/javascript">
			function pagerFilter(data){
	            if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array
	                data = {
	                    total: data.length,
	                    rows: data
	                }
	            }
	            var dg = $(this);
	            var opts = dg.datagrid('options');
	            var pager = dg.datagrid('getPager');
	            pager.pagination({
	                onSelectPage:function(pageNum, pageSize){
	                    opts.pageNumber = pageNum;
	                    opts.pageSize = pageSize;
	                    pager.pagination('refresh',{
	                        pageNumber:pageNum,
	                        pageSize:pageSize
	                    });
	                    dg.datagrid('loadData',data);
	                }
	            });
	            if (!data.originalRows){
	                data.originalRows = (data.rows);
	            }
	            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	            var end = start + parseInt(opts.pageSize);
	            data.rows = (data.originalRows.slice(start, end));
	            return data;
	        }
			
			$(function(){
				$('#dg').datagrid({
				    url:'home/hyperTentionList.zb',
				    loadFilter:pagerFilter,
				    rownumbers:true,
				    singleSelect:true,
				    pagination:true,
				    pageSize:16,
				    pageList:[16,32,48,64],
				    columns:[[
				        {field:'time',title:'随访时间',width:230,align:"center"},
				        {field:'doctor',title:'随访医生',width:200,align:"center"},
				        {field:'org',title:'服务机构',width:200,align:"center"},
				        {field:'comment',title:'随访结果评价',width:230,align:"center"}
				    ]]
				});
			});
		</script>
	</head>
	<body>
		<div style="text-align:center;font-weight:bold;margin:0 0 10px 0;">高血压随访</div>
		<table id="dg"></table>
 	</body> 
</html>