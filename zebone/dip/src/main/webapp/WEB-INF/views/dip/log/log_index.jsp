<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>日志查看模块</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jsp/dip/log/log_index.js"></script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">日志查看</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table id="query">
					<tr>
						<td>文档编号：
						<input type="text" name="docNo" style="width: 120px;" maxlength="32"/>
						</td>
						<td>机&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;构：
						<input type="hidden" name="docSourceOrgan" id="docSourceOrgan"/>
						<input type="text" name="organName" id="organName" style="width: 145px;"/>
						</td>
						<td>上传时间：
							<input type="text" class="Wdate" style="width: 90px;" name="uploadTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>--
							<input type="text" class="Wdate" style="width: 90px;" name="receiveStatus" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
					</tr><tr>
						<td>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
							<select name="uploadStatus" style="width: 120px;">
								<option value="">请选择</option>
								<option value="1">成功</option>
								<option value="0">失败</option>
							</select>
						</td>
						<td>文档类型：
						<select name="docType" style="width: 150px;">
							<option value="">请选择</option>
							<c:forEach items="${list}" var="dict">
								<option value="${dict.code}">${dict.name}</option>
							</c:forEach>
						</select>
						</td>
						<td>
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" id="querybtn">
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
<div style="display: none;" id="edit">
	<table align="center">
		<tr style="height: 30px;">
		<td>错误时间：</td>
		<td><span id="eventTime"></span></td>
		</tr>
		<tr style="height: 30px;">
		<td>错误代码：</td>
		<td><span id="errorCode"></span></td>
		</tr>
		<tr style="height: 30px;">
		<td>错误名称：</td>
		<td><span id="errorName"></span></td>
		</tr>
		<tr>
		<td>错误详情：</td>
		<td><textarea rows="10" cols="" readonly="readonly" id="logDetails"></textarea></td>
		</tr>
	</table>
</div>
</body>
</html>
