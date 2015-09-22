<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>主数据内容填写页面</title>
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
    <script type="text/javascript" src="js/jsp/dip/masterDataStru/masterdatatru_edit.js"></script>
    <script>
        var mdTableExist = '${mdTableExist}';
    </script>
    <style type="">
        html,body{background-color: #EEF7FE;overflow: hidden;}
    </style>
</head>
<body>
<div class="container">
    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">主数据结构</span>
		</span>
	</span>
    <div class="tools-panel"></div>
    <div class="c_w">
        <div class="c_e">
            <div class="c_content">
                <table class="checkForm" id="fromEdit">
                    <tr>
                        <td>主数据编码：</td>
                        <input type="hidden" name="id" value="${masterData.id}"/>
                        <td><input type="text" name="code" value="${masterData.code}"
                                   reg="['$\\|&@#={}<>!^*~?！￥……（）—【】‘；：”“'。，、？]"
                                   validate='String|1-32'/></td>
   	     	       	</tr>
   	     	       	<tr>
                        <td>主数据名称：</td>
                        <td><input type="text" name="name" value="${masterData.name}" validate='String|1-64'/></td>
                    </tr>
                    <tr>
                        <td>数据库表名称：</td>
                        <td><input type="text" name="tableName" id="tableName" value="${masterData.tableName}"/></td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>主数据类型：</td>--%>
                        <%--<td>--%>
                            <%--<select name="typeId" style="width: 130px;">--%>
                                <%--<option value="MD000001" <c:if test="${masterData.typeId =='MD000001'}">--%>
                                    <%--<c:out value="selected"/></c:if>>MD000001</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>主数据描述：</td>
                        <td>
						<textarea rows="5" cols="20" name="comment"  style="width: 130px;"
                                  reg="['$\\|&@#={}<>!^*~?！￥……—【】‘；：”“'。，、？]"
                                  validate="string|0-128">${masterData.comment}</textarea>
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
