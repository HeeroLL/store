<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>数据字典类型管理</title>
		<btp:htmlbase/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/icons.css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
	    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
	    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>

		<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.mask.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
		<script type="text/javascript" src="js/jsp/btp/dict/jsp.dictionaryType.js"></script>
		<script type="text/javascript">
			var parentId = '${parentId}';
			var pTypeName = '字典类型' ;
			if('' != '${pTypeName}'){
				pTypeName = '${pTypeName}';
			}
		</script>
	</head>
	<body>
		<!-- 查询界面 -->		
		<div id="main">
			<div class="container">
				<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
				<span class="title-l">
					<span class="title-r">
						<b class="icon"></b><span class="title-span">字典类型查询</span>
					</span>
				</span>
				<div class="tools-panel"></div>
				<div class="c_w">
					<div class="c_e">
						<div class="c_content">
							<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left;margin-left: 18px">
								<tr>
									<td>数据字典类型编码：</td>
									<td><input name="typeCode" type="text" />
									</td>
									<td>&nbsp;&nbsp;&nbsp;</td>
									<td>数据字典类型名称：</td>
									<td><input name="typeName" type="text" />
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
		<div id="grid"></div>

		<div id='edit' style="display: none;">
			<form id="dictionaryTypeForm" action="" class="checkForm">
				<div class="container">
					<div class="tools-panel"></div>
					<table id="mainTABLE" cellspacing="10" style="width: 100%;">
						<tr height="30px">
							<td align="right" valign="middle">
								数据字典类型名称：
							</td>
							<td style="float: left" valign="middle">
								<input type="hidden" name="isDeleted" />
								<input type="hidden" name="typeId" id="typeId" />
								<input type="hidden" name="isDeleted" id="isDeleted" />
								<input size="25" type="text" name="typeName" title="字典类型名称"	validate="string|1-20|btp/dict/dictTypeValidate.zb,typeId" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">数据字典类型编码：</td>
							<td style="float: left" valign="middle">
								<input size="25" type="text" name="typeCode" title="字典类型编码" reg="[]"
									validate="string|1-40|btp/dict/dictTypeValidate.zb,typeId" />
							</td>
						</tr>
						<tr height="30px">
							<td align="right" valign="middle">上级数据字典类型：</td>
							<td style="float: left" valign="middle">
								<select id="parentId" name="parentId" style="width: 25;">
									<option value="">
										请选择
									</option>
									<c:forEach var="dType" items="${dType}">
										<option value="${dType.typeId}" <c:if test="${parentId==dType.typeId}"><c:out value="selected"/></c:if>>${dType.typeName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right" valign="middle">数据字典类型描述：</td>
							<td style="float: left;">
								<textarea cols="20" rows="5" name="remark" style="font-style: normal;" title="字典类型描述"
									validate="string|0-65"></textarea>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>