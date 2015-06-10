/*
 * jquery checkform , version 2.0
   表单元素合法性验证组件,表单元素编辑状态检测（目前文本框的值,JS改变的检测不到  ）
   @author 张春雨

 *
 *1.元素必须包含：title validate
 * 如：<input type='text'  title='用户名' validate='String|1-20'>
 *2.各类型文本对照
 *	string 字符串
 *	number 数字
 *  float 数字和小数点
 *	percent 百分比
 *	int 整型
 *	date 日期
 *	IP IP
 *	email Email
 *	phone 电话
 *	fax 传真
 *	mobile 手机
 *	idcard 身份证
 *	postno 邮编
 *	money 费用
 *  startDate 验证开始时间小于结束时间.要为元素添加endDate属性.属性值为结束时间元素的name.
 *  Chinese 中文
 *3.各类型元素
 *文本类型:text textarea
 *checkbox:在checkbox外围加上一个div:<div validate="checkbox|1-n" title="项目" name="checkbox的name" value="1"></div>
 *radio:在radio外围加上一个div:<div validate="radio" title="项目" name="radio的name" value="1"></div>
 *select: validate='select'
 *4.长度： +不能为空
 * 验证信息是否符合规范。
 * 1).不符合 显示错误信息 返回false
 * 2).符合  返回true
 * 3).如果没有错误信息存在则提交表单
 *5.1 自定义校验事件
 *  参数为自定义校验的方法。返回值为验证信息字符串。为空表示通过。
 *  $('#test').checkForm(checkTest);
 *  var checkTest=function(){
 *		var t=$('#test').val();
 *		if(t=='1'){
 *			return "";
 *		}
 *		return "错误"
 *	}
 **/
 /*
  5.2 字符串自定义校验
  当元素validate='string'时支持自定义校验：
  自定校验元素要含有reg属性：
  reg:为正则表达式 或者 不能通过的字符集,如： reg="/^\d{3,5}\-{0,1}\d{7,8}$/" ,reg="[`~!@#$^&*()]"
  msg:验证失败时显示的信息(可选)
  <input type="text" reg="[`~!@#$^&*()]" msg="含有非法字符" validate="string"/>
*/
 var isError=false;//是否存在错误
 var isEdited=false;//表单是否被编辑过的标记
 var checkDisabled=false;//禁止校验的标记
 var customCheck=[];//表单加载时将元素与校验方法绑定放入数组 checkall时执行所有方法,但第二次加载表单会出现重复数据,判断数组中有重复项则不添加
 //为元素addClass customCheck,checkall时将所有自定义元素取出找到对应方法
