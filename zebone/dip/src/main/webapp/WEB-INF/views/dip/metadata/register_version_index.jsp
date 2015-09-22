<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>注册模型页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript">
var projectPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="js/jsp/dip/metadata/register_version_index.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">查询</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table id="query" border="0" cellpadding="1" cellspacing="1" align="center" style="float: left">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;实体名称：</td>
						<td>
							<input id=" " name="docName" type="text" style="width: 110px;" maxlength="64"/>
							<input id="modelType" name="modelType" type="hidden" value="2"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;版本类型：</td>
						<td>
							<select name="versionType">
								<option value="">请选择</option>	
								<c:forEach items="${versionType}" var="dict">
                   					<option value="${dict.code}">${dict.name}</option>
                   				</c:forEach>		
							</select>
						</td>
						<td>
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" id="querybtn">
									<span class="btn-text icon-search" id="find">查询</span>
								</span>
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>	

<!-- 列表界面 -->
<div id="grid" style="margin-left: 10px;margin-right: 10px;"></div>
<!-- 数据字典编辑 -->
<div id='edit' style="display: none;">
	<form id="checkForm" action="" class="checkForm">
		<div class="container">
			<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
			<span class="title-l">
				<span class="title-r">
					<b class="icon"></b><span class="title-span">实体列表</span>
				</span>
			</span>
			<div class="tools-panel"></div>
			<div class="c_w">
			<div class="c_e">
			<div class="c_content">
            	<table>
            		<tr>
            			<td>实体编码：<input type="hidden" id="id" name="id" value=""/><input id="modelType" name="modelType" type="hidden" value="2"/></td>
            			<td><input type="text" id="docCode" name="docCode" value="" validate="string|1-64" style="width: 280px;"/></td>
            		</tr>
            		<tr>
            			<td>实体名称：</td>
            			<td><input type="text" id="docName" name="docName" value="" validate="string|1-64" style="width: 280px;"/></td>
            		</tr>
            		<tr>
            			<td>版本类型：</td>
            			<td>
            				<select name="versionType" validate="select" style="width: 284px;">
                   				<option value="">请选择</option>
                   				<c:forEach items="${versionType}" var="dict">
                   					<option value="${dict.code}">${dict.name}</option>
                   				</c:forEach>
                   			</select>
            			</td>
            		</tr>
            		<tr>
                   		<td>版&nbsp;&nbsp;本&nbsp;号：</td>
                   		<td>
                   		<select id="docVersion" name="docVersion" validate="select" style="width: 284px;">
                   			<option value="">请选择</option>
                   			<option value="1.0">1.0</option>
                   			<option value="1.1">1.1</option>
                   			<option value="1.2">1.2</option>
                   			<option value="1.3">1.3</option>
                   			<option value="1.4">1.4</option>
                   			<option value="1.5">1.5</option>
                   			<option value="1.6">1.6</option>
                   			<option value="1.7">1.7</option>
                   			<option value="1.8">1.8</option>
                   			<option value="1.9">1.9</option>
                   			<option value="2.0">2.0</option>
                   			<option value="2.1">2.1</option>
                   			<option value="2.2">2.2</option>
                   			<option value="2.3">2.3</option>
                   			<option value="2.4">2.4</option>
                   			<option value="2.5">2.5</option>
                   			<option value="2.6">2.6</option>
                   			<option value="2.7">2.7</option>
                   			<option value="2.8">2.8</option>
                   			<option value="2.9">2.9</option>
                   			<option value="3.0">3.0</option>
                   		</select>
                   	</tr>
                   	<tr>
                   		<td>实体描述：</td>
                   		<td><textarea style="height:90px;" type="text" id="docDesc" name="docDesc" value="" validate="string|1-512"></textarea></td>
                   	</tr>            		
            	</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
		</div>
	</form>
</div>  
     <form id="myform" action="" method="post">
     	<input id="docid" type="hidden" name="id" value=""/>
     	<input id="docname" type="hidden" name="name" value=""/>
     </form>
</body>
</html>