<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.zebone.btp.mdm.vo.MainDataTypeVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="../css/icons.css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/main.css" id="main-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-grid.css" id="grid-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-dialog.css" id="dialog-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery.checkForm.css" id="checkform-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery-button.css" id="button-css" />
		<link rel="stylesheet" type="text/css" href="../skin/default/jquery.zTree.css" id="" />
		<script type="text/javascript" src="../js/jquery/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery.ztree.min.js"></script>
		<title>主数据类型</title>
		<script type="text/javascript">

            function onClick() {
                window.parent.document.getElementById("innerMainFrame").src = "masterData.zb";
            }

			var nodes = ${mainDataTree};
			var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
				if(treeNode.mdtParentId == 'root'){//根
					var urlparam = "btp-maindata-type.zb?mdtType=" + treeNode.mdtType + "&mdtName=" + treeNode.mdtName + "&mdtId=" + treeNode.mdtId;
					window.parent.document.getElementById("innerMainFrame").src = urlparam;
				}else{//叶子节点
					window.parent.document.getElementById("innerMainFrame").src = "btp-maindata.zb?code=" + treeNode.mdtCode;
				}
		    };	
		    var setting = {
		    	treeId:'type_tree',
			    data: {
			    	key: {
						name: "mdtName"
					},
					simpleData: {
						enable: true,
						idKey:'mdtId',
						pIdKey:'mdtParentId',
						rootPId:'root'
					}
				},
				callback: {
					onClick: zTreeOnClick
				}
		    };
		    $(function(){//初始化树并展开
				$.fn.zTree.init($("#maindata_tree"), setting, nodes);
				var treeObj = $.fn.zTree.getZTreeObj("maindata_tree");
				var node = treeObj.getNodes();
				if (node.length>0) {
					for(var i=0 ;i<node.length;i++){
						if(node[i].mhoId ="root"){
							treeObj.expandNode(node[i], true, false, false, false);
						}
					}
				}
			});		
			function callback(){
				window.location.reload();
			}
			var loadMDTree = function(){
			    window.location.href = "btp-maindata-tree.zb";	
			};
		</script>
	</head>
	<body>
		<div class="container">
			<div class="c_nw">
				<div class="c_ne">
					<div class="c_n"></div>
				</div>
			</div>
			<span class="title-l"> <span class="title-r"> <b class="icon"></b><span class="title-span">类型列表</span> </span> </span>
			<div class="tools-panel"></div>
			<div class="c_w">
				<div class="c_e">
					<div class="c_content" style="padding-left: 30px;">
						<div id="maindata_tree" class="ztree"></div>

                        <div class="click" style="margin-top: 15px;font-size: 15px;" onclick="onClick();"
                             id="bjg">主数据内容管理</div>

					</div>
				</div>
			</div>
			<div class="c_sw">
				<div class="c_se">
					<div class="c_s"></div>
				</div>
			</div>
		</div>
	</body>
</html>