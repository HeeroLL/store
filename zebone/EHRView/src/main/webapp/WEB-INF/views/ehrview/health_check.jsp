<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>成人健康体检</title>
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
    <style type="text/css">
        #scroll {
            position: absolute;
            right: 0;
            top: 200px;
        }

        #scroll li {
            border-radius: 4px;
            border: 1px solid #99BCE8;
            padding: 0 5px;
            background: #E4EEFA;
            line-height: 22px;
        }

        #scroll li:hover {
            background: none repeat scroll 0 0 #D2E5FA;
        }
    </style>
    <script type="text/javascript">
        //设置滚动按钮位置
        var fixScroll=function(){
            var iframeP=top.$('#iframe').offset();
            var cHeight=top.document.documentElement.clientHeight;
            //var t=top.$.getScroll(event);
            var t=top.document.body.scrollTop+top.document.documentElement.scrollTop;

            var h=cHeight/2+t-iframeP.top-($('#scroll').height()/2);
            $('#scroll').animate({'top':h+'px'});

        }
        //定时器
        var fInterval=function(){
            setTimeout(function(){
                fixScroll();
                fInterval();
            },1000);
        }
        $(function(){
            if(top.document.getElementById('iframe')){
                top.document.getElementById('iframe').style.height=(document.body.scrollHeight+10)+'px';
            }
            $('#scroll').css({'right':66-$('#scroll').outerWidth()});
            $('#scroll').hover(function(){
                $(this).animate({'right':'0'});
            },function(){
                $(this).css({'right':66-$(this).outerWidth()});
            });
            $('#scroll a').click(function(){
                var id=$(this).attr('to');
                var iframeP=top.$('#iframe').offset();
                top.$('#scroll').css({top:iframeP.top+$(id).offset().top});
                top.$('#scroll').ScrollTo(300);
            });
            fInterval();
        });

        //跳转到成人健康体检列表页面
        function healthCheck() {
            var empiId = '${empiId}';
            var searchInfo = '${searchInfo}';
            var patientInfo = '${patientInfo}';
            window.location.href = "healthActivityCommonList.zb?empiId=" + empiId + "&dataCode=C00.04.01.00" + "&searchInfo=" + searchInfo + "&patientInfo=" + patientInfo;
        }
    </script>
    <!--[if IE 6]>
    <script type="text/javascript" src="resources/new/js/ie6/DD_belatedPNG_0.0.8a-min.js"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('DIV');
        DD_belatedPNG.fix('A');
        DD_belatedPNG.fix('SPAN');
        DD_belatedPNG.fix('INPUT');
    </script>
    <![endif]-->
</head>
<body>
<div class="j-panel j-panel-radius j-panel-nohead" style="padding:5px;min-height:648px;overflow:auto;">
    <div class="j-panel-body" style="">
        <div class="position">当前位置：卫生服务活动-->个人基本信息--><a style="color: #0000ff;text-decoration: underline" href="javascript:healthCheck()">成人健康体检</a>--><span style="color: #0000ff">成人健康体检详情</span></div>
        <div id="scroll" style="top:300px">
            <ul>
                <li style="width: 50px"><a href="javascript:void(0);" to="#symptom">症状</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#general">一般体征</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#older">老年人</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#lifeStyle">生活方式</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#organ">脏器功能</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#examination">查体</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#auxiliary">辅助检查</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#constitution">中医体质辨识</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#healthPro">现存主要健康问题</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#hospitalization">住院治疗情况</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#medication">用药情况</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#vaccination">非免疫规划预防接种史</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#assess">健康评估</a></li>
                <li style="width: 50px"><a href="javascript:void(0);" to="#control">危险因素控制</a></li>
            </ul>
        </div>
        ${htmlValue}
    </div>
</div>
</body>
</html>