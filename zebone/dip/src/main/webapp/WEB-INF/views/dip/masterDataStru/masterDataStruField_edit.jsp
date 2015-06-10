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
        <script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
        <script type="text/javascript">
            var field_type = '${masterDataField.fieldType}';
        </script>
    <script type="text/javascript" src="js/jsp/dip/masterDataStru/masterdatatrufield_edit.js"></script>
    <style type="">
        html,body{background-color: #EEF7FE;overflow: hidden;}
    </style>
</head>
<body>
<div class="container">
    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">主数据字段</span>
		</span>
	</span>
    <div class="tools-panel"></div>
    <div class="c_w">
        <div class="c_e">
            <div class="c_content">
                <table class="checkForm" id="checkForm">
                    <tr>
                        <input type="hidden" name="id"  value="${masterDataField.id}"/>
                        <td>数据库字段名：</td>
                        <td><input type="text" name="fieldName" id="fieldName" style="width: 130px;" value="${masterDataField.fieldName}"/></td>
                        <td>显示名称：</td>
                        <td><input type="text" name="displayName"  style="width: 130px;"value="${masterDataField.displayName}"
                                    validate='String|1-64'/></td>
                    </tr>
                    <tr>
                        <td>字段类型：</td>
                        <td>
                            <select name="fieldType" style="width: 134px;" id="fieldType" validate="select">
                                <option value="">请选择</option>
                                <option value="varchar" <c:if test="${masterDataField.fieldType =='varchar'}"><c:out
                                        value="selected"/></c:if>>字符</option>
                                <option value="number" <c:if test="${masterDataField.fieldType =='number'}"><c:out
                                        value="selected"/></c:if>>数值</option>
                                <option value="datatime" <c:if test="${masterDataField.fieldType =='datatime'}"><c:out
                                        value="selected"/></c:if>>日期</option>
                                <option value="dt" <c:if test="${masterDataField.fieldType =='dt'}"><c:out
                                        value="selected"/></c:if>>字典型</option>
                                <option value="md" <c:if test="${masterDataField.fieldType =='md'}"><c:out
                                        value="selected"/></c:if>>主数据</option>
                            </select>
                        </td>
                        <td>值域代码：</td>
                        <td><input style="width: 128px;" type="text"
                               id="typeCode" name="typeCode" value="${masterDataField.typeCode}"/>
                            <input type="hidden" id="typeValue" name="typeValue" value="${masterDataField.typeValue}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>字段说明：</td>
                        <td><input type="text" name="comment" style="width: 130px;"
                                   value="${masterDataField.comment}" validate='String|0-128'/></td>
                        <td>字段长度：</td>
                        <td><input type="text" name="fieldLength" style="width: 130px;"value="${masterDataField.fieldLength}" validate='number|1-8'/></td>
                    </tr>
                    <tr>                 
                        <td>是否可为空：</td>
                        <td>
                            <select name="nullable" style="width: 134px;">
                                <option value="1" <c:if test="${masterDataField.nullable =='1'}"><c:out value="selected"/></c:if>>是</option>
                                <option value="0" <c:if test="${masterDataField.nullable =='0'}"><c:out value="selected"/></c:if>>否</option>
                            </select>
                        </td>
                        <td>排序号：</td>
                        <td><input type="text" name="sortNo" style="width: 130px;" value="${masterDataField.sortNo}"
                                   validate='number|1-2'/></td>
                    </tr>
                    <tr>
                        <td><input type="hidden" name="masterDataId" style="width: 130px;"value="${masterDataField.masterDataId}"/></td>
                    </tr>
                    <tr>
                        <td>是否显示：</td>
                        <td>
                            <select name="displayable" style="width: 134px;">
                                <option value="Y" <c:if test="${masterDataField.displayable =='Y'}"><c:out value="selected"/></c:if>>是</option>
                                <option value="N" <c:if test="${masterDataField.displayable =='N'}"><c:out value="selected"/></c:if>>否</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</body>
</html>
