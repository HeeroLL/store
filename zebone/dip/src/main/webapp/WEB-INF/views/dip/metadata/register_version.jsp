<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zebone.cn/btp" prefix="btp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<btp:htmlbase/>
    <title>实体结构设置界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/main.css" id="main-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-grid.css"  id="grid-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-dialog.css" id="dialog-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery.checkForm.css" id="checkform-css"/>
<link rel="stylesheet" type="text/css" href="skin/${sessionScope.skin}/jquery-button.css" id="button-css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery-layout.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.autocomplete.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery/css/jquery.zTree.css" id=""/>
<script type="text/javascript" src="js/jquery/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.mark.js"></script>
<script type="text/javascript" src="js/jquery/jquery-dialog.js"></script>
<script type="text/javascript" src="js/jquery/jquery-grid.js"></script>
<script type="text/javascript" src="js/jquery/jquery-checkForm.js"></script>
<script type="text/javascript" src="js/jquery/jquery.stopBubble.js"></script>
<script type="text/javascript" src="js/jquery/jquery-button.js"></script>
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery.layout.js"></script>
<script type="text/javascript" src="js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.min.js"></script>
        
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.doc_div{
	height: 100%;
}
#doc_tree_div{
	overflow:hidden;
	float: left;
	width: 25%;
}
#doc_button_div{
	float: left;
	width: 5.2%;
}
#metadata_data{
	float: left;
	width: 69.5%;
}
#metadata_struct .remove,.add{
	float: none;
}
#metadata_grid .existsFlag{
	background-color: #dddddd;
	color: #eeeeee;
}

.ztree li a:hover span{
	color: olive;
	font-weight: bold;
	font-size: 15px;
}
.ztree li a.curSelectedNode span{
	color: olive;
	font-weight: bold;
	font-size: 15px;
}
</style>
<script type="text/javascript"><!--
var projectPath = '${pageContext.request.contextPath}';
var metadata_edit_html;
var metaadd_dialog;
//树形控件配置
var docTree;
var deleteTreeNodesId = "";
//grid for add metadata
var grid_metadata;
var docName = '${docName}';

var setting = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	data: {
		key: {
			name: "name"
		},
		simpleData:{
			enable : true,
			idKey:'id',
			pIdKey:'pid'
			
		}
	},
	edit: {
		enable: true,
		//editNameSelectAll: true,
		showRemoveBtn: showRemoveBtn,
		removeTitle: "删除分类或元数据",
		showRenameBtn: false
	},
	callback: {
		onClick: onClickMetaData,
		beforeRemove: zTreeBeforeRemove,
		beforeDrop: zTreeBeforeDrop,
		onRemove: zTreeOnRemove
	}
};

var options={
	width: 350,
    maxHeight: 200,
	select:true,
	textField:'datasetName',
	serviceUrl:'metadata/dataListByDatasetName.zb',
	//onSelect: function(value){ $("#ysjbm").val(value.fieldName); },
	col:[{dataIndex:'datasetName'},{dataIndex:'standardCode'},{dataIndex:'datasetVersion'}],
	valueField:{dataIndex:'id',dataName:'id'}//隐藏文本的name
};

//判断文档同一级目录下是否存在相同的分类
function isSubclassExist(treeNode, isAdd){
    var flag = false;
    var fieldId = $("#md_englishName").val();
    var nodesArray = docTree.transformToArray(docTree.getNodes());
    var comparedId = treeNode.pid;
    if(isAdd){
        comparedId = treeNode.id;
    }
    for (var i = 0; i < nodesArray.length; i++) {
        if ((nodesArray[i].pid == comparedId) && (nodesArray[i].fieldId == fieldId)
                && (nodesArray[i].id != treeNode.id)) {
            flag = true;
            break;
        }
    }
    return flag;
}
			
