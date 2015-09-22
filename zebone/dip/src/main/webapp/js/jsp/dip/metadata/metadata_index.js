var grid,editData,formDialog,setBtnDisabled,importDialog,editHTML='';
var col = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
			{text: '名称',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldName' },
			{text: '标识符',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldId' },
			{text: '目录分类',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'onlySort' },
			{text: '数据元编码',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldCode' },
			{text: '值域类型',		width: 90,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldType',renderer: function(value){
                if (value=='1') {
                    return "日期";
                }else if(value=='2'){
                	return "字符";
                }else if(value=='3'){
                	return "数字";
                }else if(value=='4'){
                	return "数据字典";
                }else if(value=='5'){
                	return "主数据";
                }else{
                	return "";
                }
            } },
			{text: '值域',		width: 140,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldValname' },
			{text: '数据格式',		width: 120,	textAlign: 'center',	align : 'left',		dataIndex: 'fieldFormat' }
			];

var queryCondition = function(){
	var datas = "{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
		}
	});
	$("#query").find("select").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
		}
	});
	if(datas.length>1) datas = datas.substring(0, datas.length-1);
	datas += "}";
	return datas;
};

var removeData = function(){
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
					url: "metadata/metadataRemove.zb",
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

//元数据是否被使用
var isMetadataUsing =function(id){
	var flag = "";
	$.ajax({
		url:'metadata/metadataIsUsingById.zb',
		type: 'POST',
		cache: false,
		data: 'id='+id,
		dataType: 'json',
		async:false,
		success: function(res){
			if(res.result == "1"){
				flag = "1";
			}else{
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
	
	return flag;
}
	
$(function(){
	
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
		title: '数据元列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		striped:true,
		height   :document.documentElement.clientHeight-100,
		dataUrl  : 'metadata/metadataList.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 15,
		listeners: {
			dblclick: function(row){//Grid双击事件
//				var abc = isMetadataUsing(row['id']);
//				if(abc == "1"){
//					JDialog.tip('元数据被使用，无法修改');
//					return;
//				}
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
// 					var abc = isMetadataUsing(rows[0]['id']);
// 					if(abc == "1"){
// 						JDialog.tip('元数据被使用，无法修改');
// 						return;
// 					}
 					editData("修 改", rows[0]['id']);
 				}else if(btn=='remove'){ //删除操作
 					var rows = grid.getSelections();
 					if(rows.length<1){
 						JDialog.tip('请选择记录进行删除');
 						return;
 					}
 					removeData();
// 					grid.opt.removeUrl = "metadata/metadataRemove.zb";
// 					grid.opt.crudID = "id";
// 					grid.removeData();
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
			width : 600,
			height :320,
			buttons: [{
	 			text: '确定',
	 			id: 'p_metadata_save',	 			
	 			handler: function(){
					metadataEdit.saveInfo(function(){
						formDialog.closeDialog();
						grid.setParams('');
						grid.loadData(1);
					});
	 			}
	 		},{
	 			text: '返回',
	 			id: 'p_metadata_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
		
		if(!id) id = "";
		formDialog.show();
		formDialog.add("<iframe id='metadataEdit' name='metadataEdit' src='metadata/metadataEdit.zb?id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
	};
/**	
	grid.addButton({
		id:'drysj',
		text : '导入元数据',
		icon: 'images/import.png',
		handler : function() {
			//JDialog.tip('功能暂不开放!');return;
			importDialog = new JDialog({
				title : '元数据导入',
				width : 400,
				height :250
			});
			
			importDialog.show();
			importDialog.add("<iframe id='metadataImport' name='metadataImport' src='metadata/metadataImport.zb' width='100%' height='100%' frameBorder='0' scrolling='no' />");
		}
	});
	
	$("#drysj").css("width","120px");
*/	
	//控制按钮是否工作
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	$(window).resize(function(){
		grid.setGridHeight($(document.body).height()-$('.container').outerHeight()-25);
	});
});