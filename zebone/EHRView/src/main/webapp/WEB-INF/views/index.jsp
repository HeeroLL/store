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
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-dialog.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/index.css"/>
<style type="text/css">
html,body{height:100%;overflow: hidden;}
#tab1-main a{
	display:block;
	margin:4px 0 0 15px;
}
#tab2-main a, #tab3-main a{
	display:block;
	margin:4px 0 0 15px;
}
</style>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
<script type="text/javascript">

//当前选中的tab的序号
var tabSelectedIndex=0;

$(function(){
	if (typeof String.prototype.startsWith != 'function') {
	  // see below for better implementation!
	  String.prototype.startsWith = function (str){
	    return this.indexOf(str) == 0;
	  };
	}
	
	
	$('#basic_info_panel').appendTo('#basic_info');
	$('body').layout({
		north: {
			size : "auto",
			spacing_open : 0		 	
		},
		west__size:250
	});
	$('#tabpanel_body').find('.tab-body-child:first').show();
	$('.tabs-nav-left li').click(function(){
		var id=$(this).attr('id');
		$('#tabpanel_body').find('.tab-body-child').hide();
		$('#'+id+'-main').show();
		$('.tabs-nav-left li').removeClass('active');
		$(this).addClass('active');
	});

	//单击tab后，初始化右侧iframe
	$("#tab1").click(function(){
		if(tabSelectedIndex==1){
			return ;
		}
		$("#iframeRight").attr("src","home/healthInfoPage.zb?empiId=${empi}");
		tabSelectedIndex=1;
	});
	$("#tab2").click(function(){
		if(tabSelectedIndex==2){
			return ;
		}
		$("#iframeRight").attr("src","healthProblemList.zb");
		tabSelectedIndex=2;
	});
	$("#tab3").click(function(){
		if(tabSelectedIndex==3){
			return ;
		}
		$("#iframeRight").attr("src","lifePeriodList.zb");
		tabSelectedIndex=3;
	});
	
	$(window).resize(function(){
		
	});
	
	var age = getCeilAge();
	
	showOrHideLifePeriod(age);
	
	//alert($("#empi_birthday").val());
});

function getCeilAge(){
	var nowDate = new Date();
	var birthday = $("#empi_birthday").val();
	var year = birthday.substring(0,4);
	var month = birthday.substring(4,6);
	var day = birthday.substring(6,8);
	var myBirthday = new Date(year, (month-1), day);
	
	var milli=nowDate-myBirthday;
	var milliPerYear=1000*60*60*24*365.26;
	var yearFractional = milli/milliPerYear;
	var ceilYear = Math.ceil(yearFractional);
	return ceilYear;
	
}

//日期补全0
function fillZero(foo){
	if(foo.toString().length==1){
		return "0"+foo;
	}else{
		return foo;
	}
}

