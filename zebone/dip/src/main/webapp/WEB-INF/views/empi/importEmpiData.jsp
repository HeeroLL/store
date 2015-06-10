<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="../css/icons.css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/main.css" id="main-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-grid.css" id="grid-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-dialog.css" id="dialog-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery.checkForm.css" id="checkform-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-button.css" id="button-css" />
		<script type="text/javascript" src="../js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.mark.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-grid.js"></script>
		<script type="text/javascript">
			function formSubmit(){
				$("form").submit();
			}
		</script>

		<title>Empi数据导入</title>
	</head>
	<body>
		<div class="container">
			<div class="c_nw">
				<div class="c_ne">
					<div class="c_n"></div>
				</div>
			</div>
			<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">Empi数据导入</span> </span> </span>
			<div class="tools-panel"></div>
			<div class="c_w">
				<div class="c_e">
					<div class="c_content">
						<br/>
						<form method="post" id="uploadForm" action="importEmpiData.zb" enctype="multipart/form-data">
							<table cellspacing="1" style="width: 100%;">
								<tbody>
									<tr>
										<td align="center" style="width: 50%;">
											数据类型：
											<select name="template">
												<option value="0">人口数据导入</option>
												<option value="1">社保信息导入</option>
												<option value="2">新农合数据导入</option>
												<option value="3">一卡通数据导入</option>
											</select>
										</td>
										<td align="center" style="width: 50%;">
											文件类型：
											<select name="fileType">
												<option value="excel">XSL(Excel文档)</option>
												<!-- <option value="csv">CSV</option>
												<option value="xml">XML</option>
												<option value="txt">TXT</option> -->
											</select>
										</td>
									</tr>
									<tr>
										<td align="center" style="width:50%;height:50px;" colspan="2">
											上传文档:&nbsp;&nbsp;&nbsp;
											<input type="file" name="file" style="width: 40%; border: 1px solid rgb(204, 204, 204);" />
										</td>
										 
										
									</tr>
									<tr>
										<td align="center" style="width: 40%; height:20px;" colspan="2">
											<a class="btn" href="javascript:formSubmit()"> <span class="btn-left"> <span class="btn-text btn-icon-left icon-add">导入</span> </span> </a>
										</td>
										
									</tr>
								</tbody>
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


	</body>
</html>