<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="js/jsp/btp/mho/jsp.mho_edit.js"></script>
<script type="text/javascript" src="js/jsp/btp/mho/jsp.mho_inner_tree.js"></script>
<script type="text/javascript" src="js/jsp/dictionary.js"></script>
<script type="text/javascript">
var parentId='${parentId}',
var mhoId='${mhoId}',
var mho='${mho}',
var mhoName='${mho.mhoName}'
</script>
<title>医疗机构管理</title>
<style type="">
html,body{background-color: #EEF7FE;overflow: hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">医疗机构信息</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div class="mhoForm" id="mhoForm">
				<table id="mainTABLE" width="100%" border="0" cellpadding="1"
						cellspacing="1" align="center" height="200px">
			    <tr>
                  <td style="width: 130px;" align="right">机构名称：</td>
					<td style="width: 140px;">
						<input type="hidden" id="mhoId" name="mhoId" value="${mhoId}"/>
					    <input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
					    <input type="hidden" id="levelCode" name="levelCode" value="${levelCode}"/>
						<input type="text" maxlength="40" id="mhoName" name="mhoName"	title="机构名称" style="width: 95%;" value="${mho.mhoName}"/>
					</td>
					<td style="width: 130px;" align="right">机构类型：</td> 
					<td style="width: 180px;">
						<select id="typeCode" name="typeCode" dictType="jigouleixing" selectedValue="${mho.typeCode}" validate="select" style="width: 95%;"> 
							
						</select> 
					</td>
				 </tr>
				 <tr>
						<td style="width: 130px;" align="right">医院性质：</td>
						<td style="width: 180px;"><select id="hospitalNature" name="hospitalNature" dictType="yiliaojigousuoyouzhixingzhi" selectedValue="${mho.hospitalNature}" validate="select" style="width: 95%;" ></select>
						</td>
						<td style="width: 130px;" align="right">医院等级：</td>
						<td style="width: 180px;"><select id="hospitalGrade" name="hospitalGrade" dictType="yiliaojigouidengji" selectedValue="${mho.hospitalGrade}" validate="select" style="width: 95%;"></select>
						</td>
						
					</tr>
				  <tr>
					<td style="width: 130px;" align="right">负责人：</td>
					<td style="width: 140px;">
						<input type="text" name="manager" title="负责人 " value="${mho.manager}" validate="string|0-10" style="width: 95%;"/>
					</td>
					<td style="width: 130px;" align="right">联系电话：</td>
					<td style="width: 140px;">
						<input type="text" name="phone" title="联系电话" validate="telmp|0-18" style="width: 95%;" value="${mho.phone}"/>
					</td>
				  </tr>
				    	<tr>
				  <td style="width: 130px;" align="right">面积：</td>
					<td style="width: 140px;">
						<input type="text" name="area"  title="面积" style="width: 88%;" value="${mho.area}"/>m²
					</td>
					</tr>
				  <tr>
					<td style="width: 130px;" align="right">是否新农合医保定点：</td>
					<c:if test="${mho.isNcms==1}">
					<td style="width: 180px;">
						<div title="是否新农合医保定点" name="isNcms" style="width: 92%;"> 
							<label style="width: 60px;"><input type="radio" name="isNcms" value="1" checked="checked" />是</label>  
							<label style="width: 60px;"><input type="radio" name="isNcms" value="0"  />否</label> 
						</div> 
					</td>
					</c:if>
					<c:if test="${mho.isNcms==0}">
					<td style="width: 180px;">
						<div title="是否新农合医保定点" name="isNcms" style="width: 92%;"> 
							<label style="width: 60px;"><input type="radio" name="isNcms" value="1" />是</label>  
							<label style="width: 60px;"><input type="radio" name="isNcms" value="0" checked="checked" />否</label> 
						</div> 
					</td>
					</c:if>
					<c:if test="${mho.isNcms==null}">
					<td style="width: 180px;">
						<div title="是否新农合医保定点" name="isNcms" style="width: 92%;"> 
							<label style="width: 60px;"><input type="radio" name="isNcms" value="1" />是</label>  
							<label style="width: 60px;"><input type="radio" name="isNcms" value="0" />否</label> 
						</div> 
					</td>
					</c:if>
					<td style="width: 140px;" align="right" valign="middle">城镇职工基本医保定点：</td>
					<c:if test="${mho.isDesignatedHospital==1}">
					<td style="width: 180px;">
						<div title="是否城镇职工基本医保定点" name="isDesignatedHospital" style="width: 92%;">
							<label style="width: 60px;"><input type="radio" name="isDesignatedHospital" value="1" checked="checked" />是</label> 
							<label style="width: 60px;"><input type="radio" name="isDesignatedHospital" value="0"  />否</label>
						</div>
					</td>
					</c:if>
					<c:if test="${mho.isDesignatedHospital==0}">
					<td style="width: 180px;">
						<div title="是否城镇职工基本医保定点" name="isDesignatedHospital" style="width: 92%;">
							<label style="width: 60px;"><input type="radio" name="isDesignatedHospital" value="1" />是</label> 
							<label style="width: 60px;"><input type="radio" name="isDesignatedHospital" value="0" checked="checked" />否</label>
						</div>
					</td>
					</c:if>
					<c:if test="${mho.isDesignatedHospital==null}">
					<td style="width: 180px;">
						<div title="是否城镇职工基本医保定点" name="isDesignatedHospital" style="width: 92%;">
							<label style="width: 60px;"><input type="radio" name="isDesignatedHospital" value="1" />是</label> 
							<label style="width: 60px;"><input type="radio" name="isDesignatedHospital" value="0" />否</label>
						</div>
					</td>
					</c:if>
				   </tr>
				   	<tr>
				   <td style="width: 130px;" align="right">网址：</td>
					<td colspan="3" align="left">
						<input type="text" name="website" style="width: 98%;" title="网址 validate="string|0-50" reg="[]" value="${mho.website}"/>
					</td>
					</tr>
					<tr>
						<td style="width: 130px;" align="right">联系地址：</td>
					<td colspan="3" align="left">
						<input type="text" name="address" style="width: 98%;" title="联系地址" validate="string|0-50" reg="[]" value="${mho.address}"/>
					</td>
					</tr>
					<tr>
						<td style="width: 130px;" align="right">机构介绍：</td>
					<td colspan="3" align="left">
						<textarea name="remark" style="width: 98%; height: 60px; overflow: auto;" title="机构介绍" id="diabZzqtms" validate="string|0-50" reg="[]" >${mho.remark}</textarea>
					</td> 
					
			        </tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
