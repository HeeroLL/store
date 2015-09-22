var saveInfo;

var getInfoByPage = function(){
	var data = "{";
	$("#checkForm").find("input[type='text']").each(function(){
		var name = $(this).attr("name");
		if(name == "datasetName"){
			data += $(this).attr("name")+":'"+$(this).val().replace(/\s+/g, "")+"',";
		}else{
			data += $(this).attr("name")+":'"+$.trim($(this).val())+"',";
		}
	});
	$("#checkForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#checkForm").find("select").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	return data;
};

var saveDatas = function(data,callback){
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'metadata/datasetSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("保存成功",2);
				$("input[name='id']").val(res.id);
				if(callback) callback();
			}else{
				JDialog.tip(res.msg, JDialog.ERROR);
				parent.setBtnDisabled("p_dataset_save",false);
				parent.setBtnDisabled("p_dataset_close",false);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			parent.setBtnDisabled("p_dataset_save",false);
			parent.setBtnDisabled("p_dataset_close",false);
			$(document.body).unmask();
		}
	});
};

$(function(){
	saveInfo = function(callback){
		//检查数据填入是否合格
		if(!checkAll("checkForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		var a = $.trim($("input[name='datasetName']").val());
		var b = $("#datasetVersion").val();
		var c = $("input[name='id']").val();
        var d = $("#standardCode").val();
		if(!c) c = '';
		var flag = "";
		$.ajax({
			url:'metadata/existsDatasetInfo.zb',
			type: 'POST',
			cache: false,
			async:false,
			data: encodeURI("id="+c+"&datasetName="+a+"&datasetVersion="+b+"&standardCode="+d),
			dataType: 'json',
			success: function(res){
				flag = res.result;
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
			}
		});
		
		if(flag == "1"){
			JDialog.tip('该数据集已经存在，请重新填写',3);
			return;
		}
		
		parent.setBtnDisabled("p_dataset_save",true);
		parent.setBtnDisabled("p_dataset_close",true);
		
		//获取页面数据
		var datas = getInfoByPage();
		//保存页面数据
		saveDatas(datas,callback);
	};
});