function showOrHideLifePeriod(age){
	var birthday = $("#empi_birthday").val();
	var year = birthday.substring(0,4);
	var month = birthday.substring(4,6);
	var day = birthday.substring(6,8);
	var myBirthday = new Date(year, (month-1), day);
	var milliPerYear=1000*60*60*24*365.26;
	if(age>=0){
		var periodDay1 = myBirthday.getTime()+milliPerYear*1;
		var tempDay1 = new Date(periodDay1);
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=0&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+birthday+(parseInt(year)+1)+month+day+'" target="iframeRight">婴儿期（0~1岁）</a><br/><hr/>');
	}
	if(age>=1){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=1&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+1)+month+day+(parseInt(year)+3)+month+day+'" target="iframeRight">幼儿期（1~3岁）</a><br/><hr/>');
	}
	if(age>=3){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=2&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+3)+month+day+(parseInt(year)+6)+month+day+'" target="iframeRight">学龄前期（3~6岁）</a><br/><hr/>');
	}
	if(age>=6){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=3&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+6)+month+day+(parseInt(year)+12)+month+day+'" target="iframeRight">学龄期（6~12岁）</a><br/><hr/>');
	}
	if(age>=12){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=4&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+12)+month+day+(parseInt(year)+20)+month+day+'" target="iframeRight">青春期（12~20岁）</a><br/><hr/>');
	}
	if(age>=20){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=5&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+20)+month+day+(parseInt(year)+45)+month+day+'" target="iframeRight">青年期（20~45岁）</a><br/><hr/>');
	}
	if(age>=45){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=6&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+45)+month+day+(parseInt(year)+60)+month+day+'" target="iframeRight">中年期（45~60岁）</a><br/><hr/>');
	}
	if(age>=60){
		$("#tab3-main").append('<a href="lifePeriodList.zb?periodCode=7&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+60)+month+day+(parseInt(year)+200)+month+day+'" target="iframeRight">老年期（60~岁以上）</a><br/><hr/>');
	}
	
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
<!--top-->
<div class="ui-layout-north">
	<div id="topbar" class="topbar">
		<span class="logo">健康档案浏览器</span>
		<span class="toplink">
			<a href="home/index.zb" class="home">首页</a> 
		</span>
		<span class="info">
			<!-- 单位：某某社区卫生服务中心
			用户：某某某 -->
		</span>
	</div>
	<div id="basic_info" class="basic_info">
		
	</div>
</div>
<!--left-->
<div class="ui-layout-west">
		<div id="tabpanel" class="tabpanel">
			<div id="tabbar" class="tabBar-left">
				<ul class="tabs-nav-left">
					<li id="tab1" class="active" ><a><span class="">卫生服务活动</span></a></li>
					<li id="tab2"><a><span class="">健康和疾病问题</span></a></li>
					<li id="tab3"><a><span class="">生命阶段</span></a></li>
				</ul>
			</div>
			<div id="tabpanel_body" class="tabBody">
				<div id="tab1-main" class="tab-body-child">
					<c:forEach items="${menuList}" var="object">
					   <a href="${object.url}?empiId=${empi}&dataCode=${object.code}&searchInfo=${searchInfo}&patientInfo=${patientInfo}" target="iframeRight">${object.name}</a>
					   <br/> 
					   <hr/>
					</c:forEach>
				</div>
				<div id="tab2-main" class="tab-body-child" style="display:none;">
					<c:forEach items="${healthMenuItemList}" var="object">
					   <a href="healthProblemList.zb?empiId=${empi}&dataCode=${object.code}&searchInfo=${searchInfo}&patientInfo=${patientInfo}" target="iframeRight">${object.name}</a>
					   <br/> 
					   <hr/>
					</c:forEach>
				</div>
				<div id="tab3-main" class="tab-body-child" style="display:none;">
					<input type="hidden" id="empi_birthday" value="${birthday}"/>
				</div>
			</div>
		</div>
	
</div>
<!--right-->
<div class="ui-layout-center">
	<div id="mainpanel" class="mainpanel">
	<iframe id="iframeRight" name="iframeRight" scrolling="auto" frameborder="0" style="width:100%;height:100%;" marginheight="0" marginwidth="0" src="home/healthInfoPage.zb?empiId=${empi}"></iframe>
	</div>
</div>
<!--基本信息-->
<div id="basic_info_panel" style="" class="container">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="width: 200px">
		<span class="title-r">
			<b class="icon"></b>
			<span class="title-span">基本信息：
				<strong> ${name}&nbsp;${sex}&nbsp;${age}岁</strong>
			</span>
		</span>
	</span>
	<a href="home/empiDetail.zb?empiId=${empi}" target="iframeRight" class="detail">详情</a>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<table style="text-align: center; width: 80%; margin:10px 0 7px 10%;">
						<tr>
							<td align="right"><label class="j-form-item-label">身份证号:</label></td>
							<td align="left">
								${card_no}
							</td>
							<td align="right"><label class="j-form-item-label">出生日期:</label></td>
							<td align="left">
								${birthday}
							</td>
							<td align="right"><label class="j-form-item-label">联系电话:</label></td>
							<td align="left">
								${contacts_mobile}      
							</td>
							<td align="right"><label class="j-form-item-label">现住址:</label></td>
							<td align="left">
								${address}    <input type="hidden" id="empi" value="${empi}"/>
							</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
