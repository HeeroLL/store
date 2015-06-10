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
var col = [ { text: '文档编号', width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id'	},
		{ text: '文档名称', width: 200,	textAlign: 'center',	align : 'center',	dataIndex: 'docName'	},
		{ text: '文档交易码', width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'docTypeCode'	},
		{ text: '版本类型', width:200,	textAlign: 'center',	align : 'center',	dataIndex: 'docType',	
			renderer:function(value){
				if(value == '1'){
					return "CDA文档";
				}else if(value == '2'){
					return "自定义文档";
				}
			}
		},
		{ text: '版本号', width: 100,	textAlign: 'center',	align : 'center',	dataIndex: 'docVersion'	},
		{ text: '版本描述', width:0,	textAlign: 'center',	align : 'center',	dataIndex: 'docDesc'	},
		{ text: '操作',
		  width:100,	
		  textAlign: 'center',	
		  align : 'center',	
		  formatter: 
			  function(rowData){
			  	return '<a href="javascript:DownXml(\''+rowData['id']+"\',\'"+rowData['docName']+'\')">xml文档结构下载</a>';
	      	  }
		},
		{ text: '操作',
		  width:100,	
		  textAlign: 'center',	
		  align : 'center',	
		  formatter: 
			  function(rowData){
			  	return '<a href="javascript:downDoc(\''+rowData['id']+"\',\'"+rowData['docName']+'\')">doc文档下载</a>';
	      	  }
		},
		{ text: '操作',
			  width:100,	
			  textAlign: 'center',	
			  align : 'center',	
			  formatter: 
				  function(rowData){
				  	return '<a href="javascript:downTestXml(\''+rowData['id']+"\',\'"+rowData['docName']+'\')">xml测试文档下载</a>';
		      	  }
			}];

function DownXml(docId,docName){
	var url = projectPath+ "/doc/downloadXml.zb";
	$("#myform").attr("action",url);
	$("#docid").val(docId);
	$("#docname").val(docName);
	$("#myform").submit();
}
function downTestXml(docId,docName){
	var url = projectPath+ "/doc/downloadTestXml.zb";
	$("#myform").attr("action",url);
	$("#docid").val(docId);
	$("#docname").val(docName);
	$("#myform").submit();
}
function downDoc(docId,docName){
	var url = projectPath+ "/doc/downloadDoc.zb";
	$("#myform").attr("action",url);
	$("#docid").val(docId);
	$("#docname").val(docName);
	$("#myform").submit();
}
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
		title: '下载管理',
		col	 :col,
		dataCol:'',
		checkbox : false,
		height   :document.body.clientHeight-100,
		dataUrl  : 'metadata/docvListInfo.zb',
		render   : 'grid',
		striped:true,//隔行变色
		pageBar  : true,
		crudOpera: true,
		countEveryPage: 20,
		listeners: {
		}
	});
	grid.loadData();

    $("#grid").find("button[name='add']").css("display","none");
    $("#grid").find("button[name='update']").css("display","none");
    $("#grid").find("button[name='remove']").css("display","none");
    $("#grid").find("button[name='refresh']").css("display","none");

	$("#docmapIndex").css("width","145px");
	$(window).resize(function(){
		grid.setGridHeight($(document.body).height()-$('.container').outerHeight()-25);
	});
});