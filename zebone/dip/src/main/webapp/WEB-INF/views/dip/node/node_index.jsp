<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>节点管理界面</title>
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
<script type="text/javascript" src="js/jsp/dip/node/node_index.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">节点查询</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table id="queryTj">
					<tr>
						<td>节点名称：<input type="text" name="nodesName"/> </td>
						<td>节点IP地址：<input type="text" name="nodeIp"/> </td>
						<td>节点状态：<select name="nodeState">
							<option value="-1">请选择</option>
							<option value="1">启动</option>
							<option value="0">停止</option>
						</select> </td>
						<td>
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" id="querybtn"  onclick="onQueryCondition();">
									<span class="btn-text icon-search">查询</span>
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
<div id="grid" style="margin-left: 10px;margin-right: 10px;"></div>
</body>
</html>
