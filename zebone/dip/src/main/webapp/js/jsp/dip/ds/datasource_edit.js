var saveInfo;
var gettaskInfoByPage = function(){
	var data = "{";
	$("#checkForm").find("input").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	return data;
};
var saveDatas = function(data){
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'ds/dsObjInfoSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			var id = $("input[name='id']").val();
			if(res.success){
				if(id){
					JDialog.tip("更新成功");
				}else{
					$("input[name='id']").val(res.id);
					JDialog.tip("保存成功");
				}
			}else{
				if(id){
					JDialog.tip("更新失败");
				}else{
					JDialog.tip("保存失败");
				}
			}
			
			parent.setBtnDisabled("p_dsObj_save",false);
			parent.setBtnDisabled("p_dsObj_close",false);
			$(document.body).unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			parent.setBtnDisabled("p_dsObj_save",false);
			parent.setBtnDisabled("p_dsObj_close",false);
			$(document.body).unmask();
		}
	});
};

$(function(){
	saveInfo = function(){
		//检查数据填入是否合格
		if(!checkAll("checkForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		parent.setBtnDisabled("p_dsObj_save",true);
		parent.setBtnDisabled("p_dsObj_close",true);
		//获取页面数据
		var datas = gettaskInfoByPage();
		//保存页面数据
		saveDatas(datas);
	}
});