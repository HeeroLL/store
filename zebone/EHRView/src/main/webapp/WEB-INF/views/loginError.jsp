<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/ehrview.tld" prefix="ehrview" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>错误信息</title>
    <ehrview:htmlBase/>
    <link rel="shortcut icon" type="image/x-icon" href="zebone.ico"/>
    <link rel="stylesheet" type="text/css" href="resources/css/themes/default/main.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-layout.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-tab.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-grid.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/themes/default/jquery-dialog.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/index.css"/>
    <style type="text/css">
        html, body {
            height: 100%;
        }
        .j-panel {
            margin: 0 0 10px;
        }
        h3 {
            font-size: 16px;
            padding: 2px 0 0 10px;
        }
    </style>
    <script type="text/javascript" src="resources/js/jquery/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery/jquery-dialog.js"></script>
    <script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
    <script type="text/javascript" src="resources/js/jquery/jquery.layout.js"></script>
    <script type="text/javascript" src="resources/js/jquery/jquery-panel.js"></script>
</head>
<body>
<h2 style="color: red">${errorMsg}</h2>
</body>
</html>