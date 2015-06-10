<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<btp:htmlbase/>
		<link rel="stylesheet" type="text/css" href="css/icons.css" />
		<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css" />
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
		<title>标准数据字典管理</title>
		<script type="text/javascript" src="js/jsp/dip/dict/dict_main.js"></script>
		<script type="text/javascript">
			var dicttypeId = '${typeId}';
			var typeName = '${typeName}';
		</script>
	</head>
	<body>
		<!-- 查询界面  -->
		<div id="main">
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">标准数据字典查询</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1" align="center" style="float: left">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;数据字典编号：</td>
									<td><input name="dictCode" type="text" style="width: 110px;" /></td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;数据字典名称：</td>
									<td>
										<input name="dictName" type="text" style="width: 110px;" />
									</td>
								</tr>
							</table>
							<div style="float: right;width: 110px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="querybtn">
										<span class="btn-text icon-search" id="findAll">查询全部</span>
									</span>
								</a>
							</div>
							<div style="float: right;width: 110px;">
								<a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="querybtn">
										<span class="btn-text icon-search" id="find">查询</span>
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
		<div id="grid" style="margin-left: 10px;margin-right: 10px;"></div>

		<!-- 数据字典编辑 -->
		<div id='edit' style="display: none;">
			<form id="dictionaryForm" action="" class="checkForm">
				<div class="container">
					<div class="tools-panel"></div>
					<table id="mainTABLE" cellspacing="10" style="width: 100%;">
						<tr height="30px">
							<td>
								数据字典名称：
							</td>
							<td id="addDictName">
								<input name="dicttypeId" type="hidden" id="dicttypeId" value="${dicttypeId}" />
								<input type="hidden" name="dictId" id="dictId" />
								<input type="text" name="dictName" id="dictName" title="数据字典名称" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|1-40"/>
							</td>
						</tr>
						<tr height="30px">
							<td>
								数据字典编码：
							</td>
							<td style="float: left">
								<input size="25" type="text" name="dictCode" title="数据字典编码" validate="string|1-40" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]"/>
							</td>
						</tr>
						<tr>
							<td>数据字典描述：</td>
							<td>
								<textarea cols="20" rows="5" name="remark" style="font-style: normal; width: 95%;" msg="输入格式不正确,不应包含特殊字符 '$\ " reg="['$\\]" validate="string|0-65" title="数据字典描述"></textarea>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>