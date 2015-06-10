var checkSubTimeOver;
//获得当前日期
var getNowFormatDate =function (){
   var day = new Date();
   var Year = 0;
   var Month = 0;
   var Day = 0;
   var CurrentDate = "";
   //初始化时间
   Year       = day.getFullYear();//ie火狐下都可以
   Month      = day.getMonth()+1;
   Day        = day.getDate();
   CurrentDate += Year + "-";
   if (Month >= 10 )   {
    CurrentDate += Month + "-";
   }   else   {
    CurrentDate += "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate += Day ;
   }   else   {
    CurrentDate += "0" + Day ;
   }
   return CurrentDate;
}
//获得当前日期的前一天
var getYestoday = function(date){    
	var yesterday_milliseconds=date.getTime()-1000*60*60*24;     
	var yesterday = new Date();     
	    yesterday.setTime(yesterday_milliseconds);     
	  
	var strYear = yesterday.getFullYear();  
	var strDay = yesterday.getDate();  
	var strMonth = yesterday.getMonth()+1;
	if(strMonth<10)  
	{  
		strMonth="0"+strMonth;  
	}  
	datastr = strYear+"-"+strMonth+"-"+strDay;
	return datastr;
  }

//根据条件判断结束日期
var getEndDate = function(){
	var endDate = '';
	if(checkSubTimeOver()=='true'){
		var date = new Date();
		endDate = getYestoday(date);
	}else{
		endDate = getNowFormatDate();
	}
	return endDate;
}

