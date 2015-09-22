var grid,gobal_tableName = "";
var zdpp;
var nodes = [{name:'医疗机构信息',code:'WJD01.001',tableName:'BIZ_MEDICAL_ORGAN_M',pId:'1'},
			 {name:'医疗机构人员信息',code:'WJD01.003',tableName:'BIZ_MEDICAL_ORGAN_STAFF_M',pId:'1'},
			 {name:'药品信息',code:'WJD01.008',tableName:'BIZ_DRUG_INFO_M',pId:'1'},
			 {name:'行政区划信息',code:'WJD01.004',tableName:'BIZ_ADMINISTRATIVE_DIVISION_M',pId:'1'},
			 {name:'诊疗项目信息',code:'WJD01.006',tableName:'BIZ_DIAGNOSE_TREATMENT_ITEM_M',pId:'1'}];

var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
	gobal_tableName = treeNode.tableName;
	$("button[name='add']").attr("disabled",false);
	$("button[name='update']").attr("disabled",false);
	$("button[name='remove']").attr("disabled",false);
	$("#orgName").attr("readOnly",false);
	$("#dictquery").attr("disabled",false);
	
	zdpp.setOptions({
		params: { tableName: gobal_tableName },
		serviceUrl:'compare/getMainInfoListById.zb'
	});
	
	grid.setParams(eval("({tableName:'"+gobal_tableName+"'})"));
	grid.loadData(1);
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

var col = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
			{text: '名称(机构)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'name' },
			{text: '标识(标准)',		width: 0,	textAlign: 'center',	align : 'left',		dataIndex: 'mId' },
			{text: '编码(机构)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'code' },
			{text: '名称(标准)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'stdName' },
			{text: '编码(标准)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'stdCode' }
			];


var options={
	width: 400,
	select:false,
	textField:'name',
	serviceUrl:'compare/getMainInfoListById.zb',
	noCache:true,
	params: { tableName: gobal_tableName},
	col:[{dataIndex:'name'},{dataIndex:'code'}],
	valueField:{dataIndex:'id',dataName:'orgId'}//隐藏文本的name
};

var removeCompare = function(id){
	$.ajax({
		url: "compare/removeMdCompare.zb",
		type: 'POST',
		cache: false,
		data: "id="+id+"&tableName="+gobal_tableName,
		dataType: 'json',
		success: function(res){
			$(document).unmask();
			if(res.success){
				JDialog.tip("删除成功", 2);
				grid.loadData(1);
			}else{
				JDialog.tip("删除失败", 2);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document).unmask();
			JDialog.tip("服务异常，删除失败", 2);
		}
	});
}

$(function(){
	
	$('body').layout({
       west: {
           size: 250
       }
    });
    
    var maindatatree = $.fn.zTree.init($("#maintypeInfo"), setting, nodes);
    
    zdpp = $("#orgName").autocomplete(options);
    
    grid = new JGrid({
		title: '对照列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.documentElement.clientHeight-100,
		dataUrl  : 'compare/maindataPage.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 15,
		listeners: {
			dblclick: function(row){//Grid双击事件
				//editData("修 改", row['id']);
				window.location.href = projectPath + "/compare/mainInfoEdit.zb?tableName="+gobal_tableName+"&id="+row['id'];
			},
			curdButtonClick: function(btn){
 				if(btn=='add'){
 					//editData("新 建");
 					window.location.href = projectPath + "/compare/mainInfoEdit.zb?tableName="+gobal_tableName;
 				}else if(btn=='update'){ 					
 					var rows = grid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行修改');
 						return;
 					}
 					//editData("修 改", rows[0]['id']);
 					window.location.href = projectPath + "/compare/mainInfoEdit.zb?tableName="+gobal_tableName+"&id="+rows[0]['id'];
 				}else if(btn=='remove'){ //删除操作
 					var rows = grid.getSelections();
 					if(rows.length<1){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
						if(id=='yes'){
							var ids = "";
		 					for(var i=0;i<rows.length;i++){
		 						ids +=rows[i].id + ",";
		 					}
		 					if(ids.length > 1){
		 						ids = ids.substring(0,ids.length - 1);
		 					}
		 					removeCompare(ids);
						}
					});
 				}
 			}
		}    
	});
	
	$("#dictquery").click(function(){
		var id = $("#orgId").val();
		if(!gobal_tableName){
			JDialog.tip('请选择主数据类型!');
			return;
		}
		grid.setParams(eval("({tableName:'"+gobal_tableName+"',id:'"+id+"'})"));
		grid.loadData(1);
	});
	
	$("button[name='refresh']").css("display","none");
	if(tableName!=''){
		gobal_tableName = tableName;
		
		zdpp.setOptions({
			params: { tableName: gobal_tableName },
			serviceUrl:'compare/getMainInfoListById.zb'
		});
		
		grid.setParams(eval("({tableName:'"+gobal_tableName+"'})"));
		grid.loadData(1);
		
		function ztree_ready(){
			if(!maindatatree){//判断如果ztree没有初始化完成
				setTimeout('ztree_ready()',50);
			}else{
				var nodes = maindatatree.getNodes();
				for(var i=0;i<nodes.length;i++){
					if(nodes[i].tableName == tableName){
						maindatatree.selectNode(nodes[i]);
						break;
					}
				}
			}
			
		}
		ztree_ready();
	}else{
		$("button[name='add']").attr("disabled",true);
		$("button[name='update']").attr("disabled",true);
		$("button[name='remove']").attr("disabled",true);
		$("#orgName").attr("readOnly",true);
		//$("#dictquery").attr("disabled",true);
		$("#maintypeInfo_1_a").trigger("click");
	}
	$('#orgName').keydown(function(e) {
		
	    if (e.keyCode == 13) {
	    	
	    	e.preventDefault();
	     	e.stopPropagation();
	    	var id = $("#orgId").val();
			if(!gobal_tableName){
				JDialog.tip('请选择主数据类型!');
				return;
			}
			grid.setParams(eval("({tableName:'"+gobal_tableName+"',id:'"+id+"'})"));
			grid.loadData(1);
	    }
	});
	
});