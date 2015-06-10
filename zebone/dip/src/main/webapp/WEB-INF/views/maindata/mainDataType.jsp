<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<btp:htmlbase/>
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
		<script type="text/javascript" src="js/mdm/jsp.mainDataType.js"></script>
		<script type="text/javascript">
			var mdtType = '${mdtType}';
		</script>
		<title>主数据类型管理</title>
	</head>
	<body>
		<div class="container">
			<div class="c_nw">
				<div class="c_ne">
					<div class="c_n"></div>
				</div>
			</div>
			<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">【${mdtName}】管理</span> </span> </span>
			<div class="tools-panel"></div>
			<div class="c_w">
				<div class="c_e">
					<div class="c_content">
						<table id="query" border="0" cellpadding="1" cellspacing="1" style="float: left; margin-left: 18px">
							<tr>
								<td align="right">
									&nbsp&nbsp&nbsp&nbsp编码：
								</td>
								<td>
									<input name="mdtCode" type="text" style="width: 110px;" maxlength="25" validate="string|0-25" />
								</td>
								<td align="right">
									&nbsp&nbsp&nbsp&nbsp名称：
								</td>
								<td>
									<input name="mdtName" type="text" style="width: 110px;" maxlength="25" validate="string|0-25" />
								</td>
								<td align="right">
									&nbsp&nbsp&nbsp&nbsp类型：
								</td>
								<td>
									<input name="mdtType" type="text" style="width: 110px;" maxlength="25" validate="string|0-25" />
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
					<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">类型字段信息</span> </span> </span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div id="basicInformation" class="c_content">
								<table cellspacing="10" style="width: 600px; float: left; margin-left: 20px;">
									<tbody>
										<tr>
											<td align="right" style="width: 65px;">
												类型名称：
											</td>
											<td align="left" style="width: 120px;">
												<input type="text" title="类型名称" name="mdtName" id="mdtName" />
												<input type="hidden" title="类型名称" name="mdtParentId" id="mdtParentId" value='${mdtId}' />
											</td>
											<td align="right" style="width: 65px;">
												父类型：
											</td>
											<td align="left" style="width: 120px;">
												<select name="mdtType" id="mdtType" onchange="selectChange(this)">
													<c:forEach var="dType" items="${typeList}">
														<option value="${dType.mdtType}" <c:if test="${mdtType==dType.mdtType}"><c:out value="selected"/></c:if>>
															${dType.mdtName}
														</option>
													</c:forEach>
												</select>
											</td>
										</tr>
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
				<div id="basicinfo" class="container">
					<div class="c_nw">
						<div class="c_ne">
							<div class="c_n"></div>
						</div>
					</div>
					<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">类型基本信息</span> </span> </span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<table cellspacing="10" style="width: 600px; float: left; margin-left: 20px;">
									<tbody>
										<tr>
											<td align="right" style="width: 65px;">
												类型编码：
												<br>
												(自动生成)
											</td>
											<td align="left" style="width: 120px;">
												<input type="text" title="类型编码" name="mdtCode" id="mdtCode" readonly="readonly" />
											</td>
											<td align="right" style="width: 65px;">
												表名（英文）：
											</td>
											<td align="left" style="width: 120px;">
												<input type="text" title="表名" name="mdtTablename" id="mdtTablename" />
											</td>
										</tr>
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
				<div id="fieldinfo" class="container">
					<div class="c_nw">
						<div class="c_ne">
							<div class="c_n"></div>
						</div>
					</div>
					<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">类型字段信息</span> </span> </span>
					<div class="tools-panel"></div>
					<div class="c_w">
						<div class="c_e">
							<div class="c_content">
								<table cellspacing="10" style="width: 600px; float: left; margin-left: 20px;" id='relation'>
									<tbody>
										<tr>
											<td style="width: 120px;">
												字段名（英文字母）
											</td>
											<td style="width: 70px;">
												字段类型
											</td>
											<td style="width: 60px;">
												字段显示
											</td>
											<td style="width: 150px;">
												字段释义
											</td>
											<td style="">
												<a class="btn" href="javascript:void(0);"> <span class="btn-left" id='addRelation'> <span class="btn-text btn-icon-left icon-add"></span> </span> </a>
											</td>
										</tr>
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
		<div>
			<table id="tempTemplateDefault" style="display: none">
				<tr>
					<td>
						<input type="hidden" id="field" name="field" value="mdtid" />
						<input type="hidden" id="fieldType" name="fieldType" value="varchar2(50)" />
						<input type="hidden" id="visible" name="visible" value="n" />
						<input type="hidden" id="comment" name="comment" value="ID Describtion" />
					</td>
				</tr>
			</table>
			<table id="tempTemplate" style="display: none">
				<tr>
					<td>
						<input type="text" id="field" name="field" />
					</td>
					<td>
						<select name="fieldType" id="fieldType">
							<option value="varchar2(50)">
								字符型50
							</option>
							<option value="varchar2(100)">
								字符型100
							</option>
							<option value="varchar2(200)">
								字符型200
							</option>
						</select>
					</td>
					<td>
						<select name="visible" id="visible">
							<option value="y">
								显示
							</option>
							<option value="n">
								隐藏
							</option>
						</select>
					</td>
					<td>
						<input type="text" id="comment" name="comment" />
					</td>
					<td id = "buttonid">
						<a class="btn" href="javascript:void(0);"> <span class="btn-left" onclick='deleteRelation(this)'> <span class="btn-text btn-icon-left icon-cancel"></span> </span> </a>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table id="tempTemplateLoad" style="display: none">
				<tr>
					<td>
						<input type="text" id="field" name="field" readonly="readonly" />
					</td>
					<td>
						<select name="fieldType" id="fieldType">
							<option value="varchar2(50)">
								字符型50
							</option>
							<option value="varchar2(100)">
								字符型100
							</option>
							<option value="varchar2(200)">
								字符型200
							</option>
						</select>
					</td>
					<td>
						<select name="visible" id="visible">
							<option value="y">
								显示
							</option>
							<option value="n">
								隐藏
							</option>
						</select>
					</td>
					<td>
						<input type="text" id="comment" name="comment" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>