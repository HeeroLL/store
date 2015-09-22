<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单点登录账号管理</title>
		<base href="<%=basePath %>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/icons.css" />
		<link rel="stylesheet" type="text/css" href="skin/default/main.css" id="main-css"/>
		<link rel="stylesheet" type="text/css" href="skin/default/jquery-grid.css"  id="grid-css"/>
		<link rel="stylesheet" type="text/css" href="skin/default/jquery-dialog.css" id="dialog-css"/>
		<link rel="stylesheet" type="text/css" href="skin/default/jquery.checkForm.css" id="checkform-css"/>
		<link rel="stylesheet" type="text/css" href="skin/default/jquery-button.css" id="button-css"/>
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
		<script type="text/javascript" src="js/jsp/sso/jsp.users_index.js"></script>

	</head>
	<body>
		<!-- 查询界面 -->
		<div id="main">
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">单点登录帐号查询</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left;margin-left: 18px">
								<tr>
									<td>用户名称：</td>
									<td><input name="account" type="text" /></td>
								</tr>
							</table>
							<div style="float: right;width: 110px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="querybtn">
										<span class="btn-text icon-search" id="findAll">查询全部</span>
									</span>
								</a>
							</div>
							<div style="float: right;width: 110px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="querybtn">
										<span class="btn-text icon-search" id="find">查询</span>
									</span>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
			</div>
		</div>
		<!-- 列表界面 -->
		<div id="grid"></div>

		<div id='edit' style="display: none;" >
			<form id="accountform" action="" class="checkForm">
				<input type="hidden" name="accountId" value=""/>
				<div class="container">
					<div class="tools-panel"></div>
					<table id="mainTABLE" cellspacing="10"  style="width: 100%;">
						<tr height="30px">
							<td align="right" valign="middle">
								用户名
							</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="account" title="用户名" validate="string|1-20" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">密码</td>
							<td style="float: left" valign="middle">
								<input size="25" type="password" name="password" title="密码" reg="[]" validate="string|1-20" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">邮件地址</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="email" title="邮件地址" reg="[]" validate="string|1-255" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">移动电话</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="mobile" title="移动电话" reg="[]" validate="string|1-20" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">账户描述</td>
							<td style="float: left" valign="middle">
						 	<textarea rows="7" cols="20" name="description" title="账户描述" reg="[]" validate="string|1-32"></textarea>
							<!-- <input size="25" type="text" name="description" title="账户描述" reg="[]" validate="string|1-32" /> -->
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>