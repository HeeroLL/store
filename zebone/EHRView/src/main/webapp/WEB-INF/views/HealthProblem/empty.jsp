<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>健康档案浏览器-基本信息-详细</title>
<ehrview:htmlBase/>
<link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/css/index.css"/>
<style type="text/css">
html,body{height:100%;}
.j-panel{
	margin:0 0 10px;
}
h3{
	font-size:16px;padding: 2px 0 0 10px;
}
</style>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-panel.js"></script>
<script type="text/javascript">

$(function(){
	
});
</script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/ie6/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript">
 DD_belatedPNG.fix('DIV');
 DD_belatedPNG.fix('A');
 DD_belatedPNG.fix('SPAN');
 DD_belatedPNG.fix('INPUT');
</script>
<![endif]-->
</head>
<body>
<div id="grid" class="">
	<div id="JGrid" class="jgridDIV" >
		<div id="JGrid-9b4d6-main" class="jgridMain" style="">
			<div id="JGrid-9b4d6-header" class="j-panel-header">
				<div class="j-panel-title">健康和疾病问题</div>
			</div>
			<div id="JGrid-9b4d6-headerAndDataDiv" class="jHeaderAndDataDIV" style="height: auto;">
				<div class="jHeaderDIV">
					<table id="JGrid-9b4d6-headerTable" class="jgridHeaderTable" cellspacing="0" cellpadding="0" border="0" >
						<tr class="header" name="header">
							<td style="text-align:center;width:50px" name="maxicon"><div class="headerTdDiv">序号<span value="0"></span></div></td>
							<td style="text-align:center;width:150px" name="maxicon"><div class="headerTdDiv">时间<span value="0"></span></div></td>
							<td style="text-align:center;width:200px" name="orderNo"><div class="headerTdDiv">生命阶段<span value="0"></span></div></td>
							<td style="text-align:center;width:210px" name="moduleName"><div class="headerTdDiv">卫生服务活动<span value="0"></span></div></td>
							<td style="text-align:center;width:250px;" name="url"><div class="headerTdDiv">详情<span value="0"></span></div></td>
						</tr>
					</table>
				</div>
				<div id="JGrid-85a5b-dataDiv" class="jgridDataDIV" style="height: 450px;">
					<table cellspacing="1" cellpadding="0" border="0" id="JGrid-85a5b-dataTable">
						<c:forEach items="${healthProblemList}" var="object" varStatus="status">
						    <tr index="1" name="data" class="">
						    	<td align="center" title="maxicon1" selects="0" style="width: 50px;">${status.index+1}</td>
								<td align="center" title="maxicon1" selects="0" style="width: 150px;">${object.time}</td>
								<td align="center" title="1" selects="0" style="width: 200px;">${object.healthProblem}</td>
								<td align="center" title="moduleName1" selects="0" style="width: 210px;">${object.healthActivity}</td>
								<td align="center" title="url1" selects="0" style="width: 250px;"><a href="healthProblemDetails.zb?empiId=${empiId}&dataCode=${object.docNo}&docNo=${object.code}">查看</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			     
			</div>
		</div>
	</div>
</div>
</body>
</html>
	