(function($){
   $.fn.checkForm = function(fn){
	  var me=this;
	  var msg='';
	  var t=this.get(0).tagName;
	  customCheck.push({'fn':fn,'el':me});
	  if(typeof(fn) == "function"){
	  	  me.addClass('customCheck');
	  	  me.attr('fn',new String(fn));
	  	  if(t=='INPUT'||t=='TEXTAREA'){
	  	  	this.blur(function(){
	  	  		msg=fn();
	  	  		if(msg!=''){
	  	  			appendError(msg,me);
	  	  		}else{
	  	  			hideError(me);
	  	  		}
	  	  	});
	  	  	this.mouseover(function(){
	  	  		showError($(this));
	  	  	});
	  	  }
	  }
	  return this;
   }
})(jQuery)
//dom ready
$(function () {
	bindCheckEvent();
	checkFormEdit();
});
//bind blur event
function bindCheckEvent() {
	customCheck=[];//初始化数组
	$(".checkForm").find("input").each(function () {
		if ($(this).attr("validate")!=undefined&&$(this).attr("validate")!= "") {
			$(this).blur(function () {
				checkElement($(this));
			});
			$(this).mouseover(function () {
				showError($(this));
			});
			hideError($(this));
		}
	});
	$(".checkForm textarea").each(function () {
		if ($(this).attr("validate")!=undefined&&$(this).attr("validate")!= "" ) {
			$(this).blur(function () {
				checkElement($(this));
			});
			$(this).mouseover(function () {
				showError($(this));
			});
			hideError($(this));
		}
	});
	$(".checkForm select").each(function () {
		if ($(this).attr("validate")!=undefined&&$(this).attr("validate")!= "" ) {
			$(this).change(function () {
				checkElement($(this));
			});
			$(this).mouseover(function () {
				showError($(this));
			});
			hideError($(this));
		}
	});
	$(".checkForm div").each(function () {
		if ($(this).attr("validate")!=undefined&&$(this).attr("validate")!= "" ) {
			$(this).mouseout(function () {
				checkElement($(this));
			});
			$(this).mouseover(function () {
				showError($(this));//or check?
			});
			hideError($(this));
		}
	});
}
 //bind edit event
 var checkFormEdit=function(){
 	$('input').each(function(){
 		var t=$(this);
 		if(t.attr('type')!='button'){
 			if(t.attr('type')=='text'){
 				t.change(function(){
 					editCallBack();
 				});
 			}else{
 				t.click(function(){
 					editCallBack();
 				});
 			}
 		}
 	});
 	$('select').each(function(){
 		$(this).change(function(){
 			editCallBack();
 		});
 	});
 	$('textarea').each(function(){
 		$(this).change(function(){
 			editCallBack();
 		});
 	});
 }
 //编辑表单后执行的事件
 var editCallBack=function(){
 	if(!isEdited){
 		if($('.j-tle').length!=0){
 			$('.j-tle').html($('.j-tle').html()+'*');
 		}
 	}
 	isEdited=true;
 	if(parent.editCallBack&&parent!=top){
		parent.editCallBack();
	}
 }
 //清除更改文件的标记
 var clearEditFlag=function(){
 	isEdited=false;
 	if($('.j-tle').length!=0){
 		$('.j-tle').html($('.j-tle').html().replace("*",""));
 	}
 	if(parent.clearEditFlag&&parent!=top){parent.clearEditFlag();}
 }
 //清除当前页面更改文件的标记
 var clearCurrentEditFlag=function(){
 	isEdited=false;
 	if($('.j-tle').length!=0){
 		$('.j-tle').html($('.j-tle').html().replace("*",""));
 	}
 }
//单一验证：
function checkElement(elmt) {
	if (checkDisabled) { return; }
	var element;
	if (typeof (elmt) == "string" && elmt != "") {
		element = document.getElementById(elmt);
	} else {
		if (typeof (elmt) == "object") {
			element = elmt;
		} else {
			alert("表单元素参数错误！" + elmt);
		}
	}
	if(element.attr("disabled")=="disabled"){hideError(element);return true;}//disabled不校验
	var fmtMsg ="";//合法性
	var lengthMsg="";//长度
	var ajaxMsg="";//ajax
	var vType=element.attr("validate").split('|')[0];
	fmtMsg = checkFormat(element);//合法性验证结果
	if (element.attr("validate").indexOf("|") != -1&&vType!='checkbox'&&vType!='radio') {
		lengthMsg = checkLength(element);
	}
	if(element.attr("validate").split('|')[2]!=undefined){
		var r=element.attr("validate").split('|')[2];
		ajaxMsg=ajaxValidation(r.split(',')[0],r.split(',')[1],element);
	}
	if (fmtMsg == "" && lengthMsg == ""&&ajaxMsg=="") {
		hideError(element);
		return true;
	} else {
		fmtMsg != "" ? (fmtMsg = "<li>" + fmtMsg + "</li>") : "";
		lengthMsg != "" ? (lengthMsg = "<li>" + lengthMsg + "</li>") : "";
		ajaxMsg != ""&&ajaxMsg != undefined ? (ajaxMsg = "<li>" + ajaxMsg + "</li>") : ajaxMsg="";
		appendError(fmtMsg+lengthMsg +ajaxMsg, element);
	}
	return false;
}
//整体验证 参数：from 或者 form ID
function checkAll(form) {
	hideAllError();
	if (typeof (form) == "object") {
		if(form instanceof jQuery){
			var inputs = form.find("input");
			var selects= form.find("select");
			var textareas= form.find("textarea");
			var divs= form.find("div");
		}else{
			var inputs = form.getElementsByTagName("input");
			var selects= form.getElementsByTagName("select");
			var textareas= form.getElementsByTagName("textarea");
			var divs= form.getElementsByTagName("div");
		}
		checkArray(inputs);
		checkArray(selects);
		checkArray(textareas);
		checkArray(divs);
	} else {
		if (typeof (form) == "string") {
			checkArray(document.getElementById(form).getElementsByTagName("input"));
			checkArray(document.getElementById(form).getElementsByTagName("select"));
			checkArray(document.getElementById(form).getElementsByTagName("textarea"));
			checkArray(document.getElementById(form).getElementsByTagName("div"));
		} else {
			alert("form格式错误");
			return false;
		}
	}
	if(customCheck.length>0){
		for(var i=0;i<customCheck.length;i++){
			var fn=customCheck[i].fn;
			var el=customCheck[i].el;
			if(typeof(fn) == "function"){
		  	  		msg=fn();
		  	  		if(msg!=''){
		  	  			appendError(msg,el);
		  	  		}else{
		  	  			hideError(el);
		  	  		}
		  	  }
		}
	}
	if (isError) {
		//$(".error-container").length > 0
		//scrollForm();
		return false;
	}
	return true;
}
var checkArray=function(array){
	if(array instanceof jQuery){
		array.each(function(){
			if($(this).attr("validate")!= undefined){
				checkElement($(this));
			}
		});
	}else{
		for (var k = 0; k < array.length; k++) {
			if (array[k].getAttribute("validate") != undefined) {
				checkElement($(array[k]));
			}
		}
	}
}
//append to body
var appendError = function (error, element) {
	//element.css("border", "1px solid #FF0000");
	element.addClass('formError');
	if(element.get(0).tagName=='DIV'){
		element.css("overflow", "auto");
	}
	isError=true;
	error = error.replace("undefined", "\u5185\u5bb9");//替换成“内容”
	if ($("." + element.attr("name") + "Formerror").length == 0) {
		var errorContainer = $("<div>", {"class":"error-container " + element.attr("name") + "Formerror", html:"<ol>" + error + "</ol>"});
		errorContainer.appendTo($(document.body));
		if(ie6){
			errorContainer.append('<iframe src="about:blank" style="width:'+errorContainer.width()+';height:'+errorContainer.height()+';position:absolute;top:0;left:0;z-index:-1;filter:alpha(opacity=0)"></iframe>');
		}
	} else {
		$("." + element.attr("name") + "Formerror").find("ol").html(error);
	}
	
	showError(element);
};
 //show error container
