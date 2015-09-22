<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>表结构管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-tab.css" id="tab-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-button.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <script>
       var projectPath = '${pageContext.request.contextPath}';
       
    </script>
    <script type="text/javascript" src="js/jsp/dip/metadata/tablestru_index.js"></script>
    <style type="">
        html,body{background-color: #EEF7FE;overflow: hidden;}
    </style>
</head>
<body>
<div class="ui-layout-north">
	<div class="container">
	    <div class="c_nw">
	        <div class="c_ne"><div class="c_n"></div></div>
	    </div>
	    <!-- 容器标题部分 -->
		    <span class="title-l">
				<span class="title-r">
					<b class="icon"></b><span class="title-span">表结构查询</span>
				</span>
			</span>
	    <div class="tools-panel"></div>
	    <div class="c_w">
	        <div class="c_e">
	            <div class="c_content">
	                <table id="query">
	                    <tr>
	                        <td>表名：<input type="text" name="tableName"/></td>
	                        <td>表名描述：<input type="text" name="tableDesc"/></td>
	                        <td>
	                            <a class="btn" href="javascript:void(0);">
									<span class="btn-left" id="querybtn">
										<span class="btn-text icon-search">查询</span>
									</span>
	                            </a>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	    </div>
	    <div class="c_sw">
	        <div class="c_se">
	            <div class="c_s"></div>
	        </div>
	    </div>
	</div>
</div>
<div class="ui-layout-center" id="listshow">
    <div class="ui-layout-center">
		<div class="c_content" style="margin:0 0 0 0px;">
		    <div id="grid"></div>
		</div>
	</div>
	<div class="ui-layout-east">
        <div class="c_content" style="margin:0 0 0 0px;">
            <div id="gridField"></div>
        </div>
	</div>
	
</div>
</body>
</html>