<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>健康档案浏览器</title>
<ehrview:htmlBase/>
<link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/css/index.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/css/icons.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery.zTree.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery-dialog.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery-tab.css"/>
<link rel="stylesheet" href="resources/new/themes/default/jquery-ui.css"/>
<script type="text/javascript" src="resources/new/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery-ui-1.10.1.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery-tab1.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/scroll.js"></script>
<title>健康档案浏览器</title>
<style type="text/css">
html,body{height:auto;}
#smjd a{
	margin-left:27px;
}
</style>
<script type="text/javascript">
$(function(){
	if(parent.document.getElementById('iframe')){
		parent.document.getElementById('iframe').style.height=(document.body.scrollHeight+10)+'px';
	}
});

//跳转到门诊列表页面
function outPatient(){
    var empiId = '${empiId}';
    var searchInfo = '${searchInfo}';
    var patientInfo = '${patientInfo}';
    window.location.href = "healthActivityCommonList.zb?empiId=" + empiId + "&dataCode=C00.01.02.00" + "&searchInfo=" + searchInfo + "&patientInfo=" + patientInfo;
}
</script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/ie6/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript">
 DD_belatedPNG.fix('DIV');
 DD_belatedPNG.fix('A');
 DD_belatedPNG.fix('SPAN');
 DD_belatedPNG.fix('INPUT');
</script>
<![endif]-->
</head>
<body>
<div class="j-panel j-panel-radius j-panel-nohead" id="" style="padding:5px;height:648px;min-height:648px;">
	<div class="j-panel-body" id="" style="height:100%;overflow:hidden;">
		<div  class="position">
            当前位置：卫生服务活动-->医疗服务--><a style="color: #0000ff;text-decoration: underline" href="javascript:outPatient()">门诊</a>--><span style="color: #0000ff">门诊详情</span></div>
		<div id="tabs">
			<ul>
				<li>就诊信息</li>
				<li>门诊处方</li>
				<li>手术记录</li>
                <li>影像检查</li>
                <li>实验室检验</li>
			</ul>
            <div style="height:100%;">
                <iframe id="iframeRight" name="iframeRight" scrolling="auto" frameborder="0"
                        style="width:100%;height:100%;" marginheight="0" marginwidth="0"
                        src="outPatientDetail.zb?empiId=${empiId}&dataCode=${dataCode}&docNo=${docNo}&searchInfo=${searchInfo}&patientInfo=${patientInfo}"></iframe>
            </div>
            <div style="height:100%">
                <iframe id="iframeRight" name="iframeRight" scrolling="auto" frameborder="0"
                        style="width:100%;height:100%;" marginheight="0" marginwidth="0"
                        src="outPatientDetail.zb?empiId=${empiId}&dataCode=C00.01.03.00&docNo=${docNo}&searchInfo=${searchInfo}&patientInfo=${patientInfo}"></iframe>
            </div>
            <div style="height:100%">
                <iframe id="iframeRight" name="iframeRight" scrolling="auto" frameborder="0"
                        style="width:100%;height:100%;" marginheight="0" marginwidth="0"
                        src="outPatientDetail.zb?empiId=${empiId}&dataCode=C00.02.05.00&docNo=${docNo}&searchInfo=${searchInfo}&patientInfo=${patientInfo}"></iframe>
            </div>
            <div style="height:100%">
                <iframe id="iframeRight" name="iframeRight" scrolling="auto" frameborder="0"
                        style="width:100%;height:100%;" marginheight="0" marginwidth="0"
                        src="outPatientDetail.zb?empiId=${empiId}&dataCode=C00.03.01.00&docNo=${docNo}&searchInfo=${searchInfo}&patientInfo=${patientInfo}"></iframe>
            </div>
            <div style="height:100%">
                <iframe id="iframeRight" name="iframeRight" scrolling="auto" frameborder="0"
                        style="width:100%;height:100%;" marginheight="0" marginwidth="0"
                        src="outPatientDetail.zb?empiId=${empiId}&dataCode=C00.03.02.01&docNo=${docNo}&searchInfo=${searchInfo}&patientInfo=${patientInfo}"></iframe>
            </div>
		</div>
		<script type="text/javascript">
		$(function(){
			$("#tabs").jtabs();
		});
		</script>
	</div>
</div>
</body>
</html>
