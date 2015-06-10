<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<btp:htmlbase/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		<title>审计日志详细信息</title>
	</head>
	<body>
		<div id="main">
			<div class="container">
				<div class="c_nw">
					<div class="c_ne">
						<div class="c_n"></div>
					</div>
				</div>
				<span class="title-l"> <span class="title-r"> <b
						class="icon"></b><span class="title-span">日志信息</span> </span> </span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table border="0" cellpadding="1" cellspacing="1" align="center"
								style="float: left">
								<tr style="height: 30px">
									<td>
										操作人：
									</td>
									<td>
										${auditLogInfo.fullName}
									</td>
								</tr>
								<tr style="height: 20px">
									<td>
										操作时间：
									</td>
									<td>
										<fmt:formatDate value="${auditLogInfo.createTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
								</tr>
								<tr style="height: 30px">
									<td>
										ip地址：
									</td>
									<td>
										${auditLogInfo.ipAddress}
									</td>
								</tr>
								<tr style="height: 20px">
									<td>
										所属医疗机构：
									</td>
									<td>
										${auditLogInfo.mhoName}
									</td>
								</tr>
								<tr style="height: 20px">
									<td>
										操作模块：
									</td>
									<td>
										${auditLogInfo.moduleName}
									</td>
								</tr>
								<tr style="height: 20px">
									<td>
										业务id：
									</td>
									<td>
										${auditLogInfo.serviceId}
									</td>
								</tr>
								<tr style="height: 20px">
									<td>
										操作标识：
									</td>
									<td>
										<c:choose>
											<c:when test="${auditLogInfo.opFlag==1}">
												新增
											</c:when>
											<c:when test="${auditLogInfo.opFlag==2}">
												修改
											</c:when>
											<c:when test="${auditLogInfo.opFlag==3}">
												删除
											</c:when>
											<c:when test="${auditLogInfo.opFlag==4}">
												查询
											</c:when>
										</c:choose>
									</td>
								</tr>
								<tr style="height: 20px">
									<td>
										描述：
									</td>
									<td>
										${auditLogInfo.description}
									</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="c_sw">
					<div class="c_se">
						<div class="c_s"></div>
					</div>
				</div>
			</div>
			<div class="c_sw">
				<div class="c_se">
					<div class="c_s"></div>
				</div>
			</div>
			<c:if test="${!empty auditLogInfo.auditLogInfoExtList}">
				<div class="container" style="width: 500px;">
					<div class="c_nw">
						<div class="c_ne">
							<div class="c_n"></div>
						</div>
					</div>
					<span class="title-l"> <span class="title-r"> <b
							class="icon"></b><span class="title-span">字段信息</span> </span> </span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<table style="text-align: center; width: 100%;">
									<thead>
										<th align="center">
										<td>
											字段名
										</td>
										<td>
											新值
										</td>
										<td>
											旧值
										</td>
									</thead>
									<c:forEach items="${auditLogInfo.auditLogInfoExtList}"
										var="info">
										<tr>
											<td></td>
											<td>
												${info.name}
											</td>
											<td>
												${info.value}
											</td>
											<td>
												${info.oldValue}
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<div class="c_sw">
						<div class="c_se">
							<div class="c_s"></div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</body>
</html>