//是否显示移除按钮
function showRemoveBtn(treeId, treeNode) {
	var xpath001 = "/ClinicalDocument";
	var xpath002 = "/ClinicalDocument/header";
	if(treeNode.xpath == xpath001){
		return false;
	}
	if(treeNode.xpath.indexOf(xpath002)>-1){
		return false;
	}
	return true;
}
//单击节点事件
function onClickMetaData(event, treeId, treeNode){
	changeMetadata(treeNode);
}

function changeMetadata(treeNode){
	var nodesArray = docTree.transformToArray(docTree.getNodes());
	var arrayObj = new Array();
	for(var i=0;i<nodesArray.length;i++){
		if((nodesArray[i].pid == treeNode.id) && (nodesArray[i].isFeild == '1')){
			arrayObj.push(nodesArray[i].name);
		}
	}
	
	var ids = grid_metadata.getColData(2);
	
	var tr = $(".jgridDataDIV").find("table tr[name='data']");
	tr.each(function(){
		$(this).removeClass('existsFlag');
	});
	
	if(!arrayObj || arrayObj.length <1) return;
	
	for(var i=0;i<arrayObj.length;i++){
		for(var j=0;j<ids.length;j++){
			if(ids[j] == arrayObj[i]){
				tr.each(function(){
					var index = $(this).attr("index");
					if(index == j+1){
						$(this).addClass('existsFlag');
					}
				});
			}
		}
	}
}
//添加节点方法
function addHoverDom(treeId, treeNode) {
	var xpath001 = "/ClinicalDocument";
	var xpath002 = "/ClinicalDocument/header";
	if(treeNode.xpath == xpath001){
		return;
	}
	if(treeNode.xpath.indexOf(xpath002)>-1){
		return;
	}
	 
	//元数据不显示添加按钮
	if(treeNode.isFeild =='1'){
			//return;
	}else{
		//添加添加子分类按钮
		var aObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='添加分类' onfocus='this.blur();'></span>";
		aObj.append(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			if(treeNode.isFeild =='1'){
				JDialog.tip('元数据下不允许添加分类!');
				return;
			}
			openAddClassDialog(treeNode);
			return false;
		});
	}
	
	 //添加编辑按钮
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#editBtn_"+treeNode.tId).length>0) return;
	var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
		+ "' title='修改分类' onfocus='this.blur();'></span>";
	sObj.append(editStr);
	var btnEdit = $("#editBtn_"+treeNode.tId);
	if (btnEdit) btnEdit.bind("click", function(){
		 openEditDialog(treeNode);
	}); 
};
			
//添加分类信息
function openAddClassDialog(treeNode){
	metaadd_dialog=new JDialog({
		title : treeNode.name+"：子分类",
		width : 350,
		height :250,
		content:metadata_edit_html.html(),
		buttons: [{
				text: '保存',
				id: 'P_save',	 			
				handler: function(){
					if(!checkAll("checkForm")){
						JDialog.tip('请检查表单填写的数据');
						return;
					}
					var treeNodeTemp = {};
					treeNodeTemp.isParent = true;
					treeNodeTemp.id = treeNode.id+$("#md_englishName").val();
					treeNodeTemp.xpath = treeNode.xpath +"/"+ $.trim($("#md_englishName").val());
					treeNodeTemp.pid = treeNode.id;
					treeNodeTemp.name = $.trim($("#md_chineseName").val());
					treeNodeTemp.fieldId = $("#md_englishName").val();
					treeNodeTemp.isSelect = $("input[name='md_isSelected']:checked").val();
					treeNodeTemp.repeat =  $("#md_mulitiple").val();
					treeNodeTemp.isOnly = $("input[name='md_isOnly']:checked").val();

                    if(isSubclassExist(treeNode, true)){
                        JDialog.tip('该分类英文名称已经存在，请重新填写');
                        return;
                    }
					docTree.addNodes(treeNode, treeNodeTemp);
					metaadd_dialog.closeDialog();
					docTree.refresh();
					
				}
			},{
				text: '关闭',
				id: 'P_close',
				handler: function(){
					metaadd_dialog.closeDialog();
				}
			}]
	});
}
			
