var grid,formDialog;
//保存doc链接，和更新公用一个连接，由是否存在id值来判断是否更新
var saveDocUrl = "";
//单条记录载入路径,需要配置参数
var singDocLoadUrl = "";
//删除路径,注意param的格式
var docRemoveUrl = "";
			
//编辑框的html
var editHTML = '';
			
//初始化grid控件
var col = [ { text: '主键', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'	},
        { text: '实体编码', width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'docCode'		},
		{ text: '实体名称', width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'docName'		},
		{ text: '版本类型', 
		  width:200,	    
		  textAlign: 'center',	
		  align : 'center',
		  formatter:
			  function(rowData){
			  	  if(rowData['versionType'] == 1){
			  		  return "江苏省标准2014";
			  	  }else if(rowData['versionType'] == 2){
			  		  return "国家标准2011";
			  	  }else if(rowData['versionType'] == 3){
			  		  return "国家标准2012";
			  	  }
		  	  }
			
		},
		{ text: '版本号',  width: 100,		textAlign: 'center',	align : 'center',	dataIndex: 'docVersion'		},
		{ text: '版本设置',
		  width:300,	
		  textAlign: 'center',	
		  align : 'center',	
		  formatter: 
			  function(rowData){
			  	return '<a href="javascript:regStruct(\''+rowData['id']+"\',\'"+rowData['docName']+'\')">实体结构设置</a>'
			  	       +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + '<a href="javascript:docMapping(\''+rowData['id']+'\')">数据映射设置</a>'
			  	       +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + '<a href="javascript:docMapping(\''+rowData['id']+'\')">示例文档设置</a>';
	      	  }
		}
		/*
		{ text: '操作',
		  width:300,	
		  textAlign: 'center',	
		  align : 'center',	
		  formatter:
			  function(rowData){
			  	return '<a href="javascript:docStruct(\''+rowData['id']+"\',\'"+rowData['docName']+'\')">编辑</a>'
			  	       +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + '<a href="javascript:docMapping(\''+rowData['id']+'\')">删除</a>';
	      	  }
		}*/];

var queryCondition = function(){
	var datas = "{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
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

$(function(){
	
	$("#querybtn").click(function(){
		//查询的条件
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData(1);
	});
	
	$("#docName").bind('keypress',function(e){
		
		 if(e.keyCode==13){
			//查询的条件
			var datas=queryCondition();
			grid.setParams(eval('('+datas+')'));
			grid.loadData(1);
			e.preventDefault();
			e.stopPropagation();
		 }
		 
		 
	 });
	
	
	//定义grid控件
	grid = new JGrid({
		title: '实体列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		height   :document.body.clientHeight-100,
		dataUrl  : 'metadata/registervListInfo.zb',
		render   : 'grid',
		striped:true,//隔行变色
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 20,
		listeners: {
			dblclick : function(row) {// Grid双击事件
				var abc = isDocUsing(row['id']);
				if(abc == "1"){
					JDialog.tip('实体被使用，不能维护');
					return;
				}
				editData("修改", row['id']);
			},
			curdButtonClick : function(btn) {
				if (btn == 'add') {
					editData("新建");
				} else if (btn == 'update') {
					var rows = grid.getSelections();
					if (rows.length != 1) {
						JDialog.tip('请选择一条记录进行修改。');
						return;
					}
					var abc = isDocUsing(rows[0]['id']);
					if(abc == "1"){
						JDialog.tip('文档被使用，不能维护');
						return;
					}
					editData("修改",rows[0]['id']);
				} else if (btn == 'remove') { // 删除操作
					if (!grid.getSelectionIndex()) {
						JDialog.tip('请选择数据进行删除。');
						return;
					} else {
						docvInfoRemove();
						//grid.opt.removeUrl = "metadata/docvInfoRemove.zb";
	 					//grid.opt.crudID = "id";
	 					//grid.removeData();
					}
				} else if (btn == 'refresh') {
					grid.loadData();
				}
			}
		}
	});
	grid.loadData();
	
	$("#docmapIndex").css("width","145px");
	$(window).resize(function(){
		grid.setGridHeight($(document.body).height()-$('.container').outerHeight()-25);
	});
});

var editData = function(title, id) {
	formDialog = new JDialog({
		id : 'editdata',
		title : title,
		width : 390,
		height : 350,
		buttons: [{
     			text: '确定',
     			id: 'P_save',
     			handler: function(){
                    //d.mask('正在保存数据，请稍候...');
                    //校验
                    if(!checkAll("checkForm")){
						JDialog.tip('请检查表单填写的数据');
						return;
					}
					var flag = "";
					var id = $("#checkForm input[name=id]").val();
					if(!id) id = "";
					var docName = $("#checkForm input[name=docName]").val().replace(/\s*/g, "");
					docName = docName.replace(/\+/g,'%2B');
					//alert(encodeURI('docName='+docName+'&versionType='+$.trim($("#checkForm select[name=versionType]").val())+'&docVersion='+$.trim($("#docVersion").val())+'&id='+id));
					//return;
					$.ajax({
						url:'metadata/validateDocInfo.zb',
						type: 'POST',
						cache: false, 	
						async:false,
						data: encodeURI('docName='+docName+'&versionType='+$.trim($("#checkForm select[name=versionType]").val())+'&docVersion='+$.trim($("#docVersion").val())+'&id='+id),
						dataType: 'json',
						success: function(res){
							if(res.success) flag = "1";
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
						}
					});
					if(flag == '1'){
						JDialog.tip('该实体已经存在，不能保存',3);
						return;
					}
                    //获取数据
                    var data = getDatas();
                    //保存
                    saveInfos(data);
     			}
     		},{
     			text: '返回',
     			id: 'P_close',
     			handler: function(){
     				formDialog.closeDialog();
     			}
		}]
	});
	formDialog.show();
	if (editHTML == '') {
		editHTML = $('#edit').html();
		$('#edit').remove();
	}
	formDialog.add(editHTML);
	bindCheckEvent();

	if (id) {// 加载Form数据
		formDialog.mask('正在加载数据，请稍候...');
		$.ajax({
			url : 'metadata/docvInfoLoad.zb',
			type : 'POST',
			cache : false,
			data : encodeURI("id=" + id),
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
					formDialog.getComponent().find("select").each(function() {
						var name = $(this).attr('name');
						if (name) {
							$(this).selectOption(data[name]);
						}
					});
					formDialog.getComponent().find("textarea").each(function() {
						var name = $(this).attr('name');
						if (name) {
							$(this).val(data[name]);
						}
					});
					formDialog.unmask();

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
};

var getDatas = function(){
	var data = "{";
	formDialog.getComponent().find("input").each(function(){
		var name = $(this).attr('name');
		if (name == "docName") {
			data += name + ":'"+$(this).val().replace(/\s+/g,"")+"',";
		}else{
			data += name + ":'"+$.trim($(this).val())+"',";
		}
	});
	formDialog.getComponent().find("select").each(function(){
		var name = $(this).attr('name');
		if (name) {
			data += name + ":'"+ $(this).val()+"',";
		}
	});
	formDialog.getComponent().find("textarea").each(function(){
		var name = $(this).attr('name');
		if (name) {
			data += name + ":'"+$.trim($(this).val())+"',";
		}
	});
	if(data.length > 1) data = data.substring(0,data.length-1);
	data +="}";
	return data;
};

var saveInfos = function(data){
	$(document.body).mask("正在保存，请稍后...");
	//return;
	$.ajax({
		url:'metadata/docvInfoSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("保存成功");
				formDialog.closeDialog();
				grid.setParams('');
				grid.loadData(1);
			}else{
				JDialog.tip("保存失败");
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document.body).unmask();
			JDialog.tip("服务器异常，保存失败");
		}
	});
};

var docvInfoRemove = function(){
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
				var datas = "id="+ids+"&modelType=2";
				$.ajax({
					url: "metadata/docvInfoRemove.zb",
					type: 'POST',
					cache: false,
					data: encodeURI(datas),
					dataType: 'json',
					success: function(res){
						grid.jgridMain.unmask();					
						if(res.success){
							var msg = res.msg;
							JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
								if(id=='ok'){
									grid.setParams('');
									grid.loadData(1);
								}
							});	
						}else{					 		
							JDialog.tip(res.msg,3);							
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

//文档是否被使用
var isDocUsing =function(id){
	var flag = "";
	$.ajax({
		url : 'metadata/isExistXML.zb',
		type : 'POST',
		cache : false,
		data : encodeURI("id=" + id),
		dataType : 'json',
		async:false,
		success : function(res) {
			if(res.docXml){
				flag = "1";
			}else{
			}
		},
		error : function(XMLHttpRequest,textStatus, errorThrown) {
		}
	});
	
	return flag;
};

//实体结构设置
function regStruct(docId,docName){
	var url = projectPath+ "/metadata/regvStructor.zb";
	$("#myform").attr("action",url);
	$("#docid").val(docId);
	$("#docname").val(docName);
	$("#myform").submit();
}
