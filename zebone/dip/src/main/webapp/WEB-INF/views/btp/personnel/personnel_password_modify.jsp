<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <btp:htmlbase/>
    <title>修改密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/icons.css" />
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
	<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/jsp/btp/personnel/oneself_modify.js"></script>
	<script type="text/javascript" src="js/jsp/dictionary.js"></script>
	<script type="text/javascript">
	var checkPassword = function(){
		var password = $("#password").val();
		if(password&&password!=''){
			$.ajax({
			url : 'btp/personnel/checkPassword.zb',
			type : 'POST',
			cache : false,
			data : encodeURI("password="+password),
			dataType : 'json',
			success : function(res) {
				if(!res.bool){
					JDialog.tip("原密码错误！");
				}
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
			}
		});
		}
	}
	
	 var doSubmit = function(){
	 	if(!checkAll('formModify')){
			return false;
	 	}
	 	var password = $("#password").val();
	 	var newPassword = $("#newPassword").val();
	 	var confirmPassword = $("#confirmPassword").val();
	 	if(newPassword==password){
	 		JDialog.tip("新密码与原密码不能一致！");
	 		return;
	 	}
	 	if(newPassword!=confirmPassword){
	 		JDialog.tip("新密码与确认密码不一致！");
	 		return;
	 	}
	 	$.ajax({
			url : 'btp/personnel/passwordModifySave.zb',
			type : 'POST',
			cache : false,
			data : encodeURI("password="+newPassword+"&oldePassword="+password),
			dataType : 'json',
			success : function(res) {
				if(res.bool){
					JDialog.tip("密码保存成功！");
				}else{
					JDialog.tip(res.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				JDialog.showMessageDialog('异常','服务器出现异常，数据保存失败。');
			}
		});
	 }
	</script>
	<style type="text/css">
	html,body{overflow: hidden;}
	</style>
  </head>
  
  <body>
    <div class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">修改密码</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
			<form id="formModify"  class="checkForm">
				<table cellspacing="10" style="width: 200px;" align="center">
					<tbody>
					<tr>
						<td align="right" style="width: 65px;">原密码：</td>
						<td align="left" style="width: 120px;">
							<input type="password" onblur="checkPassword();" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="新密码" name="password" id="password"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 65px;">新密码：</td>
						<td align="left" style="width: 120px;">
							<input type="password" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="新密码" name="newPassword" id="newPassword"/>
						</td>
					</tr>
					<tr>	
						<td align="right" style="width: 65px;">确认密码:</td>
						<td align="left" style="width: 120px;">
							<input type="password" style="width: 95%; border: 1px solid rgb(204, 204, 204);" validate="string|1-20" title="确认密码" name="confirmPassword" id="confirmPassword"/>
						</td>
					</tr>
				</tbody>
				</table>
				</form>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
	<div class="button-bar" style="text-align: center;">
		<a class="btn" href="javascript:void(0);" onclick="doSubmit()">
			<span class="btn-left">
				<span class="btn-text icon-save">保存</span>
			</span>
		</a>
		<!--<a class="btn" href="javascript:void(0);">
			<span class="btn-left">
				<span class="btn-text icon-cancel">取消</span>
			</span>
		</a>
	--></div>
  </body>
</html>
