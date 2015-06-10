<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.zebone.btp.mdm.vo.MainDataTypeVO"%>
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
		<script type="text/javascript" src="../js/jquery/jquery-checkForm.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.stopBubble.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-button.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-tab.js"></script>
		<script type="text/javascript" src="../js/mdm/jsp.maindata.js"></script>
		<script type="text/javascript">
			//var name = '${mdt.mdtName}';
			var fieldsV = '${mdt.mdtFields}';
			var dbTableName = '${mdt.mdtTablename}';
			var commentsV = '${mdt.mdtComments}';
			var visible = '${mdt.mdtVisible}';
			var corres = '${mdt.mdtCorres}';
		</script>
		<title>主数据管理</title>
	</head>
	<body>
		<div class="container">
			<div class="c_nw">
				<div class="c_ne">
					<div class="c_n"></div>
				</div>
			</div>
			<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">【${mdt.mdtName}】管理</span> </span> </span>
			<div class="tools-panel"></div>
			<div class="c_w">
				<div class="c_e">
					<div class="c_content">
						<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left; margin-left: 18px">
							<tr>
								<td align="right">
									&nbsp&nbsp&nbsp&nbsp名称：
								</td>
								<td>
									<input name="mdNameValue" type="text" style="width: 110px;" maxlength="25" validate="string|0-25" />
								</td>
							</tr>
						</table>
						<div style="float: right; width: 110px;">
							<a class="btn" href="javascript:void(0);"> <span class="btn-left" id="querybtn"> <span class="btn-text btn-icon-left icon-search">查询</span> </span> </a>
						</div>
					</div>
				</div>
			</div>
			<div class="c_sw">
				<div class="c_se">
					<div class="c_s"></div>
				</div>
			</div>
		</div>
		<div id="grid"></div>
		<div id="edit" style="display: none">
			<form id='fromEdit' method="post" class="checkForm">
				<div class="container">
					<div class="c_nw">
						<div class="c_ne">
							<div class="c_n"></div>
						</div>
					</div>
					<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">【${mdt.mdtName}】</span> </span> </span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<table cellspacing="10" style="width: 100%;">
									<tbody>
										<%
											MainDataTypeVO vo = (MainDataTypeVO) request.getAttribute("mdt");
											//String[] fArray = vo.getMdtFields().toLowerCase().split(",");
											String[] cArray = vo.getMdtComments().split(",");
											String[] vArray = vo.getMdtVisible().split(",");
											if(!StringUtils.isEmpty(vo.getMdtCorres())){
											String[] corresArray = vo.getMdtCorres().split(",");
											for (int j = 0; j < corresArray.length; j++) {
												if ("n".equalsIgnoreCase(vArray[j].trim())) {
										%>
										<tr>
											<td align="right" style="width: 80px;">
												<input type="hidden" name='<%=corresArray[j].trim()%>' id='<%=corresArray[j].trim()%>'/>
											</td>
										</tr>
										<%
											} else {
										%>
										<tr>
											<td align="right" style="width: 80px;">
												<%=cArray[j].trim()%>：
											</td>
											<td align="left" style="width: 160px;">
												<input type="text" style="width: 95%; border: 1px solid rgb(204, 204, 204);" title='<%=cArray[j].trim()%>' name='<%=corresArray[j].trim()%>' id='<%=corresArray[j].trim()%>' />
											</td>
										</tr>
										<%
											}
											}
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="c_sw">
						<div class="c_se">
							<div class="c_s"></div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>