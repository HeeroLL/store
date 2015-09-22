var saveInfo;
$(function(){
	saveInfo = function(callback){
		if(!checkAll("checkForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		parent.setBtnDisabled("module_save",true);
		parent.setBtnDisabled("module_close",true);
		$(document.body).mask("正在保存，请稍后...");
		
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
		$.ajax({
			url:'btp/module/moduleInfoSave.zb',
			type: 'POST',
			cache: false,
			data: eval('('+data+')'),
			dataType: 'json',
			success: function(res){
				var id = $("input[name='moduleId']").val();
				if(res.success == "true"){
					$("input[name='moduleId']").val(res.moduleId);
					if(id){
						JDialog.tip("更新成功");
					}else{
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
				parent.setBtnDisabled("module_save",false);
				parent.setBtnDisabled("module_close",false);
				$(document.body).unmask();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				parent.setBtnDisabled("module_save",false);
				parent.setBtnDisabled("module_close",false);
				$(document.body).unmask();
			}
		});
	}
});