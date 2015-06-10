<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<ehrview:htmlBase/>
<link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/css/index.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/css/icons.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery.zTree.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery-dialog.css"/>
<link rel="stylesheet" type="text/css" href="resources/new/themes/default/jquery-tab.css"/>
<link rel="stylesheet" href="resources/new/css/themes/default/jquery-ui.css"/>
<script type="text/javascript" src="resources/new/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery-ui-1.10.1.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/jquery-tab1.js"></script>
<script tyep="text/javascript" src="resources/vendor/fusionCharts/FusionCharts.js"></script>

<title>健康档案浏览器</title>
<style type="text/css">

</style>
<script type="text/javascript">
$(function(){
	
	var child1 = ['zhqk', 'jqjc', 'yfjzs', 'jqjy', 'etbmiszqx', 'jqyy', 'jqmz', 'jqzy'];
	var child2 = ['zhqk', 'jqjc', 'yfjzs', 'jqjy', 'etbmiszqx', 'jqyy', 'jqsf', 'jqmz', 'jqzy', 'glys'];

    var woman1 = ['zhqk', 'jqjc', 'jws', 'jqjy', 'shxg', 'jqyy', 'jqmz', 'jqzy','sys'];
    var woman2 = ['zhqk', 'jqjc', 'jws', 'jqjy', 'shxg', 'jqyy', 'jqsf', 'jqmz', 'jqzy', 'sys','glys'];
	
	var other1 = ['zhqk', 'jqjc', 'jws', 'jqjy', 'shxg', 'jqyy', 'jqmz', 'jqzy'];
	var other2 = ['zhqk', 'jqjc', 'jws', 'jqjy', 'shxg', 'jqyy', 'jqsf', 'jqmz', 'jqzy', 'glys'];
	
	var older1 = ['zhqk', 'jqjc', 'jws', 'jqjy', 'mbqxgxy', 'jqyy', 'mbqxtnb', 'jqmz', 'shxg', 'jqzy'];
	var older2 = ['zhqk', 'jqjc', 'jws', 'jqjy', 'mbqxgxy', 'jqyy', 'mbqxtnb', 'jqsf', 'shxg', 'jqmz', 'jqzy', 'glys'];
	
	
	var infoTypeStr = "${businessBlock}";
	var infoType = eval(infoTypeStr);
	
	//实现隐藏所有的.j-panel
	$(".j-panel").hide();
	
	//存储左侧最近一次排序的id
	var leftId="zhqk";
	//存储右侧最近一次排序的id
	var rightId="jqjc";
	for(var i=0; i<infoType.length; i++){
		
		if(i>1 && infoType[i]!="glys"){
			
			if(i%2==0){
				
				$("#"+infoType[i]).insertAfter("#"+leftId);
				leftId = infoType[i];
			}else{
				
				$("#"+infoType[i]).insertAfter("#"+rightId);
				rightId = infoType[i];
			}
			
		}
		
		$("#"+infoType[i]).show();
	}
	
	$("#jwsTab").jtabs();
	
	
	if(parent.document.getElementById('iframe')){
		parent.document.getElementById('iframe').style.height=(document.body.scrollHeight+10)+'px';
	}
	
	//------------------FusionCharts Free----------------
	//basic line
    var hypertensionCharts = new FusionCharts("resources/vendor/fusionCharts/MSLine.swf", "chartsId1", "375", "170");
    hypertensionCharts.setDataXML("<chart caption='' subcaption='' hovercapbg='FFECAA' " +
            "hovercapborder='' formatNumberScale='0' decimalPrecision='0' showvalues='0' connectNullData='1'" +
            "numdivlines='5' numVdivlines='0' yaxisminvalue='0' yaxismaxvalue='180'  rotateNames='0'>" +
            "<categories ><category name='${GXYHZB1}' />" +
            "<category name='${GXYHZB2}' /><category name='${GXYHZB3}' /><category name='${GXYHZB4}' />" +
            "<category name='${GXYHZB5}' /><category name='${GXYHZB6}' /><category name='${GXYHZB7}' />" +
            "<category name='${GXYHZB8}' /><category name='${GXYHZB9}' /><category name='${GXYHZB10}' />" +
            "<category name='${GXYHZB11}' /><category name='${GXYHZB12}' /></categories>" +
            "<dataset seriesName='收缩压' color='63B8FF' anchorBorderColor='63B8FF' anchorBgColor='63B8FF'>" +
            "<set value='${SSY1}' /><set value='${SSY2}' /><set value='${SSY3}' /><set value='${SSY4}'/>" +
            "<set value='${SSY5}' /><set value='${SSY6}' /><set value='${SSY7}' /><set value='${SSY8}'/>" +
            "<set value='${SSY9}' /><set value='${SSY10}' /><set value='${SSY11}' /><set value='${SSY12}'/></dataset>" +
            "<dataset seriesName='舒张压' color='#8B008B' anchorBorderColor='#8B008B' anchorBgColor='#8B008B'>" +
            "<set value='${SZY1}' /><set value='${SZY2}' /><set value='${SZY3}' /><set value='${SZY4}'/>" +
            "<set value='${SZY5}' /><set value='${SZY6}' /><set value='${SZY7}' /><set value='${SZY8}'/>" +
            "<set value='${SZY9}' /><set value='${SZY10}' /><set value='${SZY11}' /><set value='${SZY12}'/></dataset>" +
            "</chart>");
    hypertensionCharts.render("hypertenCurve");

    var diabetesCharts = new FusionCharts("resources/vendor/fusionCharts/MSLine.swf", "chartsId2", "375", "170");
    diabetesCharts.setDataXML("<chart caption='' subcaption='' hovercapbg='FFECAA' decimals='1' " +
            "hovercapborder='' formatNumberScale='0' decimalPrecision='0' showvalues='0' connectNullData='1'" +
            "numdivlines='5' numVdivlines='0' yaxisminvalue='0' yaxismaxvalue='15' rotateNames='0'>" +
            "<categories ><category name='${XTHZB1}' />" +
            "<category name='${XTHZB2}' /><category name='${XTHZB3}' /><category name='${XTHZB4}' />" +
            "<category name='${XTHZB5}' /><category name='${XTHZB6}' /><category name='${XTHZB7}' />" +
            "<category name='${XTHZB8}' /><category name='${XTHZB9}' /><category name='${XTHZB10}' />" +
            "<category name='${XTHZB11}' /><category name='${XTHZB12}' /></categories>" +
            "<dataset seriesName='血糖值' color='63B8FF' anchorBorderColor='63B8FF' anchorBgColor='63B8FF'>" +
            "<set value='${XT1}' /><set value='${XT2}' /><set value='${XT3}' /><set value='${XT4}'/>" +
            "<set value='${XT5}' /><set value='${XT6}' /><set value='${XT7}' /><set value='${XT8}'/>" +
            "<set value='${XT9}' /><set value='${XT10}' /><set value='${XT11}' /><set value='${XT12}'/></dataset>" +
            "</chart>");
    diabetesCharts.render("diabetesCurve");
	
});
</script>
<!--[if IE 6]>
<script type="text/javascript" src="resources/new/js/ie6/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript">
 DD_belatedPNG.fix('DIV');
 DD_belatedPNG.fix('A');
 DD_belatedPNG.fix('SPAN');
 DD_belatedPNG.fix('INPUT');
