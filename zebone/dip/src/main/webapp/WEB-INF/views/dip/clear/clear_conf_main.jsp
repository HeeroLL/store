<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<btp:htmlbase/>
		<link rel="stylesheet" type="text/css" href="css/icons.css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css" />
		<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css" />
		<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
		<script type="text/javascript" src="js/jsp/dip/clear/clear_conf_main.js"></script>
		<title>清洗配置管理</title>
		<script type="text/javascript">
			var tasksjpz = '${pTask.taskFreqSm}';
		</script>
	</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">清洗配置任务信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div style="height: 150px;overflow: auto;">
				<table class="checkForm" id="checkForm">
					<tr>
						<td>任务编码：</td><td class="tasksj"><input type="hidden" name="id" value="${clearConf.id}"/>
						<input type="hidden" name="tableId" value="${tableId}"/>
						<input type="hidden" name="taskId" value="${pTask.id}"/>
						<input type="hidden" name="deployFlag" value="${clearConf.deployFlag}"/>
						<input type="text" name="taskCode" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-11" value="${pTask.taskCode }"/> 
						</td>
					</tr>
					<tr>
						<td>任务描述：</td><td class="tasksj"><input type="text" name="taskDesc" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-35" value="${pTask.taskDesc }"/> </td>
					</tr>
					<tr>
						<td>任务类型：</td><td class="tasksj"><select name="taskType" validate="select" disabled="disabled">
							<option value="">请选择</option>
							<option value="1" >ETL任务</option>
							<option value="2" >SQL任务</option>
							<option value="3" >其他任务</option>
							<option value="5" selected="selected">清洗任务</option>
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
				</div><hr/>
				<div>
					<table>
						<tr>
							<td style="width: 200px;">字段</td>
							<td style="width: 200px;">数据字典</td>
							<td style="width: 120px;">数据格式</td>
							<td style="width: 120px;">业务校验</td>
							<td>自定义校验</td>
						</tr>
					</table>
				</div>
				<div style="overflow: auto;" id="colDiv">
					<table>
						<c:forEach items="${cols}" var="col">
						
						<c:set value="" var="sjzd"></c:set>
						<c:set value="" var="sjgs"></c:set>
						<c:set value="" var="ywjy"></c:set>
						<c:set value="" var="zdyjy"></c:set>
						
						<c:forEach items="${cCloumns}" var="cCloumn">
							<c:if test="${cCloumn.clearType == 1 && cCloumn.cloumnName == col}">
								<c:set value="${cCloumn.clearContent}" var="sjzd"></c:set>
							</c:if>
							<c:if test="${cCloumn.clearType == 2 && cCloumn.cloumnName == col}">
								<c:set value="${cCloumn.clearContent}" var="sjgs"></c:set>
							</c:if>
							<c:if test="${cCloumn.clearType == 3 && cCloumn.cloumnName == col}">
								<c:set value="${cCloumn.clearContent}" var="ywjy"></c:set>
							</c:if>
							<c:if test="${cCloumn.clearType == 0 && cCloumn.cloumnName == col}">
								<c:set value="${cCloumn.clearContent}" var="zdyjy"></c:set>
							</c:if>
						</c:forEach>
						
						<tr class="colConf">
							<td style="width: 200px;">${col}<input type="hidden" value="${col}"/> </td>
							<td style="width: 200px;">
								<select clearType="1">
									<option value="-1">请选择</option>
									<c:forEach items="${pDictTypes}" var="pDictType">
										<option value="${pDictType.typeId}" <c:if test="${sjzd == pDictType.typeId}"><c:out value="selected"/></c:if>>${pDictType.dictTypeName}</option>
									</c:forEach>
								</select>
							</td>
							<td style="width: 120px;">
								<select clearType="2">
									<option value="-1">请选择</option>
									<option value="S_1"  <c:if test="${sjgs == 'S_1'}"><c:out value="selected"/></c:if>>S_1</option>
									<option value="DATE"  <c:if test="${sjgs == 'DATE'}"><c:out value="selected"/></c:if>>DATE</option>
								</select>
							</td>
							<td style="width: 120px;">
								<select clearType="3">
									<option value="-1">请选择</option>
									<option value="EmailCheck"  <c:if test="${ywjy == 'EmailCheck'}"><c:out value="selected"/></c:if>>email</option>
								</select>
							</td>
							<td>
								<input type="text"  value="${zdyjy}"/>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div><hr/>
				<a class="btn" href="javascript:void(0);">
					<span class="btn-left" id="savebtn">
						<span class="btn-text icon-save">保存</span>
					</span>
				</a>
				<a class="btn" href="javascript:void(0);">
					<span class="btn-left" id="publishbtn">
						<c:choose>
							<c:when test="${clearConf.deployFlag == '1'}">
								<span class="btn-text">已发布</span>
							</c:when>
							<c:otherwise>
								<span class="btn-text">发布</span>
							</c:otherwise>
						</c:choose>
					</span>
				</a>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>