//编辑结点信息
function openEditDialog(treeNode){
	metaadd_dialog=new JDialog({
		title : treeNode.name,
		width : 350,
		height :250,
		content:metadata_edit_html.html(),
		buttons: [{
				text: '保存',
				id: 'P_save',	 			
				handler: function(){
					var tempPath = treeNode.xpath;
					if(treeNode.isFeild =='1'){
					}else{
						if(!checkAll("checkForm")){
							JDialog.tip('请检查表单填写的数据');
							return;
						}
					}
					treeNode.name = $("#md_chineseName").val();
					treeNode.fieldId = $("#md_englishName").val();
					treeNode.isSelect = $("input[name='md_isSelected']:checked").val();
					treeNode.repeat =  $("#md_mulitiple").val();
					treeNode.isOnly = $("input[name='md_isOnly']:checked").val();
					if(treeNode.isFeild == '1'){
					}else{
						var lj = treeNode.xpath;
						var a = tempPath.substring(tempPath.lastIndexOf("/")+1,tempPath.length);
						
						treeNode.xpath = lj.substring(0,lj.lastIndexOf("/"))+"/"+$("#md_englishName").val();
						//子节点的xpath路径也要改变
						var nodes = docTree.transformToArray(treeNode.children);
						for(var i=0; i<nodes.length; i++){
							nodes[i].xpath = nodes[i].xpath.replace("/"+a+"/","/"+$("#md_englishName").val()+"/");
						}
					}
                    if(isSubclassExist(treeNode, false) && treeNode.isFeild != '1'){
                        JDialog.tip('该分类英文名称已经存在，请重新填写');
                        return;
                    }
					metaadd_dialog.closeDialog();
					docTree.refresh();
				}
			},{
				text: '关闭',
				id: 'P_close',
				handler: function(){
					metaadd_dialog.closeDialog();
				}
			}]
	});
	
	//如果是元数据，则需要隐藏英文名和禁止修改中文名
	if(treeNode.isFeild =='1'){
		$("#md_chineseName").prop('disabled', true);
		$("#div_englishname").hide();
		$("#md_mulitiple").attr("disabled","disabled");
	}
	
	//打开编辑页面时，初始化变量
	$("#md_chineseName").val(treeNode.name);
	$("#md_englishName").val(treeNode.fieldId);
	if(treeNode.isSelect == '1'){
		$("#md_isSelected_true").attr("checked","true");
	}else{
		$("#md_isSelected_false").attr("checked","true");
	}
	if(treeNode.isOnly == '1'){
		$("#md_isOnly_true").attr("checked","true");
	}else{
		$("#md_isOnly_false").attr("checked","true");
	}
	 $("#md_mulitiple").selectOption(treeNode.repeat);

}
			
//当鼠标离开结点时，隐藏删除菜单按钮
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
	$("#editBtn_"+treeNode.tId).unbind().remove();
};
			
//删除节点节点
function zTreeBeforeRemove(treeId, treeNode){
	if(!window.confirm("是否删除节点 : "+treeNode.name+" ?")){
		return false;
	}
	
}

//获得当前最大树节点id
function getMaxNodeIdPlus1(){
	var allNodes = docTree.transformToArray(docTree.getNodes());
	var tempMax = 0;
	for(var i=0; i<allNodes.length; i++){
		if(allNodes[i].id>tempMax){
			tempMax = allNodes[i].id;
		}
	}
	return tempMax+1;
}

//进行相同层级的排序
function zTreeBeforeDrop(treeId, treeNodes,targetNode,moveType){
	//alert(treeNodes[0].name);alert(targetNode.name);
	if(treeNodes[0].pid == targetNode.pid && moveType != "inner"){
		
	}else{
		return false;
	}
}

