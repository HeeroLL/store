<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>个人设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<btp:htmlbase/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" />
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jsp.personalSettings.js"></script>
<style type="text/css">
.tabBody{height: 100%;overflow:hidden;}
</style>
<script type="text/javascript">
	$(function(){
		$('#left_tab li').click(function(){
			$('#left_tab li').removeClass('left_tab_active');
			$(this).addClass('left_tab_active');
			$('.tabBody').hide();
			$('#'+$(this).attr('id')+'_div').show()
		});
	});
</script>
</head>
<body>
<!--最外层容器-->
<div class="container-outer">
	<!--右边部分容器，一定要写到前面，否则会出现结构混乱-->
	<div class="right-container">    
	    <div class="right-container-inner">
			<div id="change_person_info_div" class="tabBody" style="height: 100%;">
				<iframe src="btp/personnel/oneselfModifyForm.zb" name="pm_frame" scrolling="no" frameborder="0" frameborder="0" marginwidth="0" marginheight="0"  style="width: 100%; height: 100%; "></iframe>
			</div>

			<div id="change_pwd_div" class="tabBody" style="display: none;">
				<iframe src="btp/personnel/passwordModifyForm.zb" name="pwd_frame" scrolling="no" frameborder="0" frameborder="0" marginwidth="0" marginheight="0"  style="width: 100%; height: 100%; "></iframe>
			</div>
		</div>
	</div>
	<!--左边部分容器-->
	<div class="left-container">
		<ul id="left_tab" >
			<li id="change_person_info" class="left_tab left_tab_active">
				个人信息
			</li>
			<li id="change_pwd" class="left_tab">
				修改密码
			</li>
		</ul>
	</div>
</div>	
</body>
</html>