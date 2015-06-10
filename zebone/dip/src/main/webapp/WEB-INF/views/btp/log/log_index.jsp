<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<btp:htmlbase />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/icons.css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css"
			id="main-css" />
		<link rel="stylesheet" type="text/css"
			href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css" />
		<link rel="stylesheet" type="text/css"
			href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css" />
		<link rel="stylesheet" type="text/css"
			href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css" />
		<link rel="stylesheet" type="text/css"
			href="skin/${sessionScope.skin}/jquery-button.css" id="button-css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/css/jquery-tab.css" id="tab-css" />
		<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
		<script type="text/javascript" src="js/jsp/btp/log/log_index.js"></script>
		<title>审计日志列表</title>
		<style type="">
h2 {
	border-bottom: 1px solid;
}

html,body {
	background-color: #EEF7FE;
	overflow: hidden;
}
</style>
	</head>
	<body>
		<div id="main">
			<div class="container">
				<div class="c_nw">
					<div class="c_ne">
						<div class="c_n"></div>
					</div>
				</div>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1"
								align="center" style="float: left">
								<tr>
									<td align="right">
										IP地址：
									</td>
									<td>
										<input name="ipAddress" type="text" style="width: 110px;"
											maxlength="20" validate="string|0-20" />
									</td>
									<td align="right" style="width: 80px;">
										操作标识：
									</td>
									<td>
										<select id="opFlag" name="opFlag" style="width: 115px;">
											<option value="">
												请选择
											</option>
											<option value="1">
												新增
											</option>
											<option value="2">
												修改
											</option>
											<option value="3">
												删除
											</option>
											<option value="4">
												查询
											</option>
										</select>
									</td>
									<td align="right" style="width: 80px;">
										开始时间：
									</td>
									<td>
										<input name="beginTime" type="text" style="width: 110px;" />
									</td>
									<td align="right" style="width: 80px;">
										结束时间：
									</td>
									<td>
										<input name="endTime" type="text" style="width: 110px;" />
									</td>
									<td>
										<a class="btn" href="javascript:void(0)"> <span
											class="btn-left"> <span id="find"
												class="btn-text icon-search">查询</span> </span> </a>
									</td>
									<td>
										<a class="btn" href="javascript:void(0);"> <span
											class="btn-left" id="querybtn"> <span
												class="btn-text icon-search" id="findAll">查询全部</span>
										</a>
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
			<div id="grid"></div>
		</div>

	</body>
</html>
