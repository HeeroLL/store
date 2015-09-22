<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head> 
  	<btp:htmlbase/>
    <title>医疗工作人员查询页面</title>
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
<script type="text/javascript" src="js/jsp/btp/personnel/jsp.personnel_main.js"></script>
<script type="text/javascript" src="js/jsp/dictionary.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jsp/btp/mho/jsp.mho_inner_tree.js"></script>
<style type="text/css">
html,body{overflow: hidden;overflow: hidden;}
</style>
  </head>
  <body>
  <jsp:useBean id="nowDate" class="java.util.Date"></jsp:useBean>
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
							<input type="hidden" name="mhoName"/>
						</td>
						<td>&nbsp&nbsp&nbsp</td>
						<td>姓名：</td>
						<td><input name="fullname" type="text" style="width: 110px;"/></td>
					</tr>
					<tr>
						<td>职称：</td>
						<td>
						<select id='jobTit' name="jobTitle" dictType="zhicheng" style="width: 110px;">
						</select></td>
						<td>&nbsp&nbsp&nbsp</td>
						<td>人员类别：</td>
						<td>
						<select name="personnelType" id="personType" dictType="rylx" style="width: 110px;">
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
<div id="edit" style="display: none" >
<form  id='fromEdit' method="post" class="checkForm">
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
						<td align="right" style="width: 65px;">姓名：</td>
						<td align="left" style="width: 120px;">
							<input type="hidden" name="personnelId" id="personnelId"/>
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="姓名" name="fullname" id="fullname"/>
						</td>
						
						<td align="right" style="width: 65px;">用户别名：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|0-20" title="用户别名" name="alias" id="alias"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">手机号码：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="mobile|0-20" title="手机号码" name="mobile" id="mobile"/>
						</td>
						
						<td align="right" style="width: 65px;">性别：</td>
						<td align="left" style="width: 120px;">
							<select dictType="1001" style="width: 99%;"  title="性别" name="sex" id="sex">
							</select>
						</td>
					</tr>
						
					<tr>
						<td align="right" style="width: 65px;">办公电话：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="phone|0-18" title="办公电话" name="phone" id="phone"/>
						</td>
						
						<td align="right" style="width: 65px;">籍贯：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="jiguan" style="width: 99%;" title="籍贯" id="nativePlace" name="nativePlace">
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">出生日期：</td>
						<td align="left" style="width: 120px;">
							<input type="text" class="Wdate" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="date|0-20" title="出生日期" onclick="WdatePicker({skin:'whyGreen'});" name="birthday" id="birthday"/>
						</td>
						
						<td align="right" style="width: 65px;">民族：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="1000" style="width: 99%;" title="民族" name="nation" id='nation'>
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">文化程度：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="WH0001" style="width: 99%;" title="文化程度" name="education" id="education">
							</select>
						</td>
						
						<td align="right" style="width: 65px;">政治面貌：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="zhengzhimianmao" style="width: 99%;" title="政治面貌" name="politicalStatus" id="politicalStatus">
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">职称：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="zhicheng" style="width: 99%;" title="职称" id="jobTitle" name="jobTitle" >
							</select>
						</td>
						
						<td align="right" style="width: 65px;">人员状态：</td>
						<td align="left" style="width: 120px;">
							<select  dictType="ryzt" style="width: 99%;" title="民族" name="status" id='status'>
							</select>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">QQ号： </td>
						<td align="left" style="width: 120px;">
							<input type="text" maxlength="15" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="number|0-20" title="QQ号" name="qq" id="qq"/>
						</td>
						
						<td align="right" style="width: 65px;">email：</td>
						<td align="left" style="width: 120px;">
							<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="email|0-20" title="email" name="email" id="email"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 65px;">联系地址：</td>
						<td colspan="3"><input type="text" maxlength="30" validate="string|0-100" style="width: 98%; border: 1px solid rgb(204, 204, 204);" title="联系地址" name="address"></td>
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
			<b class="icon"></b><span class="title-span">账号信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table cellspacing="10" style="width: 100%;">
					<tbody>
					<tr>
						<td align="right" style="width: 90px;">登录账号：</td>
						<td align="left" style="width: 120px;">
							<input type="text" maxlength="10" onblur="onblurEdit(this);" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="登录账号" name="loginName" id='loginName'/>
						</td>
						
						<td align="right" style="width: 90px;">账号密码：</td>
						<td align="left" style="width: 120px;">
							<input type="password" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-32" title="账号密码" name="password" id="password"/>
							<input type="hidden" name="oldePassword"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 90px;">账户启用日期：</td>
						<td align="left" style="width: 120px;">
							<input type="text" value = '<fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd"/>' validate="startDate" endDate="disableDate" class="Wdate" style="width: 95%; border: 1px solid rgb(204, 204, 204);"  title="账户启用日期" onclick="WdatePicker({skin:'whyGreen'});" name="enableDate" id="enableDate"/>
						</td>
						
						<td align="right" style="width: 90px;">账户作废日期：</td>
						<td align="left" style="width: 120px;">
							<input type="text" class="Wdate" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="date|1-20" title="账户作废日期" onclick="WdatePicker({skin:'whyGreen'});" name="disableDate" id="disableDate"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" style="width: 90px;">账户是否启用：</td>
						<td colspan="3">
							<input name='isAccountEnable' type="radio" value="0" />停用&nbsp&nbsp&nbsp
							<input name='isAccountEnable' type="radio" value="1" checked="checked"/>启用
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
			<b class="icon"></b><span class="title-span">关联机构</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table cellspacing="10" style="width: 600px;float: left;margin-left: 20px;" id='relation'>
					<tbody>
						<tr>
							<td style="width: 140px;">关联机构</td>
							<td style="width: 100px;">所属科室</td>
							<td style="width: 100px;">机构内编号</td>
							<td style="width: 80px;">人员类型</td>
							<td>
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id='addRelation'>
										<span class="btn-text icon-add">添加</span>
									</span>
								</a>
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
			<b class="icon"></b><span class="title-span">角色分配</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table align="center"  cellspacing="10" style="width: 500px;text-align: center;" id='roleAssignment'>
					<tbody>
					<tr>
						<td style="width: 200px;">待分配角色</td>
						<td style="width: 50px;"></td>
						<td style="width: 200px;">已分配角色</td>
					</tr>
					<tr>
						<td rowspan="6"><select style="width:100%;" multiple id="left" size="15" ondblclick="moveOption('left', 'right');">
										</select>
						</td>
						<td style="height: 30px;"></td>
						<td rowspan="6"><select style="width:100%;" multiple id="right" size="15" ondblclick="moveOption('right', 'left');">
										</select>
										<input type="hidden" name='haveRole' id='haveRole'/>
						</td>
					</tr>
					<tr>
						<td  style="height: 30px;">
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" onClick="moveAllOption('left', 'right');">
									<span class="btn-text">>>>> </span>
								</span>
							</a>
						</td>
					</tr>
					<tr>
						<td style="height: 30px;">
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" onClick="moveOption('left', 'right');">
									<span class="btn-text">&nbsp&nbsp&nbsp>>&nbsp&nbsp</span>
								</span>
							</a>
						</td>
					</tr>
					<tr>
						<td style="height: 30px;">
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" onClick="moveOption('right', 'left');">
									<span class="btn-text">&nbsp&nbsp&nbsp<<&nbsp&nbsp</span>
								</span>
							</a>
						</td>
					</tr>
					<tr>
						<td style="height: 30px;">
							<a class="btn" href="javascript:void(0);">
								<span class="btn-left" onClick="moveAllOption('right', 'left');">
									<span class="btn-text"><<<<</span>
								</span>
							</a>
						</td>
					</tr>
					<tr><td style="height: 30px;"></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</form>
</div>
<div>
	<table id="tempTemplate" style="display: none"> 
		<tr>
			<td><input type="text" name='mhoName'  onclick="orgTreeChoice(this,'radio');" validate="string|1-30" style="width: 95%; border: 1px solid rgb(204, 204, 204);"/>
				<input type="hidden" name='mhoId'  value=''/>
			</td>
			<td>
				<select  dictType="yiyuankeshi" validate="select" style="width: 99%;" name='department' id='department'>
				</select>
			</td>
			<td><input type="text" id="deptPersonnelCode" validate="string|0-10" name='deptPersonnelCode' style="width: 95%; border: 1px solid rgb(204, 204, 204);"/></td>
			<td>
				<select  dictType="rylx" style="width: 99%;" name='personnelType' id='personnelType'>
				</select>
			</td>
			<td><a class="btn" href="javascript:void(0);">
					<span class="btn-left" onclick='deleteRelation(this)'>
						<span class="btn-text icon-cancel">删除</span>
					</span>
				</a>
			</td>
		</tr>
	</table>
</div>
  </body>
</html>
