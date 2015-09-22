<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>机构树</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jsp/btp/authorize/jsp.module_role_right_tree.js"></script>
<style type="text/css">
html,body{
background-color: #EEF7FE;
overflow: hidden;
height: auto;
}
</style>
</head>
<body>
<div class="container" style="width: 650px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content" style="margin-top: -15px;">
				<div style="width: 650px;">
				<div class="container" style="width: 300px;float: left;">
					<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
					<span class="title-l">
						<span class="title-r">
							<b class="icon"></b><span class="title-span">角色信息</span>
						</span>
					</span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<div id="roleDiv"></div>
							</div>
						</div>
					</div>
					<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
				</div>
				<div class="container" style="width: 300px;float: left;">
					<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
					<span class="title-l">
						<span class="title-r">
							<b class="icon"></b><span class="title-span">模块信息</span>
						</span>
					</span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<div id="module_tree" class="ztree" style="overflow: auto;"></div>
							</div>
						</div>
					</div>
					<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
				</div>
				</div>
				<div style="width: 600px;">
				<a class="btn" href="javascript:void(0);" style="margin-left: 270px;">
					<span class="btn-left">
						<span class="btn-text icon-save" id="saveModuleRole">保存</span>
					</span>
				</a>
				</div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
