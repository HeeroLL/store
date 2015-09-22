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
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/css/index.css"/>
<style type="text/css">
html,body{height:100%;}
.j-panel{
	margin:0 0 10px;
}
h3{
	font-size:16px;padding: 2px 0 0 10px;
}
</style>
<script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="resources/js/jquery/jquery-panel.js"></script>
<script type="text/javascript">
    //跳转到糖尿病专档页面
    function diabetesRep() {
        var empiId = '${empiId}';
        var dataCode1 = '${dataCode1}';
        window.location.href = "diabetesRep.zb?empiId=" + empiId + "&dataCode=" + dataCode1;
    }
    var syncColWidth=function(){
        var hT=document.getElementById('JGrid-headerTable').rows[0];
        var dT=document.getElementById('JGrid-dataTable').rows[0];

        if(dT){
            for(var i=0;i<hT.childNodes.length;i++){
                //dT.childNodes[i].style.width=hT.childNodes[i].style.width;
            }
        }
    };
    var countEveryPage=5;//每页条数
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
    };
    //设置分页面板
    var setPageBar=function(){
        $('#totalCount').html(total);
        $('span[name=curPage]').html(curPage);

        if(curPage==1){
            jgridPage.find("div[name='first']").addClass('first_disable');
            jgridPage.find("div[name='first']").attr('able', false);
            jgridPage.find("div[name='previous']").addClass('previous_disable');
            jgridPage.find("div[name='previous']").attr('able', false);
        }else{
            jgridPage.find("div[name='first']").removeClass('first_disable');
            jgridPage.find("div[name='first']").attr('able', true);
            jgridPage.find("div[name='previous']").removeClass('previous_disable');
            jgridPage.find("div[name='previous']").attr('able', true);
        }
        if(curPage==pageCount||pageCount<=0){
            jgridPage.find("div[name='next']").addClass('next_disable');
            jgridPage.find("div[name='next']").attr('able', false);
            jgridPage.find("div[name='last']").addClass('last_disable');
            jgridPage.find("div[name='last']").attr('able', false);
        }else{
            jgridPage.find("div[name='next']").removeClass('next_disable');
            jgridPage.find("div[name='next']").attr('able', true);
            jgridPage.find("div[name='last']").removeClass('last_disable');
            jgridPage.find("div[name='last']").attr('able', true);
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
        syncColWidth();//同步表头和数据表列的宽度
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
<div id="grid" class="">
	<div id="JGrid" class="jgridDIV" >
		<div id="JGrid-9b4d6-main" class="jgridMain" style="">
			<div id="JGrid-9b4d6-header" class="j-panel-header">
				<div class="j-panel-title">卫生服务活动—><a href="javascript:diabetesRep();">糖尿病专档</a> &gt;&gt; 随访记录</div>
			</div>
			<div id="JGrid-9b4d6-headerAndDataDiv" class="jHeaderAndDataDIV" style="height: auto;">
				<div class="jHeaderDIV">
					<table id="JGrid-headerTable" class="jgridHeaderTable" cellspacing="0" cellpadding="0" border="0" >
						<tr class="header" name="header">
							<%--<td width="100"  style="text-align:center;width:100px" name="maxicon"><div class="headerTdDiv">序号<span value="0"></span></div></td>--%>
							<td style="text-align:center;width:190px" name="maxicon"><div class="headerTdDiv">随访日期<span value="0"></span></div></td>
							<td style="text-align:center;width:190px" name="orderNo"><div class="headerTdDiv">随访医生<span value="0"></span></div></td>
							<td style="text-align:center;width:190px" name="moduleName"><div class="headerTdDiv">服务机构<span value="0"></span></div></td>
                            <td style="text-align:center;width:190px" name=""><div class="headerTdDiv">随访方式<span value="0"></span></div></td>
							<td style="text-align:center;width:190px" name="url"><div class="headerTdDiv">随访结果评估<span value="0"></span></div></td>
							<td style="text-align:center;width:160px" name="url"><div class="headerTdDiv">操作<span value="0"></span></div></td>
						</tr>
					</table>
				</div>
				<div id="JGrid-85a5b-dataDiv" class="jgridDataDIV" style="height: 450px;">
					<table cellspacing="1" cellpadding="0" border="0" id="JGrid-dataTable">
						<c:forEach items="${hypertensionList}" var="object" varStatus="status">
						    <tr index="1" name="data" class="data">
						    	<%--<td align="center" title="maxicon1" selects="0" style="width:100px;">${status.index+1}</td>--%>
								<td align="center" title="maxicon1" selects="0" style="width: 190px;">${object.time}</td>
								<td align="center" title="1" selects="0" style="width: 190px;">${object.doctor}</td>
								<td align="center" title="moduleName1" selects="0" style="width: 190px;">${object.org}</td>
                                <td align="center" title="" selects="0" style="width: 190px;">${object.mode}</td>
								<td align="center" title="url1" selects="0" style="width: 190px;">${object.comment}</td>
								<td align="center" title="url1" selects="0" style="width: 160px;"><a href="diabetesDetails.zb?empiId=${empiId}&dataCode=${dataCode}&docNo=${object.code}">详情</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>

                <div id="JGrid-page" class="jgridPage">
                    <div class="pageInfo" name="pageInfo">
                        <div class="page_count" name="pageInfo3" >共<span id="totalCount" name="totalCount">0</span>条</div>
                        <div able="false" name="first" title="首 页" class="jgridPageButton page_first first_disable"></div>
                        <div able="false" name="previous" title="上一页" class="jgridPageButton page_prev previous_disable"></div>
                        <div title="页码" name="pageInfo2" class="pageNo" ><span name="curPage">1</span>/<span name="pageCount">1</span></div>
                        <div able="false" name="next" title="下一页" class="jgridPageButton page_next next_disable"></div>
                        <div able="false" name="last" title="尾 页" class="jgridPageButton page_last last_disable"></div>
                        <div class="pageJump">跳到第<input type="text" class="goToPageNo" value="1" name="curPage">页<input type="button" class="pageGo" name="pageGo" title="GO"></div>
                    </div>
                </div>
			     
			</div>
		</div>
	</div>
</div>
</body>
</html>
	