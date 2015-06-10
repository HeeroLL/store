var grid;
//var col1 = [{text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
//			{text: '名称(机构)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'name' },
//			{text: '标识(标准)',		width: 0,	textAlign: 'center',	align : 'left',		dataIndex: 'mId' },
//			{text: '编码(机构)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'code' },
//			{text: '名称(标准)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'stdName' },
//			{text: '编码(标准)',		width: 150,	textAlign: 'center',	align : 'left',		dataIndex: 'stdCode' }
//			];

var col = new Array();
var str1 = fields.split(";")[1];
var array1 = str1.split(",");
for(var i=0;i<array1.length;i++){
	var obj = {text: array1[i], width: i==0?0:100,	textAlign: 'center',	align : 'center',	dataIndex: 'col'+(i+1)};
	col.push(obj);
}

var options={
	width: 600,
	select:false,
	textField:'stdName',
	serviceUrl:'compare/getMdstdByName.zb',
	noCache:true,
	params: { tableName: tableName},
	col:[{dataIndex:'stdName'},{dataIndex:'stdCode'}],
	valueField:{dataIndex:'mid',dataName:'maindataId'}//隐藏文本的name
};

var saveMdCompareInfo = function(){
	$(document.body).mask("正在保存对照信息，请稍后...");
	$.ajax({
		url: "compare/saveMdCompareInfo.zb",
		type: 'POST',
		cache: false,
		data: $("#checkForm").serialize(),
		dataType: 'json',
		async:false,
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip('对照数据保存成功',2);
				window.location.href = projectPath + "/compare/maindata/manageIndex.zb?tableName="+tableName;
			}else{
				JDialog.tip(res.msg?res.msg:'对照数据保存失败',2);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document.body).unmask();
			JDialog.tip('服务器异常，对照数据保存失败',2);
		}
	});
}

$(function(){
	grid = new JGrid({
		title: '对照列表',
		col	 :col,
		dataCol:'',
		checkbox : true,
		selectModel:1,
		striped:true,
		height   :document.documentElement.clientHeight-235,
		dataUrl  : 'compare/maindatastdPage.zb',
		render   : 'grid',
		pageBar  : true,
		countEveryPage: 10,
		listeners: {
			afterload:function(data){
        		if(maindataId){
        			if(!data) return;
        			for(var i = 0;i<data.length;i++){
        				if(maindataId == data[i].col1){
        					grid.selectRecord(i);
        					break;
        				}
        			}
        		}
        	}
		}
	});
	var param = "{tableName:'"+tableName+"',name:'"+fields+"'}";
	grid.setParams(eval("("+param+")"));
	grid.loadData();
	
	$("#stdName").autocomplete(options);
	
	$("#mdstdquery").click(function(){
		var param1 = "{tableName:'"+tableName+"',name:'"+fields+"',mid:'"+$("#maindataId").val()+"'}";
		grid.setParams(eval("("+param1+")"));
		grid.loadData();
	});
	
	$("#mdstdsave").click(function(){
		if(!checkAll("checkForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		var rows = grid.getSelections();
		if(rows.length >0){
			$("input[name='mid']").val(rows[0].col1);
		}else{
			JDialog.tip('请选择标准主数据进行匹配保存');
			return;
		}
		
		saveMdCompareInfo();
	});
	
	$("#mdstdcancel").click(function(){
		window.location.href = projectPath + "/compare/maindata/manageIndex.zb?tableName="+tableName;
	});
	
	$(window).resize(function(){
		grid.setGridHeight($(document.body).height()-225);
	});
	
	var id = $("input[name='id']").val();
	
	if(id!=''){
		$("#orgMainInfoDiv").find("input[type='text']").each(function(){
			$(this).attr("readOnly",true);
		});
	}
});