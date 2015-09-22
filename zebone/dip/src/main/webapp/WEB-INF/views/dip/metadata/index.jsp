<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>元数据管理系统</title>
    <script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <script type="text/javascript">
	  //加载完成，执行以下代码
	    $(function(){
	    	//加载layout，布局控件
	    	$('body').layout({ 
	    		resizable : true,
	    		livePaneResizing : true,
	    		slidable:true,
	    		west__size : 250,
	    		west__minSize : 250,
	    		west__resizable:true
	    	});
	    	window.document.getElementById("innerMainFrame").src = "metadata/docvIndex.zb";//文档版本管理
	    });
    </script>
</head>
<!-- <frameset id="main_frame" rows="*" cols="210,*" frameborder="no" border="0" framespacing="0">
	<frame src="metadata/metadataTree.zb" name="innerlLeftFrame" scrolling="auto" noresize="noresize" id="innerlLeftFrame" title="innerlLeftFrame" />
	<frame src="" name="innerMainFrame" id="innerMainFrame" title="innerMainFrame" />
</frameset>
<noframes>
	<body>您的浏览器不支持Iframe框架。
	</body>
</noframes> -->

<body>
	<div class="ui-layout-west">
		<iframe src="metadata/metadataTree.zb" name="innerlLeftFrame" style="height:100%;" scrolling="no" frameborder="0" noresize="noresize" id="innerlLeftFrame" title="innerlLeftFrame"></iframe>
	</div>
	<div class="ui-layout-center">
		<iframe src="" name="innerMainFrame" id="innerMainFrame" style="height:100%;width:100%;" frameborder="0" scrolling="no" title="innerMainFrame" ></iframe>
	</div>
</body>
</html>