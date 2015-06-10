<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <btp:htmlbase/>
    <title>修改个人信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/icons.css" />
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
	<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jsp/btp/personnel/jsp.personnel_oneself_modify.js"></script>
	<script type="text/javascript" src="js/jsp/dictionary.js"></script>
<!--

//-->
	<style type="text/css">
	html,body{overflow: hidden;}
	</style>
  </head>
  
  <body>
    <div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">医疗人员身份信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
			<form id="formModify" action="btp/personnel/oneselfModifySave.zb" method="post" class="checkForm">
				<table cellspacing="10" style="width: 700px;" align="center">
					<tbody>
					<tr>
						<td align="right" style="width: 65px;">姓名：</td>
						<td align="left" style="width: 120px;">
							<input type="hidden" name="personnelId" id="personnelId" value="${personnel.personnelId}"/>
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="姓名" name="fullname" id="fullname" value="${personnel.fullname}"/>
						</td>
						
						<td align="right" style="width: 65px;">用户别名：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|0-20" title="用户别名" name="alias" id="alias" value="${personnel.alias}"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">手机号码：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="mobile|0-20" title="手机号码" name="mobile" id="mobile" value="${personnel.mobile}"/>
						</td>
						
						<td align="right" style="width: 65px;">性别：</td>
						<td align="left" style="width: 120px;">
							<select dictType="1001" validate="select" selectedValue="${personnel.sex}" style="width: 99%;"  title="性别" name="sex" id="sex">
							</select>
						</td>
					</tr>
						
					<tr>
						<td align="right" style="width: 65px;">办公电话：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="phone|0-18" title="办公电话" name="phone" id="phone" value="${personnel.phone}"/>
						</td>
						
						<td align="right" style="width: 65px;">籍贯：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="jiguan" style="width: 99%;" selectedValue="${personnel.nativePlace}" title="籍贯" id="nativePlace" name="nativePlace">
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">出生日期：</td>
						<td align="left" style="width: 120px;">
							
							<input type="text" class="Wdate" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="date|1-20" title="出生日期" onclick="WdatePicker({skin:'whyGreen'});"
							value = '<fmt:formatDate value="${personnel.birthday}" pattern="yyyy-MM-dd"/>' name="birthday" id="birthday"/>
						</td>
						
						<td align="right" style="width: 65px;">民族：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="1000" style="width: 99%;" title="民族" selectedValue="${personnel.nation}" name="nation" id='nation'>
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">文化程度：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="WH0001" style="width: 99%;" title="文化程度" selectedValue="${personnel.education}" name="education" id="education">
							</select>
						</td>
						
						<td align="right" style="width: 65px;">政治面貌：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="zhengzhimianmao" style="width: 99%;" title="政治面貌" selectedValue="${personnel.politicalStatus}" name="politicalStatus" id="politicalStatus">
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">职称：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="zhicheng" style="width: 99%;" title="职称" id="jobTitle" selectedValue="${personnel.jobTitle}" name="jobTitle" >
							</select>
						</td>
						
						<td align="right" style="width: 65px;">人员状态：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="ryzt" style="width: 99%;" title="民族" name="status" selectedValue="${personnel.status}"  id='status'>
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">QQ号： </td>
						<td align="left" style="width: 120px;">
							<input type="text" maxlength="15" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="number|0-20" title="QQ号" name="qq" id="qq" value="${personnel.qq}"/>
						</td>
						
						<td align="right" style="width: 65px;">email：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="email|0-20" title="email" name="email" id="email" value="${personnel.email}"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">联系地址：</td>
						<td colspan="3"><input type="text" maxlength="30" validate="string|0-100" style="width: 98%; border: 1px solid rgb(204, 204, 204);" title="联系地址" name="address" value="${personnel.address}"></td>
					</tr>
				</tbody>

				</table>
				</form>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
	<div class="button-bar" style="text-align: center;">
		<a class="btn" href="javascript:void(0);" onclick="doSubmit()">
			<span class="btn-left">
				<span class="btn-text icon-save">保存</span>
			</span>
		</a>
		<!--<a class="btn" href="javascript:void(0);">
			<span class="btn-left">
				<span class="btn-text icon-cancel">取消</span>
			</span>
		</a>
	--></div>
  </body>
</html>
