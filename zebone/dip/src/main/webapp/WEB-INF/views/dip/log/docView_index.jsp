<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>日志查看模块</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css" id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
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
    <script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
    <script type="text/javascript" src="js/jsp/dip/log/docView_index.js"></script>
    <style type="">
        .term {
            margin-left: 10px;
        }
        html, body {
            background-color: #EEF7FE;
            overflow: hidden;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="c_nw">
        <div class="c_ne">
            <div class="c_n"></div>
        </div>
    </div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">档案调阅日志</span>
		</span>
	</span>

    <div class="tools-panel"></div>
    <div class="c_w">
        <div class="c_e">
            <div class="c_content">
                <div id="query">
                    <div style="padding: 5px;">
                        <span class="term">文档类型：</span>
                            <select name="docType" style="width: 115px;">
                                <option value="">请选择</option>
                                <c:forEach items="${docTypeList}" var="docInfo">
                                    <option value="${docInfo.code}">${docInfo.name}</option>
                                </c:forEach>
                            </select>
                        <span class="term">调阅机构：</span>
                            <input type="hidden" name="orgCode" id="orgCode"/>
                            <input type="text" name="orgName" id="orgName" style="width: 110px;"/>
                        <span class="term">日&nbsp;&nbsp;&nbsp;&nbsp;期：</span>
                            <input type="text" class="Wdate" style="width: 90px;" name="startDt"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>--
                            <input type="text" class="Wdate" style="width: 90px;" name="endDt"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </div>
                    <div style="padding: 5px;">
                        <span class="term">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
                            <input type="text" name="name" style="width: 108px;" maxlength="32"/>
                        <span class="term">标识类型：</span>
                            <select name="cardType" style="width: 117px;">
                                <option value="">请选择</option>
                                <c:forEach items="${cardTypeList}" var="cardInfo">
                                    <option value="${cardInfo.code}">${cardInfo.name}</option>
                                </c:forEach>
                            </select>
                        <span class="term">标识号：</span>
                            <input type="text" name="cardNo" style="width: 120px;" maxlength="20"/>
                        <span class="term">
                            <a class="btn" href="javascript:void(0);">
								<span class="btn-left" id="querybtn">
									<span class="btn-text icon-search">查询</span>
								</span>
                            </a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="c_sw">
        <div class="c_se">
            <div class="c_s"></div>
        </div>
    </div>
</div>
<div id="grid" style="margin-left: 10px;margin-right: 10px;"></div>
</body>
</html>