//删除节点之后的操作
function zTreeOnRemove(event, treeId, treeNode) {
	if(treeNode.id!=null&&treeNode.id!=""){
		deleteTreeNodesId += treeNode.id+",";
	}
	var nodes = docTree.transformToArray(treeNode.children);
	for(var i=0; i<nodes.length; i++){
		deleteTreeNodesId += nodes[i].id+",";
	}
}
		

var col_metadata = [ {text: '元数据ID', width: 0,	textAlign: 'center', align : 'center', dataIndex: 'id' },
			{text: '数据集关联ID', width: 0,	textAlign: 'center', align : 'center', dataIndex: 'isDeleted' },
			{text: '元数据别名', width: 200,	textAlign: 'center', align : 'center', dataIndex: 'fieldName' },
			{text: '元数据标识符号', width: 150,	textAlign: 'center', align : 'center', dataIndex: 'fieldId' },
			{text: '元数据编码', width: 150,	textAlign: 'center', align : 'center', dataIndex: 'fieldCode' }];
			
var nodes = ${list};
//var docMapping = '${docMapping}';
	
//重新加载树
var reloadZtree = function(){
	$.ajax({
		url:projectPath+'/metadata/getDocById.zb',
		type: 'POST',
		cache: false,
		data: encodeURI("id=${docId}&docName=${docName}"),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				nodes= eval("("+res.list+")");
				docTree = $.fn.zTree.init($("#metadata_struct"), setting, nodes);
				docTree.expandAll(true);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip("服务器异常，保存失败");
			$(document.body).unmask();
		}
	});
}
	
