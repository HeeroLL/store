<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<btp:htmlbase/>
<title>ETL管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/icons.css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery-tab.js"></script>
<style type="text/css">
html,body{
background-color: #EEF7FE;
overflow: hidden;
height: auto;
}
</style>
<script type="text/javascript">
var grid1,grid2,grid3,editData,formDialog;
var editHTML = "";
var currentSelectedNode = {};//当前选中的node节点
/*************************************列表显示参数start**********************************************/
	/**
	 * 节点列表DateGrid的列定义
	 */
	var col1 = [
		{text: '节点ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'			},
		{text: '节点名称',    width: 100,  textAlign: 'center',	align : 'left',	dataIndex: 'nodesName'       },
		{text: '节点描述',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeDesc'       },
		{text: 'IP地址',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeIp'       },
		{text: '端口号',    width: 100,  textAlign: 'center',	align : 'left',	dataIndex: 'nodePort'       },
		{text: '网络状态',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeNet'       },
		{text: '运行状态',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeRun'       },
		{text: '节点状态',    width: 150,  textAlign: 'center',	align : 'left',	dataIndex: 'nodeNet',
			renderer: function(value){
     			  if(value=='1'){
     			  	 return "启动";
     			  }else if(value=='0'){
     			  	 return "停止";
     			  }else{
     			  	 return "";
     			  }
			}
		}
	];
	
	/**
	 * 转换详细系信息的DateGrid的列定义
	 */
	var col2 = [
		{text: '组件名',		width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'stepname'},
		{text: '读取',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'linesRead'},
		{text: '写',			width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'linesWritten'},
		{text: '输入',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'linesInput'},
		{text: '输出	',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'linesOutput'},
		{text: '更新',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'linesUpdated'},
		{text: '拒绝',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'linesRejected'},
		{text: '错误',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'errors'},
		{text: '耗时',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'seconds'},
		{text: '速度',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'speed'},
		{text: '停止',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'stopped'},
		{text: '暂停',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'paused'},
		{text: '状态',		width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'statusDescription'}
	];


	
	/**
	 * 转换和任务列表DateGrid的列定义
	 */
	var col3 = [
		{text: 'nodeId',	 width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeId'},
		{text: 'type',	     width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'type'},
		{text: '名称',		 width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'name'},
		{text: '类型',		 width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'typeName'},
		{text: '状态',		 width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'status_desc'},
		{text: '最后日志日期',width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'linesInput'},
		{text: '操作',		 width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'name' ,formatter:formatter}
	];
	var deployBtn = {id:"deployBtn",width:"150",text:"部署转换或任务",icon:"images/icons/edit_add.png",handler:onDeployButtonClick};
	
	
	
	/**
	 * "转换和任务列表"中渲染操作按钮
	 */
	function formatter (data){
		var status = data.status_desc;
		var optBtn = "";
		if(status == "Finished" || status== "Stopped" || status=="Waiting"){
			optBtn += "<button onclick=\"startUp('"+data.name+"','"+data.type+"','"+data.nodeId+"')\">启动</button>";	
			optBtn += "<button disabled='disabled' onclick=\"stop('"+data.name+"','"+data.type+"','"+data.nodeId+"')\">停止</button>";
		}else{
			optBtn += "<button disabled='disabled' onclick=\"startUp('"+data.name+"','"+data.type+"','"+data.nodeId+"')\">启动</button>";
			optBtn += "<button onclick=\"stop('"+data.name+"','"+data.type+"','"+data.nodeId+"')\">停止</button>";
		}
		
		optBtn += "<button onclick=\"remove('"+data.name+"','"+data.type+"','"+data.nodeId+"')\">删除</button>";
		return optBtn;
		
	}
	
	/**
	 * 转换和任务列表DateGrid的列定义
	 */
	 var col4 = [
		{text: 'nodeId',width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'nodeId'},
		{text:"任务名称",width: 200,	textAlign: 'center',	align : 'center',	dataIndex:"jobname"},
		{text:"状态" , 	width: 200,	textAlign: 'center',	align : 'center',	dataIndex:"status_desc"}
	  ];

	/**
	 * 启动一个转换或任务
	 */
	function startUp(name,type,nodeId){
		var url = "";
		if(type=="trans"){
			url = "etl/startTrans.zb";
		}else{
			url = "etl/startJob.zb";
		}
		var param = {nodeId:nodeId,transName:name,jobname:name};	
		
		$.post(url,param,function(res){
			if(res.result != "ERROR"){
				JDialog.showMessageDialog('启动成功!',res.message,JDialog.INFORMATION);
				grid3.loadData();
			}else{
				JDialog.showMessageDialog('启动时出现错误!\n',JDialog.ERROR);			
			}
		});
	}
	
	/**
	 * 停止一个转换或任务
	 */ 
	function stop(name,type,nodeId){
		var url = "";
		if(type=="trans"){
			url = "etl/stopTrans.zb";
		}else{
			url = "etl/stopJob.zb";
		}
		var param = {nodeId:nodeId,transname:name,jobname:name};	
		
		$.post(url,param,function(res){
			if(res.result != "ERROR"){
				grid3.loadData();
				JDialog.showMessageDialog('启动成功!',res.message,JDialog.INFORMATION);
			}else{
				JDialog.showMessageDialog('停止时出现错误!',res.message,JDialog.ERROR);			
			}
		});
	}
	
	/**
	 * 移除一个转换或任务
	 */
	function remove(name,type,nodeId){
		var url = "";
		if(type=="trans"){
			url = "etl/removeTrans.zb";
		}else{
			url = "etl/removeJob.zb";
		}
		var param = {nodeId:nodeId,transname:name,jobname:name};	
		
		$.post(url,param,function(res){
			if(res.result != "ERROR"){
				grid3.loadData();
				JDialog.showMessageDialog('删除成功',res.message, JDialog.INFORMATION);
			}else{
				JDialog.showMessageDialog('删除时出现错误!',res.message ,JDialog.ERROR);
			}
		});
	}

//================================
//树的相关配置及回调函数 start
//================================
	/**
	 * 节点是的配置
	 */
	var setting = {
		callback:{				
			onExpand: zTreeOnExpand,//节点展开
			beforeExpand: zTreeBeforeExpand,//节点展开前
			onClick: zTreeOnClick
		},
		data: {
			keep: {
				parent: true
			}
		}		
	};

	/**
	 * 节点点击事件。
	 */
	function zTreeOnClick(event, treeId, treeNode) {
	    
		if(treeNode.pId){//有父节点id，表示点击的是转换或者任务。
	    	var nodeId = treeNode.pId;
	    	var name = treeNode.name;
	    	//$("#taskDiv").css("display","block");
	    	if(treeNode.type == 'trans'){
	    		$(".girdContainer").hide();
	    		$("#taskDiv").show();
	    		grid2.setParams({nodeId:nodeId,transname:name});
	    		grid2.loadData();
	    		
	    		
	    	}else{
	    		$(".girdContainer").hide();
	    		$("#jobinfoDiv").show();
	    		grid4.setParams({nodeId:nodeId,jobname:name});
	    		grid4.loadData();
	    	}
	    	
	    }else{//点击的是一级节点。列出点击节点下的所有转换和任务。
	    	currentSelectedNode = treeNode;
	    	var nodeId = treeNode.id;
	    	var nodeName = treeNode.name;
	    	$(".girdContainer").hide();
	    	$("#transDiv").show();
	    	grid3.setParams({nodeId:nodeId});
	    	grid3.loadData();
	    }
	};
		
	/**
	 * 节点展开事件处理 
	 */
	function zTreeOnExpand(event, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("dict_tree");
		$.ajax({
			url:'etl/getServerStatus.zb',
			type: 'POST',
			cache: false,
			data: eval("({nodeId:'"+treeNode.id+"'})"),
			dataType: 'json',
			success: function(res){
				if(res.result=="ERROR"){
					JDialog.showMessageDialog('',"访问节点失败，不能连接节点获取信息。", JDialog.ERROR);
					return;
				}
			
				var list = new Array();
				if(res.transstatuslist){
					var data = res.transstatuslist;
					for(var i=0;i<data.length;i++){
						var obj = {};
						obj.id = data[i].id;
						obj.name = data[i].transname;
						obj.pId = treeNode.id;
						obj.type = 'trans';//表示一个转换
						list.push(obj);
					}
				}
				if(res.jobstatuslist){
					var data = res.jobstatuslist;
					for(var i=0;i<data.length;i++){
						var obj = {};
						obj.id = data[i].id;
						obj.name = data[i].jobname;
						obj.pId = treeNode.id;
						obj.type = 'job';//表示一个任务
						list.push(obj);
					}
				}
				treeObj.addNodes(treeNode, list);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				JDialog.tip('树加载异常');
			}
		});		
	};

	/**
	 * 树展开之前删除以前的节点，重新装载。
	 */
	function zTreeBeforeExpand(treeId, treeNode){
		var treeObj = $.fn.zTree.getZTreeObj("dict_tree");
		treeObj.removeChildNodes(treeNode);
	};
	
    /**
	 * 左侧树的初始化 
	 */
	function treeInit(){
		var nodesData = ${list};
		var list = new Array();
		for(var i=0;i<nodesData.length;i++){
			var zNode = {};
			zNode.id = nodesData[i].id;
			zNode.name = nodesData[i].nodesName;
			zNode['isParent'] = true;
			list.push(zNode);
		}
		$.fn.zTree.init($("#dict_tree"), setting, list);
		
	}
//=================END===============
	
	/**
	 * 节点部署事件处理
	 */ 
	function onDeployButtonClick(){
		if(currentSelectedNode.id ==''){
			JDialog.tip('请选择某个节点进行部署');
			return;
		}
		//取得还没有部署过的转换或者任务
		var url = "etl/getTranAndJobForDeploy.zb";
		$.post(url,"",function(res){
			$("#forDeploy").html(res);
		});
		formDialog = new JDialog({
			title : '部署任务或者转换',
			width : 400,
			height :120,
			buttons: [
				{
		 			text: '保存',
		 			id: 'dict_save',	 			
		 			handler: function(){//保存数据字典类型信息
						var forDeploy = $("#forDeploy").val();
		 				if(forDeploy == ""){
		 					return;
		 				}
		 				deploy(forDeploy);
	 				}
	 		    },
	 		    {
		 			text: '关闭',
		 			id: 'dict_close',
		 			handler: function(){
		 				formDialog.closeDialog();
		 			}
	 			}
	 		]
		});
		
		formDialog.show();
		if (editHTML == '') {
			editHTML = $('#editPage').html();
			$('#editPage').remove();
		}
		formDialog.add(editHTML);
		$("#current_nodeName").val(currentSelectedNode.name);
		$("input[name='nodeId']").val(currentSelectedNode.id);
	}
	
	/**
	 * 部署转换或任务
	 * @param {Object} name
	 */
	function deploy(name){
		var url = "";
		if(name.indexOf("trans_")==0){
			url = "etl/addTrans.zb";
			name = name.replace ("trans_","");
		}
		if(name.indexOf("job_")==0){
			url = "etl/addJob.zb";
			name = name.replace ("job_","");
		}
		var param = {nodeId:currentSelectedNode.id,name:name};
		$(document.body).mask("部署中，请稍候...");
		$.post(url,param,function(res){
			if(res.result == "ERROR"){
				JDialog.tip("部署出现错误！");
			}else{
				JDialog.tip("部署成功！");
				grid3.loadData();
			}
			$(document.body).unmask();
			formDialog.closeDialog();
		});
	}
	
	$(function(){
		//设置DataGrid容器的高度。
		$(".girdContainer").width($(document.body).width()- 250);
		
		treeInit();//树初始化
		$("#deploy").click(onDeployButtonClick);//注册"部署转换或任务"按钮点击事件。
		
	//================================
	// DateGrid 定义  start
	//================================
	
		grid1 = new JGrid({
			title: '节点列表',
			col	 :col1,
			dataCol:'',
			striped:true,
			height   :document.documentElement.clientHeight-100,
			dataUrl  : 'node/nodeManaList.zb',
			render   : 'nodeGrid',
			pageBar  : false,
			crudOpera: false,
			countEveryPage: 20
		});
		
		grid2 = new JGrid({
			title: '转换信息',
			col	 :col2,
			dataCol:'',
			striped:true,
			height   :document.documentElement.clientHeight-100,
			dataUrl  : 'etl/getTransStatus2.zb',
			render   : 'taskGrid',
			pageBar  : false,
			crudOpera: false,
			countEveryPage: 20
		});
		
		grid3 = new JGrid({
			title: '转换和任务列表',
			col	 :col3,
			dataCol:'',
			striped:true,
			checkbox : false,
			height   :document.documentElement.clientHeight-50,
			dataUrl  : 'etl/getTransAndJob.zb',
			render   : 'transGrid',
			pageBar  : false,
			crudOpera: false
			//countEveryPage: 20
		});
		grid3.addButton(deployBtn);
		
		grid4 = new JGrid({
			title: '任务信息',
			col	 :col4,
			dataCol:'',
			striped:true,
			checkbox : false,
			height   :document.documentElement.clientHeight-50,
			dataUrl  : 'etl/getJobStatus2.zb',
			render   : 'jobinfoGrid',
			pageBar  : false,
			crudOpera: false
			//countEveryPage: 20
		});
		
	//=================END===============
		grid1.loadData();//装载节点列表数据
	});
</script>
</head>
<body>
<!-- START 节点树容器  -->
<div class="container" style="float: left;width: 230px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">前置节点列表</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content">
				<div id="dict_tree" class="ztree"></div>
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
<!-- END -->

<!-- START 节点列表容器 -->
<div id="nodeDiv" class="girdContainer" style="float: left;">
	<div class="container">
		<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
		<span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">节点列表信息</span>
			</span>
		</span>
		<div class="tools-panel"></div>
		<div class="c_w">
			<div class="c_e">
				<div class="c_content">
				<div id="nodeGrid"></div>
				</div>
			</div>
		</div>
		<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
</div>
<!-- END -->

<!-- START 任务、转换详情容器 -->
<div id="taskDiv" class="girdContainer" style="float: left;display:none">
	<div class="container">
		<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
		<span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">转换、任务列表信息</span>
			</span>
		</span>
		<div class="tools-panel"></div>
		<div class="c_w">
			<div class="c_e">
				<div class="c_content">
					<div id="taskGrid"></div>
				</div>
			</div>
		</div>
		<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
</div>
<!-- END -->

<!-- START 转换、任务列表信息 容器-->
<div id="transDiv" class="girdContainer" style="float: left;display:none">
	<div class="container">
		<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
		<span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">转换、任务列表信息</span>
			</span>
		</span>
		<div class="tools-panel"></div>
		<div class="c_w">
			<div class="c_e">
				<div class="c_content">
				<div id="transGrid"></div>
				</div>
			</div>
		</div>
		<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
</div>
<!-- END -->


<!-- START 任务信息 容器-->
<div id="jobinfoDiv" class="girdContainer" style="float: left;display:none">
	<div class="container">
		<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
		<span class="title-l">
			<span class="title-r">
				<b class="icon"></b><span class="title-span">任务信息</span>
			</span>
		</span>
		<div class="tools-panel"></div>
		<div class="c_w">
			<div class="c_e">
				<div class="c_content">
				<div id="jobinfoGrid"></div>
				</div>
			</div>
		</div>
		<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
	</div>
</div>
<!-- END -->

<div id="editPage" style="display: none;">
	<table align="center" width="100%">
		<tr>
			<td style="width:160px;text-align:right;">部署到</td>
			<td style="text-align:left;">
				<input id="current_nodeName" value="" disabled="disabled" style="width:200px;"/>
				<input type="hidden" name="nodeId"/> 
			</td>
			
		</tr>
		<tr>
			<td style="width:160px;text-align:right;">选择要部署的转换或者任务</td>
			<td >
				<select id="forDeploy" style="width:207px;">
					<option value="">请选择</option>
				</select> 
			</td>
		</tr>
	</table>
</div>
</body>
</html>
