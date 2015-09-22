<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="<%=basePath %>" />
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
<script type="text/javascript" src="./js/jsp/empi/jsp.cards_index.js"></script>
<title>增删改查</title>
<style type="">
h2{ border-bottom: 1px solid;}
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
<div id="main">
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">患者基本信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				
				<table id="userDetailTab"  border="0" cellpadding="1" cellspacing="1" align="center" style="width:100%;">
					<tr>
						<td align="right">姓名：</td>
						<td>
							<input type="hidden" name="empiId" id="empiId" value=""/> 
							<input id="userName" name="userName" type="text" style="width: 110px;" maxlength="25" validate="string|0-25" value="" disabled/>
						</td>
						<td align="center" valign="middle" rowspan="3" >
								头像:
						</td>
						<td align="left" valign="middle" rowspan="3">
							<img src="/btp3/images/wallpaper/green.jpg" height="80" width="100"/>
						</td>
					</tr>
					<tr>
						
						<td align="right">EmpiId：</td>
						<td><input name="empiId" id="detailEmpiId" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td></td><td></td>
					</tr>
					<tr>
						<td align="right">生日：</td>
						<td><input name="birthday" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td></td><td></td>
					</tr>
					<tr>
						<td align="right">身份证：</td>
						<td><input name="icn" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td align="right" style="width: 80px;">电话：</td>
						<td><input name="tel" type="text" style="width: 110px;" maxlength="20" value="" disabled/></td>
						 
					</tr>
					<tr>
						<td align="right">民族：</td>
						<td><input name="nation" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td align="right" style="width: 80px;">性别：</td>
						<td><input name="sex" type="text" style="width: 110px;" value="" disabled/></td>
						 
					</tr>
					<tr>
						
						<td align="right">户籍地址：</td>
						<td><input name="regaddress" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td align="right" style="width: 80px;">常住地址：</td>
						<td><input name="preaddress" type="text" style="width: 110px;" value="" disabled/></td>

						
					</tr>
					<tr>
						<td align="right">创建时间：</td>
						<td><input name="createDate" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td align="right" style="width: 80px;">更新时间：</td>
						<td><input name="updateDate" type="text" style="width: 110px;" value="" disabled/></td>

					 
					</tr>
					<tr>
						<td align="right">创建人：</td>
						<td><input name="creator" type="text" style="width: 110px;" value="" disabled/>
						</td>
						<td align="right" style="width: 80px;">更新人：</td>
						<td><input name="updator" type="text" style="width: 110px;" value="" disabled/></td>
 
					</tr>
					
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
			<b class="icon"></b><span class="title-span">证件列表</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="cardGrid"></div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>

<!-- 用于修改或者新增card -->
<div id='edit' style="display: none;" >
			<form id="userform" action="" class="checkForm">
				<div class="container">
					<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
					<span class="title-l">
						<span class="title-r">
							<b class="icon"></b><span class="title-span">证件信息</span>
						</span>
					</span>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<table id="mainTABLE" cellspacing="10"  style="width: 100%;">
									<tr height="25px">
										<td align="right" valign="middle" >EmpiID：</td>
										<td style="float: left" valign="middle">
											<input size="25" type="text" name="empiId" id="formEmpiId" title="EmpiID" disabled />
											<input size="25" type="hidden" name="cardId" value=""/>
										</td>
									</tr>  
									<tr height="25px">
										<td align="right" valign="middle">证件号码：</td>
										<td style="float: left" valign="middle">
											<input size="25" type="text" name="cardNo" title="证件号码" validate="string|1-52" />
										</td>
										
									</tr>
									<tr height="25px">
										<td align="right" valign="middle">证件序列号：</td>
										<td style="float: left" valign="middle">
											<input size="25" type="text" name="cardSid" title="证件序列号" validate="number|0-10" />
										</td>
										
									</tr>
									<tr height="30px">
										<td align="right" valign="middle">证件类型：</td>
										<td style="float: left" valign="middle">
										<select name="cardType">
											<option value="N0002">军官证</option>
											<option value="N0003">护照</option>
											<option value="N0004">就诊卡</option>
											<option value="N0005">社保卡</option>
											<option value="N0006">新农合证/卡</option>
											<option value="N0007">市民卡</option>
											<option value="N0008">健康卡</option>
											<option value="N0009">保健卡</option>
										</select>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					
					<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
					
				</div>
				 
			</form>
		</div>


</body>
</html>