</script>
<![endif]-->
</head>
<body>
<!--右侧1-->
<div class="right_left">
	<div class="j-panel j-panel-radius" id="zhqk" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon" style="background-image:url('resources/new/images/icons/20131122033525641_easyicon_net_24.png');">综合情况</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;padding:5px;">
			<div class="user_info_list">
				<table width="100%">
					<tr>
						<td width=""><label>ABO血型：</label></td>
						<td width="25%">${ABOXX}</td>
                        <td width=""><label>Rh血型：</label></td>
                        <td width="25%">${RHXX}</td>
					</tr>
					<tr>
                        <td width="" ><label>药物过敏史：</label></td>
                        <td width="25%">${YWGMS}</td>
						<td ><label>家族史：</label></td>
						<td width="25%">${JZS}</td>
					</tr>
                    <tr>
                        <td ><label>遗传病史：</label></td>
                        <td width="25%">${YCS}</td>
                        <td ><label>残疾类型：</label></td>
                        <td width="25%">${CJLX}</td>
                    </tr>
                    <tr>
                        <td><label>BMI指数：</label></td>
                        <td colspan="3">${BMI}</td>
                    </tr>
				</table>
			</div>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="jws" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-undo" style="background-image:url('resources/new/images/icons/20131122033754122_easyicon_net_24.png');">既往史</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;overflow:hidden;">
			<div id="jwsTab" style="height:100%;">
				<ul>
					<li>疾病史</li>
					<li>手术史</li>
					<li>外伤史</li>
					<li>输血史</li>
				</ul>
				<div>
					<table id="jws_1" width="100%" class="jgridDataTable">
						<tr class="header">
							<th>日期</th>
							<th>疾病名称</th>
						</tr>
						<tr>
							<td align="center">${JBSRQ1}</td>
							<td align="center">${JBSMC1}</td>
						</tr>
						<tr class="grid-row-alt">
							<td align="center">${JBSRQ2}</td>
							<td align="center">${JBSMC2}</td>
						</tr>
                        <tr>
                            <td align="center">${JBSRQ3}</td>
                            <td align="center">${JBSMC3}</td>
                        </tr>
					</table>
				</div>
				<div>
					<table id="jws_2" width="100%" class="jgridDataTable">
						<tr class="header">
							<th>日期</th>
							<th>手术名称</th>
						</tr>
						<tr>
							<td align="center">${SSRQ1}</td>
							<td align="center">${SSMC1}</td>
						</tr>
						<tr class="grid-row-alt">
							<td align="center">${SSRQ2}</td>
							<td align="center">${SSMC2}</td>
						</tr>
                        <tr>
                            <td align="center">${SSRQ2}</td>
                            <td align="center">${SSMC3}</td>
                        </tr>
					</table>
				</div>
				<div>
                    <table id="jws_3" width="100%" class="jgridDataTable">
                        <tr class="header">
                            <th>日期</th>
                            <th>外伤名称</th>
                        </tr>
                        <tr>
                            <td align="center">${WSRQ1}</td>
                            <td align="center">${WSMC1}</td>
                        </tr>
                        <tr class="grid-row-alt">
                            <td align="center">${WSRQ2}</td>
                            <td align="center">${WSMC2}</td>
                        </tr>
                        <tr>
                            <td align="center">${WSRQ2}</td>
                            <td align="center">${WSMC3}</td>
                        </tr>
                    </table>
				</div>
				<div>
                    <table id="jws_4" width="100%" class="jgridDataTable">
                        <tr class="header">
                            <th>日期</th>
                            <th>输血原因</th>
                        </tr>
                        <tr>
                            <td align="center">${SXRQ1}</td>
                            <td align="center">${SXMC1}</td>
                        </tr>
                        <tr class="grid-row-alt">
                            <td align="center">${SXRQ2}</td>
                            <td align="center">${SXMC2}</td>
                        </tr>
                        <tr>
                            <td align="center">${SXRQ2}</td>
                            <td align="center">${SXMC3}</td>
                        </tr>
                    </table>
				</div>
			</div>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="yfjzs" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-add" style="background-image:url('resources/new/images/icons/20131122041031665_easyicon_net_24.png');">预防接种史</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
			<div >
				预防接种史<br/>
			</div>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="jqjy" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-add" style="background-image:url('resources/new/images/icons/20131122041031665_easyicon_net_24.png');">近期检验</div>
		</div>
        <div class="j-panel-body" style="height: 177px;">
            <table width="100%" class="jgridDataTable">
                <tr class="header">
                    <th>日期</th>
                    <th>名称</th>
                    <th>结果</th>
                    <th>医院</th>
                </tr>
                <tr>
                    <td align="center">${JYRQ1}</td>
                    <td align="center">${JYMC1}</td>
                    <td align="center">${JYJG1}</td>
                    <td align="center">${JYYY1}</td>
                </tr>
                <tr class="grid-row-alt">
                    <td align="center">${JYRQ2}</td>
                    <td align="center">${JYMC2}</td>
                    <td align="center">${JYJG2}</td>
                    <td align="center">${JYYY2}</td>
                </tr>
                <tr>
                    <td align="center">${JYRQ3}</td>
                    <td align="center">${JYMC3}</td>
                    <td align="center">${JYJG3}</td>
                    <td align="center">${JYYY3}</td>
                </tr>
            </table>
        </div>
	</div>
	<div class="j-panel j-panel-radius" id="mbqxgxy" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-sum" style="background-image:url('resources/new/images/icons/20131122041031665_easyicon_net_24.png');">慢病曲线（高血压）</div>
		</div>
        <div class="j-panel-body"  style="height: 177px;">
            <div id="hypertenCurve"></div>
        </div>
	</div>
	<div class="j-panel j-panel-radius" id="mbqxtnb" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-add" style="background-image:url('resources/new/images/icons/20131122041031665_easyicon_net_24.png');">慢病曲线（糖尿病）</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
            <div >
                <div id="diabetesCurve"></div>
            </div>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="etbmiszqx" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-add" style="background-image:url('resources/new/images/icons/20131122041031665_easyicon_net_24.png');">儿童BMI生长曲线图</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
			<div>
                儿童BMI生长曲线图<br>
			</div>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="shxg" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-add" style="background-image:url('resources/new/images/icons/20131122041031665_easyicon_net_24.png');">生活习惯</div>
		</div>
		<div class="j-panel-body" style="height: 177px;">
            <div class="user_info_list">
                <table width="100%">
                    <tr>
                        <td width="25%"><label>运动频率：</label></td>
                        <td width="25%">${YDPL}</td>
                        <td width="25%"><label>饮食习惯：</label></td>
                        <td width="25%">${YSXG}</td>
                    </tr>
                    <tr>
                        <td width="25%"><label>吸烟状况：</label></td>
                        <td width="25%">${XYZK}</td>
                        <td width="25%"><label>饮酒种类：</label></td>
                        <td width="25%">${YJZL}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><label>有危害因素接触职业描述：</label></td>
                        <td colspan="2">${ZYMS}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><label>从事有危害因素职业时长：</label></td>
                        <td colspan="2">${ZYSC}</td>
                    </tr>
                </table>
            </div>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="jqsf" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-print" style="background-image:url('resources/new/images/icons/2013112204133296_easyicon_net_24.png');">近期随访</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
			<table width="100%" class="jgridDataTable">
				<tr class="header">
					<th>日期</th>
					<th>诊断</th>
					<th>医院</th>
					<th>科室</th>
				</tr>
				<tr>
					<td align="center">${SFRQ1}</td>
					<td align="center">${SFLX1}</td>
					<td align="center">${SFYY1}</td>
					<td align="center">${SFKS1}</td>
				</tr>
				<tr class="grid-row-alt">
                    <td align="center">${SFRQ2}</td>
                    <td align="center">${SFLX2}</td>
                    <td align="center">${SFYY2}</td>
                    <td align="center">${SFKS2}</td>
				</tr>
				<tr>
                    <td align="center">${SFRQ3}</td>
                    <td align="center">${SFLX3}</td>
                    <td align="center">${SFYY3}</td>
                    <td align="center">${SFKS3}</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<!--右侧2-->
