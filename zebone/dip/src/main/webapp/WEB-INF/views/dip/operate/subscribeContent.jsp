<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>服务管理内容</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
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
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.zTree.css"/>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
    <script type="text/javascript" src="js/jsp/dip/operate/subscribeContent.js"></script>
	<style type="text/css">
        html,body{
			background-color: #EEF7FE;
			overflow: auto;
			height: 100%;
		}
		.sub_service{
			margin: 20px;
		}
    </style>
    <script type="text/javascript">
		var subFrequMap = ${subFrequMap};
		var opeFlag = '${opeFlag}';
		var lastSubTime = '${lastSubTime}';
    </script>
</head>
<body>
<jsp:useBean id="nowDate" class="java.util.Date"></jsp:useBean>
<!--  容器 -->
<div id="tab0" title="文档订阅">
<div class="container">
	<div class="c_nw">
    	<div class="c_ne"><div class="c_n"></div></div>
    </div>
    <!-- 容器标题部分 -->
    <span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">文档订阅</span>
		</span>
	</span>
       <div class="tools-panel"></div>
       <div class="c_w">
           <div class="c_e">
               <div class="c_content">
               	   <c:if test="${opeFlag=='false'}">
               	   		<div style="font-weight: bold;font-size: 16px;color: red;margin-top: 10px;;margin-left: 25px;">本机构节点已经被停用，无法操作！</div>
                   </c:if>
                   <div style="width: 360px;float:right;font-weight: bold;font-size: 14px;color: blue;margin-left: 20px;margin-right:30px;line-height: 30px;">
	                   订阅操作规则：<br/>
	                 1.进行服务订阅时，首先选择该服务前的选择框，如果无法选择，说明该服务暂时不提供订阅服务  <br/>
	                 2.选择订阅类型（历史数据，当天数据）时，首先选择该类型前的选择框，否则无法设定具体的值  <br/>
	                 3.每天 ${lastSubTime} 以前，历史数据的结束日期只能是当天的前一天，${lastSubTime} 以后，结束日期可以是当天<br/>
                   </div>
                   <table class="sub_service">
                   <c:forEach items="${listFile}" var="serviceVO">
	                   <tr class="LoadTR">
		                   <td>
		                   		<table>
				                   	  <tr>
				                       <td width="30" height="30px">
				                       <c:choose>
				                       		<c:when  test="${serviceVO.serviceState=='1' and opeFlag=='true'}">
				                       			<input type="checkbox" id="selectCheckbox" name="serviceType"  onclick="subCheck(this)"/>
				                       		</c:when >
				                       		<c:otherwise>
				                       			<input type="checkbox" id="selectCheckbox" name="serviceType"  onclick="subCheck(this)" disabled="disabled"/>
				                       		</c:otherwise>
				                       	</c:choose>
				                       		<input type="hidden" id="id" name="id" value=""/>
				                       		<input type="hidden" id="serviceFrequ"  value="${serviceVO.serviceFrequ}"/>
				                       		<input type="hidden" id="serviceId" name="serviceId" value="${serviceVO.id}"/>
				                       </td>
				                       <td width="150" style="font-weight: bold;font-size: 16px;">${serviceVO.serviceName}</td>
				                       <td width="300">
				                       <c:choose>
				                       	   <c:when test="${serviceVO.serviceState=='1' and opeFlag=='true'}">
				                           		<a class="" href="javascript:void(0);">
													<span class="btn-left" id="dybtn" onclick="selectbutten()">
														<span >订阅</span>
													</span>
				                           		</a>
				                           </c:when>
				                           <c:when test="${serviceVO.serviceState=='2'}">
				                           		<div style="font-weight: bold;font-size: 14px;color: red;">该服务已经被停用，无法操作！</div>
				                           </c:when>
				                           <c:otherwise >
				                           		
				                           </c:otherwise>
				                        </c:choose>
				                        </td>
				                        </tr>
				                        <tr>
				                        <td></td>
				                        <td><input type="checkbox" value="1" id="data_history" name="isHistory" onclick="serviceTime(this)" disabled="disabled"/>历史数据</td>
				                        <td>时间范围：&nbsp;&nbsp;
				                        <input type="text" class="Wdate" name="beginDate"  style="width: 100px; border: 1px solid rgb(204, 204, 204);" validate="date|0-20" title="开始日期" onclick="WdatePicker({skin:'whyGreen'});"  disabled="disabled"/>
				                        ~
				                        <input type="text" class="Wdate" name="endDate" style="width: 100px; border: 1px solid rgb(204, 204, 204);" validate="date|0-20" title="结束日期" onclick="WdatePicker({skin:'whyGreen'});" disabled="disabled"/>
				                        </td>
				                        </tr>
				                       	<tr>
				                        <td></td>
				                       	<td><input type="checkbox" value="1" id="data_today" name="isCurrent"  onclick="serviceTime(this)" disabled="disabled"/>当天数据</td>
				                        <td>订阅频率：&nbsp;&nbsp;
				                        <select  style="width: 150px;" name="subFrequ" disabled="disabled">
				                        	<option value="-1">请选择</option>
				                        </select>
				                        </tr>
		                   		</table>
		                   </td>
	                   </tr>
	                   <tr>
	                   	<td colspan="5"><span style="color:#95DE56">-----------------------------------------------------------------------------------------------</span></td>
	                   </tr>
					</c:forEach>
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
</div>
<div id="tab1" title="资源订阅">
<!--  容器 -->
<div class="container">
	<div class="c_nw">
    	<div class="c_ne"><div class="c_n"></div></div>
    </div>
    <!-- 容器标题部分 -->
    <span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">资源订阅</span>
		</span>
	</span>
       <div class="tools-panel"></div>
       <div class="c_w">
           <div class="c_e">
               <div class="c_content">
				   <c:if test="${opeFlag=='false'}">
               	   		<div style="font-weight: bold;font-size: 16px;color: red;margin-top: 10px;;margin-left: 25px;">本机构节点已经被停用，无法操作！</div>
                   </c:if> 
                   <div style="width: 360px;float:right;font-weight: bold;font-size: 14px;color: blue;margin-left: 20px;margin-right:30px;line-height: 30px;">
	                   订阅操作规则：<br/>
	                 1.进行服务订阅时，首先选择该服务前的选择框，如果无法选择，说明该服务暂时不提供订阅服务  <br/>
	                 2.选择订阅类型（历史数据，当天数据）时，首先选择该类型前的选择框，否则无法设定具体的值  <br/>
	                 3.每天 ${lastSubTime} 以前，历史数据的结束日期只能是当天的前一天，${lastSubTime} 以后，结束日期可以是当天<br/>
                   </div>            
                   <table class="sub_service">
                   <c:forEach items="${listResource}" var="serviceVO">
	                   <tr class="LoadTR">
		                   <td>
		                   		<table>
				                   	  <tr>
				                       <td width="30" height="30px">
				                       <c:choose>
				                       		<c:when  test="${serviceVO.serviceState=='1' and opeFlag=='true'}">
				                       			<input type="checkbox" id="selectCheckbox" name="serviceType"  onclick="subCheck(this)"/>
				                       		</c:when >
				                       		<c:otherwise>
				                       			<input type="checkbox" id="selectCheckbox" name="serviceType"  onclick="subCheck(this)" disabled="disabled"/>
				                       		</c:otherwise>
				                       	</c:choose>
				                       		<input type="hidden" id="id" name="id" value=""/>
				                       		<input type="hidden" id="serviceFrequ"  value="${serviceVO.serviceFrequ}"/>
				                       		<input type="hidden" id="serviceId" name="serviceId" value="${serviceVO.id}"/>
				                       </td>
				                       <td width="150" style="font-weight: bold;font-size: 16px;">${serviceVO.serviceName}</td>
				                       <td width="300">
				                       <c:choose>
				                       	   <c:when test="${serviceVO.serviceState=='1' and opeFlag=='true'}">
				                           		<a class="" href="javascript:void(0);">
													<span class="btn-left" id="dybtn" onclick="selectbutten()">
														<span >订阅</span>
													</span>
				                           		</a>
				                           </c:when>
				                           <c:when test="${serviceVO.serviceState=='2'}">
				                           		<div style="font-weight: bold;font-size: 14px;color: red;">该服务已经被停用，无法操作！</div>
				                           </c:when>
				                           <c:otherwise >
				                           		
				                           </c:otherwise>
				                        </c:choose>
				                        </td>
				                        </tr>
				                        <tr>
				                        <td></td>
				                        <td><input type="checkbox" value="1" id="data_history" name="isHistory" onclick="serviceTime(this)" disabled="disabled"/>历史数据</td>
				                        <td>时间范围：&nbsp;&nbsp;
				                        <input type="text" class="Wdate" name="beginDate"  style="width: 100px; border: 1px solid rgb(204, 204, 204);" validate="date|0-20" title="开始日期" onclick="WdatePicker({skin:'whyGreen'});"  disabled="disabled"/>
				                        ~
				                        <input type="text" class="Wdate" name="endDate"  style="width: 100px; border: 1px solid rgb(204, 204, 204);" validate="date|0-20" title="结束日期" onclick="WdatePicker({skin:'whyGreen'});" disabled="disabled"/>
				                        </td>
				                        </tr>
				                       	<tr>
				                        <td></td>
				                       	<td><input type="checkbox" value="1" id="data_today" name="isCurrent"  onclick="serviceTime(this)" disabled="disabled"/>当天数据</td>
				                        <td>订阅频率：&nbsp;&nbsp;
				                        <select  style="width: 150px;" name="subFrequ" disabled="disabled">
				                        	<option value="-1">请选择</option>
				                        </select>
				                        </tr>
		                   		</table>
		                   </td>
	                   </tr>
	                   <tr>
	                   	<td colspan="5"><span style="color:#95DE56">-----------------------------------------------------------------------------------------------</span></td>
	                   </tr>
					</c:forEach>
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
</div>
</body>
</html>