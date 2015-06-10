<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>单点登录注销</title>
    <!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html,charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/bootstrap.css" media="screen" >
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/docs.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/bootstrap-responsive.css">
    <script src="../bootstrap/jquery.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
		var percent = 1;
		$().ready(function(){
				var timer_progress = setInterval(function(){
					percent+=3;
					$("#progressbar").css("width",percent+"%");
					if(percent>100){
						clearInterval(timer_progress);
						window.location="login.zb";
					}
				},100);
			});
		
    </script>
    <style type="text/css">
		body{
			margin:0 0 0 0;
			padding:0;
			background-color: #f5f5f5;
		}
    </style>
  </head>
  <body>
  		<c:forEach items="${sysRegInfoList}" var="sysRegInfo">
			<img src="${sysRegInfo.sysLogoutUrl}" style="display: none;"/>
		</c:forEach>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12" style="height:150px;"></div>
            </div>
            <div class="row-fluid" style="height:200px">
                <div class="span4"></div>
                <div class="span4 hero-unit" style="height:200px; line-height:100px;">
                    <div class="progress progress-striped active">
                        <div class="bar" id="progressbar" style="width: 1%;"></div>
                    </div>
                    <div class="alert alert-success" style="height:20px; line-height:20px;">退出成功，正在跳转....</div>
                </div>
                <div class="span4"></div>
            </div>
        </div>  
  </body>
</html>
