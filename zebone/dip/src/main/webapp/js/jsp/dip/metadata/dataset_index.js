var grid,editData,formDialog,setBtnDisabled;
var col = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
			{text: '数据集名称',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'datasetName' },
			{text: '标准组织',		width: 120,	textAlign: 'center',	align : 'left',		dataIndex: 'standardCode' },
			{text: '数据集编码',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'datasetCode' },
			{text: '版本',		width: 70,	textAlign: 'center',	align : 'left',		dataIndex: 'datasetVersion' }
			];
//数据集标识
var datasetId = '';

var options={
	width: 350,
    maxHeight: 200,
	select:false,
	textField:'fieldName',
	serviceUrl:'metadata/metaListByDatasetId.zb?id='+datasetId,
	onSelect: function(value){ $("#ysjbm").val(value.fieldName); },
	col:[{dataIndex:'fieldName'},{dataIndex:'fieldId'},{dataIndex:'fieldCode'}],
	valueField:{dataIndex:'id',dataName:'metadataId'}//隐藏文本的name
};
var grid1;
var col1 = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
			{text: '元数据名称',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldName' },
			{text: '元数据别名',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldDesc' },
			{text: '元数据标识符',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldId' }
			];
			
var queryCondition = function(){
	var datas = "{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" +encodeURIComponent($.trim($(this).val())) + "',";
		}
	});
	$("#query").find("select").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
		}
	});
	if(datas.length>1) datas = datas.substring(0, datas.length-1);
	datas += "}";
	return datas;
};

var metadataByDataset = function(id,datasetName){
	var formDialog1 = new JDialog({
		title : '数据集元数据',
		width : 600,
		height :400
	});
	
	formDialog1.show();
	formDialog1.add("<iframe id='datasetEdit' name='datasetEdit' src='metadata/datasetMetadata.zb?id="+id+"&datasetName="+datasetName+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
};

var removeDataSet = function(){
	var rows = grid.getSelections();
	if(rows.length>0){
		JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
			if(id=='yes'){
				grid.jgridMain.mask('正在删除数据，请稍候...');
				var ids = '';
				for(var i=0; i<rows.length; i++){
					ids += rows[i]['id'] + ',';
				}
				ids = ids.substring(0, ids.length-1);
				var datas = "id="+ids;
				$.ajax({
					url: "dataset/datasetRemove.zb",
					type: 'POST',
					cache: false,
					data: encodeURI(datas),
					dataType: 'json',
					success: function(res){
						grid.jgridMain.unmask();					
						if(res.success){
							var msg = res.msg?res.msg:'数据删除成功。';
							JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
								if(id=='ok'){
									grid.setParams('');
									grid.loadData(1);
								}
							});	
						}else{					 		
							JDialog.tip(res.msg?res.msg:'数据删除失败。',3);							
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						grid.jgridMain.unmask();
						JDialog.tip('服务器出现异常，数据删除失败。',3);
					}
				});
			}
		});
	}
};

