<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<btp:htmlbase/>
	<link rel="stylesheet" type="text/css" href="css/icons.css">
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-layout.css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"/>
	<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css" />
	   
	<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
	   
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css"/>
	<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css"/>
	     
	<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
	<script type="text/javascript" src="js/jquery/scroll.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
	<!-- <script type="text/javascript" src="js/jsp/dip/dict/dip_dict.js"></script> -->
	<script type="text/javascript">
	
	var nodes = [{name:'导入模板管理',urlJump:'compare/loadModuleindex.zb'},
	             {name:'主数据对照导入',urlJump:'compare/maindata/index.zb'},
	             {name:'字典对照导入',urlJump:'compare/dictionary/index.zb'},
	             {name:'主数据对照管理',urlJump:'compare/maindata/manageIndex.zb'},
	             {name:'字典对照管理',urlJump:'compare/dictManaIndex.zb'}
				  ];
	
	var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
		$("#mainFrameIframe").attr("src", treeNode.urlJump);		
		$("#titleNavi").text(treeNode.name);
	};
	var setting = {
	    data: {
	    	key: {
				name: "name"
			},
			simpleData: {
				enable: true,
				idKey:'code',
				pIdKey:'pId'
				
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};


	$(function() {
	    var maindatatree = $.fn.zTree.init($("#maintypeInfo"), setting, nodes);
	});
	</script>
	<style type="text/css">
		.left_input_label{
			 
			width:120px;
		 
		}
		.input_text{
			float:left;
			text-align:center;
			width:90px;
		}
		#dictionaryForm input[type=text]{
			width:150px;
		}
		input[disabled=disabled]{
			background:#E0E0E0 ;
			color:black;
		}
	</style>
	
	<script type="text/javascript">
	
	//加载完成，执行以下代码
	$(function(){
		//加载layout，布局控件
		$('body').layout({ 
			resizable : false,
			livePaneResizing : true,
			slidable:true,
			west__size : 220,
			west__minSize : 220,
			west__maxSize : .5 ,// 50% of layout width
			west__resizable:true,
			center__resizable:true
		});
		
		//加载layout，布局控件
		$('#mainDiv').layout({ 
			resizable : false,
			livePaneResizing : false,
			slidable:false,
			west__size : 220,
			west__minSize : 220,
			west__maxSize : .5 ,// 50% of layout width
			west__resizable:false,
			center__resizable:false
		});
	});
	
	</script>
	

</head>
<body>
 
	<div class="ui-layout-center" style="overflow:auto;" id="mainDiv">
		<iframe name="mainFrame" id="mainFrameIframe" style="overflow:auto;" class="ui-layout-center" src="compare/loadModuleindex.zb"></iframe>
		<div class="ui-layout-north" style="overflow:auto;">
			<p style="padding:0 0 0 20px"><span style="font-weight:bold;font-size:14px;">当前位置: </span><span style="font-weight:bold;"> &nbsp;主数据管理</span>--<span style="font-weight:bold;" id="titleNavi">导入模板管理</span></p> 
		</div>	
	</div>
	 
	<div class="ui-layout-west" style="overflow:auto;">
		<!--  容器 -->
		<div class="container" style="overflow:auto;">
			<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	    	<!-- 容器标题部分 -->
	   		<span class="title-l">
				<span class="title-r">
					<b class="icon"></b><span class="title-span">主数据对照管理</span>
				</span>
			</span>
			<div class="c_w" style="overflow:auto;">
	      		<div class="c_e" style="overflow:auto;">
	      			<!-- 容器内容 -->
		           	<div class="c_content">
		           	    <!--  <div style="margin:10px 0 10px 40px">
		           			<a href="compare/loadModuleindex.zb" target="mainFrame">导入模板管理</a>
		           		 </div>
		           		  <div style="margin:10px 0 10px 40px">
		           			<a href="compare/maindata/index.zb" target="mainFrame">主数据对照导入</a>
		           		 </div>
		           		  <div style="margin:10px 0 10px 40px">
		           			<a href="compare/dictionary/index.zb" target="mainFrame">字典对照导入</a>
		           		 </div>
		           		  <div style="margin:10px 0 10px 40px">
		           			<a href="compare/maindata/manageIndex.zb" target="mainFrame">主数据对照管理</a>
		           		 </div>
		           		 <div style="margin:10px 0 10px 40px">
		           			<a href="compare/dictManaIndex.zb" target="mainFrame">字典对照管理</a>
		           		 </div> -->
		           		 <div class="ztree" id="maintypeInfo"></div>
		            </div>
		        </div>
			</div>
			<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
		</div>
	</div>	
</body>
</html>