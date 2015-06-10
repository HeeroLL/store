<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
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
	<script type="text/javascript" src="js/jsp/btp/role/jsp.role_allot_update.js"></script>
	<script type="text/javascript" src="js/jsp/dictionary.js"></script>
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
				<table cellspacing="10" style="width: 100%;" id="check">
					<tbody><tr>
						<td align="right" style="width: 65px;">姓名：</td>
						<td align="left" style="width: 120px;">
							<input type="hidden" name="workerId" id="workerId" value="${person.personnelId}"/>
							<input name="fullname" type="text" maxlength="10" onblur="onblurEdit(this);" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="姓名" value="${person.fullname}"/>
						</td>
						<td align="right" style="width: 65px;">出生日期：</td>
						<td align="left" style="width: 120px;">
							<input name="birthday" id="dateOfBirth" type="text" class="Wdate" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="date|1-20" title="出生日期" onclick="WdatePicker({skin:'whyGreen'});" value="${person.birthday}"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 65px;">性别：</td>
						<td align="left" style="width: 120px;">
							<select id="sex" name="sex" class="dict" dictType="1001" validate="select" style="width: 99%; border: 1px solid rgb(204, 204, 204);" title="性别" >
									<option value="-1">请选择</option>
							</select>
						</td>
						
						<td align="right" style="width: 65px;">手机号码：</td>
						<td align="left" style="width: 120px;">
							<input name="mobile" id="mobile" type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="mobile|0-20" title="手机号码" value="${person.mobile}"/>
						</td>
					</tr>
						
					<tr>
						<td align="right" style="width: 65px;">办公电话：</td>
						<td align="left" style="width: 120px;">
							<input name="phone" id="phone" type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="phone|0-18" title="办公电话" value="${person.phone}"/>
						</td>
						
						<td align="right" style="width: 65px;">籍贯：</td>
						<td align="left" style="width: 120px;">
							<select id="nativePlace" class="dict" dictType="jiguan" name="nativePlace" style="width: 99%;" title="籍贯" >
								<option value="-1">请选择</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 65px;">文化程度：</td>
						<td align="left" style="width: 120px;">
							<select id="education" name="education" class="dict" dictType="WH0001" style="width: 99%;" title="文化程度" >
								<option value="-1">请选择</option>
							</select>
						</td>
						<td align="right" style="width: 65px;">政治面貌：</td>
						<td align="left" style="width: 120px;">
							<select name="politicalStatus" style="width: 99%;" title="政治面貌" >
								<option value="-1">请选择</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 65px;">职称：</td>
						<td align="left" style="width: 120px;">
							<select id="jobTitle" class="dict" dictType="yishengzhicheng" style="width: 99%;" title="职称" name="jobTitle">
								<option value="-1">请选择</option>
							</select>
						</td>
						<td align="right" style="width: 65px;">民族：</td>
						<td align="left" style="width: 120px;">
							<select style="width: 99%;" title="民族" name="nation" id="nation" class="dict" dictType="1000">
								<option value="-1">请选择</option>
								<option selected="selected" value="1">汉族</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 65px;">QQ号： </td>
						<td align="left" style="width: 120px;">
							<input type="text" maxlength="15" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="number|0-20" title="QQ号" name="qq" id="qq" value="${person.qq}"/>
						</td>
						
						<td align="right" style="width: 65px;">MSN：</td>
						<td align="left" style="width: 120px;">
							<input type="text" validate="email|0-20" style="width: 95%; border: 1px solid rgb(204, 204, 204);" title="MSN" name="workerMsn" id="workerMsn" />
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 65px;">email：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="email|0-20" title="email" name="email" id="email" value="${person.email}"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">联系地址：</td>
						<td colspan="3">
							<input type="text" maxlength="30" validate="string|0-100" style="width: 98%; border: 1px solid rgb(204, 204, 204);" title="联系地址" name="address" value="${person.address}"/>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
	
	
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
				<table cellspacing="10" style="width: 100%;"> 
					<tbody>
						<tr>
								<td width="200">
								<div class="container" style="width:300px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">拥有的角色</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<select style="width:100%;" multiple id="left" size="15"
					ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">
						<c:forEach items="${roleOwnerList}" var="role"> 
							<option value="${role.roleId}">${role.name}</option>
						</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
									
								</td>
								<td width="90" align="center" >
								<div class="container" style="width:80px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span"></span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<input type="button" value="  >>>>  " onClick="moveAllOption(document.getElementById('left'), document.getElementById('right'))"/>
									
				<input type="button" value="    >>    " onClick="moveOption(document.getElementById('left'), document.getElementById('right'))"/>
				
				<input type="button" value="    <<    " onClick="moveOption(document.getElementById('right'), document.getElementById('left'))"/>
				
				<input type="button" value="  <<<<  " onClick="moveAllOption(document.getElementById('right'), document.getElementById('left'))"/>
				
				
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
								</td>
								<td width="200">
								
								<div class="container" style="width:300px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">可以得到的角色</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
			<select style="width:100%;" multiple id="right" size="15"
					ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
						<c:forEach items="${roleNoList}" var="role">
								<option value="${role.roleId}">${role.name}</option>
						</c:forEach>
			</select>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
								</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>		
  </body>
</html>