$(function(){
	$("body").layout({
		resizable : false,
		livePaneResizing : true,
		slidable:true,
		north_size:80,
		north_minSize:80,
		east_size:480,
		east__minSize : 480,
		center__resizable:true
	});
	//查询事件
	$("#querybtn").click(function(){
		//查询的条件
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData(1);
	});

    $("#query").bind('keypress', function (e) {
        if (e.keyCode == 13) {
            var datas = queryCondition();
            grid.setParams(eval('(' + datas + ')'));
            grid.loadData(1);
            e.preventDefault();
            e.stopPropagation();
        }
    });
	
	grid = new JGrid({
		title: '数据集列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.body.clientHeight-110,
		dataUrl  : 'metadata/datasetList.zb',
		render   : 'grid',
		selectModel:1,
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 15,
		listeners: {
			afterload:function(){
				datasetId = '';
				$("#metaInfo").val('');
				$("input[name='metadataId']").val('');
				$("#ysjbm").val('');
						
				$("#addYsj").attr("disabled","disabled");
				$("#querybtn1").attr("disabled","disabled");
				grid1.setParams({id:''});
            	grid1.loadData(1);
			},
			click : function(row) {
				$("#addYsj").attr("disabled",false);
				$("#querybtn1").attr("disabled",false);
				datasetId = row['id'];
				grid1.setParams({id:row['id']});
            	grid1.loadData(1);
			},
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
 					//return;
 					var rows = grid.getSelections();
 					if(rows.length<1){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					removeDataSet();
 					
 					//grid.opt.removeUrl = "dataset/datasetRemove.zb";
 					//grid.opt.crudID = "id";
 					//grid.removeData();
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
		}
	});
	
	grid.loadData();
	
	editData = function(title,id){
		formDialog = new JDialog({
			title : title,
			width : 400,
			height :320,
			buttons: [{
	 			text: '确定',
	 			id: 'p_dataset_save',	 			
	 			handler: function(){
					datasetEdit.saveInfo(function(){
						formDialog.closeDialog();
						grid.setParams('');
						grid.loadData(1);
					});
	 			}
	 		},{
	 			text: '返回',
	 			id: 'p_dataset_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		formDialog.show();
		formDialog.add("<iframe id='datasetEdit' name='datasetEdit' src='metadata/datasetEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	};
	
//	grid.addButton({
//		id:'datasetmetadata',
//		text : '数据集元数据',
//		icon: 'images/icons/sum.png',
//		handler : function() {
//			//展示该数据及中的元数据
//			var rows = grid.getSelections();
//			if(rows.length != 1){
//				JDialog.tip('请选择一条数据集查看元数据');
//				return;
//			}
//			metadataByDataset(rows[0].id,encodeURI(rows[0].datasetName).replace(/\+/g,'%2B'));
//		}
//	});
	
	$("#datasetmetadata").css("width","135px");
	/**
	grid.addButton({
		id:'importdataset',
		text : '导入数据集',
		handler : function() {
			JDialog.tip('功能暂不开放!');
		}
	});
	*/
	//控制按钮是否工作
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	$(window).resize(function(){
		grid.setGridHeight($(document.body).height()-$('.container').outerHeight()-35);
		grid1.setGridHeight($(document.body).height()-180);
	});
	
	$("#metaInfo").autocomplete(options);
	
	grid1 = new JGrid({
		title: '元数据列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.documentElement.clientHeight-181,
		dataUrl  : 'metadata/datasetMetadataList.zb',
		render   : 'grid1',
		pageBar  : true,
		countEveryPage: 10
	});
	
	//grid1.loadData();
	
	grid1.addButton({
		id:'deletemetadata',
		text : '删除',
		icon:'images/icons/edit_remove.png',
		handler : function() {
			//JDialog.tip('功能暂不开放!');return;
			var rows = grid1.getSelections();
			if(rows.length<1){
				JDialog.tip('请选择元数据解除绑定!');
			}else{
				JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
					if(id=='yes'){
						var ids = '';
						for(var i=0; i<rows.length; i++){
							ids += rows[i]['id'] + ',';
						}
						if(ids.length >0) ids = ids.substring(0,ids.length-1);
							
						$(document.body).mask("正在解除元数据绑定，请稍后...");
						$.ajax({
							url: "metadata/metadataRevoke.zb",
							type: 'POST',
							cache: false,
							data: encodeURI("fieldId="+ids+"&datasetId="+datasetId),
							dataType: 'json',
							success: function(res){
								$(document.body).unmask();
								if(res.success){
									grid1.loadData(1);
								}else{
									JDialog.tip('元数据解除绑定失败!');
								}
							},
							error: function(XMLHttpRequest, textStatus, errorThrown){
								$(document.body).unmask();
								JDialog.tip('服务器出现异常，解除绑定失败。', JDialog.ERROR);
							}
						});
					}else{
						return;
					}
				});
				
			}
		}
	});
	
	//查询事件
	$("#querybtn1").click(function(){
		//查询的条件
		var fieldName = $.trim($("input[name='bieming']").val());
		grid1.setParams({id:datasetId,fieldName:fieldName});
		grid1.loadData(1);
	});
	
	//在数据集中添加元数据
	$("#addYsj").click(function(){
		var metaInfo = $.trim($("#metaInfo").val());
		var id = $("input[name='metadataId']").val();
		var ysjbm = $.trim($("#ysjbm").val());
		if(metaInfo != '' && id != '' && ysjbm != ''){
			var flag = "";
			//验证此元数据别名在该数据集中是否存在
			$.ajax({
				url: "metadata/datasetExistsYsjbm.zb",
				type: 'POST',
				cache: false,
				async:false,
				data: encodeURI("datasetId="+datasetId+"&fieldName="+ysjbm),
				dataType: 'json',
				success: function(res){
					if(res.result == '1'){
						flag = "1";
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
				}
			});
			
			if(flag == "1"){
				JDialog.tip('该元数据别名已经存，请重新添加。', 3);
				return;
			}
			
			$(document.body).mask("正在添加元数据，请稍后...");
			var datas = "fieldId="+id+"&datasetId="+datasetId+"&fieldName="+ysjbm;
			$.ajax({
				url: "metadata/metadataAdd.zb",
				type: 'POST',
				cache: false,
				data: encodeURI(datas),
				dataType: 'json',
				success: function(res){
					$(document.body).unmask();
					if(res.success){
						$("#metaInfo").val('');
						$("input[name='metadataId']").val('');
						$("#ysjbm").val('');
						grid1.loadData(1);
					}else{					 		
						JDialog.tip('元数据添加失败。', 3);							
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					$(document.body).unmask();
					JDialog.tip('服务器出现异常，元数据添加失败。',3);
				}
			});
		}else{
			JDialog.tip('请选择元数据添加!');
		}
	});
});