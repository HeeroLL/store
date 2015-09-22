<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head> 
  	<btp:htmlbase/>
    <title>医疗工作人员角色分配页面</title>
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
<script type="text/javascript" src="js/jsp/btp/role/jsp.role_allot_right.js"></script>
<script type="text/javascript" src="js/jsp/dictionary.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <body>
<div id="main">
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">医疗工作人员查询</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left;margin-left: 18px">
					<tr>
						<td>机构内编号：</td>
						<td><input name="deptPersonnelCode" type="text" style="width: 110px;"/>
							<input type="hidden" name="mhoId" id="mhoId"/>
						</td>
						<td>&nbsp&nbsp&nbsp</td>
						<td>姓名：</td>
						<td><input name="fullname" type="text" style="width: 110px;"/></td>
					</tr>
					<tr>
						<td>职称：</td>
						<td>
						<select name="jobTitle" class="dict" dictType="1001" style="width: 110px;">
							<option value="">请选择</option>
						</select></td>
						<td>&nbsp&nbsp&nbsp</td>
						<td>人员类别：</td>
						<td>
						<select name="personnelType" class="dict" dictType="1001" style="width: 110px;">
							<option value="">请选择</option>
						</select>
						</td>
					</tr>
				</table>
				<div style="float: right;width: 110px;">
					<a class="btn" href="javascript:void(0);">
						<span class="btn-left" id="querybtn">
							<span class="btn-text icon-search">查询</span>
						</span>
					</a>
				</div>	
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>	
<div id="grid"></div>
</div>
</body>
</html>
