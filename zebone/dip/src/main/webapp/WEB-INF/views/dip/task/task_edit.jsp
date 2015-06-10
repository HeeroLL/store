<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>任务管理界面</title>
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
<script type="text/javascript" src="js/jsp/dip/task/task_edit.js"></script>
<script type="text/javascript">
var tasksjpz = '${pTask.taskFreqSm}';
</script>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">任务信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content" style="overflow: auto;">
				<table class="checkForm" id="checkForm">
					<tr>
						<td>任务编码：</td><td class="tasksj">
						<input type="hidden" name="id" value="${pTask.id}"/>
						<input type="text" name="taskCode" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-11" value="${pTask.taskCode }"/> 
						</td>
					</tr>
					<tr>
						<td>任务描述：</td><td class="tasksj"><input type="text" name="taskDesc" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-35" value="${pTask.taskDesc }"/> </td>
					</tr>
					<tr>
						<td>任务类型：</td><td class="tasksj"><select name="taskType" validate="select">
							<option value="">请选择</option>
							<option value="1" <c:if test="${pTask.taskType==1}"><c:out value="selected" /> </c:if> >ETL任务</option>
							<option value="2" <c:if test="${pTask.taskType==2}"><c:out value="selected" /> </c:if>>SQL任务</option>
							<option value="3" <c:if test="${pTask.taskType==3}"><c:out value="selected" /> </c:if>>其他任务</option>
							<option value="5" <c:if test="${pTask.taskType==5}"><c:out value="selected" /> </c:if>>清洗任务</option>
						</select> </td>
					</tr>
					<tr>
						<td>前置节点：</td><td class="tasksj"><select name="taskNode" validate="select">
							<option value="">请选择</option>
							<c:forEach items="${list}" var="nodeInfo">
								<option value="${nodeInfo.id}" <c:if test="${pTask.taskNode==nodeInfo.id}"><c:out value="selected"/></c:if>>${nodeInfo.nodesName}</option>
							</c:forEach>
						</select> </td>
					</tr>
					<tr>
						<td>执行频率：</td><td>
				
				<div style="border: 1px solid #eef;" id="taskFreq">
				<table>
					<tr>
						<td align="right">重复：</td><td>
						<input type="checkbox" name="sfcf"/> 
						</td>
					</tr>
					<tr>
						<td align="right">类型：</td><td>
							<select style="width: 100px;" name="timelx" id="timelx">
								<option value="1">不需要定时</option>
								<option value="2">时间间隔</option>
								<option value="3">天</option>
								<option value="4">周</option>
								<option value="5">月</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">时间间隔（秒）：</td>
						<td><input type="text" style="width: 100px;" name="secondTime" id="secondTime"/></td>
					</tr>
					<tr>
						<td align="right">时间间隔（分钟）：</td>
						<td><input type="text" style="width: 100px;" name="minuteTime" id="minuteTime"/></td>
					</tr>
					<tr>
						<td align="right">时间：</td>
						<td><input type="text" name="hour" id="hour" style="width: 40px;"/>时
						<input type="text" name="minute" id="minute" style="width: 40px;"/>分</td>
					</tr>
					<tr>
						<td align="right">星期几：</td>
						<td>
							<select style="width: 100px;" id="week" name="week" validate="select">
								<option value="">请选择</option>
								<option value="1">星期一</option>
								<option value="2">星期二</option>
								<option value="3">星期三</option>
								<option value="4">星期四</option>
								<option value="5">星期五</option>
								<option value="6">星期六</option>
								<option value="7">星期日</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">几号：</td>
						<td><input type="text" style="width: 100px;" name="day" id="day"/></td>
					</tr>
				</table>
				</div>
				
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
