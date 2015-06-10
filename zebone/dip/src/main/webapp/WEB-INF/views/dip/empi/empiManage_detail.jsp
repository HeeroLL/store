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
        var col = [ {text: '卡号', width: 160,	textAlign: 'center',	align : 'center',	dataIndex: 'cardNo'   },
            {text: '卡类型',	  width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'cardType'   },
            {text: '发卡机构',	  width: 180,	textAlign: 'center',	align : 'center',	dataIndex: 'cardOrg'	},
            {text: '卡状态',	  width: 60,	textAlign: 'center',	align : 'center',	dataIndex: 'cardStatus',
                renderer: function (value) {
                    if (value == '1') {
                        return "正常";
                    } else if (value == '2') {
                        return "注销";
                    }
                }}
        ];
        $(function(){
            var grid = new JGrid({
                title : '标识信息列表',
                height:200,
                col : col,
                checkbox : false,
                dataUrl : 'empiManage/cardList.zb?empi='+'${empiInfo.empi}',
                render : 'grid',
                pageBar  : false,
                crudOpera: false
            });
            grid.loadData();
        });
    </script>
</head>
<body>
<div style="text-align:right;padding-right: 30px;margin-top: 20px;">主索引号：${empiInfo.empi}</div>
<table style="width:95%;margin-bottom: 20px;border-collapse:collapse;" align="center" >
    <tr>
        <td colspan="4" class="tableTitle">个人基本信息</td>
    </tr>
    <tr>
        <td rowspan="7">
            <div class="tableKey">
                <img src="data:image/png;base64,${empiInfo.strPhoto}"/>
            </div>
        </td>
        <td class="tableKey">姓名</td>
        <td class="tableValue" colspan="2">${empiInfo.name}</td>
    </tr>
    <tr>
        <td class="tableKey" >性别</td>
        <td class="tableValue" colspan="2">${empiInfo.sex}</td>
    </tr>
    <tr>
        <td class="tableKey">出生日期</td>
        <td class="tableValue"colspan="2">${empiInfo.strBirthday}</td>
    </tr>
    <tr>
        <td class="tableKey">婚姻状况</td>
        <td class="tableValue" colspan="2">${empiInfo.maritalStatus}</td>
    </tr>
    <tr>
        <td class="tableKey">国籍</td>
        <td class="tableValue" colspan="2">${empiInfo.nationality}</td>
    </tr>
    <tr>
        <td class="tableKey">民族</td>
        <td class="tableValue" colspan="2">${empiInfo.nation}</td>
    </tr>
    <tr>
        <td class="tableKey">人员等级</td>
        <td class="tableValue" colspan="2">${empiInfo.starLevel}</td>
    </tr>
    <tr>
        <td class="tableKey">手机号码</td>
        <td class="tableValue">${empiInfo.mobileNumber}</td>
        <td class="tableKey">电话号码</td>
        <td class="tableValue">${empiInfo.telNumber}</td>
    </tr>
    <tr>
        <td class="tableKey">职业</td>
        <td class="tableValue">${empiInfo.profession}</td>
        <td class="tableKey">工作单位</td>
        <td class="tableValue">${empiInfo.workUnit}</td>
    </tr>
    <tr>
        <td class="tableKey">联系人姓名</td>
        <td class="tableValue">${empiInfo.contacts}</td>
        <td class="tableKey">联系人电话</td>
        <td class="tableValue">${empiInfo.contactsMobile}</td>
    </tr>
    <tr>
        <td class="tableKey">户籍地址</td>
        <td class="tableValue" colspan="3">${empiInfo.permanentAddress}</td>
    </tr>
    <tr>
        <td class="tableKey">常住地址</td>
        <td class="tableValue" colspan="3">${empiInfo.address}</td>
    </tr>
</table>

<div class="c_content" style="margin-left: 10px;margin-right: 10px;">
    <div id="grid"></div>
</div>

</body>
</html>