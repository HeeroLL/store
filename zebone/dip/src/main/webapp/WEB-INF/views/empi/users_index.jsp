<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>数据字典类型管理</title>
		<base href="<%=basePath %>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="./css/icons.css" />
		<link rel="stylesheet" type="text/css" href="./skin/default/main.css" id="main-css"/>
		<link rel="stylesheet" type="text/css" href="./skin/default/jquery-grid.css"  id="grid-css"/>
		<link rel="stylesheet" type="text/css" href="./skin/default/jquery-dialog.css" id="dialog-css"/>
		<link rel="stylesheet" type="text/css" href="./skin/default/jquery.checkForm.css" id="checkform-css"/>
		<link rel="stylesheet" type="text/css" href="./skin/default/jquery-button.css" id="button-css"/>
		<link rel="stylesheet" type="text/css" href="./js/jquery/css/jquery-tab.css" id="tab-css"/>
		<link rel="stylesheet" href="./skin/green/jquery.window.css" type="text/css" id="windowCss" />
		<script type="text/javascript" src="./js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery-window.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="./js/jquery/jquery-tab.js"></script>
		<script type="text/javascript" src="./js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="./js/jsp/empi/jsp.users_index.js"></script>
		<style type="text/css">
			#cardTable{
				width:80%;
				margin-left:15%;
			}
			#cardTable td{
				text-align:center;
			}
		</style>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<!-- 查询界面 -->		
		<div id="main">
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">患者Empi查询</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left;margin-left:40px">
								<tr>
									<td>姓名：</td>
									<td width="80px"><input name="userName" type="text" title="姓名" validate="string|1-25"/></td>
									<td>Empi：</td>
									<td width="80px"><input name="empiId" type="text" title="EmpiID" validate="idcard|1-32"/></td>
								</tr>
								<tr>
									<td>民族：</td>
									<td width="80px"><input name="nation" type="text" title="民族" validate="string|1-5" /></td>
									<td>户籍：</td>
									<td width="80px"><input name="regaddress" type="text" title="户籍地址" validate="string|1-200"/></td>
								</tr>
							</table>
							<div style="float: right;width:110px; padding-top:20px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="importConfig">
										<span class="btn-text icon-tip" onclick="javascript:showConfigDlg();">参数设置</span>
									</span>
								</a>
							</div>	
							<div style="float: right;width:80px;padding-top:20px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="querybtn">
										<span class="btn-text btn-icon-left icon-search" id="find">查询</span>
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
		
		
		<!-- <div id="importdata"  style="display: none;" >
			<form method="post" id="uploadForm" action="empi/importEmpiData.zb" enctype="multipart/form-data">
				<div class="container">
				    <div style="margin-left:10%;width:85%; ">数据类型：
				   		<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="template"/>人口数据导入</span>
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="template"/>社保信息导入</span>
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="template"/>新农合数据倒入</span>
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="template"/>一卡通数据倒入</span>
					</div>
					<br/>
					<div style="margin-left:20%;width:60%; ">文件类型：
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="type" value="excel"/>XSL</span>
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="type" value="csv"/>CSV</span>
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="type" value="xml"/>XML</span>
						<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="type" value="txt"/>TXdT</span>
					</div>
					<br/>
					<div style="margin-left:20%;width:60%;">
						上传：<input type="file" name="file"/>
					</div>
					
				</div>
			</form>
		</div> -->
		
		
		<div id="configDiv"  style="display: none;" >
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">Empi参数设置</span>
					</span>
				</span>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<form method="post" action="empi/empi-config-operate.zb" id="configForm">
									
								    <div style="margin:20px 0 0 10%;width:100%; " >
										<span style="margin:0px 5px 0px 0px ; text-align:right; width:200px;">更新基本信息：</span>
										<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="updateEmpiFlag" value="1"/>是<input type="radio" name="updateEmpiFlag" value="0"/>否</span>
										&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
										<span style="margin:0px 5px 0px 0px ; text-align:right; width:200px;">更新证件信息：</span>
										<span style="margin:0px 5px 0px 0px ;"><input type="radio" name="updateCardFlag" value="1"/>是<input type="radio" name="updateCardFlag" value="0"/>否</span>
									</div>
									<br/>
									<div style="margin-left:20%;width:100%; ">
										<span style="margin:0px 5px 0px 0px ;">上传文件路径：</span>
										<input type="text" name="uploadPath" title="文件路径" validate="string|1-100"/>
									</div>
									<br/>
									<div style="margin-left:20%;width:60%;">
									<span style="margin:0px 17px 0px 10px ;">Empi类型：</span>
										<select name="empiType">
											<option value="1">身份证</option>
											<option value="2">军官证</option>
											<option value="3">护照</option>
										</select>
									</div>					
								 
							</form>
						</div>
					</div>
				</div>
				<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
			</div>
		</div>
		
		<div id='edit' style="display: none;" >
			<form id="userform" action="" class="checkForm">
				
				<div class="container">
					<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
					<span class="title-l">
						<span class="title-r">
							<b class="icon"></b><span class="title-span">患者基本信息</span>
						</span>
					</span>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
							
								<table id="mainTABLE" style="margin-right:5%;width: 95%;">
									<tr height="30px">
										<td align="right" valign="middle"  style="width:15%;">
											姓名：
										</td>
										<td style="float:left;width:45%;margin-top:6px;" >
											<input type="hidden" name="userId" value=""/>
											<input size="25" type="text" name="userName" title="姓名" validate="string|1-25" />
										</td>
										 
										<td align="center" valign="middle" rowspan="3" style="width:10%;">
											头像
										</td>
										<td align="left" valign="middle" rowspan="3" style="width:25%;">
											&nbsp;&nbsp;&nbsp;<img src="/btp3/images/wallpaper/green.jpg" height="100" width="100"/>
										</td>
									</tr>
									<!-- <tr height="30px">
										<td align="right" valign="middle">身份证</td>
										<td style="float: left" valign="middle">
											<input size="25" type="text" name="icn" title="身份证" reg="[]" validate="string|1-32" />
										</td>
										
									</tr> -->
									<tr height="30px">
										<td align="right" valign="middle">EmpiID：</td>
										<td style="float: left;" valign="middle">
											<input  style="width: 110px;" type="text" name="empiId" id="empiIdEdit" title="EmpiID" validate="idcard|0-32" />
											<select name="empiType" id="empiIdTypeEdit">
												<option value="1">身份证</option>
												<option value="2">军官证</option>
												<option value="3">护照</option>
											</select>
										</td>
									</tr>
									<tr height="30px">
										<td align="right" valign="middle">民族：</td>
										<td style="float: left;margin-top:6px;" valign="middle">
											<input size="5" type="text" name="nation" title="民族" validate="string|0-5" />&nbsp;&nbsp;性别：
													<select name="sex" id="sex">
														<option value="未知">未知</option>
														<option value="男">男</option>
														<option value="女">女</option>
													</select>
										</td>
										
									</tr>
									<tr height="30px">
										<td align="right" valign="middle">生日：</td>
										<td style="float: left" valign="middle">
											<input type="text" name="birthday" id="birthday" class="Wdate" onclick="WdatePicker({skin:'whyGreen'});"  title="生日" validate="string|0-20" />
										</td>
										<td align="right" valign="middle">电话：</td>
										<td style="float: left" valign="middle">
											<input type="text" name="tel" title="电话" validate="telmp|0-20" />
										</td>
										
										
										
									</tr>
									<tr height="20px">
										<td align="right" valign="middle">户籍地址：</td>
										<td style="float: left;" valign="middle" colspan="3">
											<input type="text" style="width:285%;" name="regaddress" title="户籍地址" validate="string|0-200" />
										</td>
									</tr>
									<tr height="20px">
										<td align="right" valign="middle">常住地址：</td>
										<td style="float: left;" valign="middle" colspan="3">
											<input style="width:285%;" type="text" name="preaddress" title="常住地址" validate="string|0-200" />
										</td>
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
							<b class="icon"></b><span class="title-span">添加患者证件信息</span>
						</span>
					</span>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<table id="cardTable" style="width:80%;margin-left:5%;text-align:center;"  cellspacing="0" cellpadding="0">
									
									<thead>
										<tr height="50px">
											<th width="60px">序号</th>
											<th width="100px">证件号</th>
											<th width="100px">证件序列号</th>
											<th width="50px">证件类型</th>
											<th width="70px">操作</th>
										</tr>
									</thead>
									<tbody id="cardTableBody">
									</tbody>
									<tfoot border="0px">
										<tr  border="0px">
											<td colspan="5"><a class="btn" href="javascript:appendCardInfo();"> <span class="btn-left"> <span class="btn-text btn-icon-left icon-add">新增</span> </span> </a></td>
										</tr>
									</tfoot>									
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