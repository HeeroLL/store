var grid, formDialog, deleteRelation, check, click, operateType, selectValue;
var editHTML='';
$(document).ready(function() {

	selectValue =$("#basicInformation").find("select").children('option:selected').val();

	click = 0;
	
	var col=[      
		{text: '主数据类型ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'mdtId'},
		{text: '主数据类型编码',		width: 200,	textAlign: 'center',	align : 'left',		dataIndex: 'mdtCode'},
		{text: '主数据类型名称',		width: 200,	textAlign: 'center',	align : 'left',	dataIndex: 'mdtName'},
		{text: '主数据类型种类',	    width: 200,	textAlign: 'center',	align : 'left',	dataIndex: 'mdtType'},
		{text: '表名',		        width: 200,	textAlign: 'center',	align : 'left',		dataIndex: 'mdtTablename'}];
		
	grid = new JGrid({
				title: '模块列表',
				col	 :col,
				dataCol:'',
				checkbox : true,
				striped:true,
				height   :document.body.clientHeight-175,
				dataUrl  : 'btp-maindata-type-list.zb?mdtType=' + mdtType,
				render   : 'grid',
				pageBar  : true,
				crudOpera: true,
				countEveryPage: 15,
				formToggleId:'eoc',
				listeners: {
					dblclick: function(row){//Grid双击事件
						operateType = 'update';
						editData("查 看", row['mdtCode']);
					},
					curdButtonClick: function(btn){
						if(btn=='add'){
							operateType = 'add';
							editData("新 建");
								
						}else if(btn=='update'){ 					
							var rows = grid.getSelections();
							if(rows.length!=1){
								JDialog.tip('请选择一条记录');
								return;
							}
							operateType = 'update';
							editData("修 改", rows[0]['mdtCode']);
						}else if(btn=='remove'){
							var rows = grid.getSelections();
							if(rows.length!=1){
								JDialog.tip('请选择一条记录进行删除');
								return;
							}
							grid.opt.removeUrl = "btp-mdmtype-remove.zb?dbName=" + rows[0]['mdtTablename'];
							grid.opt.crudID = "mdtId";
							grid.removeData(function(res){
		 						if(res){
		 							window.parent.frames["innerlLeftFrame"].loadMDTree();
		 						}else{
		 							JDialog.showMessageDialog('提示','删除失败! 请检查是否存在已有数据');
		 						}
		 					});
						}else if(btn=='refresh'){
							grid.loadData();
						}
					}
				}
		});
	
	grid.loadData(1);
	/*
	//导入按钮
	grid.addButton({
		text : '导 入',
		handler : function() {
			editData("导 入");	
		}
	});
	//导出按钮
	grid.addButton({
		text : '导 出',
		handler : function() {
			editData("导 出");	
		}
	});*/
	

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
var editData=function(title, code){

	checkTypeSelect();

	formDialog=new JDialog({
		title : title,
		width : 700,
		height :$(document.body).height()*0.6,
		buttons: [{
 			text: '保存',
 			id: 'personnel_save',	 			
 			handler: function(){
 				checkDefault();
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
 	
 	if(code)// id不为空则为修改
	{
		loadDataByCode(code, formDialog);
	}
	//按钮失效
	setBtnDisabled = function(btnId, bool)
	{
	    $("#"+btnId).attr("disabled", bool);
	}
	
 	//添加字段信息
	$("#addRelation").click(function(){
		if(click == 0)
		{
			$("#relation").append($("#tempTemplateDefault").html());
			click ++;
		}
		var content = $("#tempTemplate").html();
		$("#relation").append(content);
	});

	//保存方法
	saveInfo = function(){
		formDialog.mask('正在保存数据，请稍候...');
		var options = { 
			beforeSubmit:  check,
	        success:   showResponse,
	        url:       "btp-mdtype-operate.zb?operateType="+ operateType
	    };
		$("#fromEdit").ajaxSubmit(options); 
	}
	
	//保存前校验
	check = function(){
	}
	
	//保存成功后响应
	showResponse = function(data){
		JDialog.tip(data.msg);
		setBtnDisabled("personnel_save",false);
		setBtnDisabled("personnel_close",false);
		formDialog.unmask();
		if(data.bool)
		{
			formDialog.closeDialog();
			window.parent.frames["innerlLeftFrame"].loadMDTree();
			grid.loadData(1);	
			click = 0;
		}
	}
	
}//新增修改数据end


//删除字段信息
deleteRelation = function(obj){
	$(obj).parent().parent().parent().remove();
}

//加载数据
var loadDataByCode=function(code, formDialog){
	formDialog.mask('正在加载数据，请稍候...');
	var paramURILoad = "code=" + code;
	$.ajax({
		url: 'btp-mdt-load.zb',
		type: 'POST',
		cache: false,
		data: encodeURI(paramURILoad),
		dataType: 'json',
		success: function(res){
			//setBtnDisabled("personnel_save",true);
			
			formDialog.getComponent().find("input[type='text']").each(function(){
				var name = $(this).attr('name');
				if(name){
					$(this).val(res[name]);
				}
			});
			
			var fieldObjectList = res.fieldList;
			//忽略ID字段对象
			for(var i = 1; i<fieldObjectList.length;i++){
				var content = $("#tempTemplateLoad").html();
			    $("#relation").append(content);
			}
			var len = 0;
			$("#relation").find("tr").each(function(){
	 		   $(this).find("input").each(function(){
					var name = $(this).attr('name');
					if(name){
						$(this).val(fieldObjectList[len][name]);
					}
				});
				$(this).find("select").each(function(){
					var name = $(this).attr('name');
					if(name){
						$(this).val(fieldObjectList[len][name]);
					}
				});
				len++;
			});

			formDialog.unmask();								
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			formDialog.unmask();
			JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);
		}
	});
}

//type的select值变化
var selectChange = function(obj)
{
	var value = $(obj).children('option:selected').val();
	hidderContain(value);
}

//加载时判断select值
var checkTypeSelect = function()
{
	hidderContain(selectValue);
}

//隐藏Contain
var hidderContain = function(value)
{
	if('add' == operateType && 0 == value)
	{
		$("#basicinfo").attr("style", "display: none");
		$("#fieldinfo").attr("style", "display: none");
	}
	else{
		$("#basicinfo").attr("style", "display: block");
	    $("#fieldinfo").attr("style", "display: block");
	}
}

var checkDefault = function()
{	
	if(click == 0)
	{
		$("#relation").append($("#tempTemplateDefault").html());
		click ++;
	}
}
