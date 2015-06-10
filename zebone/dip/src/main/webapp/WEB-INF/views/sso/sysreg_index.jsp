<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath %>" />
		<title>注册系统</title>
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
		<script type="text/javascript" src="js/jsp/sso/jsp.sysreg_index.js"></script>
	</head>
	<body>
		<!-- 查询界面 -->		
		<div id="main">
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">系统查询</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left;margin-left: 18px">
								<tr>
									<td>注册系统标识：</td>
									<td><input name="sysId" type="text" />
									</td>
									<td>&nbsp;&nbsp;&nbsp;</td>
									<td>系统名称：</td>
									<td><input name="sysName" type="text" />
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
			<form id="sysregform" action="" class="checkForm">
				<div class="container">
					<div class="tools-panel"></div>
					<table id="mainTABLE" cellspacing="10"  style="width: 100%;">
						<input type="hidden" value="" name="regId"/>
						<tr height="30px">
							<td align="right" valign="middle">
								注册系统标识
							</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="sysId" title="系统标识" validate="string|1-20" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">系统名称</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="sysName" title="系统名称" reg="[]" validate="string|1-40" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">系统链接地址</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="sysUrl" title="系统链接地址" reg="[]" validate="string|1-200" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">系统注销地址</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="sysLogoutUrl" title="系统链接地址" reg="[]" validate="string|1-200" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">系统图片上传</td>
							<td style="float: left" valign="middle">
							<input type="file" name="上传图片"/>
							<!-- 	<input size="25" type="text" name="typeCode" title="系统路径" reg="[]" validate="string|1-40" /> -->
							</td>
						</tr>
						<tr height="100px">
							<td align="right" valign="middle">系统描述</td>
							<td style="float: left;">
								<textarea cols="20" rows="5" name="sysDescription" style="font-style: normal;" title="系统描述"
									validate="string|0-65"></textarea>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>