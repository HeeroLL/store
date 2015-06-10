<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <btp:htmlbase/>
    <title>文档结构管理树</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
    <link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
    <script type="text/javascript" src="bootstrap/jquery.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
    <link href="bootstrap/css/${sessionScope.skin}-bootstrap.min.css" rel="stylesheet" media="screen"/>

    <style type="text/css">
        html,body{
            background-color: #EEF7FE;
            overflow: hidden;
            height: auto;
        }
        .overClass{
            background: #e8fcde;
        }
        .c_content div{
            height: 300px;
            overflow:auto;
        }
        .checked{background: #FBEC88;}
        .dropdown-menu li{
            text-align:center;
        }
    </style>

    <script type="text/javascript">
        var checkedClass = "wdbbgl";
        var nodes = [
            {id:"1",pId:"0",name:"下载管理"},
            {id:"2",pId:"0",name:"示例文档管理"}
        ];

        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: zTreeOnClick
            }
        };

        function zTreeOnClick(event, treeId, treeNode){
            onClicktree(treeNode.id);
        }


        function onClicktree(str){
            if(str ==1){
                window.parent.document.getElementById("innerMainFrame").src = "doc/docDownload.zb";//下载管理
            }else if(str ==2){
                window.parent.document.getElementById("innerMainFrame").src = "doc/docExampleIndex.zb";//示例文档管理
            }
        }

        $(function(){
            typeTree = $.fn.zTree.init($("#type_tree"), setting, nodes);
        });

    </script>
</head>
<body>
<div class="container" style="width:210px;margin-left: 10px;">
    <div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="padding-left:4px;">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">文档结构管理</span>
		</span>
	</span>
    <div class="tools-panel"></div>
    <div class="c_w">
        <div class="c_e" style="text-align:center;">
            <div class="c_content"	>
                <div class="ztree" id="type_tree"></div>
            </div>
        </div>
    </div>
    <div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>


<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
