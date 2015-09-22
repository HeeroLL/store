<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看公告</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',height:400" style="padding:5px;">
			<table width="100%">
				<tr>
					<td width="30px" align="right"><c:if
							test='${publicNotice.noticeType == "1"}'>
							<span style="color: #969696;">●</span>
						</c:if> <c:if test='${publicNotice.noticeType == "2"}'>
							<span style="color: red">●</span>
						</c:if>
						&nbsp;&nbsp;
					</td>
					<td>
						${publicNotice.noticeTitle}<br />
						${publicNotice.publishPersonName} <span style="color: #969696;">发给</span>
						所有医疗卫生人员
					</td>
					<td width="100px" align="right"><c:if
							test="${!empty attachmentList}">
							<a href="#" class="easyui-linkbutton file"
								data-options="iconCls:'icon-attachments',plain:true"> 附件 </a>
							<br />
						</c:if> 
						<fmt:formatDate value="${publicNotice.publishTime}" pattern="yyyy-MM-dd" />
					</td>
				</tr>
			</table>
			<hr style="background-color: gray;border: none;height: 1px;" />
			<span>${publicNotice.noticeContent}</span>
		</div>
		<div id="attachmentDiv2"
			data-options="region:'south',title:'附件',split:true,collapsible:false"
			style="height:95px;padding:5px;">
			<c:forEach items="${attachmentList}" var="attachment">
				<a href="#" title="${attachment.attachmentName}"
					style="height: 50px" aid="${attachment.aId}"
					id="attachment_${attachment.orderNumber}" class="easyui-linkbutton"
					data-options="iconCls:'${attachment.attachmentImage}',plain:true">
					<p align="left">
						<span id="span_attachment_${attachment.aId}">${attachment.viewName}</span><br />
						${attachment.viewSize}
					</p>
				</a>&nbsp;
			</c:forEach>
		</div>
		<div id="menu2" class="easyui-menu" style="width:80px;"
			data-options="onClick:menuHandler,duration:1000">
			<div data-options="iconCls:'icon-save'">下载</div>
		</div>
	</div>
</body>
</html>
