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
			{text: '元数据标识符',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldId' },
			{text: '元数据编码',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldCode' }
			];

$(function(){
	$("#metaInfo").autocomplete(options);
	
	grid1 = new JGrid({
		title: '元数据列表',
		col	 :col1,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.documentElement.clientHeight-110,
		dataUrl  : 'metadata/datasetMetadataList.zb?id='+datasetId,
		render   : 'grid1',
		pageBar  : true,
		countEveryPage: 6
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