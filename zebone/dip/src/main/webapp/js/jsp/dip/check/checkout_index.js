$(function(){
	$("#operAll").click(function(){
		if($(this).attr("checked")){
			$("#checkoutNoOff").find("input[type='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("#checkoutNoOff").find("input[type='checkbox']").each(function(){
				$(this).attr("checked",false);
			});
		}
	});
	
	$("#saveToDB").click(function(){
		var data = "";
		$(".doccheckout").each(function(){
			var tr = $(this).find("input[type='hidden']").val()+",";
			$(this).find("input[type='checkbox']").each(function(){
				if($(this).attr("checked")){
					tr += "1,";
				}else{
					tr += ",";
				}
			});
			tr = tr.substring(0,tr.length-1);
			data += tr + ";";
		});
		if(data.length > 1) data = data.substring(0,data.length-1);
		
		//alert(data);
		$(document.body).mask("正在保存校验开关，请稍后...");
		$.ajax({
			url:'check/checkoutSave.zb',
			type: 'POST',
			cache: false,
			data: encodeURI("data="+data),
			dataType: 'json',
			success: function(res){
				$(document.body).unmask();
				if(res.success){
					JDialog.tip("保存成功",2);
				}else{
					JDialog.tip("保存失败",2);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$(document.body).unmask();
				JDialog.tip("服务器出现异常，保存失败",2);
			}
		});
	});
});