var saveDocInfoToDB = function(data){
//     if (docMapping == 'true') {
//         JDialog.tip("文档已存在数据映射关系,不能维护");
//         return;
//     }
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'metadata/docMappingSave.zb',
		type: 'POST',
		cache: false,
		data: encodeURI(data),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
			    reloadZtree();
			    JDialog.tip("保存成功");
				
			}else{
				JDialog.tip("保存失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip("服务器异常，保存失败");
			$(document.body).unmask();
		}
	});
};	
$(document).ready(function () {
	
	//$("#metadata_data").width(document.body.clientWidth -302);
	$("#mmmm").height(document.body.clientHeight-100);
	
	$("#datasetName").autocomplete(options);
	
	//保存树结构到数据库
	$("#saveToDB").click(function(){
		var allNodes = docTree.transformToArray(docTree.getNodes());
		var tempStr = '';
		if(allNodes.length < 1){
			JDialog.tip("文档结构为空，不能保存",2);
			return;
		}
		for(var i=0; i<allNodes.length; i++){
			 var tempdata = "";
			 if(allNodes[i].id){
			 	tempdata = allNodes[i].id;
			 }
			 tempStr += "treeInfos["+i+"].id=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].fieldId){
			 	tempdata = allNodes[i].fieldId;
			 }
			 tempStr += "treeInfos["+i+"].fieldId=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].pid){
			 	tempdata = allNodes[i].pid;
			 }
			 tempStr += "treeInfos["+i+"].pid=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].xpath){
			 	tempdata = allNodes[i].xpath;
			 }
			 tempStr += "treeInfos["+i+"].xpath=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].name){
			 	tempdata = allNodes[i].name;
			 }
			 tempStr += "treeInfos["+i+"].name=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].isSelect){
			 	tempdata = allNodes[i].isSelect;
			 }
			 tempStr += "treeInfos["+i+"].isSelect=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].repeat){
			 	tempdata = allNodes[i].repeat;
			 }
			 tempStr += "treeInfos["+i+"].repeat=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].isFeild){
			 	tempdata = allNodes[i].isFeild;
			 }
			 tempStr += "treeInfos["+i+"].isFeild=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].fieldCode){
			 	tempdata = allNodes[i].fieldCode;
			 }
			 tempStr += "treeInfos["+i+"].fieldCode=" + tempdata+"&";
			 tempdata = "";
			 if(allNodes[i].isOnly){
			 	tempdata = allNodes[i].isOnly;
			 }
			 tempStr += "treeInfos["+i+"].isOnly=" + tempdata+"&";
		}
		tempStr +="docId="+$("#docId").val();
		//if(tempStr.length>0)tempStr = tempStr.substring(1);
		if(deleteTreeNodesId.length>0){
			//删除的节点单独处理
			deleteTreeNodesId = deleteTreeNodesId.substring(0,deleteTreeNodesId.length-1);
			tempStr +="&deleteTreeNodesId="+deleteTreeNodesId;
		}
		
		saveDocInfoToDB(tempStr);
	});
	
	//保存原始修改框内容
	metadata_edit_html = $("#cHTML");
	metadata_edit_html.remove();
	
	//加载布局控件
    /**$('body').layout({
		west : {
			size:250
	    }
	 });*/
	 //加载文档树
	 docTree = $.fn.zTree.init($("#metadata_struct"), setting, nodes);
	 docTree.expandAll(true);
	 
	 //加载grid控件
	 grid_metadata = new JGrid({
		title : '元数据列表',
		crudID : 'id',
		col	: col_metadata,
		checkbox : true,
		striped : true,
		dataUrl : 'metadata/metadataListBydatasetId.zb',
		height : document.body.clientHeight-112,
		render : 'metadata_grid',
		pageBar : true,
		crudOpera: false,
		countEveryPage: 100,
		listeners: {
			afterload:function(){
				var nodes = docTree.getSelectedNodes();
				if(nodes && nodes.length>0){
					changeMetadata(nodes[0]);
				}
			}
		}
	});
	
	//保存选中元数据至文档树
	$("#SaveMetadata").click(function(){
		var selectNodes = docTree.getSelectedNodes();
		var arrayObj = new Array();
		var nodesArray = docTree.transformToArray(docTree.getNodes());
		
		if(selectNodes.length===0){
			JDialog.tip('请选择左侧文档树节点！');
			return;
		}
		if(selectNodes[0].isFeild== '1'){
			JDialog.tip('元数据不允许添加子节点！');
			return;
		}
		
		//找出某节点下的子节点是元数据的所有节点
		for(var i=0;i<nodesArray.length;i++){
			if((nodesArray[i].pid == selectNodes[0].id) && (nodesArray[i].isFeild == '1')){
				arrayObj.push(nodesArray[i].name);
			}
		}
		
		var selectRow = grid_metadata.getSelections();
		
		for(var i=0; i<selectRow.length; i++){
			var tempJsonObj = {};
			
			//判断在某一节点下是否已经添加该元数据节点
			if(arrayObj && arrayObj.length>0){
				for(var j=0;j<arrayObj.length;j++){
					if(arrayObj[j] == selectRow[i].fieldName){
						JDialog.tip('不允许重复添加 "'+selectRow[i].fieldName+'" 节点！');
						return;
					}
				}
			}
			
			tempJsonObj.xpath = selectNodes[0].xpath+"/slot[@code='"+selectRow[i].fieldId+"'][@name='"+selectRow[i].fieldName+"']";
			tempJsonObj.id = selectNodes[0].xpath+"/slot/"+selectRow[i].isDeleted;
			tempJsonObj.pid = selectNodes[0].id;
			tempJsonObj.fieldId = selectRow[i].id;
			tempJsonObj.fieldCode = selectRow[i].fieldId;
			tempJsonObj.name = selectRow[i].fieldName;
			tempJsonObj.repeat = '0';
			//分辨是否为元数据
			tempJsonObj.isFeild = '1';
			docTree.addNodes(selectNodes[0],tempJsonObj);
		}
		
		var nodes = docTree.getSelectedNodes();
		if(nodes && nodes.length>0){
			changeMetadata(nodes[0]);
		}
		
		$(".jgridDataDIV").find("input[type='checkbox']").each(function(){
			if($(this).attr("checked")){
				$(this).parent().parent().removeClass("grid-row-selected");
				$(this).attr("checked",false);
			}
		});
		
	});

	//返回文档管理页面
	$("#backToDoc").click(function(){
		window.location.href = projectPath+ "/metadata/docvIndex.zb";
	});

	//文档与数据映射
	/**$("#docDataMap").click(function(){
		var docId = $("#docId").val();
		if(!docId) return;
		$.ajax({
			url : 'metadata/isExistXML.zb',
			type : 'POST',
			cache : false,
			data : encodeURI("id=" + docId),
			dataType : 'json',
			success : function(res) {
				if(res.docXml){
					window.location.href = projectPath + "/metadata/docmapIndex.zb?id="+docId;
				}else{
					JDialog.tip('请先维护文档结构信息');
				}
			},
			error : function(XMLHttpRequest,textStatus, errorThrown) {
				JDialog.showMessageDialog('异常','服务器异常，检测文档失败。',JDialog.ERROR);
			}
		});
	});*/

	//加载数据集中元数据列表
	/**
	var sjji_data = "{";
	sjji_data += "datasetName:'"+encodeURIComponent($("#datasetName").val())+"',datasetVersion:'"+$("#datasetVersion").val()+"',standardCode:'"+$("#standardCode").val()+"'}";
	grid_metadata.setParams(eval('('+sjji_data+')'));
	grid_metadata.loadData();
	
	$("#datasetName").change(function(){
		var sjji_data = "{";
		sjji_data += "datasetName:'"+encodeURIComponent($("#datasetName").val())+"',datasetVersion:'"+$("#datasetVersion").val()+"',standardCode:'"+$("#standardCode").val()+"'}";
		grid_metadata.setParams('');
		grid_metadata.setParams(eval('('+sjji_data+')'));
		grid_metadata.loadData();
	});
	
	$("#datasetVersion").change(function(){
		var sjji_data = "{";
		sjji_data += "datasetName:'"+encodeURIComponent($("#datasetName").val())+"',datasetVersion:'"+$("#datasetVersion").val()+"',standardCode:'"+$("#standardCode").val()+"'}";
		grid_metadata.setParams('');
		grid_metadata.setParams(eval('('+sjji_data+')'));
		grid_metadata.loadData();
	});

    $("#standardCode").change(function(){
        var sjji_data = "{";
        sjji_data += "datasetName:'"+encodeURIComponent($("#datasetName").val())+"',datasetVersion:'"+$("#datasetVersion").val()+"',standardCode:'"+$("#standardCode").val()+"'}";
        grid_metadata.setParams('');
        grid_metadata.setParams(eval('('+sjji_data+')'));
        grid_metadata.loadData();
    });*/
    
    $("#querybtn").click(function(){
    	var id = $("#id").val();
    	var fieldName = $.trim($("input[name='fieldName']").val());
    	grid_metadata.setParams({id:id,fieldName:fieldName});
    	grid_metadata.loadData(1);
    });
    
    $(window).resize(function(){
		grid_metadata.setGridHeight($(document.body).height()-112);
		$("#mmmm").height(document.body.clientHeight-99);
	});
});
		

