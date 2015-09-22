var saveInfo;
var getInfoByPage = function(){
	var data = "{";
	$("#checkForm").find("input[type='text']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#checkForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#checkForm").find("select").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#checkForm").find("textarea").each(function(){
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
		url:'dataset/datasetInfoSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.showMessageDialog('信息', res.msg, JDialog.INFORMATION,function(id,text){
					if(id =='ok'){
						$("input[name='id']").val(res.id);
						if(callback) callback();
					}
				},false);
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
		parent.setBtnDisabled("p_dataset_save",true);
		parent.setBtnDisabled("p_dataset_close",true);
		
		//获取页面数据
		var datas = getInfoByPage();
		//保存页面数据
		saveDatas(datas,callback);
	};
});