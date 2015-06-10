<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>随访详情</title>
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
        //跳转到老年人保健随访记录页面
        function olderFollow() {
            var empiId = '${empiId}';
            var searchInfo = '${searchInfo}';
            var patientInfo = '${patientInfo}';
            window.location.href = "healthActivityCommonList.zb?empiId=" + empiId + "&dataCode=B04.04.01.00" + "&searchInfo=" + searchInfo + "&patientInfo=" + patientInfo;
        }

        $(function () {
            if (parent.document.getElementById('iframe')) {
                parent.document.getElementById('iframe').style.height = (document.body.scrollHeight + 10) + 'px';
            }
            $('#scroll a').click(function () {
                var id = $(this).attr('to');
                var iframeP = top.$('#iframe').offset();
                top.$('#scroll').css({top: iframeP.top + $(id).offset().top});
                top.$('#scroll').ScrollTo(300);
            });
        });
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
        <div class="position">当前位置：卫生服务活动-->疾病管理-->老年人健康管理--><a style="color: #0000ff;text-decoration: underline" href="javascript:olderFollow()">老年人保健随访</a>--><span style="color: #0000ff">老年人随访保健详情</span></div>
        <%--<div id="scroll" style="top:120px">--%>
            <%--<ul>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#healthRec">个人基本信息</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#allergy">药物过敏史</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#exposure">暴露史</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#pastHis">既往史</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#familyHis">家族史</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#geneticHis">遗传病史</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#disability">残疾情况</a></li>--%>
                <%--<li style="width: 50px"><a href="javascript:void(0);" to="#lifeEnv">生活环境</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        ${htmlValue}
    </div>
</div>
</body>
</html>