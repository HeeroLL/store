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
<script type="text/javascript" src="resources/new/js/jquery/jquery-tab.js"></script>
<script type="text/javascript" src="resources/new/js/jquery/scroll.js"></script>
<title>健康档案浏览器</title>
<style type="text/css">
html,body{height:auto;}
#smjd a{
	margin-left:27px;
}
#jkjbwt a{
	margin-left:47px;
}
</style>
<script type="text/javascript">
var setting1 = {
	view: {
		showLine: false,
		showIcon:false
	},
	callback:{
		onClick:function(event, treeId, treeNode){
		}
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
//标准文档类型树形表（包含所有情况）
var originNodes=[
         		{name:'个人基本信息', children:[
         		                        	'A00.01.01.00',
         		                      	    'C00.04.01.00'
         		                       ]}, 
        		{name:'医疗服务', children:[
        		                     'C00.01.02.00',
        		                     'C00.02.01.00',
        		                     'C00.04.04.00'
        		                     ]},
        		{name:'疾病管理', children:[
        		                     'B04.01.01.00',
        		                     'B04.02.01.00'
//缺少中兴精神疾病
									 
        		                     ]},
        		{name:'妇女保健', children:[
									 'B02.01.00.00',
									 'B02.03.00.00',
									 'B02.04.00.00'
        		                     ]},
        		{name:'儿童保健', children:[
									'B01.01.00.00',
									'B01.02.00.00',
									'B01.03.01.00'
        		                     ]},
        		{name:'疾病控制', children:[
        		                     ]},
				{name:'其他', children:[
				                   ]}
];
//拼装后台传过来的，需要前端展示的节点
var realNodesStrRaw = '<c:forEach items="${menuList}" var="object">{name:"${object.name}", url:"${object.url}", code:"${object.code}", acturl:"${object.url}?empiId=${empi}&dataCode=${object.code}&searchInfo=${searchInfo}&patientInfo=${patientInfo}"},</c:forEach>';
var realNodesStr = realNodesStrRaw.substring(0, realNodesStrRaw.length-1);
//eval成js对象
var realNodes = eval("["+realNodesStr+"];");
//用于记录node节点的id
var nodeId = 0;
//需要显示的节点（包含二级节点，后端传回的只有三级节点）
var showNodes = [];
//疾病管理
var diseasemanageid=-1;
//高血压建档id
var hyperid;
//糖尿病建档id
var diabid;
//老年人建档id
var olderid;
//妇女保健id
var womenhealth=-1;
//产前随访id
var beborthid=-1;

for(var i=0; i<originNodes.length; i++){
	var children = originNodes[i].children;
	var isChildExist = false;
	var secondMenuId = ++nodeId;
	for(var j=0; j<children.length;j++){
		for(var k=0; k<realNodes.length; k++){
			if(realNodes[k].code==children[j]){
				isChildExist=true;
				//alert(realNodes[k].code);
				//realNodes[k].pid=secondMenuId;
				 ++nodeId;
				 if(children[j]=="B04.01.01.00"){
					 diseasemanageid=secondMenuId;
					 hyperid=nodeId;
				 }else if(children[j]=="B04.02.01.00"){
					 diseasemanageid=secondMenuId;
					 diabid=nodeId;
				 }else if(children[j]=="B02.01.00.00" || children[j]=="B02.03.00.00"||children[j]=="B02.04.00.00"){
					 womenhealth=secondMenuId;
				 }
				var thirdMenuNode = {id:nodeId, pId:secondMenuId, name:realNodes[k].name, target:'right_iframe', url:realNodes[k].acturl , open:true };
				showNodes.push(thirdMenuNode);
			}
		}
	}
	//如果二级菜单存在，则添加一级菜单
	if(isChildExist){
		var secondMenuNode = {id:secondMenuId, pId:0, name:originNodes[i].name,open:true };
		showNodes.push(secondMenuNode);
	}	
}

//添加第三级节点
for(var l=0; l<realNodes.length;l++){
	//高血压
	if(realNodes[l].code=="B04.01.02.00"){
		++nodeId;
		var thirdMenuNode = {id:nodeId, pId:hyperid, name:realNodes[l].name, target:'right_iframe', url:realNodes[l].acturl };
		showNodes.push(thirdMenuNode);
	}else if(realNodes[l].code=="B04.02.02.00"){
		//糖尿病
		++nodeId;
		var thirdMenuNode = {id:nodeId, pId:diabid, name:realNodes[l].name, target:'right_iframe', url:realNodes[l].acturl };
		showNodes.push(thirdMenuNode);
	}else if(realNodes[l].code=="B04.04.01.00"){
		//老年人随访
		if(diseasemanageid==-1){
			++nodeId;
			var secondMenuNode = {id:nodeId, pId:0, name:'疾病管理',open:true };
			showNodes.push(secondMenuNode);
		}
		++nodeId;
		var thirdMenuNode = {id:nodeId, pId:diseasemanageid, name:'老年人健康管理',open:true };
		showNodes.push(thirdMenuNode);
		olderid=nodeId;
		++nodeId;
		var forthMenuNode = {id:nodeId, pId:olderid, name:realNodes[l].name, target:'right_iframe', url:realNodes[l].acturl };
		showNodes.push(forthMenuNode);
	}else if(realNodes[l].code=="B02.02.01.00"||realNodes[l].code=="B02.02.02.00"){
		if(womenhealth==-1){
			++nodeId;
			var secondMenuNode = {id:nodeId, pId:0, name:'妇女保健',open:true };
			showNodes.push(secondMenuNode);
			womenhealth = nodeId;
		}
		if(beborthid==-1){
			++nodeId;
			var thirdMenuNode = {id:nodeId, pId:womenhealth, name:'产前随访',open:true };
			showNodes.push(thirdMenuNode);
			beborthid=nodeId;
		}
		++nodeId;
		var thirdMenuNode = {id:nodeId, pId:beborthid, name:realNodes[l].name, target:'right_iframe', url:realNodes[l].acturl };
		showNodes.push(thirdMenuNode);
		
	}
	
	
}

//iframe高度适应
function setIframeHeight(){
	document.getElementById('iframe').style.height='500px';
}
$(function(){
	$.fn.zTree.init($("#wsfwhd"), setting1, showNodes);
	$("#accordion").accordion({
		heightStyle: "fill",
		collapsible:true,
		icons:{"header":"ui-icon-plus", "activeHeader":"ui-icon-minus"}
	});
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
	if(age>=60){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=7&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+60)+month+day+(parseInt(year)+200)+month+day+'" target="right_iframe">老年期（60~岁以上）</a><br/><hr/>');
	}
	if(age>=45){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=6&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+45)+month+day+(parseInt(year)+60)+month+day+'" target="right_iframe">中年期（45~60岁）</a><br/><hr/>');
	}
	if(age>=20){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=5&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+20)+month+day+(parseInt(year)+45)+month+day+'" target="right_iframe">青年期（20~45岁）</a><br/><hr/>');
	}
	if(age>=12){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=4&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+12)+month+day+(parseInt(year)+20)+month+day+'" target="right_iframe">青春期（12~20岁）</a><br/><hr/>');
	}
	if(age>=6){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=3&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+6)+month+day+(parseInt(year)+12)+month+day+'" target="right_iframe">学龄期（6~12岁）</a><br/><hr/>');
	}
	if(age>=3){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=2&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+3)+month+day+(parseInt(year)+6)+month+day+'" target="right_iframe">学龄前期（3~6岁）</a><br/><hr/>');
	}
	if(age>=1){
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=1&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+(parseInt(year)+1)+month+day+(parseInt(year)+3)+month+day+'" target="right_iframe">幼儿期（1~3岁）</a><br/><hr/>');
	}
	if(age>=0){
		var periodDay1 = myBirthday.getTime()+milliPerYear*1;
		var tempDay1 = new Date(periodDay1);
		$("#smjd").append('<a href="lifePeriodList.zb?periodCode=0&empiId=${empi}&searchInfo=${searchInfo}&patientInfo=${patientInfo}&period='+birthday+(parseInt(year)+1)+month+day+'" target="right_iframe">婴儿期（0~1岁）</a><br/><hr/>');
	}
	
}
$(function(){
	var age = getCeilAge();
	showOrHideLifePeriod(age);
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
<div class="index_outer">
	<div class="index_header">
		<div class="index_logo">
			江苏振邦健康档案浏览器
		</div>
		<div class="user_toolbar">
            <span class="home"><a href="home/homePage.zb?empiId=${empi}&birthday=${birthday}&role=${role}" target="right_iframe">首页</a></span>
            <span class="org">单位：湟里卫生院</span>
			<span class="user">用户：李四</span>
			<span class="role">角色：
                <c:if test="${role == 'R01'}">医疗医生</c:if>
                <c:if test="${role == 'R02'}">公卫医生</c:if>
            </span>
			<span class="logout"><a href="#">注销</a></span>
		</div>
	</div>
	<div class="main">
		<!--左侧-->
		<div class="left">
			<div class="j-panel j-panel-radius j-panel-nohead" id="" style="padding:0 5px;">
				<div class="j-panel-body" id="" style="padding:5px 0;">
				<table width="100%">
				<tr>
					<td>
						<div class="avatar">
                            <img src="data:image/png;base64,${photo}"/>
                            <%--<c:if test="${sex == '女'}"><img src="resources/new/images/avatar_red.png"/></c:if>--%>
                            <%--<c:if test="${sex == '男'}"><img src="resources/new/images/avatar_blue.png"/></c:if>--%>
                            <%--<c:if test="${sex == ''}"><img src="resources/new/images/avatar.png"/></c:if>--%>
                        </div>
					</td>
					<td valign="bottom">		
						<ul class="user_info_list">
							<li>
								<b>&nbsp;姓名：</b> ${name}
							</li>
							<li>
								<b>&nbsp;性别：</b> ${sex}
							</li>
							<li>
								<b>&nbsp;年龄：</b> ${age}
							</li>
						</ul>
					</td>
				</tr>
			
				</table>
					
					<div id="" class="user_info_list" style="">			
						<ul>
                            <c:if test="${patientCardType != '身份证'}">
                                <li>
                                    <label>${patientCardType}：</label> ${patientCardNo}
                                </li>
                            </c:if>
							<li>
								<label>身份证：</label> ${card_no}
							</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="j-panel j-panel-radius j-panel-nohead" id="" style="height: 350px;">
				<div id="accordion" class="j-panel-body"  style="height:100%;overflow:hidden;">
						 <h3>卫生服务活动</h3>
						<div>
							<div id="wsfwhd" class="ztree">
							</div>
						</div>
						<h3>健康疾病问题</h3>
						<div>
						   <div id="jkjbwt" class="ztree">
							   <c:forEach items="${healthMenuItemList}" var="object">
								   <a href="healthProblemList.zb?empiId=${empi}&dataCode=${object.code}&searchInfo=${searchInfo}&patientInfo=${patientInfo}" target="right_iframe">${object.name}</a>
								   <br/><hr/> 
								   
								</c:forEach>
							</div>
						</div>
						<h3>生命阶段</h3>
						<div>
						   <div id="smjd" class="ztree">
						   	<input type="hidden" id="empi_birthday" value="${birthday}"/>
							</div>
						</div>
				</div>
			</div>
		</div>
		<!--右侧-->
		<div class="right">
			<iframe id="iframe" src="home/homePage.zb?empiId=${empi}&birthday=${birthday}&role=${role}" name="right_iframe" class="iframeApp" frameborder="0" SCROLLING="no" marginwidth="0" marginheight="0" style="width: 100%; height: 100%;"></iframe>
		</div>
	</div>
	<div class="clear"></div>
    <P align="right"><font style="font-size: 12px"> 版权所有：江苏振邦智慧城市信息系统有限公司</font></P>
</div>
<div id="scroll" style="position:absolute;"></div><!--锚点滚动用-->
</body>
</html>
