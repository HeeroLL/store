var tab,mainHTML,grid;
var dialog, editData,MhoId,LevelCode,formDialog,mhoId,loadMho;
var parentId='100';
var editHTML = "";
		var col1= [ {text : '机构ID',		textAlign: 'center',	align : 'left',		width : 0,		dataIndex : 'mhoId'},
		          {	text : '父机构ID',		textAlign: 'center',	align : 'left',		width : 0,		dataIndex : 'parentId'},      
		          {	text : '机构层级编码',		textAlign: 'center',	align : 'left',		width : 90,		dataIndex : 'levelCode'}, 
		          {	text : '机构名称', 		textAlign: 'center',	align : 'left',		width : 195,	dataIndex : 'mhoName'},
		          {	text : '上级机构',		textAlign: 'center',	align : 'left',		width : 195,	dataIndex : 'parentName'},
		          {	text : '机构类型',		textAlign: 'center',	align : 'left',		width : 80,		dataIndex : 'typeCode', dictType:'jigouleixing'	},
		          {	text : '医院性质',		textAlign: 'center',	align : 'left',		width : 80,		dataIndex : 'hospitalNature', dictType:'yiliaojigousuoyouzhixingzhi'},
		          {	text : '医院等级',		textAlign: 'center',	align : 'left',		width : 80,		dataIndex : 'hospitalGrade', dictType:'yiliaojigouidengji'}];

		   	    

$(function(){
$("#Query").click(function(){
		var levelCode = $.trim($("input[name='levelCode']").val());
		var mhoName = $.trim($("input[name='mhoName']").val());
		var typeCode = $.trim($("select[name='typeCode']").val());
			grid.setParams({
				levelCode: levelCode,
				mhoName: mhoName,
				typeCode:typeCode,
				mhoId: mhoId
			});
			grid.loadData();
			});
		
		
	grid = new JGrid({
			title: '医疗列表',
			col	 :col1,
			dataCol:'',
			checkbox : true,
			striped:true,
			height   :document.body.clientHeight-130,
			dataUrl  : 'btp/mho/mhoList.zb',
			render   : 'grid',
			pageBar  : true,
			crudOpera: true,
			countEveryPage: 20,
			listeners: {
 			dblclick: function(row){//Grid双击事件
 				editData("修 改", row['mhoId']);
 			},
 			curdButtonClick: function(btn){
 				if(btn=='add'){
 					editData("新 建",null);
 				}else if(btn=='update'){ 					
 					var rows = grid.getSelections();
 					if(rows.length!=1){
 						JDialog.tip('请选择一条记录进行修改');
 						return;
 					}
 					editData("修 改", rows[0]['mhoId']);
 				}else if(btn=='remove'){ //删除操作
 					var datas = grid.getSelections();
					if(datas.length != 1){
						JDialog.showMessageDialog('提示','请选择一条记录进行删除。');
					}else{
						grid.opt.removeUrl = "btp/mho/mhoRemove.zb";
						grid.opt.crudID = "mhoId";
							grid.removeData(function(res){
 						if(res==true){
 							JDialog.tip("医疗机构删除成功");
 							window.parent.frames["innerlLeftFrame"].loadMhoTree();
 						}else{
 							JDialog.tip("医疗机构下存在角色或医疗人员，不能删除");
 						}
 					});
					}
 				}else if(btn=='refresh'){
 					grid.loadData();
 				}
 			}
 		}
	});

	loadMho= function(id,levelCode,parentId){
		MhoId = id;
		mhoId=id;
//		levelCode=levelCode;
		parentId=id;
		grid.setParams({
			MhoId: id,
			mhoId: id,
//			levelCode:levelCode,
			parentId: parentId
		});
		grid.loadData();
	}
	setBtnDisabled = function(btnId, bool){
		$("#"+btnId).attr("disabled", bool);
	};
	
	grid.loadData();
	
		editData=function(title, id){ 
		formDialog=new JDialog({
			title : title,
			width : 700,
			height :370,
			buttons: [{
	 			text: '保存',
	 			id: 'mho_save',	 			
	 			handler: function(){
					mhoEdit.saveMho(function(){
					window.parent.frames["innerlLeftFrame"].loadMhoTree();
					formDialog.closeDialog();
					grid.loadData();
		    });
	 		}
	 		},{
	 			text: '关闭',
	 			id: 'mho_close',
	 			handler: function(){
	 				formDialog.closeDialog();
	 			}
	 		}]
		});
	
		formDialog.show();
		formDialog.add("<iframe id='mhoEdit' name='mhoEdit' src='btp/mho/mhoEdit.zb?mhoId="+MhoId+"&mhoId2="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");
		
		
	}
					
});

		

   