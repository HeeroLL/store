<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>健康档案浏览器-基本信息</title>
<ehrview:htmlBase/>
<link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-dialog.css"/>
<style type="text/css">
html,body{height:100%;}
#fieldset-body1 span{
	margin:10px 50px 10px 0;
	display:inline-block;
}
#fieldset-body1 {
    text-align: left;
	padding:10px 0 20px 0;
}
#fieldset-body2 span{
	margin:10px 50px 10px 0;
	display:inline-block;
}
#fieldset-body2 {
	text-align:left;
	padding:10px 0 20px 0;
}
#fieldset-body2 a{
	margin-top:20px;
}
#fieldset-body3 span{
    margin:10px 50px 10px 0;
    display:inline-block;
}
#fieldset-body3 {
    text-align:left;
    padding:10px 0 20px 0;
}
#fieldset-body4 span{
    margin:10px 50px 10px 0;
    display:inline-block;
}
#fieldset-body4 {
    text-align:left;
    padding:10px 0 20px 0;
}
#fieldset-body5 span{
    margin:10px 50px 10px 0;
    display:inline-block;
}
#fieldset-body5 {
    text-align:left;
    padding:10px 0 20px 0;
}
</style>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-panel.js"></script>

<script type="text/javascript">
var ie=navigator.appName=="Microsoft Internet Explorer"?true:false;
$(function(){
	var panel_1 = new JPanel({
        title: '健康基本信息',
        html: $('#fieldset-body1'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });
    var panel_2 = new JPanel({
        title: '生活方式',
        html: $('#fieldset-body2'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });
    var panel_3 = new JPanel({
        title: '高血压',
        html: $('#fieldset-body3'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });
    var panel_4 = new JPanel({
        title: '糖尿病',
        html: $('#fieldset-body4'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });
    var panel_5 = new JPanel({
        title: '老年人体征',
        html: $('#fieldset-body5'),
        width: 'auto',
        collapsible: true,
        renderTo: $('#container'),
        tools: [
            {icon: 'j-tool-maximize', handler: function () {
            }}
        ]
    });

});
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
<div id="container" class="" style="padding:10px;"></div>
	<div id="fieldset-body1" class="j-fieldset-body ">
        <table>
            <tr>
                <td><span>药物过敏史: ${GMS}</span></td>
                <td><span>暴露史: ${BLS}</span></td>
            </tr>
            <tr>
                <td><span>既往史: ${GWS}</span></td>
                <td><span>手术史: ${SSS}</span></td>
            </tr>
            <tr>
                <td><span>输血史: ${SXS}</span></td>
                <td><span>家族史: ${JZS}</span></td>
            </tr>
            <tr>
                <td><span>遗传病史: ${YCS}</span></td>
                <td><span>残疾情况: ${CJS}</span></td>
            </tr>
        </table>
	 	<!-- <div style="width:auto;text-align:right;"><a href="healthInfoDetails.zb">查看详情</a></div> -->
	</div>


<div id="fieldset-body2" class="j-fieldset-body">
    <table>
        <tr>
            <td><span>运动频率: ${YDPL}</span></td>
            <td><span>饮食习惯: ${YSXG}</span></td>
        </tr>
        <tr>
            <td><span>吸烟状况: ${XYZK}</span></td>
            <td><span>饮酒种类: ${YJZL}</span></td>
        </tr>
        <tr>
            <td><span>有危害因素接触职业描述: ${ZYMS}</span></td>
            <td><span>从事有危害因素职业时长: ${ZYSC}</span></td>
        </tr>
    </table>
</div>

<div id="fieldset-body3" class="j-fieldset-body">
    <table>
        <tr>
            <td><span>高血压水平分级: ${HSPFJ}</span></td>
            <td><span>高血压管理级别: ${HGLJB}</span></td>
        </tr>
        <tr>
            <td><span>高血压类型: ${HLX}</span></td>
            <td><span>发现方式: ${HFXFS}</span></td>
        </tr>
        <tr>
            <td><span>收缩压: ${SSY}</span></td>
            <td><span>舒张压: ${SZY}</span></td>
        </tr>
    </table>
</div>

<div id="fieldset-body4" class="j-fieldset-body">
    <table>
        <tr>
            <td><span>糖尿病类型: ${DLX}</span></td>
            <td><span>糖尿病管理级别: ${DGLJB}</span></td>
        </tr>
        <tr>
            <td><span>发现方式: ${DFXFS}</span></td>
            <td><span>空腹血糖: ${KFXT}</span></td>
        </tr>
        <tr>
            <td><span>餐后2小时血糖: ${CHXT}</span></td>
            <td><span>糖化血红蛋白: ${XHDB}</span></td>
        </tr>
    </table>
</div>

<div id="fieldset-body5" class="j-fieldset-body">
    <table>
        <tr>
            <td><span>听力情况: ${TLQK}</span></td>
            <td><span>视力情况: ${SLQK}</span></td>
        </tr>
        <tr>
            <td><span>心理状态: ${XLZT}</span></td>
            <td><span>智力状况: ${ZLZK}</span></td>
        </tr>
        <tr>
            <td><span>牙齿残缺: ${YCCQ}</span></td>
            <td><span>危险因素: ${WXYS}</span></td>
        </tr>
    </table>
</div>

</body>
</html>

