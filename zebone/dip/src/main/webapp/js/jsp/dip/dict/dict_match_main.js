var changeDictStandard = function(id){
	if(!id){
		$(".dictData").each(function(){
			var opt = $(this).find("select");
			opt.html("<option value='' selected>请选择</option>");
		});
		return;
	}
	$.ajax({
		url:'dict/getDictStandardById.zb',
		type: 'POST',
		cache: false,
		data: eval("({dictId:'"+id+"'})"),
		dataType: 'json',
		success: function(res){
			if(res.success){
				var data = res.data;
				$(".dictData").each(function(){
					var opt = $(this).find("select");
					opt.html("<option value='' selected>请选择</option>");
					for(var i=0; i<data.length;i++){
						opt.append("<option value="+data[i].dictId+","+data[i].dictCode+">"+data[i].dictName+ "</option>");
					}
				});
			}else{
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
};

var getDataByPage = function(){
	var data = "";
	$(".dictData").each(function(){
		var valueId = $(this).find("input[type='hidden']");
		var dictId = $(this).find("select");
		data += $(valueId).val()+','+$(dictId).val()+';';
	});
	if(data.length >0) data = data.substring(0,data.length-1);
	return data;
};

var saveDictMatchInfo = function(val){
	$(document.body).mask("正在保存匹配数据，请稍后......");
	$.ajax({
		url:'dict/saveDictMatchInfo.zb',
		type: 'POST',
		cache: false,
		data: eval("({dictData:'"+val+"'})"),
		dataType: 'json',
		success: function(res){
			if(res.success){
				JDialog.tip('保存成功');
			}else{
				JDialog.tip('保存失败');
			}
			$(document.body).unmask();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			$(document.body).unmask();
			JDialog.tip('服务器异常，请重试');
		}
	});
	
};
$(function(){
	$("#dictMatch").height($(document.body).height()-70);
	
	$("#dictTypeStandard").change(function(){
		changeDictStandard($(this).val());
	});
	
	$("#dictSave").click(function(){
		if(dictFlag =="1"){
			JDialog.tip('请选择要匹配的数据字典类型，再匹配保存');
			return;
		}
		var flag = "";
		$("#dictMatch").find("select").each(function(){
			if($(this).val() == ''){
				flag = "1";
			}
		});
		if(flag == "1"){
			JDialog.tip('请进行字典匹配后再进行保存');
			return;
		}
		//获取页面数据信息
		var data = getDataByPage();
		//保存匹配后的数据字典信息
		saveDictMatchInfo(data);
	});
});