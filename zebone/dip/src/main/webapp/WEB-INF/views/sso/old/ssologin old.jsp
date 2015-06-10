<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>SSO登录页面</title>
		<link rel="shortcut icon" href="../favorite.ico" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-dialog.css" id="dialog-css"/>
		<link rel="stylesheet" type="text/css" href="../css/login.css"/>
		<!--[if IE 6]>
		<script type="text/javascript" src="js/ie6/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script type="text/javascript">
		 DD_belatedPNG.fix('DIV');
		 DD_belatedPNG.fix('A');
		 DD_belatedPNG.fix('SPAN');
		 DD_belatedPNG.fix('INPUT');
		</script>
		<![endif]-->
		<script type="text/javascript" src="../js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-dialog.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.mark.js"></script>
		<script type="text/javascript">
			var ie=navigator.appName=="Microsoft Internet Explorer"?true:false;
			var tipBg=function(){
				if($.trim($("#username").val())==''){
					$('.usernameTip').show();
				}else{
					$('.usernameTip').hide();;
				}
				if($.trim($("#password").val())==''){
					$('.pwdTip').show();
				}else{
					$('.pwdTip').hide();
				}
			}
			var loginStart=function(){
				$('#loginState').show();
				$('#loginBtn').hide();
			}
			var loginEnd=function(){
				$('#loginState').hide();
				$('#loginBtn').show();
			}
			var loginAjax=function(){
				var username = $.trim($("#username").val());
				var password = $.trim($("#password").val());
				if(username==''){ JDialog.tip('用户名不能为空');$('#login-box').shake();return;}
				if(password==''){ JDialog.tip('密码名不能为空');$('#login-box').shake();return;}
				var datas = "username="+username+"&password="+password;
				loginStart();
				$.ajax({
					url: 'login.zb',
					type: 'POST',
					data: encodeURI(datas),
					cache: false,
					dataType: 'json',
					success: function(data){
				 		if(data.success){
			 				var url='home.zb';
				 			if(ie){
				 				try{
				 					var win=window.open(url+"?tmpOrganId="+tmpOrganId,"",'alwaysRaised=yes,width='+ (screen.availWidth - 5) +',height='+ (screen.availHeight-30) +',location=no,menubar=no,resizable=yes,status=no,scrollbars=no,toolbar=no,title=no,center=yes');
				 					if(win){
				 						top.window.opener=null;
										top.window.open('','_self');
										top.window.close();
				 					}
				 				}catch(e){
				 					window.top.location=url;
				 				}
				 			}else{
				 				window.top.location=url;
				 			}
				 		}else{
				 			loginEnd();
				 			$('#login-box').shake();
				 			JDialog.tip(data.msg);
				 		}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						loginEnd();
						JDialog.tip('服务器出现异常，用户登录失败。');
					}
				});
			}
			jQuery.fn.shake = function (setting){
				var so=jQuery(this);
			    var style = so[0].style,
			        p = [4, 8, 4, 0, -4, -8, -4, 0],
			        fx = function () {
			            style.marginLeft = p.shift() + 'px';
			            if (p.length <= 0) {
			                style.marginLeft = 0;
			                clearInterval(timerId);
			            };
			        };
			    p = p.concat(p.concat(p));
			    timerId = setInterval(fx, 13);
			    return this;
			};
			$(function(){
				//tipBg();
				$('#username').focus();
				$('#username').click(function(){
					$('.usernameTip').hide();
				}).keydown(function(){
					$('.usernameTip').hide();
				}).blur(function(){
					tipBg();
				});
				$('#password').click(function(){
					$('.pwdTip').hide();
				}).keydown(function(){
					$('.pwdTip').hide();
				}).blur(function(){
					tipBg();
				});
				$('#loginBtn').click(function(){
					loginAjax();
				});
				$('#password').bind('keydown', function(event){
				   if (event.keyCode=="13"){
				    loginAjax();
				   }
				});
			});
		</script>
	</head>
	<body>
		<div class="login-img-bg"><img src="../images/login/login_bg.jpg"/> </div>
		<div id="loginMainDIV">
			<div id="login-box" class="login-box">
				<div class="login-box-inner">
					<div class="usernameDiv">
						<div class="rltv">
							<span class="userNameIco"></span>
							<input type="text" id="username" name="username" class="userNameText" value=""/>
							<div class="usernameTip"></div>
						</div>
					</div>
					<div class="pwdDiv">
						<div class="rltv">
							<span class="pwdIco"></span>
							<input type="password" id="password" name="password" class="userPwdText" value="" />
							<div class="pwdTip"></div>
						</div>
					</div>
					<div class="svpwdDiv">
					</div>
						<a href="###" id="loginBtn" class="loginBtn" type="submit" value="登录">
							<span id="loginBtnText" class="loginBtnText"></span>
						</a>
						<div href="###" id="loginState" class="loginState">
						</div>
				</div>
			</div>
		</div>




		<div style="color:red;">
			<c:forEach items="${errors}" var="error">
				<c:out value="${error}"/><br/>
			</c:forEach>

		</div>
		<form id="loginForm" action="sso/login.zb" method="post">
			用户:
			<br />
			密码:
			<br />
		</form>
		<a href="securityTest.zb">安全测试</a>
	</body>
</html>