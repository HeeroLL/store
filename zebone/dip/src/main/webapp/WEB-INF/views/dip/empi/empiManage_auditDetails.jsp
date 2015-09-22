<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>主索引详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
    <script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-button.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.zTree.css"/>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
    <style type="text/css">
        html,body{
            background-color: #F9F9F9;
            height: auto;
        }
        table tr td{border: 1px solid #CCCCCC;}
        .tableTitle{
            font-weight:bold;
            padding-left: 20px;
        }
        .tableKey{
            text-align:center;
            padding-left: 20px;
            width:25%;
        }
        .tableValue{
            padding-left: 15px;
            width:25%;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
<table style="width:95%;margin-bottom: 20px;border-collapse:collapse;" align="center" >
    <tr style="height: 32px"><td colspan="7" align="center">身份证号：${updateApply.cardNo}</td></tr>
    <tr style="height: 32px">
        <td width="120" rowspan="4">
            <div align="center">
                <img src="data:image/png;base64,${empiInfo.strPhoto}"/>
            </div>
        </td>
        <td width="130" align="center">姓名</td>
        <td width="200" align="center">${empiInfo.name}</td>
        <td width="90" rowspan="7" align="center"><img src="./images/arrow-right.gif"/></span>
        </td>
        <td width="120" rowspan="4">
            <div align="center">
                <img src="data:image/png;base64,${updateApply.strPhoto}"/>
            </div>
        </td>
        <td width="130" align="center">姓名</td>
        <td width="200" align="center">${updateApply.name}</td>
    </tr>
    <tr style="height: 32px">
        <td align="center">性别</td>
        <td align="center">${empiInfo.sex}</td>
        <td align="center">性别</td>
        <td align="center">${updateApply.sex}</td>
    </tr>
    <tr style="height: 32px">
        <td align="center">出生日期</td>
        <td align="center">${empiInfo.strBirthday}</td>
        <td align="center">出生日期</td>
        <td align="center">${updateApply.strBirthday}</td>
    </tr>
    <tr style="height: 32px">
        <td align="center">民族</td>
        <td align="center">${empiInfo.nation}</td>
        <td align="center">民族</td>
        <td align="center">${updateApply.nation}</td>
    </tr>
    <tr style="height: 32px">
        <td align="center">户籍地址</td>
        <td colspan="2" align="center">${empiInfo.permanentAddress}</td>
        <td align="center">户籍地址</td>
        <td colspan="2" align="center">${updateApply.permanentAddress}</td>
    </tr>
</table>

<form id="auditForm" action="" class="checkForm">
    <div style="width:95%;margin-left: 20px;border-collapse:collapse; display: inline">
        <span>审核说明:<input type="text" id="auditDesc" name="auditDesc" style="width: 623px; height: 22px"/></span>
        <input type="hidden" id="empi" name="empi" value="${updateApply.empi}"/>
    </div>
</form>

</body>
</html>