<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>运行管理</title>
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

    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.zTree.css"/>
    <script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
    <script type="text/javascript" src="js/jsp/dip/operate/operateIndex.js"></script>
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
    <script type="text/javascript">
		
    </script>

</head>
<body>
<div class="ui-layout-west" style="overflow:auto;">
	<!--  容器 -->
	<div class="container">
		<div class="c_nw">
	    	<div class="c_ne"><div class="c_n"></div></div>
	    </div>
	    <!-- 容器标题部分 -->
	    <span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">运行管理内容</span>
			</span>
		</span>
		<div class="c_w">
        	<div class="c_e">
        		<!-- 容器内容 -->
            	<div class="c_content">
            		<div id="operate_tree" class="ztree"></div>
                </div>
            </div>
       	</div>
		<div class="c_sw">
        	<div class="c_se"><div class="c_s"></div></div>
        </div>
	</div>
</div>
<div class="ui-layout-center" style="overflow:auto;" id="mainDiv">
	<div class="ui-layout-north" style="overflow:hidden;">
		<p style="margin-top:7px;margin-left: 13px;"><span style="font-weight:bold;font-size:14px;">当前位置:运行管理-->  </span><span style="font-weight:bold;font-size:13px;" id="titleNavi"></span></p> 
	</div>	
	<div class="ui-layout-center"  id="contentDiv">
		<iframe name="mainFrame" id="mainFrameIframe" style="height:100%;width:100%;" frameborder="0" scrolling="no" src=""></iframe>
    </div>
</div>

</body>
</html>