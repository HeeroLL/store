<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<btp:htmlbase/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/icons.css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css"	id="main-css" />
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
		<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="js/jsp/btp/log/errorlog_index.js"></script>
		<title>错误日志查询</title>
		<style type="">
			h2 {border-bottom: 1px solid;}
			html,body {background-color: #EEF7FE;overflow: hidden;}
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
							<form id="frm" action="btp/errorlog/findErrorLog.zb" method="post">
							<table border="0" cellpadding="1" cellspacing="1" align="center" style="float: left">
								<tr>
									<td align="right">类名：</td>
									<td>
										<input name="className" type="text" style="width: 110px;" maxlength="50" />
									</td>
									<td align="right">方法名：</td>
									<td>
										<input name="methodName" type="text" style="width: 110px;" maxlength="50" />
									</td>
									<td align="right">开始时间：</td>
									<td>
										<input name="beginCreateTime" type="text" style="width: 110px;" maxlength="50" onclick="WdatePicker({skin:'whyGreen'});" validate="date|1-20" readonly="readonly"/>
									</td>
									<td align="right">结束时间：</td>
									<td>
										<input name="endCreateTime" type="text" style="width: 110px;" maxlength="50" onclick="WdatePicker({skin:'whyGreen'});"  validate="date|1-20" readonly="readonly"/>
									</td>
									<td>
										<a class="btn" href="javascript:void(0)" onclick="doSearch()"> 
											<span class="btn-left"> 
												<span class="btn-text icon-search">查询</span> 
											</span> 
										</a>
										&nbsp;
										<a class="btn" href="javascript:void(0)" onclick="doSearchAll()"> 
											<span class="btn-left"> 
												<span class="btn-text icon-search">查询全部</span> 
											</span> 
										</a>
									</td>
								</tr>
							</table>
							</form>
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
		<div id="cHTML" style="display:none;">
			<textarea id="errorInfo" readonly="readonly" style="width:768px;height:90%;margin-top:10px;margin-left:10px;margin-right:10px"></textarea>
		</div>
	</body>
</html>