<div class="right_right">
	<div class="j-panel j-panel-radius" id="jqjc" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-search" style="background-image:url('resources/new/images/icons/20131122041018304_easyicon_net_24.png');">近期检查</div>
		</div>
		<div class="j-panel-body" style="height: 177px;">
			<table width="100%" class="jgridDataTable">
				<tr class="header">
					<th>日期</th>
					<th>名称</th>
					<th>结果</th>
					<th>医院</th>
				</tr>
				<tr>
					<td align="center">${JCRQ1}</td>
					<td align="center">${JCMC1}</td>
					<td align="center">${JCJG1}</td>
					<td align="center">${JCYY1}</td>
				</tr>
				<tr class="grid-row-alt">
                    <td align="center">${JCRQ2}</td>
                    <td align="center">${JCMC2}</td>
                    <td align="center">${JCJG2}</td>
                    <td align="center">${JCYY2}</td>
				</tr>
				<tr>
                    <td align="center">${JCRQ3}</td>
                    <td align="center">${JCMC3}</td>
                    <td align="center">${JCJG3}</td>
                    <td align="center">${JCYY3}</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="jqyy" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-edit" style="background-image:url('resources/new/images/icons/20131122033640441_easyicon_net_24.png');">近期用药</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
			<table width="100%" class="jgridDataTable">
				<tr class="header">
					<th>分类</th>
					<th>日期</th>
					<th>药物名称</th>
					<th>用法</th>
					<th>用药途径</th>
				</tr>
                <tr>
                    <td align="center">${YYFL1}</td>
                    <td align="center">${YYRQ1}</td>
                    <td align="center">${YWMC1}</td>
                    <td align="center">${YYYF1}</td>
                    <td align="center">${YYTJ1}</td>
                </tr>
                <tr class="grid-row-alt">
                    <td align="center">${YYFL2}</td>
                    <td align="center">${YYRQ2}</td>
                    <td align="center">${YWMC2}</td>
                    <td align="center">${YYYF2}</td>
                    <td align="center">${YYTJ2}</td>
                </tr>
                <tr>
                    <td align="center">${YYFL3}</td>
                    <td align="center">${YYRQ3}</td>
                    <td align="center">${YWMC3}</td>
                    <td align="center">${YYYF3}</td>
                    <td align="center">${YYTJ3}</td>
                </tr>
                <tr class="grid-row-alt">
                    <td align="center">${YYFL4}</td>
                    <td align="center">${YYRQ4}</td>
                    <td align="center">${YWMC4}</td>
                    <td align="center">${YYYF4}</td>
                    <td align="center">${YYTJ4}</td>
                </tr>
			</table>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="jqmz" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-ok" style="background-image:url('resources/new/images/icons/2013112204122366_easyicon_net_24.png');">近期门诊</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
			<table width="100%" class="jgridDataTable">
				<tr class="header">
					<th>日期</th>
					<th>诊断</th>
					<th>医院</th>
					<th>科室</th>
				</tr>
				<tr>
					<td align="center">${MZRQ1}</td>
					<td align="center">${MZZD1}</td>
					<td align="center">${MZYY1}</td>
					<td align="center">${MZKS1}</td>
				</tr>
				<tr class="grid-row-alt">
                    <td align="center">${MZRQ2}</td>
                    <td align="center">${MZZD2}</td>
                    <td align="center">${MZYY2}</td>
                    <td align="center">${MZKS2}</td>
				</tr>
				<tr>
                    <td align="center">${MZRQ3}</td>
                    <td align="center">${MZZD3}</td>
                    <td align="center">${MZYY3}</td>
                    <td align="center">${MZKS3}</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="j-panel j-panel-radius" id="jqzy" style="height: 200px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-cancel" style="background-image:url('resources/new/images/icons/2013112204122366_easyicon_net_24.png');">近期住院</div>
		</div>
		<div class="j-panel-body"  style="height: 177px;">
			<table width="100%" class="jgridDataTable">
				<tr class="header">
                    <th>日期</th>
                    <th>诊断</th>
                    <th>医院</th>
                    <th>科室</th>
				</tr>
				<tr>
					<td align="center">${ZYRQ1}</td>
					<td align="center">${ZYZD1}</td>
					<td align="center">${ZYYY1}</td>
					<td align="center">${ZYKS1}</td>
				</tr>
				<tr>
                    <td align="center">${ZYRQ2}</td>
                    <td align="center">${ZYZD2}</td>
                    <td align="center">${ZYYY2}</td>
                    <td align="center">${ZYKS2}</td>
				</tr>
				<tr>
                    <td align="center">${ZYRQ3}</td>
                    <td align="center">${ZYZD3}</td>
                    <td align="center">${ZYYY3}</td>
                    <td align="center">${ZYKS3}</td>
				</tr>
			</table>
		</div>
	</div>
    <div class="j-panel j-panel-radius" id="sys" style="height: 200px;">
        <div class="j-panel-header" >
            <div class="j-panel-title j-panel-icon icon-undo" style="background-image:url('resources/new/images/icons/20131122033754122_easyicon_net_24.png');">既往史</div>
        </div>
        <div class="j-panel-body"  style="height: 177px;overflow:hidden;">
        </div>
    </div>

</div>
<div class="clear">

</div>
<!--右侧3-->
<div class="right_bottom">
	<div class="j-panel j-panel-radius" id="glys" style="height: 100px;">
		<div class="j-panel-header" >
			<div class="j-panel-title j-panel-icon icon-add" style="background-image:url('resources/new/images/icons/20131122041415869_easyicon_net_24.png');">管理医生</div>
		</div>
		<div class="j-panel-body"  style="height:77px;">
			<table width="100%">
			<tr>
				<td align="center">姓名：${YSXM}</td>
				<td align="center">联系电话：${LXDH}</td>
				<td align="center">所属机构：${SSJJ}</td>
				<td align="center">上次服务日期：${FWRQ}</td>
			</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
