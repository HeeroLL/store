<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>        
<% 
	String id = request.getParameter("id");
	if(id==null||id.equals("")) id = "0";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
	<head>	
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
		<title>患者信息管理</title>
		<script type="text/javascript" src="merge/jsp.index.js"></script>
		<script type="text/javascript">
			var x = '<%=id%>';			
		</script>
	</head>
	<body>
		<!-- 查询界面  -->
		<div id="main">
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">主索引合并</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1" align="center" style="float: left">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;姓名：</td>
									<td><input name="dictCode" type="text" style="width: 110px;" /></td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;性别：</td>
									<td>
										<select>
											<option value="0">全部</option>
											<option value="1">男</option>
											<option value="2">女</option>
										</select>
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;证件类型：</td>
									<td><select>
										<option value="0">无</option>
										<option value="1">身份证</option>
										<option value="2">军官证</option>
										<option value="3">护照</option>
									</select></td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;证件号：</td>
									<td>
										<input name="dictName" type="text" style="width: 110px;" />
									</td>
								</tr>
							</table>
							<div style="float: right;width: 110px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="hbbtn">
										<span class="btn-text" id="hb">合并</span>
									</span>
								</a>
							</div>
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
		<div id="grid" style="margin-left: 10px;margin-right: 10px;"></div>
	<form action="merge/detail.jsp" method="post" id="detailForm">
		<input type="hidden" name="hzzsys" value="">
	</form>
	</body>	
</html>