var showError = function (element) {
	var fOffset = element.offset();
	var errorContainer = $("." + element.attr("name") + "Formerror");
	if (errorContainer.length == 0) {
		return;
	}
	var t=fOffset.top;
	/*if(t<35){//如果元素靠上
		t+=48;
		errorContainer.find('.tip_top').show();
		errorContainer.find('.tip_btm').hide();
	}else{
		errorContainer.find('.tip_top').hide();
		errorContainer.find('.tip_btm').show();
		t-=(errorContainer.height()-56);//56px
	}*/
	errorContainer.css({left:fOffset.left + element.outerWidth(), top:t});
	errorContainer.show();//errorContainer.animate({ top: fOffset.top - errorContainer.height()});
	errorContainer.mouseover(function(){
		$(this).hide();
	});
	setTimeout(function () {
		$("." + element.attr("name") + "Formerror").fadeOut();
	}, 3000);
};
//隐藏移除错误信息
var hideError = function (element) {
	//if(element.get(0).tagName=='DIV'){
	//}else{
	//}
	element.removeClass('formError');
	$("." + element.attr("name") + "Formerror").slideUp().remove();
};
//hide all error containers
var hideAllError = function () {
	$(".error-container").remove();
	isError=false;
};
//scroll to first error container
var scrollForm = function () {
	destination = $(".error-container:first").offset().top;
	$("html:not(:animated),body:not(:animated)").animate({scrollTop:destination}, 1100);
};

