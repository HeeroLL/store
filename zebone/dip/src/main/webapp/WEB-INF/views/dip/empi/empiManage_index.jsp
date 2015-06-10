<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>主索引管理系统</title>
    <script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <script type="text/javascript">
        //加载完成，执行以下代码
        $(function(){
            //加载layout，布局控件
            $('body').layout({
                resizable : true,
                livePaneResizing : true,
                slidable:true,
                west__size : 250,
                west__minSize : 250,
                west__resizable:true
            });
            window.document.getElementById("innerMainFrame").src = "empiManage/query.zb";//主索引信息查询页面
        });
    </script>
</head>

<body>
<div class="ui-layout-west">
    <iframe src="empiManage/tree.zb" name="innerlLeftFrame" style="height:100%;" scrolling="no" frameborder="0"
            noresize="noresize" id="innerlLeftFrame" title="innerlLeftFrame"></iframe>
</div>

<div class="ui-layout-center">
    <iframe src="" name="innerMainFrame" id="innerMainFrame" style="height:100%;width:100%;" frameborder="0"
            scrolling="no" title="innerMainFrame"></iframe>
</div>
</body>
</html>