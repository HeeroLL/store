<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>单点登录</title>
    <!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html,charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/bootstrap.css" media="screen" >
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/docs.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/bootstrap-responsive.css">
    <script type="text/javascript">
		//alert("Remind ,Hello boy~");
    </script>
    <style type="text/css">
		.form-user-login{
			max-width: 480px;
			padding: 19px 29px 29px;
			margin: 0 auto 20px;
			background-color: #fff;
			border: 1px solid #e5e5e5;
			-webkit-border-radius: 5px;
			   -moz-border-radius: 5px;
					border-radius: 5px;
			-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
			   -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
					box-shadow: 0 1px 2px rgba(0,0,0,.05);
		}
		body {
				padding-top: 40px;
				padding-bottom: 40px;
				background-color: #f5f5f5;
			  }

      
    </style>
  </head>
  <body>
    <script src="../bootstrap/jquery.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <div class="container-fluid form-user-login">
    			<div class="row-fluid">
    				<div class="span12" style="text-align:center"><h3>SSO用户登录</h3></div>
    				
    				<c:if test="${flag=='false'}">
    					<div class="alert alert-error span6 offset3">
	    					<button type="button" class="close" data-dismiss="alert">×</button>
	    					    用户名或者密码错误
	    				</div>
    				</c:if>
    				<c:if test="${flag=='true'}">
    					<c:redirect url="home.zb"/>
    				</c:if>
    				 
    				
    			</div>
                <form class="form-horizontal" action="login.zb" method="post">
                    <div class="control-group">
                        <label class="control-label" for="username">用户名</label>
                        <div class="controls">
                            <input type="text" name="username" id="username" placeholder="长度为1~10的字符"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="password">密码</label>
                        <div class="controls">
                            <input type="text" name="password" id="password" placeholder="长度为1~15的字符"/>
                        </div>
                    </div>
                    <div class="control-group visible-phone">
                        <label class="control-label" for="valicode">验证码</label> 
                        <div class="controls">
                            <input type="text" name="valicode" id="valicode" class="input-mini" placeholder="验证码"/>
                            <img src="../assets/img/examples/browser-icon-firefox.png" class="img-polaroid" style="width:60px; height:20px;"/>
                        </div>
                        
                    </div>
                  
                    <div class="control-group">
                        <div class="controls">
                            <label class="checkbox">
                                <input type="checkbox" value="remember-me"/>记住我
                            </label>
                            <button type="submit" class="btn btn-large btn-primary">登录</button>
                        </div>
                    </div>
                </form>
    </div>
    
  </body>
</html>



			

