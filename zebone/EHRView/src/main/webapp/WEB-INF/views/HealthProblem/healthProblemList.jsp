<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>健康档案浏览器-基本信息-详细</title>
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


<script type="text/javascript">

var countEveryPage=15;//每页条数
var pageCount=0;//总页数
var curPage=1;//当前页码
var total=0;//总条数
//初始化分页
var initPage=function(opt){ 
	trs=$('.data');
	total=trs.length;
	pageCount=Math.ceil(total/countEveryPage);
	$('span[name=pageCount]').html(pageCount);
	goTopage(1);
	for(var i=0; i<pageCount; i++){
		$("#selectJumpto").append("<option value='"+(i+1)+"'>"+(i+1)+"</option>");
	}
};


//显示第几页
var goTopage=function(n){
	curPage=n;
	var startIndex,lastIndex;
	if(n==1){
		startIndex=0;
		lastIndex=n*countEveryPage-1;
	}else{
		startIndex=(n-1)*countEveryPage;
		lastIndex=n*countEveryPage-1;
	}

	for(var i=0;i<total;i++){
		if(i>=startIndex&&i<=lastIndex){
			trs[i].style.display='';
		}else{
			trs[i].style.display='none';
		}
	}
	setPageBar();
}
//设置分页面板
var setPageBar=function(){
	$('#totalCount').html(total);
	$('span[name=curPage]').html(curPage);
	
	if(curPage==1){
		$("a[name='first']").hide();
		$("a[name='previous']").hide();
	}else{
		$("a[name='first']").show();
		$("a[name='previous']").show();
	}
	if(curPage==pageCount||pageCount<=0){
		$("a[name='next']").hide();
		$("a[name='last']").hide();
	}else{
		$("a[name='next']").show();
		$("a[name='last']").show()
	}

    //跳转按钮
    jgridPage.find("input[name='pageGo']").click(function(){
        var val=jgridPage.find("input[name='curPage']").val();
        var regx = /^[1-9]\d*$/;
        if(!regx.exec(val)){ alert('数据不合法');return false;}
        if(parseInt(val)>pageCount){
            goTopage(pageCount);
            jgridPage.find("input[name='curPage']").val(pageCount);
        }else{
            goTopage(val);
        }
    });
};

$(function(){
	pageBar=document.getElementById('JGrid-page');
	jgridPage=$('#JGrid-page');
	initPage();
	
	$('.jgridPageButton').click(function(){
		var able = $(this).attr("able"); 		
 		if(able=='false') return ;
		var name=$(this).attr('name');
		if(name=='first'){//首页
			goTopage(1);
		}else if(name=='previous'){//上页
			curPage--;
			goTopage(curPage);				
		}else if(name=='next'){//下页
			curPage++;
			goTopage(curPage);
		}else if(name=='last'){//尾页
			goTopage(pageCount);
		}
	});
	
	$(".data").each(function(){
		var age = getCeilAge($(this).find("td:eq(2)").html());
		var lifeperiod;
		if(age==0){
			lifeperiod="婴儿期（0~1岁）";
		}else
		if(age>=1 &&age<3){
			lifeperiod="幼儿期（1~3岁）";
		}else
		if(age>=3&&age<6){
			lifeperiod="学龄前期（3~6岁） ";
		}else
		if(age>=6&&age<12){
			lifeperiod="学龄期（6~12岁） ";
		}else
		if(age>=12&&age<20){
			lifeperiod="青春期（12~20岁） ";
		}else
		if(age>=20&&age<45){
			lifeperiod="青年期（20~45岁） ";
		}else
		if(age>=45&&age<60){
			lifeperiod="中年期（45~60岁） ";
		}else
		if(age>=60){
			lifeperiod="老年期（60~岁以上） ";
		}
		$(this).find("td:eq(2)").html(lifeperiod);
	});
	
});

var jumpto = function(){
	goTopage($("#selectJumpto").val());
};

//得到年龄（参数格式lifeperiod：20130802T0833）
function getCeilAge(lifeperiod){
	var birthday1 = $("#patientage").val();
	
	//var nowDate = new Date();
	var birthday = lifeperiod;
	var year = birthday.substring(0,4);
	var month = birthday.substring(4,6);
	var day = birthday.substring(6,8);
	var year1 = birthday1.substring(0,4);
	var month1 = birthday1.substring(4,6);
	var day1 = birthday1.substring(6,8);
	var myBirthday = new Date(year, (month-1), day);
	var myBirthday1 = new Date(year1, (month1-1), day1);
	
	var milli=myBirthday-myBirthday1;
	var milliPerYear=1000*60*60*24*365.26;
	var yearFractional = milli/milliPerYear;
	var ceilYear = Math.ceil(yearFractional);
	return ceilYear;
	
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

<div class="j-panel j-panel-radius j-panel-nohead" id="" style="padding:5px;min-height:648px;">
		<div class="j-panel-body" id="" style="">
			<div  class="position">
				<a>健康和疾病问题</a> » <a>${healthName}</a>
				<input type="hidden" id="patientage" value="${patientage}"/>
			</div>
			<table width="100%" class="jgridDataTable">
				<tr class="header">
					<th>序号</th>
					<th>时间</th>
					<th>生命阶段</th>
					<th>卫生服务活动</th>
					<th>详情</th>
				</tr>
				<c:forEach items="${healthProblemList}" var="object" varStatus="status">
					<tr class="data">
						<td align="center">${status.index+1}</td>
						<td align="center">${object.time}</td>
						<td align="center">${object.healthProblem}</td>
						<td align="center">${object.healthActivity}</td>
						<td align="center"><a href="healthProblemDetails.zb?empiId=${empiId}&dataCode=${object.docNo}&docNo=${object.code}&searchInfo=${searchInfo}&patientInfo=${patientInfo}">查看</a></td>
					</tr>	
				</c:forEach>
				
			</table>
			
			<div class="pagination">
				<div>
					共<span id="totalCount" name="totalCount">0</span>条记录 
					<span name="curPage">1</span>/<span name="pageCount">1</span>页&nbsp;
					<a name="first" href="javascript:goTopage(1);">首页</a>
					<a name="previous"href="javascript:curPage--;goTopage(curPage);">上一页</a>&nbsp;
					<a name="next" href="javascript:curPage++;goTopage(curPage);">下一页</a>
					<a name="last" href="javascript:goTopage(pageCount);">尾页</a>&nbsp;
					第
					<select onchange="jumpto();" id="selectJumpto">
					</select>
					页
				</div>
			</div>
		</div>
	</div>
</body>
</html>
	