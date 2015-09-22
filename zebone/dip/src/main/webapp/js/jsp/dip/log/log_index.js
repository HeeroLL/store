var grid,dialog;
var editHTML = '';
var col = [ {text: 'ID',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'id' },
           	{text: '执行时间',		width: 0,	textAlign: 'center',	align : 'center',	dataIndex: 'execTime' },
			{text: '文档编号',		width: 100,	textAlign: 'center',	align : 'left',		dataIndex: 'docNo' },
			{text: '上传时间',		width: 125,	textAlign: 'center',	align : 'left',		dataIndex: 'uploadTime' },
			{text: '上传机构',		width: 160,	textAlign: 'center',	align : 'left',		dataIndex: 'docSourceOrgan' },
			{text: '文档类型',		width: 140,	textAlign: 'center',	align : 'left',		dataIndex: 'docType' },
			{text: '上传状态',		width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'uploadStatus',
			 formatter:function(data){
			 	var value = data['uploadStatus'];
			 	if(data['execTime'] == "0"){
			 		if(value == "0"){
				 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(1,'"+data['id']+"');\">失败</a>)";
				 	}else if(value == "1"){
				 		return "成功";
				 	}else{return "";}
			 	}else{
				 	if(value == "0"){
				 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(1,'"+data['id']+"');\">失败</a>("+data['execTime']+"ms)";
				 	}else if(value == "1"){
				 		return "成功("+data['execTime']+"ms)";
				 	}else{return "";}
				 }} },
//			{text: '接收状态',		width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'receiveStatus',
//			 formatter:function(data){
//			 	var value = data['receiveStatus'];
//			 	if(value == "0"){
//			 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(2,'"+data['id']+"');\">失败</a>";
//			 	}else if(value == "1"){
//			 		return "成功";
//			 	}else{return "";}
//			 } },
			{text: '校验状态',		width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'checkStatus',
			 formatter:function(data){
			 	var value = data['checkStatus'];
			 	if(value == "0"){
			 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(3,'"+data['id']+"');\">失败</a>";
			 	}else if(value == "1"){
			 		return "成功";
			 	}else{return "";}
			 } },
			{text: '存储状态',		width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'storageStatus',
			 formatter:function(data){
			 	var value = data['storageStatus'];
			 	if(value == "0"){
			 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(4,'"+data['id']+"');\">失败</a>";
			 	}else if(value == "1"){
			 		return "成功";
			 	}else{return "";}
			 } },
			{text: '注册状态',		width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'registerStatus',
			 formatter:function(data){
			 	var value = data['registerStatus'];
			 	if(value == "0"){
			 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(5,'"+data['id']+"');\">失败</a>";
			 	}else if(value == "1"){
			 		return "成功";
			 	}else{return "";}
			 } },
			{text: '解析状态',		width: 60,	textAlign: 'center',	align : 'left',		dataIndex: 'analyzeStatus',
			 formatter:function(data){
			 	var value = data['analyzeStatus'];
			 	if(value == "0"){
			 		return "<a  href=\"javascript:void(0);\" onclick=\"logDetail(6,'"+data['id']+"');\">失败</a>";
			 	}else if(value == "1"){
			 		return "成功";
			 	}else{return "";}
			 } }];

var queryCondition = function(){
	var datas = "{";
	$("#query").find("input").each(function(){
		if($.trim($(this).val())!=''){
			datas += $(this).attr("name") + ":'" +encodeURIComponent($.trim($(this).val())) + "',";
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

function logDetail(type,logId){
	dialog = new JDialog({
		id : 'editdata',
		title : '错误详细信息',
		width : 390,
		height : 350,
		buttons: [{
     			text: '关闭',
     			id: 'P_close',
     			handler: function(){
     				dialog.closeDialog();
     			}
		}]
	});
	dialog.show();
	if (editHTML == '') {
		editHTML = $('#edit').html();
		$('#edit').remove();
	}
	dialog.add(editHTML);
	bindCheckEvent();
	$(document.body).mask("正在加载，请稍后...");
	$.ajax({
		url:'log/logInfoLoad.zb',
		type: 'POST',
		cache: false,
		data: encodeURI("eventType="+type+"&logId="+logId),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				var data = res.data;
				dialog.getComponent().find("span[id='eventTime']").text(data.eventTime);
				dialog.getComponent().find("span[id='errorCode']").text(data.errorCode);
				dialog.getComponent().find("span[id='errorName']").text(data.logId);
				dialog.getComponent().find("textarea[id='logDetails']").text(data.logDetails);
			}else{
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document.body).unmask();
			JDialog.tip("服务器异常数据加载失败",2);
		}
	});
}

$(function(){
	$("#querybtn").click(function(){
		var receiveStatus = $("input[name='receiveStatus']").val();
		var uploadTime = $("input[name='uploadTime']").val();
		if(receiveStatus !='' && uploadTime != ''){
			if(uploadTime > receiveStatus){
				JDialog.tip("上传时间范围不对合法",2);
				$("input[name='uploadTime").focus();
				return;
			}
		}
		if(receiveStatus !='' || uploadTime != ''){
			var date = new Date();
			var year = date.getFullYear().toString();
			var month = (date.getMonth()+1).toString();
			var day = date.getDate().toString();
			var sj = year+"-";
			sj +=month.length==1?"0"+month:month;
			sj += "-";
			sj +=day.length==1?"0"+day:day;
			
			if(receiveStatus !=''){
				if(receiveStatus > sj){
					JDialog.tip("时间不能超过今天",2);
					$("input[name='receiveStatus").focus();
					return;
				}
			}
			if(uploadTime != ''){
				if(uploadTime > sj){
					JDialog.tip("时间不能超过今天",2);
					$("input[name='uploadTime").focus();
					return;
				}
			}
		}
		if($("#docSourceOrgan").val()=='' && $("#organName").val()!=''){
			JDialog.tip("机构不存在",2);
			$("input[name='organName").focus();
			return;
		}
		if($("input[name='docNo']").val()!=''){
			if($("input[name='docNo']").val().length > 32){
				JDialog.tip("文档编号长度超出范围",2);
				$("input[name='docNo").focus();
				return;
			}
		}
		//查询的条件
		var datas=queryCondition();
		grid.setParams(eval('('+datas+')'));
		grid.loadData(1);
	});
	
	$("#organName").autocomplete({//获取医疗机构信息
		width: 350,
		select:false,
		noCache:true,
		textField:'name',
		serviceUrl:'log/logOrganInfo.zb',
		col:[{dataIndex:'name'},{dataIndex:'code'}],
		valueField:{dataIndex:'code',dataName:'docSourceOrgan'},//隐藏文本的name
		paging:{pageSize: 10 }//每页显示条数
	});
	
	grid = new JGrid({
		title: '日志列表',
		col	 :col,
		dataCol:'',
		checkbox : false,
		striped:true,
		height   :document.body.clientHeight-130,
		dataUrl  : 'log/docLogInfoList.zb',
		render   : 'grid',
		pageBar  : true,
		crudOpera: false,
		countEveryPage: 15
	});
	
	grid.loadData();
});