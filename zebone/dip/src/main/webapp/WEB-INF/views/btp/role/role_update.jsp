<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jsp/btp/role/jsp.role_update.js"></script>
<script type="text/javascript" src="js/jsp/btp/mho/jsp.mho_inner_tree.js"></script>
<title>demo</title>
<style type="text/css">
h2{ border-bottom: 1px solid;}

</style>
</head>  
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">角色信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
			<form id="roleMessage" method="post" action="btp/role/test.zb">
				<table cellspacing="10" style="width: 100%;">
					<tbody>
						<tr>
							<td align="right" style="width: 20px;">角色名称：</td>   
							<td align="left" style="width: 100px;">     
							<input type="hidden" name="roleId" id="roleId" value="${role.roleId}"/>   
							<input type="text" maxlength="10" style="width: 80%; border: 1px solid rgb(204, 204, 204);" title="姓名" name="name" id="name" value="${data.name}"/>          
							</td>
						</tr>   
						<c:if test="${loginName != null}">	
						<tr>
								<td align="right" style="width: 20px;">公共角色标志:</td>
								<c:if test="${data.isPublicRole==1}">
								<td align="left" style="width: 100px;">
									<input type="radio" name="isPublicRole" value="1" style="width: 20%;" checked="checked"/>是
									<input type="radio" name="isPublicRole" value="0" style="width: 20%;"/>否
								</td>
								</c:if>
								<c:if test="${data.isPublicRole==0}">
								<td align="left" style="width: 100px;">
									<input type="radio" name="isPublicRole" value="1" style="width: 20%;"/>是
									<input type="radio" name="isPublicRole" value="0" style="width: 20%;" checked="checked"/>否
								</td>
								</c:if>
								<c:if test="${data.isPublicRole==null}">
								<td align="left" style="width: 100px;">
									<input type="radio" name="isPublicRole" value="1" style="width: 20%;"/>是
									<input type="radio" name="isPublicRole" value="0" style="width: 20%;"/>否
								</td>
								</c:if>
						</tr>
						</c:if>
						<tr>
							<td align="right" style="width: 20px;">角色说明：</td>
							<td>
								<input type="text" maxlength="10"  style="width: 80%; border: 1px solid rgb(204, 204, 204);"  title="角色描述" name="remark" id="remark" value="${data.remark }"/>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 20px;">医疗机构：</td>
							<td>
								<input type="text" onclick="loadMho('id','medicalOrganId','checkbox','${mhoId}');" style="width: 80%; border: 1px solid rgb(204, 204, 204);"  title="医疗机构" name="medicalOrganId" id="medicalOrganId" value="${data.temp}"/>
								<input type="hidden" name="id" id="id" value="${data.medicalOrganId}"/>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
