<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>门诊详情</title>
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
    <div class="j-panel-body" style="height: 600px;overflow:hidden;background:#fff;">
        ${htmlValue}
    </div>
</body>
</html>