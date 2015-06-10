<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <title>单点登录子系统列表</title>
    <!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html,charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/bootstrap.css" media="screen" >
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/docs.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap/assets/css/bootstrap-responsive.css">
    <script src="../bootstrap/jquery.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <!--<script type="text/javascript">
		$().ready(function(){
				$(".thumbnail .caption .sys_dsp").each(function(){
						var descp = $(this).html();
						if(descp.length>100){
						}
						//alert($(this).html());
				});
			});
		//alert("Remind ,Hello boy~");
    </script>
    -->
    <style type="text/css">
	    body {				 
				background-color: #f5f5f5;
				margin:0 15px 0;
			  }
		
      .sys_dsp{
		  height:100px;
	  }
    </style>
  </head>
  <body >
            <div class="nav navbar-inverse navbar-fixed-top">
            	<div class="navbar-inner">
                	<div class="container" >
                        <div class="nav-collapse collapse navbar-inverse-collapase">
                    		<span class="nav label label-warning pull-left" style="height:24px;line-height:24px;margin:10px 0 10px 50px;">单点登录</span>
                            <a href="logout.zb" class="btn btn-danger nav pull-right btn-small" style="margin:10px -30px 10px 50px;">退出</a>
                            <span class="nav label label-info pull-right" style="margin:14px 0 0 20px;">2013-1-19 18:23</span>
                        	<span class="nav label label-success pull-right" style="margin:14px 0 0 50px;">您好，欢迎登录</span>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div style="height:26px"></div>
            <div class="row-fluid" style="margin:0 0 0 0;">
                <ul class="thumbnails span12">
                    <li class="span3">
                        <div class="thumbnail">
                            <img class="img-rounded" src="../bootstrap/img/google.jpg" style="height:200px;width:300px"/>
                            <div class="caption">
                                <h2>谷歌</h2>
                                <p class="sys_dsp">Google 目前被公认为是全球规模最大的搜索引擎，它提供了简单易用的免费服务。不作恶（Don't be evil）是谷歌公司的一项非正式的公司口号。</p>
                                <p><a target="_blank" href="http://www.google.com.hk" class="btn btn-primary">登录谷歌</a></p>
                            </div>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img class="img-rounded" src="../bootstrap/img/youtube.jpg" style="height:200px;width:300px"/>
                            <div class="caption">
                                <h2>Youtube</h2>
                                <p class="sys_dsp">YouTube是世界上最大的视频分享网站，早期公司总部位于加利福尼亚州的圣布里诺。在比萨店和日本餐馆，让用片或短片。</p>
                                <p><button class="btn btn-primary">登录Youtube</button></p>
                            </div>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img class="img-rounded" src="../bootstrap/img/qq.jpg" style="height:200px;width:300px"/>
                            <div class="caption">
                                <h2>QQ</h2>
                                <p class="sys_dsp">QQ是深圳市腾讯计算机系统有限公司开发的一款基于Internet的即时通信（IM）软件。腾讯QQ支持在线聊天、视频电话、点对点断点续传通讯方式相连。</p>
                                <p><button class="btn btn-primary">登录QQ</button></p>
                            </div>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img class="img-rounded" src="../bootstrap/img/bigjoke.jpg" style="height:200px;width:300px"/>
                            <div class="caption">
                                <h2>国家健康报告</h2>
                                <p class="sys_dsp">"国家健康"研究开始于2003年，是什么促使课题组开始这一研究呢？根据课题组的相关论文、访谈和报告的介绍，背景是这样的：课题组认为当前世界康。</p>
                                <p><button class="btn btn-primary">登录子系统</button></p>
                            </div>
                        </div>
                    </li>
                    <c:forEach items="${sysRegInfoList}" var="sysRegInfo">
                        <li class="span3">
	                        <div class="thumbnail">
	                            <img class="img-rounded" src="../bootstrap/img/bigjoke.jpg" style="height:200px;width:300px"/>
	                            <div class="caption">
	                                <h2>${sysRegInfo.sysName}</h2>
	                                <p class="sys_dsp">${sysRegInfo.sysDescription}</p>
	                                <p><a target="_blank" href="toSubSystem.zb?regId=${sysRegInfo.regId}" class="btn btn-primary">登录${sysRegInfo.sysName}</a></p>
	                            </div>
	                        </div>
                        </li>
				    </c:forEach>
                  
                </ul>
            </div>  
      

  	  
   
    
  </body>
</html>