var saveMho;
$(function(){
	saveMho= function(callback){
		parent.setBtnDisabled("mho_save",true);
		parent.setBtnDisabled("mho_close",true);
       var mhoName=$("#mhoForm").find("input[name='mhoName']").val();
       if(mhoName==""){
       	JDialog.showMessageDialog('失败', "机构名称不能为空", JDialog.INFORMATION, function(){});
       	return;
       }
		var data = "{";
		$("#mhoForm").find("input[type='text']").each(function(){
			data += $(this).attr("name")+":'"+$(this).val()+"',";
		});
		$("#mhoForm").find("input[type='hidden']").each(function(){
			data += $(this).attr("name")+":'"+$(this).val()+"',";
		});
		$("#mhoForm").find("select").each(function(){
			if ($.trim($(this).val())!='-1') {
		   data += $(this).attr("name") + ":'" + $(this).val() + "',";
			}
		});
		$("#mhoForm").find("textarea").each(function(){
			data += $(this).attr("name")+":'"+$(this).val()+"',";
		});
		$("#mhoForm").find("input[type='radio']").each(function(){
			if($(this).attr("checked")!= undefined){
			data += $(this).attr('name')+":'"+$(this).val()+"',";
			}
		});
		if(data.length>1){
			data = data.substring(0,data.length-1);
		}
    	data +="}";
		$.ajax({
			url:'btp/mho/mhoSave.zb',
			type: 'POST',
			cache: false,
			data: eval('('+data+')'),
			dataType: 'json',
			success: function(res){
				if(res.bool==true&&res.b==true){
				       JDialog.tip("保存成功");
				       if(callback) callback();
				}else if(res.bool==false&&res.b==false){
						JDialog.tip("保存失败");
				}else if(res.b==false){
				JDialog.tip("机构名称已存在，请重新输入");
				}
				parent.setBtnDisabled("mho_save",false);
				parent.setBtnDisabled("mho_close",false);
				$(document.body).unmask();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				parent.setBtnDisabled("mho_save",false);
				parent.setBtnDisabled("mho_close",false);
			$(document.body).unmask();
			}
		});
	}
});
