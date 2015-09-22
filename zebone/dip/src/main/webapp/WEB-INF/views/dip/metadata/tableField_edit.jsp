<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>元数据信息页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
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
    <script type="text/javascript" src="js/jsp/dip/metadata/tablefield_edit.js"></script>
    <style type="">
        html,body{background-color: #EEF7FE;overflow: hidden;}
    </style>
</head>
<body>
<div class="container">
    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">表字段信息</span>
		</span>
	</span>
    <div class="tools-panel"></div>
    <div class="c_w">
        <div class="c_e">
            <div class="c_content">
                <table class="checkForm" id="fromEdit">
                    <tr>
                        <input type="hidden" name="id"  value="${fieldColumnConf.id}"/>
                        <input type="hidden" name="tableId" value="${fieldColumnConf.tableId}"/>
                        <td>字段名：</td>
                        <td><input type="text" name="columnName" value="${fieldColumnConf.columnName}"
                                    validate='String|1-32'/></td>
                        <td>字段名描述：</td>
                        <td><input type="text" name="columnDesc" value="${fieldColumnConf.columnDesc}"
                                     validate='String|1-32'/></td>
                    </tr>
                    <tr>
                        <td>字段说明：</td>
                        <td><input type="text" name="columnContent"
                                   value="${fieldColumnConf.columnContent}" validate='String|1-64'/></td>
                        <!--          
                        <td>长度：</td>
                        <td><input type="text" name="columnLength" value="${fieldColumnConf.columnLength}" maxlength="2"/></td>
                         -->
                    </tr>
                    <!--  
                    <tr>
                        <td>类型：</td>
                        <td>
                            <select name="columnType" style="width: 130px;">
                                <option value="字符" <c:if test="${fieldColumnConf.columnType =='字符'}"><c:out
                                        value="selected"/></c:if>>字符</option>
                                <option value="数值" <c:if test="${fieldColumnConf.columnType =='数值'}"><c:out
                                        value="selected"/></c:if>>数值</option>
                                <option value="日期" <c:if test="${fieldColumnConf.columnType =='日期'}"><c:out
                                        value="selected"/></c:if>>日期</option>
                            </select>
                        </td>
                        <td>是否为空：</td>
                        <td>
                            <select name="isNull" style="width: 130px;">
                                <option value="Y" <c:if test="${fieldColumnConf.isNull =='Y'}"><c:out value="selected"/></c:if>>是</option>
                                <option value="N" <c:if test="${fieldColumnConf.isNull =='N'}"><c:out value="selected"/></c:if>>否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>是否主键：</td>
                        <td>
                            <select name="isKey" style="width: 130px;">
                                <option value="Y" <c:if test="${fieldColumnConf.isKey =='Y'}"><c:out value="selected"/></c:if>>是</option>
                                <option value="N" <c:if test="${fieldColumnConf.isKey =='N'}"><c:out value="selected"/></c:if>>否</option>
                            </select>
                        </td>
                        <td>生成策略：</td>
                        <td><input type="text" name="columnPolicy"
                                   value="${fieldColumnConf.columnPolicy}" maxlength="100"/></td>
                    </tr>
                    -->
                </table>
            </div>
        </div>
    </div>
    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
