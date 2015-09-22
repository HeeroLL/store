var saveInfo;

var getInfoByPage = function(){
    var data = "{";
    $("#fromEdit").find("input[type='text']").each(function(){
        var name = $(this).attr("name");
        if(name == "tableName"){
            data += name + ":'" + ($(this).val().toUpperCase()).replace(/\s+/g, "") + "',";
        } else {
            data += name + ":'" + $(this).val() + "',";
        }
    });
    $("#fromEdit").find("input[type='hidden']").each(function(){
        data += $(this).attr("name")+":'"+$(this).val()+"',";
    });
    $("#fromEdit").find("select").each(function(){
        data += $(this).attr("name")+":'"+$(this).val()+"',";
    });
    if(data.length>1){
        data = data.substring(0,data.length-1);
    }
    data +="}";
    return data;
};

var saveDatas = function(data,callback){
	if (!checkAll('fromEdit')) {
		return;
	}
    $(document.body).mask("正在保存，请稍后...");
    $.ajax({
        url:'metadata/saveTableStruInfo.zb',
        type: 'POST',
        cache: false,
        data: eval('('+data+')'),
        dataType: 'json',
        success: function(res){
            $(document.body).unmask();
            if(res.success){
                JDialog.tip("保存成功");
                $("input[name='id']").val(res.id);
                if(callback) callback();
//				JDialog.showMessageDialog('信息', res.msg, JDialog.INFORMATION,function(id,text){
//					if(id =='ok'){
//						$("input[name='id']").val(res.id);
//						if(callback) callback();
//					}
//				},false);
            }else{
                JDialog.tip(res.msg, 2);
                parent.setBtnDisabled("p_tablestru_save",false);
                parent.setBtnDisabled("p_tablestru_close",false);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            parent.setBtnDisabled("p_tablestru_save",false);
            parent.setBtnDisabled("p_tablestru_close",false);
            $(document.body).unmask();
        }
    });
};

$(function(){
    saveInfo = function(callback){
        //检查数据填入是否合格

//        parent.setBtnDisabled("p_tablestru_save",true);
//        parent.setBtnDisabled("p_tablestru_close",true);

        //获取页面数据
        var datas = getInfoByPage();
        //保存页面数据
        saveDatas(datas,callback);
    };
});