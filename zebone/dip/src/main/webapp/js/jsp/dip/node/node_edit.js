var saveInfo;
var getNodeInfoByPage = function(){
	var data = "{";
	$("#checkForm").find("input[type='text']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#checkForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	return data;
}
//保存数据
var saveDatas = function(data,callback){
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'node/nodeInfoSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			var id = $("input[name='id']").val();
			if(res.success == "true"){
				if(id){
					JDialog.tip("更新成功");
				}else{
					$("input[name='id']").val(res.id);
					JDialog.tip("保存成功");
				}
				if(callback) callback();
			}else{
				if(id){
					JDialog.tip("更新失败");
				}else{
					JDialog.tip("保存失败");
				}
			}
			
			parent.setBtnDisabled("node_save",false);
			parent.setBtnDisabled("node_close",false);
			$(document.body).unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			parent.setBtnDisabled("node_save",false);
			parent.setBtnDisabled("node_close",false);
			$(document.body).unmask();
		}
	});
}

$(function(){
	saveInfo = function(callback){
		//检查数据填入是否合格
		if(!checkAll("checkForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		parent.setBtnDisabled("node_save",true);
		parent.setBtnDisabled("node_close",true);
		//获取页面数据
		var datas = getNodeInfoByPage();
		//保存页面数据
		saveDatas(datas,callback);
	}
});