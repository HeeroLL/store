var col = [ {text: '序号',		width: 50,	textAlign: 'center',	align : 'center',	dataIndex: 'path'   },
			{text: '名称',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'name'	},
			{text: '编码',		width: 150,	textAlign: 'center',	align : 'center',	dataIndex: 'code'	},
			{text: '操作',		width: 300,	textAlign: 'center',	align : 'center',	dataIndex: 'path',
				formatter:function(data){
				 	var value = data['uploadStatus'];
				 	return "<a  href='compare/getModuleTemplateByTypeId.zb?typeId="+data['path']+"'>下载模板</a>";
				}  
			}];

var grid,editData,formDialog,setBtnDisabled;
 
 

$(function(){
	grid = new JGrid({
		//title : '数据字典类型列表<font color=red>【】</font>',
		title : '',
		col : col,
		dataCol:'',
		checkbox : false,
		dataUrl : 'compare/modules.zb',
		render : 'grid',
		pageBar : false,
		height : document.body.clientHeight-95,
		countEveryPage : 20,
		crudOpera : false
	});
	
	grid.loadData();
});