--></script>
</head>
<body style="overflow: hidden;">
<div class="doc_div" id="doc_tree_div">
<div class="container" style="margin-left: 5px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="padding-left:4px;">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">文档结构</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content"	>
			
	<div style="height: 30px;margin: 5px 0px;text-align: center;">
		<a class="btn" href="javascript:void(0);" id="saveToDB">
		    <span class="btn-left">
		        <span class="btn-text icon-save" >保存文档结构</span>
		    </span>
		</a>
		<!-- 
		<a class="btn" href="javascript:void(0);" id="backToDoc">
		    <span class="btn-left">
		        <span class="btn-text icon-back" >返回</span>
		    </span>
		</a>
		 -->
	</div>
	<!-- 
	<div style="position: relative;margin: 2px;max-width: 200px;">
		<span style="font-size: 15px;"><b>${docName}</b></span>
	</div>
	 -->
	<div style="overflow: auto;" id="mmmm">
	<div id="metadata_struct" class="ztree" style="margin-top: 5px;"></div>
	</div>
	</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
</div>
<div class="doc_div" id="doc_button_div" style="padding-top: 250px;">
  <center>
  	<a class="btn" href="javascript:void(0);" id="SaveMetadata" title="添加元数据至文档树">
	    <span class="btn-left">
	        <span class="btn-text icon-back" ></span>
	    </span>
	</a>
  </center>