//动态产生条件
var serviceTime = function(obj){
	var EndDate = getEndDate();
	if($(obj).attr("checked")){
		if($(obj).attr("id")=='data_history'){
			$(obj).parent().parent().find("input[name='beginDate']").attr("disabled",false);
			$(obj).parent().parent().find("input[name='endDate']").attr("disabled",false);
		}else if($(obj).attr("id")=='data_today'){
		    $(obj).parent().parent().find("select").attr("disabled",false);
		}
	}else{
		if($(obj).attr("id")=='data_history'){
			$(obj).parent().parent().find("input[name='beginDate']").val("");
			$(obj).parent().parent().find("input[name='endDate']").val(EndDate);
			$(obj).parent().parent().find("input[name='beginDate']").attr("disabled",true);
			$(obj).parent().parent().find("input[name='endDate']").attr("disabled",true);
		}else if($(obj).attr("id")=='data_today'){
			$(obj).parent().parent().find("select option").each(function(){
				if($(this).val()=="-1"){
					$(this).attr("selected",true);
				}
			});
			$(obj).parent().parent().find("select").attr("disabled",true);
		}
	}
}
//保存删除订阅
var dybutten = function(obj){
	if($(obj).find("span").text()=="取消订阅"){
		//删除订阅
		JDialog.showConfirmDialog('警告', '一旦取消订阅，这些数据将无法恢复，确定是否取消订阅？', JDialog.WARNING, function(id, text){
			if(id=='yes'){
				deletesub($(obj).parent().parent().parent());
				//动态消除隐藏id
				$(obj).parent().parent().parent().find("input[id='id']").val("");
				//动态消除条件
				$(obj).find("span").text("订阅");
				var obj3 = $(obj).parent().parent().parent().parent().find("input[id='data_history']");
				obj3.attr("checked",false);
				serviceTime(obj3);
				var obj4 = $(obj).parent().parent().parent().parent().find("input[id='data_today']");
				obj4.attr("checked",false);
				serviceTime(obj4);
			}
		});
		var obj2 = $(obj).parent().parent().parent().find("input[id='selectCheckbox']");
		obj2.attr("checked",false);
		subCheck(obj2);
	}else{
		//保存订阅
		//保存前校验
		var  tempobj = $(obj).parent().parent().parent().parent();
		//是否勾选类型
		if(!tempobj.find("input[id='data_history']").attr("checked")&&!tempobj.find("input[id='data_today']").attr("checked")){
			JDialog.tip("请至少选择一种订阅类型",2);
			return;
		}
		if(tempobj.find("input[id='data_history']").attr("checked")){
			var beginDate =  tempobj.find("input[name='beginDate']").val();
			var endDate =  tempobj.find("input[name='endDate']").val();
			if(beginDate==""||beginDate==null||endDate==""||endDate==null){
				JDialog.tip("时间不能为空",2);
				return;
			}
			if(checkTime(beginDate,endDate)=="false"){
				JDialog.tip("开始时间不能大于结束时间",2);
				return;
			}
			var date = new Date();
			var now = date.getFullYear()+"-"+ (date.getMonth()+1)+"-"+date.getDate();
			if(checkTime(endDate,now)=="false"){
				JDialog.tip("结束时间不能大于当前时间",2);
				return;
			}
			if(checkTime(endDate,getEndDate())=="false"){
				JDialog.tip("订阅时间在 "+lastSubTime+" 之前，结束时间不能大于当前时间的前一天",2);
				return;
			}
		}
		/**
		if(tempobj.find("input[id='data_history']").attr("checked")&&tempobj.find("input[id='data_today']").attr("checked")){
			var date = new Date();
			var ysetoday = getYestoday(date);
			if(checkTime(endDate,ysetoday)=="false"){
				JDialog.tip("当前数据已选择时，历史数据的结束时间不能大于当前时间的前一天",2);
				return;
			}
		}**/
		if(tempobj.find("input[id='data_today']").attr("checked")){
			var val = tempobj.find("select[name='subFrequ']").val();
			if(val==""||val=="-1"){
				JDialog.tip("请选择订阅频率",2);
				return;
			}
		}
		savesub($(obj).parent().parent().parent().parent(),function(){
			$(obj).find("span").text("取消订阅");
			var obj2 = $(obj).parent().parent().parent().find("input[id='selectCheckbox']");
			obj2.attr("checked",false);
			subCheck(obj2);
		});
	}
	
}
var selectbutten = function(){
	JDialog.tip("请选择前面的单选框",1);
}
//是否可编辑
var subCheck = function(obj){
	if($(obj).attr("checked")){
		$(obj).parent().parent().parent().find("span[id='dybtn']").attr("onclick","dybutten(this)");
		$(obj).parent().parent().find("a").attr("class","btn");
		var temp_obj = $(obj).parent().parent().parent();
		temp_obj.find("input[type='checkbox']").attr("disabled",false);
		serviceTime(temp_obj.find("input[id='data_history']"));
		serviceTime(temp_obj.find("input[id='data_today']"));
	}else{
		$(obj).parent().parent().parent().find("span[id='dybtn']").attr("onclick","selectbutten()");
		$(obj).parent().parent().find("a").attr("class","");
		var temp_obj = $(obj).parent().parent().parent();
		temp_obj.find("input").each(function(){
			if($(this).attr("id")!='selectCheckbox'){
				$(this).attr("disabled",true);
			}
			
		});
		temp_obj.find("select").attr("disabled",true);
	}
	//上面的操作会把本身也灰掉，这个操作所以不能去掉
	//$(obj).attr("disabled",false);
}
//删除订阅服务
var deletesub = function(obj){
	var subid = $(obj).find("input[id='id']").val();
		$.ajax({
		url:'operationalGuidance/deleteSub.zb',
		type: 'POST',
		cache: false,
		data: eval("({subid:'"+subid+"'})"),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				$(document.body).unmask();
				JDialog.tip("取消订阅成功",2);
			}else{
				$(document.body).unmask();
				JDialog.tip('取消订阅失败',JDialog.ERROR);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，取消订阅失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
}
//保存订阅服务
var savesub = function(obj,callback){
	var data = "{";
	$(obj).find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$(obj).find("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			data += $(this).attr("name")+":'"+$(this).val()+"',";
		}else{
			data += $(this).attr("name")+":'0',";
		}
	});
	if($(obj).find("input[id='data_today']").attr("checked")){
		$(obj).find("select").each(function(){
			var val = $(this).val();
			if(val!=""&&val!="-1"){
				data += $(this).attr("name")+":'"+$(this).val()+"',";
			}
		});
	}
	if($(obj).find("input[id='data_history']").attr("checked")){
		$(obj).find("input[type='text']").each(function(){
			data += $(this).attr("name")+":'"+$(this).val()+"',";
		});
	}
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	$.ajax({
		url:'operationalGuidance/saveSub.zb',
		type: 'POST',
		cache: false,
		data: eval("("+data+")"),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				$(document.body).unmask();
				$(obj).find("input[id='id']").val(res.subid);
				JDialog.tip("保存订阅成功",2);
				callback();
			}else{
				$(document.body).unmask();
				JDialog.tip(res.msg,2);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，取消订阅失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
}
//日期比较；false：startTime>endTime;true:startTime<endTime
var checkTime  = function(startTime,endTime){
	var endDate = endTime.split("-");
	var nian = parseFloat(endDate[0]);
	var yue = parseFloat(endDate[1]);
	var ri = parseFloat(endDate[2]);
	var startDate = startTime.split("-");
	var a = parseFloat(startDate[0]);
	var b = parseFloat(startDate[1]);
	var c = parseFloat(startDate[2]);
	if(a > nian){
		return "false";
	}else if(a == nian){
		if(b > yue){
			return "false";
		}else if(b == yue){
			if(c > ri){
				return "false";
			}
		}
	}
}

$(function(){
	//比较当前时间和最后一次订阅发送时间，true:当前时间小于发送时间，false：当前时间大于发送时间(只比较小时，分钟)
	checkSubTimeOver = function(){
		var day = new Date();
		var hours =  day.getHours();
		var minutes =  day.getMinutes();
		var seconds =  day.getSeconds();
		var str = lastSubTime.split(":");
		if(minutes<10){
			minutes = "0"+minutes;
		}
		if(seconds<10){
			seconds = "0"+seconds;
		}
		if(hours>str[0]){
			//小时大于最后一次订阅时间
			return "false";
		}else if(hours<str[0]){
			//小时小于最后一次订阅时间
			return "true";
		}else{
			//小时等于最后一次订阅时间
			if(minutes>=str[1]){
				//分钟大于最后一次订阅时间
				return "false";
			}else{
				//分钟小于最后一次订阅时间
				return "true";
			}
		}
	}
	//频率加载
	var initEndDate = getEndDate();
	$(".LoadTR").each(function(){
		var serviceFrequ = $(this).find("input[id='serviceFrequ']").val().split(',');
		var subFrequSelect = $(this).find("select[name='subFrequ']");
		subFrequSelect.html("<option value='-1' selected>请选择</option>");
		for(var i =0;i<serviceFrequ.length;i++){
			$.each(subFrequMap,function(key,values){
				if(key==serviceFrequ[i]){
					subFrequSelect.append("<option value='"+key+"'>"+values+ "</option>");   
				}
			});
		}
	});
	//加载订阅
	$(document.body).mask("正在加载，请稍后...");
	$.ajax({
		url:'operationalGuidance/subLoad.zb',
		type: 'POST',
		cache: false,
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				$(document.body).unmask();
				var temp_datas = res.listSub;
				$(".LoadTR").each(function(){
					//结束日期初始化
					$(this).find("input[name='endDate']").val(initEndDate);
					for(var i=0;i<temp_datas.length;i++){
						if($(this).find("input[name=serviceId]").val()==temp_datas[i].serviceId){
							//历史数据
							if($(this).find("input[id=data_history]").val()==temp_datas[i].isHistory){
								var obj = $(this).find("input[id=data_history]");
								obj.attr("checked",true);
								serviceTime(obj);
								$(this).find("input[name=beginDate]").val(temp_datas[i].beginDate);
								$(this).find("input[name=endDate]").val(temp_datas[i].endDate);
							}
							//当天数据
							if($(this).find("input[id=data_today]").val()==temp_datas[i].isCurrent){
								var obj = $(this).find("input[id=data_today]");
								obj.attr("checked",true);
								serviceTime(obj);
								$(this).find("select option").each(function(){
									if($(this).val()==temp_datas[i].subFrequ){
											$(this).attr("selected",true);
									}
								});
							}
							//按钮
							$(this).find("span[id='dybtn']").find("span").text("取消订阅")
							//隐藏框
							$(this).find("input[id='id']").val(temp_datas[i].id);
							//设置不可修改
							var obj2 = $(this).find("input[id='selectCheckbox']");
							subCheck(obj2);
						}
					}
				});
			}else{
				$(document.body).unmask();
				JDialog.tip('数据加载失败',JDialog.ERROR);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			JDialog.tip('服务器出现异常，数据加载失败。', JDialog.ERROR);
			$(document.body).unmask();
		}
	});
	var tab = new JTab();
	var tabTitle = '';
	var tabHtml = '';
	for(var i=0; i<2;i++){
		tabTitle = $('#tab'+i).attr('title');
		tabHtml = $('#tab'+i).html();
		$('#tab'+i).remove();
		tab.addTab({
			id: 'sub_tab'+i,
			title: tabTitle,
			html: tabHtml
		});
		tabTitle = '';
		tabHtml = '';
	}
	tab.setActiveTab('sub_tab0');
	$("#tabMain").css("height","auto");

});