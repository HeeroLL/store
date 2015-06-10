<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>写公告</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',height:400" style="padding:5px;">
			<form id="noticeForm" method="post" enctype="multipart/form-data">
				<input type="hidden" id="nId" name="nId" value="${publicNotice.nId}" />
				<input id="status" type="hidden" name="status" value="0" /> <input
					type="hidden" id="orderNumber" name="orderNumber" value="0" />
				<table width="100%">
					<tr>
						<td><a href="#" onclick="javascript:submitForm('1')"
							class="easyui-linkbutton">&nbsp;&nbsp;发送&nbsp;&nbsp;</a> <a
							href="#" onclick="javascript:submitForm('0')"
							class="easyui-linkbutton">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
							<a id="btn" href="#" class="easyui-linkbutton file" data-options="iconCls:'icon-attachments'">
								附件<input type="file" id="uploadFile" name="uploadFile" onchange="uploadFile1()"/>
							</a> 
							<!--  
							<input class="easyui-filebox" id="uploadFile1" name="uploadFile1"
								data-options="buttonText:'附件',buttonIcon:'icon-attachments',buttonAlign:'left',height:26,width:54,
								onChange:function(newValue, oldValue){uploadFile1()}">
							 -->
						</td>
						<td align="right">有效时长 <select id="effectiveTime"
							name="effectiveTime" value="${publicNotice.effectiveTime}"
							class="easyui-combobox" style="width: 80px"
							data-options="editable:false,panelHeight:95">
								<option value="1">1个月</option>
								<option selected="selected" value="3">3个月</option>
								<option value="6">6个月</option>
								<option value="12">12个月</option>
						</select>
						</td>
					</tr>
				</table>
				<hr style="background-color: gray;border: none;height: 1px;" />
				<table width="100%">
					<tr>
						<td width="50px" align="right">收件人：</td>
						<td width="*">所有医疗卫生人员</td>
					</tr>
					<tr>
						<td width="50px" align="right">主题：</td>
						<td width="*"><input id="noticeTitle" name="noticeTitle"
							value="${publicNotice.noticeTitle}" class="easyui-textbox"
							data-options="required:true,validType:'length[1,64]',width:715" /></td>
					</tr>				  
					<tr>
						<td width="50px" align="right">类型：</td>
						<td width="*"><select id="noticeType" name="noticeType"
							value="${publicNotice.noticeType}" class="easyui-combobox"
							style="width: 90px" data-options="editable:false,panelHeight:50">
								<option value="1">一般公告</option>
								<option value="2">重大公告</option>
						</select></td>
					</tr>
				</table>
				<hr style="background-color: gray;border: none;height: 1px;" />
				<input id="noticeContent" name="noticeContent"
					class="easyui-textbox" value="${publicNotice.noticeContent}"
					data-options="multiline:true,prompt:'内容',width:'775',height:'320',validType:'length[0,333]'" />
			</form>
		</div>
		<div id="attachmentDiv"
			data-options="region:'south',title:'附件',split:true,collapsible:false"
			style="height:95px;padding:5px;">
			<c:forEach items="${attachmentList}" var="attachment">
				<a href="#" title="${attachment.attachmentName}" style="height: 50px" aid="${attachment.aId}"
					id="attachment_${attachment.orderNumber}" class="easyui-linkbutton"
					data-options="iconCls:'${attachment.attachmentImage}',plain:true">
					<p align="left">
						<span id="span_attachment_${attachment.aId}">${attachment.viewName}</span><br/>
						${attachment.viewSize}
					</p>
				</a>&nbsp;
			</c:forEach>
		</div>
		<div id="menu" class="easyui-menu" style="width:80px;" data-options="onClick:menuHandler,duration:1000">
			<div data-options="iconCls:'icon-remove'">删除</div>
			<div data-options="iconCls:'icon-edit'">重命名</div>
		    <div data-options="iconCls:'icon-save'">下载</div>   
		</div>
	</div>
</body>
</html>
