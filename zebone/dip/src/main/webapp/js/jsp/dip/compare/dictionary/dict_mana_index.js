var grid,orgDictTypeId="",orgDictType="",orgDictTypeCode="",editData,formDialog;
var editHTML = '';
var zy,zy1,zy2;
var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
	orgDictTypeId = treeNode.typeId;
	orgDictType = treeNode.typeName;
	orgDictTypeCode = treeNode.typeCode;
	grid.setParams(eval("({orgDictTypeId:'"+orgDictTypeId+"'})"));
	grid.loadData(1);
	
	$("#orgDict").attr("readonly",false);
    $("#dictquery").attr("disabled",false);
    
    zy.setOptions({
		params: { orgDictTypeId:treeNode.typeId },
		serviceUrl:'compare/getDictByTypeId.zb'
	});
    
};
var setting = {
	treeId:'dicttype_tree',
    data: {
    	key: {
			name: "typeName"
		},
		simpleData: {
			enable: true,
			idKey:'typeId',
			pIdKey:'parentId'
			
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};

var options={
	width: 400,
	select:false,
	textField:'orgDict',
	serviceUrl:'compare/getDictByTypeId.zb',
	noCache:true,
	params: { orgDictTypeId: orgDictTypeId},
	col:[{dataIndex:'orgDict'},{dataIndex:'orgDictCode'}],
	valueField:{dataIndex:'orgDictId',dataName:'orgDictId'}//隐藏文本的name
};

var options1={
	width: 400,
	select:false,
	textField:'dict',
	serviceUrl:'compare/getDictByOrgTypeId.zb',
	noCache:true,
	onSelect:function(data){
		formDialog.getComponent().find("input[name='dictCode']").val(data.dictCode);
	},
	params: { orgDictTypeId: ''},
	col:[{dataIndex:'dict'},{dataIndex:'dictCode'}],
	valueField:{dataIndex:'dictId',dataName:'dictId'}//隐藏文本的name
};
var options2={
	width: 400,
	select:false,
	textField:'typeName',
	serviceUrl:'compare/getDictTypeInfoByName.zb',
	noCache:true,
	onSelect:function(data){
		formDialog.getComponent().find("input[name='dictTypeCode']").val(data.typeCode);
		zy1.setOptions({
			params: { orgDictTypeId:data.typeId },
			serviceUrl:'compare/getDictByOrgTypeId.zb'
		});
	},
	col:[{dataIndex:'typeName'},{dataIndex:'typeCode'}],
	valueField:{dataIndex:'typeId',dataId:'dictTypeId'}//隐藏文本的name
};

var col = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
			{text: '名称(机构)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'orgDict' },
			{text: '标识(机构)',		width: 0,	textAlign: 'center',	align : 'left',		dataIndex: 'orgDictId' },
			{text: '标识(标准)',		width: 0,	textAlign: 'center',	align : 'left',		dataIndex: 'DictId' },
			{text: '编码(机构)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'orgDictCode' },
			{text: '名称(标准)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'dict' },
			{text: '编码(标准)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'dictCode' }
			];
var loadTree = function(){
	var typename = $.trim($("#query_typename").val());
   	$("#divmask").mask("正在加载，请稍后...");
   	$.ajax({
   		url: "compare/getDictTypeByName.zb",
           type: 'POST',
           cache: false,
           data: encodeURI("typeName="+typename),
           dataType: 'json',
           async:false,
           success: function(res){
           	$("#divmask").unmask();
           	if(res.data){
            	$.fn.zTree.init($("#dictTypeInfo"), setting, res.data);
            }else{
            	$.fn.zTree.init($("#dictTypeInfo"), setting, []);
            }
           },
           error: function(XMLHttpRequest, textStatus, errorThrown){
           	$("#divmask").unmask();
           }
   	});
}

var removeCompare = function(id){
	$.ajax({
		url: "compare/removeCompareInfo.zb",
		type: 'POST',
		cache: false,
		data: "id="+id,
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
};

var saveInfo = function(){
	
	if(!checkAll("checkForm")){
		JDialog.tip('请检查表单填写的数据');
		return;
	}
	$(document).mask("正在保存，请稍后...");
	
	formDialog.getComponent().find("input").each(function(){
		$(this).attr("disabled",false);
	});
	
	$.ajax({
		url: "compare/saveCompareInfo.zb",
		type: 'POST',
		cache: false,
		data: $("#checkForm").serialize(),
		dataType: 'json',
		async:false,
		success: function(res){
			$(document).unmask();
			if(res.success){
				JDialog.tip("保存成功", 2);
				formDialog.closeDialog();
				if(res.typeId){
					loadTree();
					var treeObj = $.fn.zTree.getZTreeObj("dictTypeInfo");
					var nodes = treeObj.getNodes();
					if (nodes.length>0) {
						for(var i=0;i<nodes.length;i++){
							if(res.typeId == nodes[i].typeId){
								treeObj.selectNode(nodes[i]);
								orgDictTypeId = nodes[i].typeId;
								orgDictType = nodes[i].typeName;
								orgDictTypeCode = nodes[i].typeCode;
								grid.setParams(eval("({orgDictTypeId:'"+orgDictTypeId+"'})"));
								grid.loadData(1);
								
								$("#orgDict").attr("readonly",false);
							    $("#dictquery").attr("disabled",false);
							    
							    zy.setOptions({
									params: { orgDictTypeId:nodes[i].typeId },
									serviceUrl:'compare/getDictByTypeId.zb'
								});
							}
						}
					}
				}else{
					grid.loadData(1);
				}
				
			}else{
				JDialog.tip(res.msg?res.msg:"服务异常，保存失败", 2);
				
				var typeId1 = formDialog.getComponent().find("input[name='orgDictTypeId']").val();
				if(typeId1!=''){
					formDialog.getComponent().find("input[name='orgDictType']").attr("disabled",true);
					formDialog.getComponent().find("input[name='orgDictTypeCode']").attr("disabled",true);
				}
				var id1 = formDialog.getComponent().find("input[name='orgDictId']").val();
				if(id1!=''){
					formDialog.getComponent().find("input[name='orgDict']").attr("disabled",true);
					formDialog.getComponent().find("input[name='orgDictCode']").attr("disabled",true);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document).unmask();
			JDialog.tip("服务异常，保存失败", 2);
			var typeId1 = formDialog.getComponent().find("input[name='orgDictTypeId']").val();
			if(typeId1!=''){
				formDialog.getComponent().find("input[name='orgDictType']").attr("disabled",true);
				formDialog.getComponent().find("input[name='orgDictTypeCode']").attr("disabled",true);
			}
			var id1 = formDialog.getComponent().find("input[name='orgDictId']").val();
			if(id1!=''){
				formDialog.getComponent().find("input[name='orgDict']").attr("disabled",true);
				formDialog.getComponent().find("input[name='orgDictCode']").attr("disabled",true);
			}
		}
	});
};

		
$(function(){
	$('body').layout({
       west: {
           size: 250
       }
    });
    
    $("#treeDiv").height($(document.body).height()-63);
    
    $("#orgDict").attr("readonly",true);
    //$("#dictquery").attr("disabled",true);
    
    $.fn.zTree.init($("#dictTypeInfo"), setting, nodes);
    
    zy = $("#orgDict").autocomplete(options);
    
    $("#querybtn").click(function(){
    	loadTree();
    	orgDictTypeId="";
    	orgDictType="";
    	orgDictTypeCode="";
    	
    	grid.setParams('');
		grid.loadData(1);
		
		$("#orgDict").val('');
		$("#orgDict").attr("readonly",true);
    	//$("#dictquery").attr("disabled",true);
    	$("#orgDictId").val('');
    	
    });
    
    $("#dictquery").click(function(){
    	if(!orgDictTypeId){
    		JDialog.tip('请选择字典类型!');
    		return;
    	}
    	grid.setParams(eval("({orgDictTypeId:'"+orgDictTypeId+"',orgDictId:'"+$("#orgDictId").val()+"'})"));
		grid.loadData(1);
    });
    
    grid = new JGrid({
		title: '对照列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.documentElement.clientHeight-100,
		dataUrl  : 'compare/getDictComparePage.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 15,
		listeners: {
			dblclick: function(row){//Grid双击事件
				editData("修 改", row['id']);
			},
			curdButtonClick: function(btn){
 				if(btn=='add'){
 					editData("新 建");
 				}else if(btn=='update'){ 					
 					var rows = grid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行修改');
 						return;
 					}
 					editData("修 改", rows[0]['id']);
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
		 						ids +=rows[i].id + ","+rows[i].orgDictId +";";
		 					}
		 					if(ids.length > 1){
		 						ids = ids.substring(0,ids.length - 1);
		 					}
		 					removeCompare(ids);
						}
					});
 					
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
		}
	});
	
	$("button[name='refresh']").css("display","none");
    
	editData = function(title,orgDictId){
		formDialog = new JDialog({
			title : title,
			width : 400,
			height :350,
			buttons: [{
	 			text: '保存',
	 			id: 'p_metadata_save',	 			
	 			handler: function(){
	 				saveInfo();
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'p_metadata_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		formDialog.show();
		if (editHTML == '') {
			editHTML = $('#dictCompareDIV').html();
			$('#dictCompareDIV').remove();
		}
		formDialog.add(editHTML);
		bindCheckEvent();
		
		if(orgDictTypeId!=''){
			formDialog.getComponent().find("input[name='orgDictTypeId']").val(orgDictTypeId);
			formDialog.getComponent().find("input[name='orgDictType']").val(orgDictType);
			formDialog.getComponent().find("input[name='orgDictTypeCode']").val(orgDictTypeCode);
			formDialog.getComponent().find("input[name='orgDictType']").attr("disabled",true);
			formDialog.getComponent().find("input[name='orgDictTypeCode']").attr("disabled",true);
		}
		$("#dictType").autocomplete(options2);
		zy1 = $("#dict").autocomplete(options1);
		
		if(orgDictId){
			formDialog.mask('正在加载数据，请稍候...');
			$.ajax({
				url : 'compare/getDictInfoByOrgDictId.zb',
				type : 'POST',
				cache : false,
				data : encodeURI("orgDictId=" + orgDictId),
				dataType : 'json',
				success : function(res) {
					if (res.data) {
						var data = res.data;
						formDialog.getComponent().find("input").each(function() {
							var name = $(this).attr('name');
							if (name) {
								$(this).val(data[name]);
							}
						});
						formDialog.unmask();
						
						zy1.setOptions({
							params: { orgDictTypeId: data.dictTypeId},
							serviceUrl:'compare/getDictByOrgTypeId.zb'
						});
						
						formDialog.getComponent().find("input[name='orgDictType']").attr("disabled",true);
						formDialog.getComponent().find("input[name='orgDictTypeCode']").attr("disabled",true);
						formDialog.getComponent().find("input[name='orgDict']").attr("disabled",true);
						formDialog.getComponent().find("input[name='orgDictCode']").attr("disabled",true);
					} else {
						formDialog.unmask();
						JDialog.showMessageDialog('失败','数据加载失败。',JDialog.ERROR);
					}
				},
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					formDialog.unmask();
					JDialog.showMessageDialog('异常','服务器出现异常，数据加载失败。',JDialog.ERROR);
				}
			});
		}
		
		$("#dictType").change(function(){//类型变换事件
			var value = formDialog.getComponent().find("input[name='dictTypeId']").val();
			if(value=='') {
				formDialog.getComponent().find("input[name='dictTypeCode']").val('');
				formDialog.getComponent().find("input[name='dictCode']").val('');
				formDialog.getComponent().find("input[name='dict']").val('');
				formDialog.getComponent().find("input[name='dictId']").val('');
			}
		});
		
		$("#dict").change(function(){//类型变换事件
			var value = formDialog.getComponent().find("input[name='dictId']").val();
			if(value=='') formDialog.getComponent().find("input[name='dictCode']").val('');
		});
	}
	
    $(window).resize(function(){
		$("#treeDiv").height($(document.body).height()-63);
		grid.setGridHeight($(document.body).height()-100);
	});
    
    $("#dictTypeInfo_1_a").trigger("click");
    $('#orgDict').keydown(function(e) {
    	
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	e.stopPropagation();
	    	if(!orgDictTypeId){
	    		JDialog.tip('请选择字典类型!');
	    		return;
	    	}
	    	grid.setParams(eval("({orgDictTypeId:'"+orgDictTypeId+"',orgDictId:'"+$("#orgDictId").val()+"'})"));
			grid.loadData(1);
	    }
	});
});