</div>
<div id="metadata_data"><input type="hidden" name="docId" id="docId" value="${docId}"/>
	<div class="container" style="margin-left: 10px;">
	<div class="c_nw"><div class="c_ne"><div class="c_n"></div></div></div>
	<span class="title-l" style="padding-left:4px;">
		<span class="title-r">
			<b class="icon"></b><span class="title-span">数据集中数据元查询</span>
		</span>
	</span>
	<div class="tools-panel"></div>
	<div class="c_w">
		<div class="c_e">
			<div class="c_content"	>
			
			<div style="margin:10px 20px;">
	<form id="queryform">
	数据集:
	<input type="text" name="datasetName" id="datasetName"/><input type="hidden" name="id" id="id"/>
	元数据别名：
	<input type="text" name="fieldName"/>
	<a class="btn" href="javascript:void(0);">
		<span class="btn-left" id="querybtn">
			<span class="btn-text icon-search">查询</span>
		</span>
	</a>
	</form>
	</div>
			
			</div>
		</div>
	</div>
	<div class="c_sw"><div class="c_se"><div class="c_s"></div></div></div>
</div>
	
	<div id="metadata_grid" ></div>
	<div style="text-align:center; margin-top:10px;">
	</div>
</div>
   
<div id="cHTML" style="display:none;padding:40px 20px 0 20px;">
    <fieldset id="fs_html" style="background:wheat;margin:30px 20px 0px 20px;padding:10px 40px 10px 40px;">
    	<div class="checkForm" id="checkForm">
	    	<div>中文名：<input type="text" id="md_chineseName" name="md_chineseName" value="" reg="/^\D+\S*$/" msg="请输入非数字开头的中文名" validate="string|1-16"/></div>
	        <div id="div_englishname">英文名：<input type="text" id="md_englishName" name="md_englishName" value="" reg="/^([a-z]|[A-Z])+\w*$/" msg="请输入字母开头的合法的字符" validate="string|1-64"/></div>
	        <div>
	        	<div validate="radio" title="可选性" name="md_isSelected" value="1" style="width: 180px;">
	        	可选性：
	        	<input type="radio" id="md_isSelected_true" name="md_isSelected" value="1"/>
	              是&nbsp;&nbsp; 
	            <input type="radio" id="md_isSelected_false" name="md_isSelected" value="0"/>
	              否</div></div>
	        <div>
	        	<div validate="radio" title="唯一性" name="md_isOnly" value="1" style="width: 180px;">
	        	唯一性：
	        	<input type="radio" id="md_isOnly_true" name="md_isOnly" value="1"/>
	              是&nbsp;&nbsp; 
	            <input type="radio" id="md_isOnly_false" name="md_isOnly" value="0"/>
	              否</div></div>
	        <div>重复性：<select name="md_mulitiple" id="md_mulitiple" style="width: 120px;" validate="select">
	        	<option value="">请选择</option>
	        	<option value="0">0</option>
	        	<option value="1">1</option>
	        	<option value="2">0-n</option>
	        	<option value="3">1-n</option>
	        </select></div>
        </div>
    </fieldset>
</div>
</body>
</html>