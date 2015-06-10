var mainHTML,grid;
var col1=[ {text: '角色ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'roleId'			},
		   	    {text: '角色名称',		width: 200,	textAlign: 'center',	align : 'center', 	dataIndex: 'name'			},
		   	    {text: '名称拼音',		width: 200,	textAlign: 'center',	align : 'left',		dataIndex: 'nameSpell'			},
		   	    {text: '角色说明',		width: 250,	textAlign: 'center',	align : 'center',	dataIndex: 'remark'			},
		   	    {text: '是否为公共角色',	width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'isPublicRole',renderer: function(value){
 				if(value == "1"){
 					return "是";
 				}else{
 					return  "否";
 				} 				
 			}},
		   	    {text: '创建者',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'creatorId'			},
		   	    {text: '创建时间',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'createTime'			} ];

$(function(){
	grid = new JGrid({
			title: '角色列表',
			col	 :col1,
			dataCol:'',
			checkbox : true,
			striped:true,
			height   :document.body.clientHeight-50,
			dataUrl  : 'btp/role/roleQueryPage.zb',
			render   : 'grid',
			pageBar  : true,
			crudOpera: true,
			countEveryPage: 20,
			listeners: {
 			dblclick: function(row){//Grid双击事件
 				editData("修 改", row['roleId'],"btp/role/goUpdateAndAddRole.zb");
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
 					editData("修 改", rows[0]['roleId']);
 				}else if(btn=='remove'){ //删除操作
 					var datas = grid.getSelections();
					if(datas.length != 1){
						JDialog.showMessageDialog('提示','请选择一条记录进行删除。');
					}else{
						grid.opt.removeUrl = "btp/role/removeRole.zb";
						grid.opt.crudID = "roleId";
						grid.removeData();
					}
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
 		}
	});
//grid.loadData();
});

var editData=function(title,id,url){
	formDialog = new JDialog({
			title: title,
			width: 900,
			height: $(document.body).height()*0.98,
	 		icon: 'images/jkda_tiny.png',
	 		maximizable:true,
	 		buttons: [{
	 			text: '保存',
	 			id: 'save_user',
	 			handler: function(){
	 				user_edit.saveRole(function(){
	 					grid.loadData();
	 					formDialog.closeDialog(); 
	 				});
	 				
	 			}
	 		},{
	 			text: '关闭',
	 			id: 'save_close',
	 			handler: function(){
	 				JDialog.showConfirmDialog("警告", "您确定要关闭吗？", JDialog.WARNING, function(btn){
	 					if(btn=='yes'){ 							
	 					   grid.loadData(); 		 					
	 					   formDialog.closeDialog(); 						
	 					   }else{ 							
						}
	 				});
	 			}
	 	    }]
		});
		
		if(!id) id = "";
		formDialog.show();
		formDialog.add("<iframe id='user_edit' name='user_edit' src='btp/role/goUpdateAndAddRole.zb?roleId="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />"); 
};


function loadMho(mhoId,levelCode,parentId){
	grid.setParams({
		medicalOrganId: mhoId
	});
	grid.loadData();
}