//进行合法性验证
function checkFormat(element) {
	//如果元素有值则进行验证	
	if(element[0].tagName=='DIV'){
		switch (element.attr("validate").split("|")[0]) {
		  case "checkbox":return (checkCheckbox(element));break;//checkbox
		  case "radio":return (checkRadio(element));break;//radio
		}
	}else if(element[0].tagName=='SELECT'){
		  return (checkSelect(element));
	}else if (element.attr('value') && element.val().length > 0) {
		switch (element.attr("validate").split("|")[0]) {
		  case "number":return (checkNumber(element));break;//验证数字
		  case "int":return (checkInt(element));break;//验证整型
		  case "float":return (checkFloat(element));break;//数字和小数点
		  case "IP":return (checkIP(element));break;//验证IP
		  case "email":return (checkEmail(element));break;//验证电子邮件
		  case "phone":return (checkPhone(element));break;//验证电话
		  case "mobile":return (checkMobile(element));break;//校验手机
		  case "telmp":return (checkTelMP(element));break;//校验固定电话或手机
		  case "QQ":return (checkQQ(element));break;//校验QQ
		  case "idcard":return (checkIdCardNo(element));break;//校验身份证号码
		  case "postno":return (checkPostNo(element));break;//验证邮编
		  case "string":return (checkString(element));break;//验证字符串
		  case "percent":return (checkPercent(element));break;//验证百分比
		  case "money":return (checkMoney(element));break;//验证费用
		  case "startDate":return(checkStartDate(element));break;//验证开始时间小于结束时间
		  case "Chinese":return(checkChinese(element));break;//验证是否为中文
          //case 'rate':return(checkRate(CheckElement));break;//验证比率
          case "":return "";break;//不填不校验
		  default:return (checkString(element));break;//其它情况，按字符串校验
		}
	}
	return "";
}
//判断中英文：
function isChinese(str) {
	var lst = /[u00-uFF]/;
	return !lst.test(str);
}
//计算中英文数字长度
function strlen(str) {
	var strlength = 0;
	for (i = 0; i < str.length; i++) {
		if (isChinese(str.charAt(i)) == true) {
			strlength = strlength + 2;
		} else {
			strlength = strlength + 1;
		}
	}
	return strlength;
}
//验证长度 返回验证信息
var checkLength=function(element) {
	var msg = "";
	var title = "";
	element.val($.trim(element.val()));
	if (element.attr("title") != undefined) {
		title = element.attr("title");
	}
	//长度等于多少位
	var checkNum = element.attr("validate").split("|")[1];
	if (checkNum.indexOf("-") == -1) {
		if(checkNum=='+'&&element.val().length==0){
			msg = "请正确填写"+title;
		}else if (element.val().length != checkNum) {
			msg = title + "长度应为:" + checkNum+"位";
			//element.val(element.val().substring(0, checkNum));//自动更改长度
		}
	}else{
		//范围
		var min = checkNum.split("-")[0];
		var max = checkNum.split("-")[1];
		if(min == "0"){
			if(element.val().length == 0){
			}else if(element.val().length < min){
				msg = title + "过短.应多于或等于" + min +"个字";
			}else if(element.val().length > max){
				msg = title + "过长.应少于或等于"+ max + "个字.";
				//element.val(element.val().substring(0, max));
			}
		}else{
			if (element.val().length == 0){
				msg = "请正确填写"+title;
			}else if(element.val().length < min){
				msg = title + "过短.应多于或等于" + min +"个字";
			}else if(element.val().length > max){
				msg = title + "过长.应少于或等于"+ max + "个字.";
				//element.val(element.val().substring(0, max));
			}
		}
	}
	return msg;
}
//验证数字
function checkNumber(element) {
	var str = "";
	var reg=/^(\-?[1-9]\d*|0)(\.\d+)?$/;
	if(!reg.test(element.val())){
		str = element.attr("title") + "格式不正确,应填写数字";
	}
	return str;
}
//验证整型
function checkInt(element) {
	var str = "";
	var reg=/^(\-?[1-9]\d*|0)$/;
	if(!reg.test(element.val())){
		str = element.attr("title") + "格式不正确,应填写整数";
	}
	return str;
}
//数字和小数点
function checkFloat(element) {
	var str = "";
	var patrn = /^(\-?[1-9]\d*|0)\.\d+$/;
	if (patrn.exec(element.val())) {
	} else {
		str = element.attr("title") + "格式不正确,应填写数字和小数点";
	}
	return str;
}
//验证IP
function checkIP(element) {
	var str = "";
	var rep = /^(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([0-9][0-9])|([0-9]))\.(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([0-9][0-9])|([0-9]))\.(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([0-9][0-9])|([0-9]))\.(([1][0-9][0-9])|([2][0-4][0-9])|([2][5][0-5])|([0-9][0-9])|([0-9]))$/;
	if (!rep.test(element.val())) {
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//验证电子邮件
function checkEmail(element) {
	var str = "";
	var regu = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;   //@前面可以含有'.',但是两个'.'不能连续
	var re = new RegExp(regu);
	if (!re.test(element.val())) {
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//验证国内电话
function checkPhone(element) {
	var str = "";
	var ev = element.val();
	var regu =  /^(\d{7,13}|\d{3,5}-\d{7,8})(\-\d{1,4})?$/;//分机号必须含有-
	var re = new RegExp(regu);
	if (!re.test(ev)) {
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//校验手机号码
function checkMobile(element) {
	var str = "";
	var regu = /^\+?[0-9]{11,17}$/;
	var re = new RegExp(regu);
	if (!re.test(element.val())) {
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//固定电话或手机号码，加号数字下划线
function checkTelMP(element){
	var str = "";
	var ev = element.val();
	var regu = /^\+?[0-9-\s]*$/;
	var re = new RegExp(regu);
	if (!re.test(ev)) {
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//校验QQ
function checkQQ(element) {
	var str = "";
	var regu = /^[1-9][0-9]{4,}$/;
	var re = new RegExp(regu);
	if (!re.test(element.val())) {
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//验证身份证号码
function checkIdCardNo(element) {
	var str = "";
	var num = element.val();
	num1 = num.substr(0, num.length - 1);
	if (isNaN(num1)) {
		str = element.attr("title") + "格式不正确" + "\n";//除最后一位是否都是数字
		return str;
	}
	var len = num.length, re;
	if (len == 15) {
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	} else {
		if (len == 18) {
			re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d|X|x)$/);
		} else {
			str = element.attr("title") + "格式不正确,应为15位或18位" + "\n";
			return str;
		}
	}
	var a = num.match(re);
	if (a != null) {
		if (len == 15) {
			var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
		} else {
			var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
			if(a[3]<1800){
				str+="出生日期年份不正确\n";
			}
		}
		var dt=new Date();
		if (!B||D>dt) {
			str = element.attr("title") + a[0] + " 出生日期不正确" + "\n";
			return str;
		}
	}else{
		str = element.attr("title") + "格式不正确" + "\n";
	}
	return str;
}
//验证邮编
function checkPostNo(element) {
	var str = "";
	var ev = element.val();
	var regu = /^[0-9]\d{5}(?!\d)$/;
	var re = new RegExp(regu);
	if (!re.test(ev)) {
		str = element.attr("title") + "格式不正确,应为六位数字" + "\n";
	}
	return str;
}
//验证字符串
function checkString(element) {
	var str = "";
    var msg=element.attr('msg')?element.attr('msg'):'错误';
	var title=element.attr('title')?element.attr('title'):'';
	var reg=element.attr('reg');
	if(reg){
		try{
			reg=eval(reg)
		}catch(e){
		}
		var re=new RegExp(reg);
		var result=re.test(element.val())
		if(typeof(reg)=='object'){
			if(!result){
				str=title+msg;
			}
		}else{
			if(result){
				str=title+msg;//string
			}
		}
	}else{
		var re = new RegExp("[`\\\\~!@#$^&*()=|{}':;',\[\\]\/.<>?~！@#￥……&*（）—|{}【】‘；：”“'。，、？]") ;//如果不自定义就过滤全部非法字符
		if (re.test(element.val())) {
			str = title + "不能包含非法字符" + "\n";
		}
	}
	return str;
}
//验证百分比
function checkPercent(ele) {
	var str = "";
	var elevalue = new Number(ele.value);
	if (isNaN(elevalue) || (elevalue < 0) || (elevalue > 100)) {
		str = ele.msg + "格式不正确" + "\n";
	}
	return str;
}

//验证提醒时间
function checkRemTime(element) {
	var str = "";
	var ev = element.val();
	var regu = /^[1-9]\d{0,3}$/;
	var re = new RegExp(regu);
	if (!re.test(ev)) {
		str = element.attr("title") + "格式不正确,应为1-4位数字" + "\n";
	}
	return str;
}
//验证费用
function checkMoney(element) {
	var str = "";
	var ev = element.val();
	var regu = /^\d+(\.\d{0,2})?$/;
	var re = new RegExp(regu);
	if (!re.test(ev)) {
		str = element.attr("title") + "格式不正确,应为数字,最多保留小数点后两位" + "\n";
	}
	return str;
}
/*ajax验证*/
var ajaxValidation = function (url,id,element) {
	var str="";
	var eId="";
	if($('#'+id)){
		eId=$('#'+$.trim(id)).val();
	}else{
		eId=document.getElementsByName(id)[0].value;
	}
	param=id+"="+eId;
	param+='&'+element.attr('name')+'='+element.val();
	if(element.attr('param')!=undefined){
		param+='&'+element.attr('param');
	}
	$.ajax({
			url: url,
			type: 'POST',
			cache: false,
			data:  encodeURI(param),
			async:false,
			success: function(res){								
				if(res.success){									
				}else{
					str=element.attr("title")+"已存在";
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert('ajax error');
				formDialog.unmask();
			}
		});
	return str;	
};
/*checkbox*/
var checkCheckbox=function(el){
	var msg='';
	var title = el.attr("title")?el.attr("title"):'';
	var v=el.attr('validate');
	var n=el.attr('name');
	var cnum=0;
	if($("div[name='"+n+"']").length==1){
		$('input:[name='+n+']').each(function(){
			if(($(this).attr("disabled")==undefined||$(this).attr("disabled")==false)&&$(this).attr('checked')=="checked"){
				cnum++;
			}
		});
	}else{
		$("div[name='"+n+"']").each(function(){
				var div=$(this);
				if(div.attr("disabled")==undefined||div.attr("disabled")==false){
					div.find('input:[name='+n+']').each(function(){
						if($(this).attr('checked')=="checked"){
							cnum++;
						}
					});
				}			
			}
		);
	}
	if(el.attr("validate").split("|")[1]!=undefined){
		var checkNum = el.attr("validate").split("|")[1];
		//只能选几项
		if (checkNum.indexOf("-") == -1) {
			if(checkNum!=cnum){
				msg = "请选择" + checkNum+"项"+title;
			}
			return msg;
		}
		var min = checkNum.split("-")[0];
		var max = checkNum.split("-")[1];
		if (min == "0" && cnum==0) {//可以不选
			return msg;
		}else if (max=='n'&&cnum<min) {
			msg = "请选择至少"  + min +"项"+title;
		}else if (cnum < min||cnum.length > max) {
			msg = "请选择"  + min + "-" + max +"项"+title;
		}
	}else{
		msg = "请选择" +title;
	}
	return msg;
}
/*radio*/
var checkRadio=function(el){
	var msg='';
	var title = "";
	var v=el.attr('validate')?el.attr('validate'):'';
	var n=el.attr('name');
	if(n==''){return '';}
	var checked=false;
	$('input:[name='+n+']').each(function(){
		if($(this).attr('disabled')!='disabled'&&$(this).attr('checked')=='checked'){
			checked=true;
		}
	});
	if(!checked){
		msg = "请选择" +title;
	}
	return msg;
}
/*select*/
var checkSelect=function(el){
	var str='';
	var title = "";
	if (el.attr("title") != undefined) {
		title = el.attr("title");
	}
	if(el.val()==''){
		str='请选择'+title;
	}
	return str;
}
/*校验开始日期小于结束日期*/
var checkStartDate=function(el){
	var msg='';
	var startDate=el.val();
	var endDate=$('input[name='+el.attr('endDate')+']').val();
	if (startDate==''||endDate==''){return '';}
	if (!checkDate(startDate,endDate)){
		msg='开始时间要小于结束时间'
	}
	return msg;
}
/*校验是否中文*/
var checkChinese=function(el){
	var str='';
	var title = "";
	if (el.attr("title") != undefined) {
		title = el.attr("title");
	}
	
	var isC=true;
	for (i = 0; i < el.val().length; i++) {
		if (isChinese(el.val().charAt(i)) == false) {
			isC=false;
		}
	}
	if(!isC){
		str=title+'必须全部是中文';
	}
	return str;
}
/*日期比较*/
function checkDate(startTime,endTime){
   var aStart=startTime.split('-'); //转成成数组，分别为年，月，日，下同
   var aEnd=endTime.split('-');
   var startDate = aStart[0]+"/" + aStart[1]+ "/" + aStart[2];
   var endDate = aEnd[0] + "/" + aEnd[1] + "/" + aEnd[2];
   if (startDate > endDate) {
    return false;
   }
   return true;
}
