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
			function goBackIndex(){
				window.parent.closeWindowRefresh();
			}
		</script>

		<title>Empi数据导入结果</title>
	</head>
	<body>
		<div class="container">
			<div class="c_nw">
				<div class="c_ne">
					<div class="c_n"></div>
				</div>
			</div>
			<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">Empi数据导入跳转</span> </span> </span>
			<div class="tools-panel"></div>
			<div class="c_w">
				<div class="c_e">
					<div class="c_content" style="text-align:center;">
						<br/>
						<h2 align="center">文件数据处理完毕</h2>
						<a class="btn" href="javascript:goBackIndex();"> <span class="btn-left"> <span class="btn-text  btn-icon-left icon-undo">返回主页面</span> </span> </a>
						<a class="btn" href="#"> <span class="btn-left"> <span class="btn-text btn-icon-left icon-search">查看错误记录</span> </span> </a>
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