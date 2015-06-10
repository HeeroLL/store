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


<script>
 
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
});

var jumpto = function(){
	goTopage($("#selectJumpto").val());
};
//跳转到糖尿病专档页面
function diabetesRep() {
    var empiId = '${empiId}';
    var dataCode1 = '${dataCode1}';
    window.location.href = "diabetesRep.zb?empiId=" + empiId + "&dataCode=" + dataCode1;
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
				<a>卫生服务活动</a> »<a href="javascript:diabetesRep();">糖尿病专档</a>» <a>随访记录</a>
			</div>
			<table width="100%" class="jgridDataTable">
				<tr class="header">
					<th>随访日期</th>
					<th>随访医生</th>
					<th>服务机构</th>
					<th>随访方式</th>
					<th>随访结果评估</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${hypertensionList}" var="object" varStatus="status">
					<tr class="data">
						<td align="center">${object.time}</td>
						<td align="center">${object.doctor}</td>
						<td align="center">${object.org}</td>
						<td align="center">${object.mode}</td>
						<td align="center">${object.comment}</td>
						<td align="center"><a href="diabetesDetails.zb?empiId=${empiId}&dataCode=${dataCode}&docNo=${object.code}">详情</a></td>
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
	