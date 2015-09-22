<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>辅助功能/消息中心</title>
<link rel="stylesheet" type="text/css"
	href="../js/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="../js/jquery/themes/icon.css">
		<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			function toPage(url) {
				$('#frm').attr('src',url);
			}
		</script>
</head>
<body class="easyui-layout"> 
    <div style="padding:5px;" data-options="region:'north'">
    	辅助功能/消息中心
    	<hr style="background-color: gray;border: none;height: 1px;" />
    	消息类型：
    	<a href="#" onclick="toPage('http://www.qq.com')" class="easyui-linkbutton" 
    		data-options="plain:true,group:'btn',toggle:true">通知</a>
    	<a href="#" onclick="toPage('../notice/publicNoticeViewIndex.zb')" class="easyui-linkbutton" 
    		data-options="plain:true,group:'btn',toggle:true,selected:true">公告</a>
    </div>
    <div data-options="region:'center'">
    	<iframe id="frm" name="frm" 
    		src="../notice/publicNoticeViewIndex.zb" height="100%" width="100%" scrolling="auto" frameborder="0"></iframe>
    </div>
</body>
</html>
