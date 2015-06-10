var field, commeValue, col, colum, paramURI, grid, formDialog, primaryField, visibleFlag, corresV, fieldArray;
var editHTML='';
var idkey;
$(document).ready(function() {

	// 动态生成Grid列表
    corresV = corres.split(",");
	fieldArray = fieldsV.split(",");
    commeValue = commentsV.split(",");
    visibleFlag = visible.split(",");
    
    col = '';
    var widthV;
    for(var k=0;k<commeValue.length;k++)
	{
		if('n' == $.trim(visibleFlag[k]).toLowerCase()){
			widthV = 0;
		}else{
			widthV = 200;
		}
		col = col + "{text: '" + $.trim(commeValue[k])+ "', width: "+widthV+", textAlign: 'center',	align : 'center', dataIndex: '"+  $.trim(corresV[k]) + "' },";
	}
	if(col.length>1) col = col.substring(0, col.length-1);
	colum = eval('[' + col + ']');

	// grid加载地址
	paramURI = 'btp-mdm-list.zb?dbName=' + dbTableName + "&dbFields=" + fieldsV + "&corres=" + corres;
	
	// 记录的主键
	idkey=fieldArray[0];
	primaryField = corresV[0];
	grid = new JGrid({
				title    : '模块列表',
				col	     : colum,
				dataCol  : '',
				checkbox : true,
				striped  : true,
				height   : document.body.clientHeight-175,
				dataUrl  : encodeURI(paramURI),
				render   : 'grid',
				pageBar  : true,
				crudOpera: true,
				countEveryPage: 15,
				formToggleId:'eoc',//切换表单显示的id
				listeners: {
					dblclick: function(row){//Grid双击事件
						editData("修 改", row[primaryField]);
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
							editData("修 改", rows[0][primaryField]);
						}else if(btn=='remove'){
							var rows = grid.getSelections();
							if(rows.length==0){
								JDialog.tip('请至少选择一条记录进行删除');
								return;
							}
							grid.opt.removeUrl = "btp-mdm-remove.zb?dbName=" + $.trim(dbTableName) + "&dbFields=" + idkey;
							grid.opt.crudID = primaryField;
							grid.removeData();
						}else if(btn=='refresh'){
							grid.loadData();
						}
					}
		 	  }
	});
	
	grid.loadData(1);
	
	//查询单击事件
	$("#querybtn").click(function(){
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData();
	});
	
	//查询条件的拼装
	var queryCondition = function(){
		var datas="{";
		$("#query").find("input").each(function(){
			if($.trim($(this).val())!=''){
				datas += $(this).attr("name") + ":'" + $.trim($(this).val()) + "',";
			}
		});
		if(datas.length>1) datas = datas.substring(0, datas.length-1);
		datas += "}";
		return datas;
	}
});//document end


//新增修改数据start
var editData=function(title, id){

	formDialog=new JDialog({
		title : title,
		width : 700,
		height :$(document.body).height()*0.6,
		buttons: [{
 			text: '保存',
 			id: 'personnel_save',	 			
 			handler: function(){
				saveInfo();
 			}
 		},{
 			text: '关闭',
 			id: 'personnel_close',
 			handler: function(){
 				formDialog.closeDialog();
 			}
 		}]
	});
	formDialog.show();
 	if(editHTML==''){
 		editHTML = $('#edit').html();
 		$('#edit').remove();
 	}
 	formDialog.add(editHTML);
 	
 	if(id)// id不为空则为修改
	{
		loadDataByID(id, formDialog);
	}

	//按钮失效
	setBtnDisabled = function(btnId, bool)
	{
	    $("#"+btnId).attr("disabled", bool);
	}
	
	//保存前校验
	check = function()
	{
		//alert("555555555");
	}
	
	//保存方法
	saveInfo = function(){
		formDialog.mask('正在保存数据，请稍候...');
		setBtnDisabled("personnel_save",true);
		setBtnDisabled("personnel_close",true);
		
		//构造参数值
		var paramV = '';
        for(var k=1;k<corresV.length;k++)
		{
			paramV = paramV + ''+$("#"+$.trim(corresV[k])).val() + "~";
		}
		if(paramV.length>1) paramV = paramV.substring(0, paramV.length-1);
		var paramURL = "?dbName=" + $.trim(dbTableName);
		paramURL = paramURL + "&primaryKeyValue=" + $("#"+$.trim(corresV[0])).val();
		paramURL = paramURL + "&dbFields=" + fieldsV;
		paramURL = paramURL + "&fieldValue=" + paramV;
		var options = { 
			beforeSubmit: check,
	             success: showResponse,
	                 url: "btp-mdm-operate.zb",
	                data: { corres: corres, 
	                		dbFields: fieldsV, 
	                        dbName: $.trim(dbTableName), 
	                        primaryKeyValue:$("#"+$.trim(corresV[0])).val(),
	                        primaryKeyField : idkey }
	    };
		$("#fromEdit").ajaxSubmit(options); 
	}
	
	//保存成功后返回的数据
	showResponse = function(data){
		JDialog.tip(data.msg);
		setBtnDisabled("personnel_save",false);
		setBtnDisabled("personnel_close",false);
		formDialog.unmask();
		if(data.bool)
		{
			formDialog.closeDialog();
			grid.loadData(1);	
		}
	}
}//新增修改数据end

//加载数据
var loadDataByID=function(id, formDialog){
	formDialog.mask('正在加载数据，请稍候...');
	var paramURILoad = "dbName=" + dbTableName + "&dbFields=" + fieldsV + "&primaryKeyField=" + idkey + "&primaryKeyValue=" + id;
	$.ajax({
		url: 'btp-mdm-load.zb',
		type: 'POST',
		cache: false,
		data: encodeURI(paramURILoad),
		dataType: 'json',
		success: function(res){
			//基本信息和账号信息的加载
			formDialog.getComponent().find("input[type='text']").each(function(){
				var name = $(this).attr('name');
				if(name){
					$(this).val(res[name]);
				}
			});
			formDialog.getComponent().find("input[type='hidden']").each(function(){
				var name = $(this).attr('name');
				if(name){
					$(this).val(res[name]);
				}
			});
			formDialog.unmask();								
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			formDialog.unmask();
			JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);
		}
	});
}