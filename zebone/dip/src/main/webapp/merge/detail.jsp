<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String hzzsys = request.getParameter("hzzsys");
	if(hzzsys==null||hzzsys.length()==0) {
		response.sendRedirect("index.jsp");
		return;
	}
	String[] hs = hzzsys.split(",");
%>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript">
var data;
$(document.body).ready(function(){
	$.ajax({
		url: 'merge/hz.json',
		dataType: 'json',
		success: function(res){
			data = res.data;
			for(var i=0; i<data.length; i++){
			if(data[i].id==$("#zjjl").val()){
				for(var o in data[i]){
					$("."+o).html(data[i][o]);
				}
				break;
			}
		}
		}
	});
	$("#zjjl").change(function(){
		for(var i=0; i<data.length; i++){
			if(data[i].id==$(this).val()){
				for(var o in data[i]){
					$("."+o).html(data[i][o]);
				}
				break;
			}
		}
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body {
		font-size: 12px;
	}
	table tr td{
		height: 30px;
		border:1px solid #9BBDEA;		
	}
	table{border:1px solid #9BBDEA;}
	.colName{background: #D1E0F5;}
</style>
</head>
<body>
<div>请选择最佳纪录：<select id="zjjl">
<% 
	for(String hzzsy : hs){
%><option value="<%=hzzsy%>"><%=hzzsy%></option><%		
	}
%>
</select></div>
<br/>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="colName">姓名：</td>
		<td class="xm"></td>
		<td class="colName">性别：</td>
		<td class="xb"></td>
		<td class="colName">身份证号：</td>
		<td class="sfzh"></td>
	</tr>
	<tr>
		<td class="colName">出生日期：</td>
		<td class="csrq"></td>
		<td class="colName">国籍：</td>
		<td class="gj"></td>
		<td class="colName">民族：</td>
		<td class="mz"></td>
	</tr>
	<tr>
		<td class="colName">婚姻状况：</td>
		<td class="hyzk"></td>
		<td class="colName">手机号码：</td>
		<td class="sjhm"></td>
		<td class="colName">联系电话：</td>
		<td class="lxdh"></td>
	</tr>
	<tr>
		<td class="colName">职业：</td>
		<td class="zy"></td>
		<td class="colName">工作单位：</td>
		<td class="gzdw"></td>
		<td class="colName">联系人：</td>
		<td class="lxr"></td>
	</tr>
	<tr>
		<td class="colName">联系人手机号码：</td>
		<td class="lxrsjhm"></td>
		<td class="colName">机构名称：</td>
		<td class="jgmc" colspan="3"></td>
	</tr>
	<tr>
		<td class="colName">户籍地址：</td>
		<td colspan="5" class="hjdz"></td>		
	</tr>
	<tr>
		<td class="colName">常住地址：</td>
		<td colspan="5" class="czdz"></td>		
	</tr>
	<tr>
		<td colspan="6" align="center">
			<input type="button" value="确 定" onclick="alert('合并成功'); window.location.href='<%=basePath%>merge/index.jsp?id=x';">
			<input type="button" value="取 消" onclick="window.location.href='<%=basePath%>merge/index.jsp';">
		</td>
	</tr>
</table>
</body>
</html>